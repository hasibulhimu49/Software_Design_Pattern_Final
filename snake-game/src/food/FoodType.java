package food;

public enum FoodType {
    NORMAL(10, 1),
    GOLDEN(50, 3),
    SPEED_BOOST(20, 2),
    REVERSE(15, 0);

    private final int score;
    private final int growth;

    FoodType(int score, int growth) {
        this.score = score;
        this.growth = growth;
    }

    public int getScore() { return score; }
    public int getGrowth() { return growth; }
}