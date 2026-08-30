package dosw.semana_4.taller.ejercicio10;

public class GrayscaleDecorator extends ImageDecorator {
    public GrayscaleDecorator(Image wrapped) {
        super(wrapped);
    }

    @Override
    public void render() {
        super.render();
        System.out.print(" + [Filtro: Blanco y Negro]");
    }
}
