public class CopyTextCommand implements Command {
    private final TextEditor editor;
    private final String target;
    private EditorMemento backup;

    public CopyTextCommand(TextEditor editor, String target) {
        this.editor = editor;
        this.target = target;
    }

    @Override
    public void execute() {
        backup = editor.save();
        editor.copySubstring(target);
    }

    @Override
    public void undo() {
        if (backup != null) editor.restore(backup);
    }
}
