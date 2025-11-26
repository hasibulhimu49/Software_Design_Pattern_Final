public class ScreenDown implements Command {
    private final HomeTheaterFacade home;
    private final Screen screen;

    public ScreenDown(HomeTheaterFacade home, Screen screen) {
        this.home = home;
        this.screen = screen;
    }

    @Override
    public void execute() {
        if (!home.isTheaterOn()) {
            System.out.println("Start the theater first.");
            return;
        }
        screen.down();
    }
}
