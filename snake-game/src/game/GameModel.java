package game;

import snake.Snake;
import snake.Direction;
import food.Food;
import food.FoodFactory;
import food.FoodType;
import observer.GameEvent;
import observer.GameObserver;
import strategy.*;
import utils.GameConfig;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
    private Snake snake;
    private Food currentFood;
    private int score;
    private boolean gameOver;
    private boolean paused;
    private List<GameObserver> observers;
    private Random random;

    public GameModel() {
        snake = new Snake();
        observers = new ArrayList<>();
        random = new Random();
        score = 0;
        gameOver = false;
        paused = false;
        generateFood();
    }

    public void update() {
        if (gameOver || paused) return;

        snake.move();

        // Check collisions
        if (snake.checkCollision()) {
            gameOver = true;
            notifyObservers(new GameEvent(GameEvent.EventType.GAME_OVER, null));
            return;
        }

        // Check food consumption
        if (currentFood != null && !currentFood.isConsumed()) {
            Point headPos = snake.getBody().getFirst().getPosition();
            if (headPos.equals(currentFood.getPosition())) {
                consumeFood();
            }
        }
    }

    private void consumeFood() {
        if (currentFood == null || currentFood.isConsumed()) return;

        currentFood.consume();
        score += currentFood.getType().getScore();

        // Grow snake based on food type
        for (int i = 0; i < currentFood.getType().getGrowth(); i++) {
            snake.grow();
        }

        // Apply special effects
        applyFoodEffects(currentFood.getType());

        notifyObservers(new GameEvent(GameEvent.EventType.SCORE_CHANGED, score));
        notifyObservers(new GameEvent(GameEvent.EventType.FOOD_EATEN, currentFood));

        generateFood();
    }

    private void applyFoodEffects(FoodType type) {
        switch (type) {
            case SPEED_BOOST:
                snake.setMovementStrategy(new FastMovement());
                break;
            case REVERSE:
                snake.setMovementStrategy(new ReverseMovement());
                break;
            default:
                snake.setMovementStrategy(new NormalMovement());
        }
    }

    private void generateFood() {
        GameConfig config = GameConfig.getInstance();
        Point position;
        boolean validPosition;

        do {
            position = new Point(
                    random.nextInt(config.GRID_WIDTH),
                    random.nextInt(config.GRID_HEIGHT)
            );
            validPosition = true;

            // Ensure food doesn't spawn on snake
            for (snake.SnakeBodyPart part : snake.getBody()) {
                if (part.getPosition().equals(position)) {
                    validPosition = false;
                    break;
                }
            }
        } while (!validPosition);

        currentFood = FoodFactory.createRandomFood(config.GRID_WIDTH, config.GRID_HEIGHT);
        currentFood.setPosition(position);
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(GameEvent event) {
        for (GameObserver observer : observers) {
            observer.update(event);
        }
    }

    // Getters
    public Snake getSnake() { return snake; }
    public Food getFood() { return currentFood; }
    public int getScore() { return score; }
    public boolean isGameOver() { return gameOver; }
    public boolean isPaused() { return paused; }

    // Setters
    public void setPaused(boolean paused) { this.paused = paused; }
    public void setDirection(Direction direction) { snake.setDirection(direction); }
}