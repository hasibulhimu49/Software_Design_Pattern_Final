public class InsertTextCommand implements Command {
    private final TextEditor editor;
    private final String textToInsert;
    private EditorMemento backup;

    public InsertTextCommand(TextEditor editor, String textToInsert) {
        this.editor = editor;
        this.textToInsert = textToInsert;
    }

    @Override
    public void execute() {
        backup = editor.save();
        editor.insert(textToInsert);
    }

    @Override
    public void undo() {
        if (backup != null) editor.restore(backup);
    }
}
