public class PopcornPopperOff implements Command {
    private final HomeTheaterFacade home;
    private final PopcornPopper popper;

    public PopcornPopperOff(HomeTheaterFacade home, PopcornPopper popper) {
        this.home = home;
        this.popper = popper;
    }

    @Override
    public void execute() {
        if (!home.isTheaterOn()) {
            System.out.println("Start the theater first.");
            return;
        }

        popper.off();
    }
}
