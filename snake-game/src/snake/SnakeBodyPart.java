package snake;

import java.awt.Point;

public class SnakeBodyPart {
    private Point position;

    public SnakeBodyPart(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}