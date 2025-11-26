public class LightsUp implements Command {
    private final HomeTheaterFacade home;
    private final TheaterLights lights;

    public LightsUp(HomeTheaterFacade home, TheaterLights lights) {
        this.home = home;
        this.lights = lights;
    }

    @Override
    public void execute() {
        if (!home.isTheaterOn()) {
            System.out.println("Start the theater first.");
            return;
        }

        int newLevel = lights.getLevel() + 10;
        if (newLevel > 100) newLevel = 100;

        lights.dim(newLevel);
    }
}
