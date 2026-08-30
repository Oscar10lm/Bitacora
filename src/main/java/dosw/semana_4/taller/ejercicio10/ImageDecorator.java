package dosw.semana_4.taller.ejercicio10;

public abstract class ImageDecorator implements Image {
    protected Image wrapped;

    public ImageDecorator(Image wrapped) {
        this.wrapped = wrapped;
    }

    public Image getWrapped() {
        return wrapped;
    }
    
    @Override
    public void render() {
        wrapped.render();
    }
}
