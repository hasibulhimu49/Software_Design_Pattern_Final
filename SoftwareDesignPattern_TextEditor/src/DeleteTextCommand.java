public class DeleteTextCommand implements Command {
    private final TextEditor editor;
    private final String target;
    private EditorMemento backup;

    public DeleteTextCommand(TextEditor editor, String target) {
        this.editor = editor;
        this.target = target;
    }

    @Override
    public void execute() {
        backup = editor.save();
        editor.deleteSubstring(target);
    }

    @Override
    public void undo() {
        if (backup != null) editor.restore(backup);
    }
}
