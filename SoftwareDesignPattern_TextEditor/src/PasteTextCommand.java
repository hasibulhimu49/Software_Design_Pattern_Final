public class PasteTextCommand implements Command {
    private final TextEditor editor;
    private EditorMemento backup;

    public PasteTextCommand(TextEditor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        backup = editor.save();
        editor.pasteClipboard();
    }

    @Override
    public void undo() {
        if (backup != null) editor.restore(backup);
    }
}
