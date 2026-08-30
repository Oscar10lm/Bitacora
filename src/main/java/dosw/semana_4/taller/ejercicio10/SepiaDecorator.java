package dosw.semana_4.taller.ejercicio10;

public class SepiaDecorator extends ImageDecorator {
    public SepiaDecorator(Image wrapped) {
        super(wrapped);
    }

    @Override
    public void render() {
        super.render();
        System.out.print(" + [Filtro: Sepia]");
    }
}
