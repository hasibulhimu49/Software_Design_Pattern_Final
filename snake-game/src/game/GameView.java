package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import snake.SnakeBodyPart;
import snake.Snake;
import snake.Direction;
import food.Food;
import food.FoodType;
import utils.GameConfig;

public class GameView extends JFrame {
    private GamePanel gamePanel;
    private JLabel scoreLabel;
    private JLabel statusLabel;

    public GameView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Snake Game - Responsive");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make window responsive
        setLayout(new BorderLayout());

        // Game panel
        gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER);

        // Control panel
        JPanel controlPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.setBackground(new Color(50, 50, 60));

        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setForeground(Color.GREEN);

        statusLabel = new JLabel("Status: Playing", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        statusLabel.setForeground(Color.YELLOW);

        JLabel controlsLabel = new JLabel("Controls: Arrow Keys | Pause: Space | Restart: R", SwingConstants.CENTER);
        controlsLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        controlsLabel.setForeground(Color.WHITE);

        controlPanel.add(scoreLabel);
        controlPanel.add(statusLabel);
        controlPanel.add(controlsLabel);

        add(controlPanel, BorderLayout.SOUTH);

        // Set initial size (can be maximized later)
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(true); // Allow resizing
    }

    public void updateView(GameModel model) {
        gamePanel.setModel(model);
        scoreLabel.setText("Score: " + model.getScore());
        statusLabel.setText(getStatusText(model));
        gamePanel.repaint();
    }

    private String getStatusText(GameModel model) {
        if (model.isGameOver()) {
            return "Status: Game Over";
        } else if (model.isPaused()) {
            return "Status: Paused";
        } else {
            return "Status: Playing";
        }
    }

    class GamePanel extends JPanel {
        private GameModel model;
        private int cellSize;
        private int gridWidth;
        private int gridHeight;
        private int xOffset;
        private int yOffset;

        public GamePanel() {
            setBackground(Color.BLACK);
            setDoubleBuffered(true);
            calculateDimensions();
        }

        private void calculateDimensions() {
            // Calculate based on current panel size
            int width = getWidth();
            int height = getHeight();

            if (width == 0 || height == 0) {
                width = 800;
                height = 600;
            }

            GameConfig config = GameConfig.getInstance();

            // Calculate cell size to fit available space
            int maxCellWidth = width / config.GRID_WIDTH;
            int maxCellHeight = (height - 100) / config.GRID_HEIGHT; // Leave space for controls
            cellSize = Math.min(maxCellWidth, maxCellHeight);
            cellSize = Math.max(20, Math.min(40, cellSize)); // Min 20px, Max 40px

            // Calculate grid dimensions
            gridWidth = config.GRID_WIDTH * cellSize;
            gridHeight = config.GRID_HEIGHT * cellSize;

            // Center the grid
            xOffset = (width - gridWidth) / 2;
            yOffset = (height - gridHeight) / 2;
        }

        public void setModel(GameModel model) {
            this.model = model;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (model == null) return;

            Graphics2D g2d = (Graphics2D) g;
            enableAntiAliasing(g2d);

            // Recalculate dimensions on each paint (for responsiveness)
            calculateDimensions();

            // Draw grid
            drawGrid(g2d);

            // Draw snake
            drawSnake(g2d);

            // Draw food
            Food food = model.getFood();
            if (food != null && !food.isConsumed()) {
                drawFood(g2d, food);
            }

            // Draw game messages
            drawGameMessages(g2d);
        }

        private void enableAntiAliasing(Graphics2D g2d) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

        private void drawGrid(Graphics2D g2d) {
            GameConfig config = GameConfig.getInstance();

            // Draw grid background
            g2d.setColor(new Color(30, 30, 30));
            g2d.fillRect(xOffset, yOffset, gridWidth, gridHeight);

            // Draw grid lines
            g2d.setColor(new Color(50, 50, 50));
            for (int i = 0; i <= config.GRID_WIDTH; i++) {
                int x = xOffset + i * cellSize;
                g2d.drawLine(x, yOffset, x, yOffset + gridHeight);
            }
            for (int i = 0; i <= config.GRID_HEIGHT; i++) {
                int y = yOffset + i * cellSize;
                g2d.drawLine(xOffset, y, xOffset + gridWidth, y);
            }
        }

        private void drawSnake(Graphics2D g2d) {
            if (model == null || model.getSnake() == null) return;

            Snake snake = model.getSnake();
            java.util.LinkedList<SnakeBodyPart> body = snake.getBody();
            if (body == null || body.isEmpty()) return;

            // Draw body
            for (int i = 0; i < body.size(); i++) {
                SnakeBodyPart part = body.get(i);
                if (part == null) continue;

                Point pos = part.getPosition();
                if (pos == null) continue;

                // Calculate screen position
                int x = xOffset + pos.x * cellSize;
                int y = yOffset + pos.y * cellSize;

                // Color gradient
                float colorRatio = 1.0f - (float)i / body.size();
                Color bodyColor = new Color(
                        (int)(50 * colorRatio),
                        (int)(150 + 100 * colorRatio),
                        (int)(50 * colorRatio)
                );

                g2d.setColor(bodyColor);
                g2d.fillRect(x + 1, y + 1, cellSize - 2, cellSize - 2);

                // Draw body border
                g2d.setColor(new Color(0, 100, 0));
                g2d.drawRect(x + 1, y + 1, cellSize - 2, cellSize - 2);
            }

            // Draw head with eyes
            if (!body.isEmpty()) {
                drawSnakeHead(g2d);
            }
        }

        private void drawSnakeHead(Graphics2D g2d) {
            if (model == null || model.getSnake() == null) return;

            Snake snake = model.getSnake();
            java.util.LinkedList<SnakeBodyPart> body = snake.getBody();
            if (body.isEmpty()) return;

            SnakeBodyPart head = body.getFirst();
            if (head == null) return;

            Point headPos = head.getPosition();
            if (headPos == null) return;

            Direction direction = snake.getDirection();

            // Calculate screen position
            int x = xOffset + headPos.x * cellSize;
            int y = yOffset + headPos.y * cellSize;

            // Draw head (brighter green)
            g2d.setColor(new Color(100, 255, 100));
            g2d.fillRect(x, y, cellSize, cellSize);

            // Draw head border
            g2d.setColor(new Color(0, 150, 0));
            g2d.drawRect(x, y, cellSize, cellSize);

            // Draw eyes based on direction
            drawEyes(g2d, x, y, direction);
        }

        private void drawEyes(Graphics2D g2d, int x, int y, Direction direction) {
            int eyeSize = Math.max(4, cellSize / 5);

            int leftEyeX, leftEyeY, rightEyeX, rightEyeY;

            switch (direction) {
                case RIGHT:
                    leftEyeX = x + cellSize - eyeSize - 2;
                    leftEyeY = y + cellSize / 3;
                    rightEyeX = x + cellSize - eyeSize - 2;
                    rightEyeY = y + 2 * cellSize / 3 - eyeSize;
                    break;
                case LEFT:
                    leftEyeX = x + 2;
                    leftEyeY = y + cellSize / 3;
                    rightEyeX = x + 2;
                    rightEyeY = y + 2 * cellSize / 3 - eyeSize;
                    break;
                case UP:
                    leftEyeX = x + cellSize / 3;
                    leftEyeY = y + 2;
                    rightEyeX = x + 2 * cellSize / 3 - eyeSize;
                    rightEyeY = y + 2;
                    break;
                case DOWN:
                    leftEyeX = x + cellSize / 3;
                    leftEyeY = y + cellSize - eyeSize - 2;
                    rightEyeX = x + 2 * cellSize / 3 - eyeSize;
                    rightEyeY = y + cellSize - eyeSize - 2;
                    break;
                default:
                    return;
            }

            // Draw eye whites
            g2d.setColor(Color.WHITE);
            g2d.fillOval(leftEyeX, leftEyeY, eyeSize, eyeSize);
            g2d.fillOval(rightEyeX, rightEyeY, eyeSize, eyeSize);

            // Draw pupils
            g2d.setColor(Color.BLACK);
            int pupilSize = eyeSize / 2;
            g2d.fillOval(leftEyeX + pupilSize/2, leftEyeY + pupilSize/2, pupilSize, pupilSize);
            g2d.fillOval(rightEyeX + pupilSize/2, rightEyeY + pupilSize/2, pupilSize, pupilSize);
        }

        private void drawFood(Graphics2D g2d, Food food) {
            if (food == null) return;

            Point pos = food.getPosition();
            if (pos == null) return;

            // Calculate screen position
            int x = xOffset + pos.x * cellSize;
            int y = yOffset + pos.y * cellSize;

            // Draw food based on type
            switch (food.getType()) {
                case GOLDEN:
                    g2d.setColor(Color.YELLOW);
                    break;
                case SPEED_BOOST:
                    g2d.setColor(Color.CYAN);
                    break;
                case REVERSE:
                    g2d.setColor(Color.MAGENTA);
                    break;
                default:
                    g2d.setColor(Color.RED);
            }

            // Draw food as a circle (not arrow symbol)
            g2d.fillOval(x + 2, y + 2, cellSize - 4, cellSize - 4);

            // Draw food highlight
            g2d.setColor(Color.WHITE);
            g2d.fillOval(x + 5, y + 5, cellSize/4, cellSize/4);
        }

        private void drawGameMessages(Graphics2D g2d) {
            if (model.isGameOver()) {
                // Semi-transparent overlay
                g2d.setColor(new Color(0, 0, 0, 150));
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Game Over text
                g2d.setColor(Color.RED);
                g2d.setFont(new Font("Arial", Font.BOLD, 40));
                String message = "GAME OVER!";
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(message)) / 2;
                int y = getHeight() / 2 - 20;
                g2d.drawString(message, x, y);

                // Score
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.PLAIN, 24));
                message = "Final Score: " + model.getScore();
                fm = g2d.getFontMetrics();
                x = (getWidth() - fm.stringWidth(message)) / 2;
                y = getHeight() / 2 + 30;
                g2d.drawString(message, x, y);

                // Restart instruction
                message = "Press 'R' to restart";
                fm = g2d.getFontMetrics();
                x = (getWidth() - fm.stringWidth(message)) / 2;
                y = getHeight() / 2 + 70;
                g2d.drawString(message, x, y);
            }

            if (model.isPaused() && !model.isGameOver()) {
                // Semi-transparent overlay
                g2d.setColor(new Color(0, 0, 0, 100));
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Paused text
                g2d.setColor(Color.YELLOW);
                g2d.setFont(new Font("Arial", Font.BOLD, 50));
                String message = "PAUSED";
                FontMetrics fm = g2d.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(message)) / 2;
                int y = getHeight() / 2;
                g2d.drawString(message, x, y);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            // Return a reasonable default size
            return new Dimension(800, 600);
        }
    }
}