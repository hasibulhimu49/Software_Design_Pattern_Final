package snake;

import strategy.MovementStrategy;
import strategy.NormalMovement;
import utils.GameConfig;
import java.awt.Point;
import java.util.LinkedList;

public class Snake {
    private LinkedList<SnakeBodyPart> body;
    private Direction direction;
    private MovementStrategy movementStrategy;

    public Snake() {
        body = new LinkedList<>();
        movementStrategy = new NormalMovement();
        initializeSnake();
    }

    private void initializeSnake() {
        GameConfig config = GameConfig.getInstance();
        int startX = config.GRID_WIDTH / 2;
        int startY = config.GRID_HEIGHT / 2;

        for (int i = 0; i < config.INITIAL_SNAKE_LENGTH; i++) {
            body.add(new SnakeBodyPart(new Point(startX - i, startY)));
        }

        direction = Direction.RIGHT;
    }

    public void setMovementStrategy(MovementStrategy strategy) {
        this.movementStrategy = strategy;
    }

    public void move() {
        if (movementStrategy != null) {
            movementStrategy.move(this);
        } else {
            normalMove();
        }
    }

    private void normalMove() {
        // Remove tail
        body.removeLast();

        // Add new head
        SnakeBodyPart head = body.getFirst();
        Point newHeadPos = new Point(
                head.getPosition().x + direction.getDx(),
                head.getPosition().y + direction.getDy()
        );
        body.addFirst(new SnakeBodyPart(newHeadPos));
    }

    public void grow() {
        if (!body.isEmpty()) {
            SnakeBodyPart tail = body.getLast();
            body.addLast(new SnakeBodyPart(new Point(tail.getPosition())));
        }
    }

    // MAKE SURE THIS GETTER EXISTS
    public LinkedList<SnakeBodyPart> getBody() {
        return body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        if (!direction.isOpposite(newDirection)) {
            this.direction = newDirection;
        }
    }

    public boolean checkCollision() {
        if (body.isEmpty()) return false;

        SnakeBodyPart head = body.getFirst();
        Point headPos = head.getPosition();

        // Check wall collision
        GameConfig config = GameConfig.getInstance();
        if (headPos.x < 0 || headPos.x >= config.GRID_WIDTH ||
                headPos.y < 0 || headPos.y >= config.GRID_HEIGHT) {
            return true;
        }

        // Check self collision
        for (int i = 1; i < body.size(); i++) {
            if (headPos.equals(body.get(i).getPosition())) {
                return true;
            }
        }

        return false;
    }
}