package food;

import java.awt.Point;

public interface Food {
    Point getPosition();
    FoodType getType();
    void consume();
    boolean isConsumed();
    void setPosition(Point position);
}