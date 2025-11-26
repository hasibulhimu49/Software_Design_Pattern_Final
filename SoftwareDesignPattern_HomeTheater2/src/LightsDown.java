public class LightsDown implements Command {
    private final HomeTheaterFacade home;
    private final TheaterLights lights;

    public LightsDown(HomeTheaterFacade home, TheaterLights lights) {
        this.home = home;
        this.lights = lights;
    }

    @Override
    public void execute() {
        if (!home.isTheaterOn()) {
            System.out.println("Start the theater first.");
            return;
        }

        int newLevel = lights.getLevel() - 10;
        if (newLevel < 0) newLevel = 0;

        lights.dim(newLevel);
    }
}
