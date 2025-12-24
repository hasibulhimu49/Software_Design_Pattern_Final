package food;

import java.awt.Point;

public class SpeedFood implements Food {
    private Point position;
    private boolean consumed;

    public SpeedFood(Point position) {
        this.position = position;
        this.consumed = false;
    }

    @Override
    public Point getPosition() { return position; }

    @Override
    public FoodType getType() { return FoodType.SPEED_BOOST; }

    @Override
    public void consume() { consumed = true; }

    @Override
    public boolean isConsumed() { return consumed; }

    @Override
    public void setPosition(Point position) { this.position = position; }
}