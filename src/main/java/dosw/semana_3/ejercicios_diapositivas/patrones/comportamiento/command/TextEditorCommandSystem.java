package dosw.semana_3.ejercicios_diapositivas.patrones.comportamiento.command;

import java.util.Stack;

public class TextEditorCommandSystem {

    // ==========================================
    // 1. EL RECEPTOR (Receiver - El Documento de Texto)
    // ==========================================
    public static class TextDocument {
        private StringBuilder content = new StringBuilder();

        public void insertText(String text) {
            content.append(text);
        }

        public void deleteLast(int length) {
            if (length <= content.length()) {
                content.delete(content.length() - length, content.length());
            }
        }

        public void toUpperCase() {
            String upper = content.toString().toUpperCase();
            content.setLength(0);
            content.append(upper);
        }

        // Método vital para el Undo universal: Restaurar el estado completo
        public void setState(String state) {
            content.setLength(0);
            content.append(state);
        }

        public String getState() {
            return content.toString();
        }

        @Override
        public String toString() {
            return content.toString();
        }
    }

    // ==========================================
    // 2. LA INTERFAZ COMANDO (Con soporte para Undo)
    // ==========================================
    public interface Command {
        void execute();
        void undo();
    }

    // Clase abstracta para los comandos que necesitan guardar el estado previo
    public static abstract class BackupCommand implements Command {
        protected TextDocument document;
        protected String backup;

        public BackupCommand(TextDocument document) {
            this.document = document;
        }

        // Guarda una foto del texto antes de modificarlo
        protected void saveBackup() {
            backup = document.getState();
        }

        @Override
        public void undo() {
            // Si hacemos Undo, restauramos la foto
            document.setState(backup);
        }
    }

    // ==========================================
    // 3. COMANDOS CONCRETOS
    // ==========================================
    
    // Comando: Escribir Texto
    public static class WriteTextCommand extends BackupCommand {
        private String textToWrite;

        public WriteTextCommand(TextDocument document, String textToWrite) {
            super(document);
            this.textToWrite = textToWrite;
        }

        @Override
        public void execute() {
            saveBackup(); // Guardar estado antes de dañar
            document.insertText(textToWrite);
        }
    }

    // Comando: Eliminar Texto
    public static class DeleteTextCommand extends BackupCommand {
        private int charsToDelete;

        public DeleteTextCommand(TextDocument document, int charsToDelete) {
            super(document);
            this.charsToDelete = charsToDelete;
        }

        @Override
        public void execute() {
            saveBackup(); 
            document.deleteLast(charsToDelete);
        }
    }

    // Comando: Convertir a Mayúsculas
    public static class UpperCaseCommand extends BackupCommand {

        public UpperCaseCommand(TextDocument document) {
            super(document);
        }

        @Override
        public void execute() {
            saveBackup();
            document.toUpperCase();
        }
    }

    // ==========================================
    // 4. EL INVOCADOR (Invoker - La Barra de Herramientas)
    // ==========================================
    public static class Toolbar {
        private Stack<Command> history = new Stack<>();

        public void executeAction(Command command) {
            command.execute();
            history.push(command); // Apilamos el comando ejecutado
        }

        public void undoAction() {
            if (!history.isEmpty()) {
                Command lastCommand = history.pop(); // Sacamos el último
                lastCommand.undo(); // Deshacemos
            } else {
                System.out.println("No hay acciones para deshacer.");
            }
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void printDoc(TextDocument doc) {
        System.out.println("Documento Actual: [" + doc.toString() + "]");
    }

    public static void main(String[] args) {
        
        System.out.println("--- ABRIENDO EDITOR DE TEXTO ---\n");
        
        TextDocument document = new TextDocument();
        Toolbar toolbar = new Toolbar();

        // 1. Escribimos algo
        System.out.println(">> Acción: Escribiendo 'Hola Mundo '");
        Command cmd1 = new WriteTextCommand(document, "Hola Mundo ");
        toolbar.executeAction(cmd1);
        printDoc(document);

        // 2. Escribimos más
        System.out.println("\n>> Acción: Escribiendo 'cruel'");
        Command cmd2 = new WriteTextCommand(document, "cruel");
        toolbar.executeAction(cmd2);
        printDoc(document);

        // 3. Convertimos a mayúsculas
        System.out.println("\n>> Acción: Aplicar formato MAYÚSCULAS");
        Command cmd3 = new UpperCaseCommand(document);
        toolbar.executeAction(cmd3);
        printDoc(document);
        
        // 4. Eliminamos la palabra "CRUEL" (5 caracteres)
        System.out.println("\n>> Acción: Borrar últimos 5 caracteres");
        Command cmd4 = new DeleteTextCommand(document, 5);
        toolbar.executeAction(cmd4);
        printDoc(document);

        // ==========================================
        // PRUEBA DEL HISTORIAL (CTRL + Z)
        // ==========================================
        System.out.println("\n--- EL USUARIO PRESIONA CTRL+Z (Deshacer) MÚLTIPLES VECES ---");
        
        System.out.println("\n>> [Undo 1]: Debería restaurar la palabra 'CRUEL'");
        toolbar.undoAction();
        printDoc(document);
        
        System.out.println("\n>> [Undo 2]: Debería quitar las mayúsculas");
        toolbar.undoAction();
        printDoc(document);
        
        System.out.println("\n>> [Undo 3]: Debería quitar la palabra 'cruel' original");
        toolbar.undoAction();
        printDoc(document);
        
        System.out.println("\n>> [Undo 4]: Debería dejar el documento vacío");
        toolbar.undoAction();
        printDoc(document);
    }
}
