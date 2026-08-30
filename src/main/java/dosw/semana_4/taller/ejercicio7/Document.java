package dosw.semana_4.taller.ejercicio7;

public class Document {
    private final String content;
    private final String type;
    private DocumentState state;

    public Document(String content, String type) {
        this.content = content;
        this.type = type;
        this.state = new DraftState();
    }

    public void setState(DocumentState state) {
        this.state = state;
    }

    public void approve() {
        state.approve(this);
    }

    public void reject() {
        state.reject(this);
    }

    public String getType() {
        return type;
    }
    
    public String getContent() {
        return content;
    }
}
