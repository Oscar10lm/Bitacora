package dosw.semana_4.taller.ejercicio10;

public class ApplyFilterCommand implements ImageCommand {
    private final ImageEditor editor;
    private final String filterType;

    public ApplyFilterCommand(ImageEditor editor, String filterType) {
        this.editor = editor;
        this.filterType = filterType;
    }

    @Override
    public void execute() {
        Image currentImage = editor.getImage();
        
        // Aplicamos el decorador según el filtro
        if ("Sepia".equalsIgnoreCase(filterType)) {
            editor.setImage(new SepiaDecorator(currentImage));
        } else if ("Grayscale".equalsIgnoreCase(filterType)) {
            editor.setImage(new GrayscaleDecorator(currentImage));
        }
        
        System.out.println("  [Command] Filtro aplicado: " + filterType);
    }

    @Override
    public void undo() {
        Image currentImage = editor.getImage();
        
        // El undo consiste en desenvolver la imagen (quitar el último decorator)
        if (currentImage instanceof ImageDecorator) {
            ImageDecorator decorator = (ImageDecorator) currentImage;
            editor.setImage(decorator.getWrapped());
            System.out.println("  [Command] Undo ejecutado: removido el filtro " + filterType);
        } else {
            System.out.println("  [Command] No hay filtros para deshacer.");
        }
    }
}
