package food;

import java.awt.Point;
import java.util.Random;

public class FoodFactory {
    private static final Random random = new Random();

    public static Food createFood(FoodType type, Point position) {
        switch (type) {
            case GOLDEN:
                return new GoldenFood(position);
            case SPEED_BOOST:
                return new SpeedFood(position);
            case REVERSE:
                return new ReverseFood(position);
            case NORMAL:
            default:
                return new NormalFood(position);
        }
    }

    public static Food createRandomFood(int gridWidth, int gridHeight) {
        Point position = new Point(
                random.nextInt(gridWidth),
                random.nextInt(gridHeight)
        );

        int rand = random.nextInt(100);
        FoodType type;

        if (rand < 60) {
            type = FoodType.NORMAL;
        } else if (rand < 80) {
            type = FoodType.GOLDEN;
        } else if (rand < 90) {
            type = FoodType.SPEED_BOOST;
        } else {
            type = FoodType.REVERSE;
        }

        return createFood(type, position);
    }
}