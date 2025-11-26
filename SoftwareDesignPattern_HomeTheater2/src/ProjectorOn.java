public class ProjectorOn implements Command {
    private final HomeTheaterFacade home;
    private final Projector projector;

    public ProjectorOn(HomeTheaterFacade home, Projector projector) {
        this.home = home;
        this.projector = projector;
    }

    @Override
    public void execute() {
        if (!home.isTheaterOn()) {
            System.out.println("Start the theater first.");
            return;
        }
        projector.on();
        projector.wideScreenMode();
    }
}
