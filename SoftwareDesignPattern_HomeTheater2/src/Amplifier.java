public class Amplifier {
    private int volume = 5;

    public void on() {
        System.out.println("Amplifier on");
    }

    public void off() {
        System.out.println("Amplifier off");
    }

    public void setVolume(int v) {
        volume = v;
        System.out.println("Amplifier volume set to " + volume);
    }

    public int getVolume() {
        return volume;
    }
}
