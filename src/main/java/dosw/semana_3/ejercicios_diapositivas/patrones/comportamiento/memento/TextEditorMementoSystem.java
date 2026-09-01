package dosw.semana_3.ejercicios_diapositivas.patrones.comportamiento.memento;

import java.util.Stack;

public class TextEditorMementoSystem {

    // ==========================================
    // 1. EL MEMENTO (La Captura de Estado)
    // ==========================================
    // Una clase inmutable que guarda el estado. Solo el Originador debería 
    // poder crearla y leerla a plenitud.
    public static class TextMemento {
        private final String textState;

        public TextMemento(String textState) {
            this.textState = textState;
        }

        private String getSavedText() {
            return textState;
        }
    }

    // ==========================================
    // 2. EL ORIGINADOR (El Creador de la Captura)
    // ==========================================
    // El objeto cuyo estado queremos proteger y restaurar.
    public static class TextEditor {
        private StringBuilder currentText;

        public TextEditor() {
            this.currentText = new StringBuilder();
        }

        public void write(String text) {
            currentText.append(text);
        }

        public String getText() {
            return currentText.toString();
        }

        // Crea una "foto" de su estado actual
        public TextMemento createSnapshot() {
            // Es vital guardar un CLON del estado, no la referencia al StringBuilder
            return new TextMemento(currentText.toString());
        }

        // Restaura su estado a partir de una "foto" antigua
        public void restore(TextMemento memento) {
            this.currentText = new StringBuilder(memento.getSavedText());
            System.out.println("  [Sistema]: Restaurando estado anterior...");
        }
    }

    // ==========================================
    // 3. EL CUIDADOR (Caretaker - El Gestor del Historial)
    // ==========================================
    // Sabe CUÁNDO guardar y restaurar, pero NO sabe QUÉ hay dentro del Memento.
    public static class HistoryManager {
        private Stack<TextMemento> history = new Stack<>();
        private TextEditor editor;

        public HistoryManager(TextEditor editor) {
            this.editor = editor;
        }

        public void save() {
            history.push(editor.createSnapshot());
        }

        public void undo() {
            if (!history.isEmpty()) {
                TextMemento previousState = history.pop();
                editor.restore(previousState);
            } else {
                System.out.println("  [Sistema]: No hay más historial para deshacer.");
            }
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> ABRIENDO BLOC DE NOTAS (Memento) <<<\n");

        TextEditor editor = new TextEditor();
        HistoryManager history = new HistoryManager(editor);

        // 1. Escribimos primera línea
        System.out.println(">> Escribiendo...");
        editor.write("Hola, ");
        history.save(); // Guardamos el estado: "Hola, "
        System.out.println("Documento: [" + editor.getText() + "]\n");

        // 2. Escribimos segunda línea
        System.out.println(">> Escribiendo...");
        editor.write("este es el patrón ");
        history.save(); // Guardamos el estado: "Hola, este es el patrón "
        System.out.println("Documento: [" + editor.getText() + "]\n");

        // 3. Cometemos un error (no lo guardamos en el historial porque es error)
        System.out.println(">> Escribiendo (Error)...");
        editor.write("Medieitor (Error de ortografía)");
        System.out.println("Documento: [" + editor.getText() + "]\n");

        // 4. Aplicamos CTRL+Z (Deshacer)
        System.out.println("--- PRESIONANDO CTRL+Z ---");
        history.undo();
        System.out.println("Documento: [" + editor.getText() + "]\n");
        
        // 5. Corregimos el error y guardamos
        System.out.println(">> Escribiendo corrección...");
        editor.write("Memento.");
        history.save();
        System.out.println("Documento final: [" + editor.getText() + "]\n");
    }
}
