public class StartTheater implements Command {
    private final HomeTheaterFacade home;

    public StartTheater(HomeTheaterFacade home) {
        this.home = home;
    }

    @Override
    public void execute() {
        home.startTheater();
    }
}
