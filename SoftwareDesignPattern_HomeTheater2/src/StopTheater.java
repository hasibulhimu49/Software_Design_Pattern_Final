public class StopTheater implements Command {
    private final HomeTheaterFacade home;

    public StopTheater(HomeTheaterFacade home) {
        this.home = home;
    }

    @Override
    public void execute() {
        home.shutdownTheater();
    }
}
