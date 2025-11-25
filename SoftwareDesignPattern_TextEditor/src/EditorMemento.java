public class EditorMemento {
    private final String text;
    private final String clipboard;

    public EditorMemento(String text, String clipboard) {
        this.text = text;
        this.clipboard = clipboard;
    }

    public String getText() {
        return text;
    }

    public String getClipboard() {
        return clipboard;
    }
}
