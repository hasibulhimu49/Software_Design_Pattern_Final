// File: src/Main.java
public class Main {
    public static void main(String[] args) {
        // Use reflection to avoid compilation errors
        try {
            // Load classes dynamically
            Class<?> gameModelClass = Class.forName("game.GameModel");
            Class<?> gameViewClass = Class.forName("game.GameView");
            Class<?> gameControllerClass = Class.forName("game.GameController");

            // Create instances
            Object model = gameModelClass.getDeclaredConstructor().newInstance();
            Object view = gameViewClass.getDeclaredConstructor().newInstance();

            // Create controller
            Object controller = gameControllerClass.getConstructor(
                    gameModelClass,
                    gameViewClass
            ).newInstance(model, view);

            // Make view visible
            gameViewClass.getMethod("setVisible", boolean.class)
                    .invoke(view, true);

            // Start game
            gameControllerClass.getMethod("startGame").invoke(controller);

        } catch (Exception e) {
            System.err.println("Failed to start game: " + e.getMessage());
            e.printStackTrace();
            System.out.println("\nPlease compile all files first:");
            System.out.println("1. Open Command Prompt");
            System.out.println("2. cd to snake-game folder");
            System.out.println("3. Run: javac -d bin src/**/*.java");
            System.out.println("4. Then run: java -cp bin Main");
        }
    }
}