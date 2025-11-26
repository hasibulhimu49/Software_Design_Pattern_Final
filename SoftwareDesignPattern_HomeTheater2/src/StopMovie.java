public class StopMovie implements Command {
    private final HomeTheaterFacade home;

    public StopMovie(HomeTheaterFacade home) {
        this.home = home;
    }

    @Override
    public void execute() {
        home.stopMovie();    // ONLY stops the movie, theater stays powered
    }
}
