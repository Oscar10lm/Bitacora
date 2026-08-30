package dosw.semana_4.taller.ejercicio10;

public class BaseImage implements Image {
    private final String filename;

    public BaseImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void render() {
        System.out.print("Imagen[" + filename + "]");
    }
}
