package strategy;

import snake.Snake;

public class NormalMovement implements MovementStrategy {
    @Override
    public void move(Snake snake) {
        snake.getBody().removeLast();

        snake.SnakeBodyPart head = snake.getBody().getFirst();
        java.awt.Point newHeadPos = new java.awt.Point(
                head.getPosition().x + snake.getDirection().getDx(),
                head.getPosition().y + snake.getDirection().getDy()
        );
        snake.getBody().addFirst(new snake.SnakeBodyPart(newHeadPos));
    }
}