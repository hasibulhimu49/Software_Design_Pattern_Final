public class ProjectorOff implements Command {
    private final HomeTheaterFacade home;
    private final Projector projector;

    public ProjectorOff(HomeTheaterFacade home, Projector projector) {
        this.home = home;
        this.projector = projector;
    }

    @Override
    public void execute() {
        if (!home.isTheaterOn()) {
            System.out.println("Start the theater first.");
            return;
        }
        projector.off();
    }
}
