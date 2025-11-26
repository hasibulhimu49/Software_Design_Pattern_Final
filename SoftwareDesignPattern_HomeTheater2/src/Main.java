import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Create device objects
        Amplifier amp = new Amplifier();
        Projector projector = new Projector();
        Screen screen = new Screen();
        PopcornPopper popper = new PopcornPopper();
        TheaterLights lights = new TheaterLights();
        DvdPlayer dvd = new DvdPlayer();

        // Facade
        HomeTheaterFacade home =
                new HomeTheaterFacade(amp, projector, screen, popper, dvd, lights);

        Scanner scanner = new Scanner(System.in);

        // Remote with buttons 0..14  (0 is "Exit", 1–14 are commands)
        RemoteControl remote = new RemoteControl(15);

        // Button mapping:
        remote.setCommand(1, new StartTheater(home));           // auto power-on sequence
        remote.setCommand(2, new StopTheater(home));            // auto shutdown sequence
        remote.setCommand(3, new PlayMovie(home, scanner));     // asks movie name, dvd.play(...)
        remote.setCommand(4, new StopMovie(home));              // dvd.stop() only
        remote.setCommand(5, new VolumeUp(home, amp));
        remote.setCommand(6, new VolumeDown(home, amp));
        remote.setCommand(7, new ScreenDown(home, screen));
        remote.setCommand(8, new ScreenUp(home, screen));
        remote.setCommand(9, new ProjectorOn(home, projector));
        remote.setCommand(10, new ProjectorOff(home, projector));
        remote.setCommand(11, new PopcornPopperOn(home, popper));
        remote.setCommand(12, new PopcornPopperOff(home, popper));
        remote.setCommand(13, new LightsUp(home, lights));
        remote.setCommand(14, new LightsDown(home, lights));

        // ===== print the menu ONCE here =====
        System.out.println("==== Home Theater Remote ====");
        System.out.println("1.  Start Theater");
        System.out.println("2.  Stop Theater");
        System.out.println("3.  Play Movie");
        System.out.println("4.  Stop Movie");
        System.out.println("5.  Volume Up");
        System.out.println("6.  Volume Down");
        System.out.println("7.  Screen Down");
        System.out.println("8.  Screen Up");
        System.out.println("9.  Projector On");
        System.out.println("10. Projector Off");
        System.out.println("11. PopcornPopper On");
        System.out.println("12. PopcornPopper Off");
        System.out.println("13. Lights Up");
        System.out.println("14. Lights Down");
        System.out.println("0.  Exit");

        // ===== main loop: only ask for button =====
        while (true) {
            System.out.print("Press button (0-14): ");

            String line = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
                continue;
            }

            if (choice == 0) {
                System.out.println("Goodbye.");
                break;
            }

            if (choice < 0 || choice > 14) {
                System.out.println("Invalid button.");
                continue;
            }

            remote.pressButton(choice);
        }

        scanner.close();
    }
}
