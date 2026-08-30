package dosw.semana_4.taller.ejercicio6;

public class Content {
    private final String title;

    public Content(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
