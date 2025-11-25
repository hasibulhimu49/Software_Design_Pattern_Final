public class ReplaceTextCommand implements Command {
    private final TextEditor editor;
    private final String target;
    private final String replacement;
    private EditorMemento backup;

    public ReplaceTextCommand(TextEditor editor, String target, String replacement) {
        this.editor = editor;
        this.target = target;
        this.replacement = replacement;
    }

    @Override
    public void execute() {
        backup = editor.save();
        editor.replaceSubstring(target, replacement);
    }

    @Override
    public void undo() {
        if (backup != null) editor.restore(backup);
    }
}
