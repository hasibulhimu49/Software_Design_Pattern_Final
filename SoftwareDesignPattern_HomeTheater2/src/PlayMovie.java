import java.util.Scanner;

public class PlayMovie implements Command {
    private final HomeTheaterFacade home;
    private final Scanner scanner;

    public PlayMovie(HomeTheaterFacade home, Scanner scanner) {
        this.home = home;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        if (!home.isTheaterOn()) {
            System.out.println("Start the theater first (button 1).");
            return;
        }

        System.out.print("Enter movie name: ");
        String movie = scanner.nextLine();   // <<--- HERE you type the movie name
        home.playMovie(movie);              // DVD ON already, now it plays
    }
}
