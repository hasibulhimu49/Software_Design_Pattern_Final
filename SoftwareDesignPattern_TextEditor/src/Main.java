import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TextEditor editor = new TextEditor();
        CommandHistory history = new CommandHistory();
        Scanner scanner = new Scanner(System.in);

        System.out.println("============================");
        System.out.println("     TEXT EDITOR (Java)");
        System.out.println(" Using Command + Memento Pattern ");
        System.out.println("============================");

        System.out.println("Available Commands:");
        System.out.println("1.Type <text>");
        System.out.println("2.Delete <substring>");
        System.out.println("3.Copy <substring>");
        System.out.println("4.Cut <substring>");
        System.out.println("5.Replace <oldSub> <newSub>");
        System.out.println("6.Paste");
        System.out.println("7.Undo");
        System.out.println("8.Redo");
        System.out.println("9.Show");
        System.out.println("10.Exit");

        while (true) {
            System.out.print("> ");
            String cmd = scanner.next();

            if ("type".equalsIgnoreCase(cmd)) {
                String text = scanner.nextLine().trim();
                history.executeCommand(new InsertTextCommand(editor, text));

            } else if ("delete".equalsIgnoreCase(cmd)) {
                String target = scanner.next();
                history.executeCommand(new DeleteTextCommand(editor, target));

            } else if ("copy".equalsIgnoreCase(cmd)) {
                String target = scanner.next();
                history.executeCommand(new CopyTextCommand(editor, target));

            } else if ("cut".equalsIgnoreCase(cmd)) {
                String target = scanner.next();
                history.executeCommand(new CutTextCommand(editor, target));

            } else if ("replace".equalsIgnoreCase(cmd)) {
                String oldSub = scanner.next();
                String newSub = scanner.next();
                history.executeCommand(new ReplaceTextCommand(editor, oldSub, newSub));

            } else if ("paste".equalsIgnoreCase(cmd)) {
                history.executeCommand(new PasteTextCommand(editor));

            } else if ("undo".equalsIgnoreCase(cmd)) {
                history.undo();

            } else if ("redo".equalsIgnoreCase(cmd)) {
                history.redo();

            } else if ("show".equalsIgnoreCase(cmd)) {
                System.out.println("Text     : \"" + editor.getText() + "\"");
                System.out.println("Clipboard: \"" + editor.getClipboard() + "\"");

            } else if ("exit".equalsIgnoreCase(cmd)) {
                System.out.println("Bye.");
                break;

            } else {
                System.out.println("Unknown command.");
                scanner.nextLine();
            }
        }

        scanner.close();
    }
}
