package game;

import snake.Direction;
import observer.ScoreObserver;
import utils.GameConfig;
import javax.swing.*;
import java.awt.event.*;

public class GameController {
    private GameModel model;
    private GameView view;
    private Timer gameTimer;
    private ScoreObserver scoreObserver;

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        this.scoreObserver = new ScoreObserver();

        model.addObserver(scoreObserver);
        setupKeyBindings();
    }

    public void startGame() {
        view.setVisible(true);
        startGameLoop();
    }

    private void startGameLoop() {
        GameConfig config = GameConfig.getInstance();
        gameTimer = new Timer(config.GAME_SPEED, e -> {
            model.update();
            view.updateView(model);

            if (model.isGameOver()) {
                gameTimer.stop();
            }
        });
        gameTimer.start();
    }

    private void setupKeyBindings() {
        InputMap inputMap = view.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = view.getRootPane().getActionMap();

        // Direction controls
        inputMap.put(KeyStroke.getKeyStroke("UP"), "up");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down");
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left");
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        inputMap.put(KeyStroke.getKeyStroke("W"), "up");
        inputMap.put(KeyStroke.getKeyStroke("S"), "down");
        inputMap.put(KeyStroke.getKeyStroke("A"), "left");
        inputMap.put(KeyStroke.getKeyStroke("D"), "right");

        // Game controls
        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "pause");
        inputMap.put(KeyStroke.getKeyStroke("R"), "restart");
        inputMap.put(KeyStroke.getKeyStroke("P"), "pause");

        actionMap.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!model.isGameOver()) {
                    model.setDirection(Direction.UP);
                }
            }
        });

        actionMap.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!model.isGameOver()) {
                    model.setDirection(Direction.DOWN);
                }
            }
        });

        actionMap.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!model.isGameOver()) {
                    model.setDirection(Direction.LEFT);
                }
            }
        });

        actionMap.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!model.isGameOver()) {
                    model.setDirection(Direction.RIGHT);
                }
            }
        });

        actionMap.put("pause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setPaused(!model.isPaused());
                view.updateView(model);
            }
        });

        actionMap.put("restart", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });
    }

    private void restartGame() {
        if (gameTimer != null) {
            gameTimer.stop();
        }

        model = new GameModel();
        model.addObserver(scoreObserver);
        view.updateView(model);
        startGameLoop();
    }
}