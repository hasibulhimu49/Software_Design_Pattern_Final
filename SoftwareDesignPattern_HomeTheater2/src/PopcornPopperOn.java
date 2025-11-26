public class PopcornPopperOn implements Command {
    private final HomeTheaterFacade home;
    private final PopcornPopper popper;

    public PopcornPopperOn(HomeTheaterFacade home, PopcornPopper popper) {
        this.home = home;
        this.popper = popper;
    }

    @Override
    public void execute() {
        if (!home.isTheaterOn()) {
            System.out.println("Start the theater first.");
            return;
        }

        popper.on();
    }
}
