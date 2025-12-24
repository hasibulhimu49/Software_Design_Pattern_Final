package strategy;

import snake.Snake;

public class FastMovement implements MovementStrategy {
    @Override
    public void move(Snake snake) {
        // Move twice for speed boost
        snake.getBody().removeLast();

        snake.SnakeBodyPart head = snake.getBody().getFirst();
        java.awt.Point newHeadPos = new java.awt.Point(
                head.getPosition().x + snake.getDirection().getDx(),
                head.getPosition().y + snake.getDirection().getDy()
        );
        snake.getBody().addFirst(new snake.SnakeBodyPart(newHeadPos));

        // Second move
        snake.getBody().removeLast();
        head = snake.getBody().getFirst();
        newHeadPos = new java.awt.Point(
                head.getPosition().x + snake.getDirection().getDx(),
                head.getPosition().y + snake.getDirection().getDy()
        );
        snake.getBody().addFirst(new snake.SnakeBodyPart(newHeadPos));
    }
}