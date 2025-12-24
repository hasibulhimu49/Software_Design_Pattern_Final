package strategy;

import snake.Snake;
import snake.Direction;

public class ReverseMovement implements MovementStrategy {
    @Override
    public void move(Snake snake) {
        Direction current = snake.getDirection();
        Direction reversed = getReversedDirection(current);
        snake.setDirection(reversed);

        // Use the normal move logic after reversing direction
        snake.getBody().removeLast();

        snake.SnakeBodyPart head = snake.getBody().getFirst();
        java.awt.Point newHeadPos = new java.awt.Point(
                head.getPosition().x + snake.getDirection().getDx(),
                head.getPosition().y + snake.getDirection().getDy()
        );
        snake.getBody().addFirst(new snake.SnakeBodyPart(newHeadPos));
    }

    private Direction getReversedDirection(Direction dir) {
        // CORRECTED: Use unqualified enum constant names
        switch (dir) {
            case UP: return Direction.DOWN;
            case DOWN: return Direction.UP;
            case LEFT: return Direction.RIGHT;
            case RIGHT: return Direction.LEFT;
            default: return dir;
        }
    }
}