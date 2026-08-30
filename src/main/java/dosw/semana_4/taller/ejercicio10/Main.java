package dosw.semana_4.taller.ejercicio10;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Aplicación de Edición de Imágenes ---");

        // 1. Cargamos la imagen original
        Image baseImage = new BaseImage("foto_vacaciones.png");
        ImageEditor editor = new ImageEditor(baseImage);
        
        System.out.println("\n--- 1. Original ---");
        editor.show();

        // 2. Aplicamos un filtro (Encapsulado en un Command)
        System.out.println("\n--- 2. Aplicar filtro Sepia ---");
        ImageCommand sepiaCmd = new ApplyFilterCommand(editor, "Sepia");
        editor.applyCommand(sepiaCmd);
        editor.show();

        // 3. Aplicamos otro filtro encima
        System.out.println("\n--- 3. Aplicar filtro Blanco y Negro ---");
        ImageCommand grayscaleCmd = new ApplyFilterCommand(editor, "Grayscale");
        editor.applyCommand(grayscaleCmd);
        editor.show();

        // 4. Deshacer el último filtro (Blanco y Negro)
        System.out.println("\n--- 4. Deshacer última acción ---");
        editor.undo();
        editor.show();

        // 5. Deshacer el primer filtro (Sepia)
        System.out.println("\n--- 5. Deshacer otra vez ---");
        editor.undo();
        editor.show();
    }
}
