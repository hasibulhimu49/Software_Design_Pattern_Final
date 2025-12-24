package food;

import java.awt.Point;

public class NormalFood implements Food {
    private Point position;
    private boolean consumed;

    public NormalFood(Point position) {
        this.position = position;
        this.consumed = false;
    }

    @Override
    public Point getPosition() { return position; }

    @Override
    public FoodType getType() { return FoodType.NORMAL; }

    @Override
    public void consume() { consumed = true; }

    @Override
    public boolean isConsumed() { return consumed; }

    @Override
    public void setPosition(Point position) { this.position = position; }
}