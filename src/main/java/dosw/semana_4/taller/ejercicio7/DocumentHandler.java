package dosw.semana_4.taller.ejercicio7;

public abstract class DocumentHandler {
    private DocumentHandler next;

    public void setNext(DocumentHandler next) {
        this.next = next;
    }

    public void handle(Document doc) {
        if (canHandle(doc)) {
            process(doc);
        } else if (next != null) {
            next.handle(doc);
        } else {
            System.out.println("  [Chain] Nadie más puede procesar este documento. Fin de la cadena.");
        }
    }

    protected abstract boolean canHandle(Document doc);
    protected abstract void process(Document doc);
}
