public class RemoteControl {
    private final Command[] buttons;

    // we’ll use buttons 1..10, so make it a bit bigger
    public RemoteControl(int numberOfButtons) {
        buttons = new Command[numberOfButtons + 1]; // ignore index 0
    }

    public void setCommand(int slot, Command command) {
        if (slot < 1 || slot >= buttons.length) {
            throw new IllegalArgumentException("Invalid button slot: " + slot);
        }
        buttons[slot] = command;
    }

    public void pressButton(int slot) {
        if (slot < 1 || slot >= buttons.length || buttons[slot] == null) {
            System.out.println("No command assigned to button " + slot);
            return;
        }
        buttons[slot].execute();
    }
}
