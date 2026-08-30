package dosw.semana_4.taller.ejercicio10;

import java.util.Stack;

public class ImageEditor {
    private Image image;
    private final Stack<ImageCommand> history = new Stack<>();

    public ImageEditor(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void applyCommand(ImageCommand command) {
        command.execute();
        history.push(command);
    }

    public void undo() {
        if (!history.isEmpty()) {
            ImageCommand lastCommand = history.pop();
            lastCommand.undo();
        } else {
            System.out.println("No hay acciones en el historial para deshacer.");
        }
    }

    public void show() {
        System.out.print("Estado de la imagen: ");
        image.render();
        System.out.println();
    }
}
