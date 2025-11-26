public class VolumeDown implements Command {
    private final HomeTheaterFacade home;
    private final Amplifier amp;

    public VolumeDown(HomeTheaterFacade home, Amplifier amp) {
        this.home = home;
        this.amp = amp;
    }

    @Override
    public void execute() {
        if (!home.isTheaterOn()) {
            System.out.println("Start the theater first.");
            return;
        }
        int v = amp.getVolume();
        amp.setVolume(v - 1);
    }
}
