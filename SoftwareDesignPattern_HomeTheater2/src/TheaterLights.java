public class TheaterLights {

    private int level = 100;

    public void on() {
        System.out.println("Theater lights on");
    }

    public void off() {
        System.out.println("Theater lights off");
    }

    public void dim(int level) {
        if (level < 0) level = 0;
        if (level > 100) level = 100;
        this.level = level;
        System.out.println("Theater lights dimming to " + level + "%");
    }

    public int getLevel() {
        return level;
    }
}
