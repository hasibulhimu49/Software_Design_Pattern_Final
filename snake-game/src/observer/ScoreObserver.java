package observer;

public class ScoreObserver implements GameObserver {
    private int score;

    @Override
    public void update(GameEvent event) {
        // CORRECTED: Use unqualified enum constant names
        if (event.getType() == GameEvent.EventType.SCORE_CHANGED) {
            score = (int) event.getData();
            System.out.println("Score updated: " + score);
        } else if (event.getType() == GameEvent.EventType.FOOD_EATEN) {
            food.Food food = (food.Food) event.getData();
            System.out.println("Ate " + food.getType() + " food!");
        }
    }

    public int getScore() {
        return score;
    }
}