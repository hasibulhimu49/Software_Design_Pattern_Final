public class HomeTheaterFacade {

    private final Amplifier amp;
    private final Projector projector;
    private final Screen screen;
    private final PopcornPopper popper;
    private final DvdPlayer dvd;
    private final TheaterLights lights;

    // Theater power state (not the movie)
    private boolean theaterOn = false;

    // Track the currently playing movie (optional, but useful)
    private String currentMovie;

    public HomeTheaterFacade(Amplifier amp, Projector projector,
                             Screen screen, PopcornPopper popper,
                             DvdPlayer dvd, TheaterLights lights) {
        this.amp = amp;
        this.projector = projector;
        this.screen = screen;
        this.popper = popper;
        this.dvd = dvd;
        this.lights = lights;
    }

    public boolean isTheaterOn() {
        return theaterOn;
    }

    // === THEATER POWER ON (no movie name here) ===
    public void startTheater() {
        if (theaterOn) {
            System.out.println("Theater already started.");
            return;
        }

        System.out.println("Starting home theater...");

        screen.down();
        projector.on();
        projector.wideScreenMode();

        amp.on();
        amp.setVolume(5);

        // Lights automation: on first, then dim to 10%
        lights.on();
        lights.dim(10);

        popper.on();
        dvd.on();   // DVD powered, waiting for play()

        theaterOn = true;

        System.out.println("Theater is ready.");
        System.out.println("Now you can press 'Play Movie'.");
    }

    // === THEATER POWER OFF (reverse sequence) ===
    public void shutdownTheater() {
        if (!theaterOn) {
            System.out.println("Theater is already off.");
            return;
        }

        System.out.println("Shutting down home theater...");

        // brighten while shutting down
        lights.on();

        stopMovie();   // stop current movie if any

        dvd.off();
        amp.off();
        projector.off();
        popper.off();
        screen.up();

        // finally, completely turn lights off
        lights.off();

        theaterOn = false;
        System.out.println("Theater is closed.");
    }

    // === MOVIE CONTROL ONLY (DVD) ===
    public void playMovie(String movie) {
        if (!theaterOn) {
            System.out.println("Start the theater first (button 1).");
            return;
        }
        currentMovie = movie;
        dvd.play(movie);
    }

    public void stopMovie() {
        if (!theaterOn) {
            System.out.println("Theater is off. No movie to stop.");
            return;
        }

        if (currentMovie == null) {
            System.out.println("No movie is currently playing.");
            return;
        }

        dvd.stop();
        currentMovie = null;
    }

    // Getters for other commands (if you need them anywhere)
    public Amplifier getAmplifier() { return amp; }
    public Projector getProjector() { return projector; }
    public Screen getScreen() { return screen; }
    public PopcornPopper getPopper() { return popper; }
    public TheaterLights getLights() { return lights; }
    public DvdPlayer getDvd() { return dvd; }
}
