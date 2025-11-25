public class TextEditor {
    private StringBuilder text = new StringBuilder();
    private String clipboard = "";

    public void insert(String toInsert) {
        text.append(toInsert);
    }

    public void deleteSubstring(String target) {
        int idx = text.indexOf(target);
        if (idx != -1) {
            text.delete(idx, idx + target.length());
        }
    }

    public void copySubstring(String target) {
        int idx = text.indexOf(target);
        if (idx != -1) {
            clipboard = text.substring(idx, idx + target.length());
        }
    }

    public void cutSubstring(String target) {
        int idx = text.indexOf(target);
        if (idx != -1) {
            clipboard = text.substring(idx, idx + target.length());
            text.delete(idx, idx + target.length());
        }
    }

    public void replaceSubstring(String target, String replacement) {
        int idx = text.indexOf(target);
        if (idx != -1) {
            text.replace(idx, idx + target.length(), replacement);
        }
    }

    public void pasteClipboard() {
        text.append(clipboard);
    }

    public String getText() {
        return text.toString();
    }

    public String getClipboard() {
        return clipboard;
    }

    public EditorMemento save() {
        return new EditorMemento(text.toString(), clipboard);
    }

    public void restore(EditorMemento memento) {
        this.text = new StringBuilder(memento.getText());
        this.clipboard = memento.getClipboard();
    }
}
