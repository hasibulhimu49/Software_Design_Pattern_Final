package utils;

public class GameConfig {
    private static GameConfig instance;

    // Game constants
    public static final int GRID_WIDTH = 30;
    public static final int GRID_HEIGHT = 20;
    public static final int GRID_SIZE = 25;
    public static final int GAME_SPEED = 150;
    public static final int INITIAL_SNAKE_LENGTH = 3;

    private GameConfig() {}

    public static GameConfig getInstance() {
        if (instance == null) {
            instance = new GameConfig();
        }
        return instance;
    }
}