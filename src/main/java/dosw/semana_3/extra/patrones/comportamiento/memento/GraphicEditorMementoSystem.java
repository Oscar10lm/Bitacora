package dosw.semana_3.extra.patrones.comportamiento.memento;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GraphicEditorMementoSystem {

    // ==========================================
    // 1. EL MEMENTO (La foto del Lienzo)
    // ==========================================
    public static class CanvasSnapshot {
        // Guardamos una lista inmutable (copia) de las figuras en ese momento
        private final List<String> savedShapes;

        public CanvasSnapshot(List<String> currentShapes) {
            // Hacemos una copia profunda (o al menos un nuevo arraylist) del estado
            this.savedShapes = new ArrayList<>(currentShapes);
        }

        private List<String> getShapes() {
            return savedShapes;
        }
    }

    // ==========================================
    // 2. EL ORIGINADOR (El Lienzo / Canvas)
    // ==========================================
    public static class Canvas {
        private List<String> shapes = new ArrayList<>();

        public void addShape(String shape) {
            shapes.add(shape);
            System.out.println("  [Canvas]: Añadido -> " + shape);
        }

        public void clearCanvas() {
            shapes.clear();
            System.out.println("  [Canvas]: \uD83D\uDCA3 Lienzo limpiado por completo.");
        }

        public void printCanvas() {
            System.out.println("  > Contenido del Canvas: " + shapes.toString());
        }

        // Crear captura
        public CanvasSnapshot takeSnapshot() {
            return new CanvasSnapshot(this.shapes);
        }

        // Restaurar captura
        public void restoreSnapshot(CanvasSnapshot memento) {
            // Reemplazamos la lista actual con la que estaba guardada en el memento
            this.shapes = new ArrayList<>(memento.getShapes());
            System.out.println("  [Canvas]: Estado restaurado desde el historial.");
        }
    }

    // ==========================================
    // 3. EL CUIDADOR (El Gestor de Deshacer/Rehacer)
    // ==========================================
    public static class ActionHistory {
        private Stack<CanvasSnapshot> undoStack = new Stack<>();
        private Stack<CanvasSnapshot> redoStack = new Stack<>();
        private Canvas canvas;

        public ActionHistory(Canvas canvas) {
            this.canvas = canvas;
        }

        // Hace una captura justo ANTES de hacer un cambio
        public void backup() {
            undoStack.push(canvas.takeSnapshot());
            // Si haces una nueva acción, pierdes el historial del "Rehacer"
            redoStack.clear(); 
        }

        public void undo() {
            if (!undoStack.isEmpty()) {
                System.out.println("\n--- [ACCIÓN: DESHACER (CTRL+Z)] ---");
                // Antes de volver atrás, guardo el estado actual en REDO por si quiero volver al futuro
                redoStack.push(canvas.takeSnapshot());
                
                CanvasSnapshot previousState = undoStack.pop();
                canvas.restoreSnapshot(previousState);
            } else {
                System.out.println("No hay nada que deshacer.");
            }
        }

        public void redo() {
            if (!redoStack.isEmpty()) {
                System.out.println("\n--- [ACCIÓN: REHACER (CTRL+Y)] ---");
                // Antes de ir al futuro, guardo el estado actual en UNDO por si quiero volver atrás
                undoStack.push(canvas.takeSnapshot());
                
                CanvasSnapshot nextState = redoStack.pop();
                canvas.restoreSnapshot(nextState);
            } else {
                System.out.println("No hay nada que rehacer.");
            }
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        System.out.println(">>> ABRIENDO PHOTOSHOP CLON <<<\n");
        
        Canvas lienzo = new Canvas();
        ActionHistory historia = new ActionHistory(lienzo);
        
        System.out.println("--- Dibujando la escena ---");
        historia.backup(); // Guardo lienzo vacío
        lienzo.addShape("Círculo Rojo (Sol)");
        
        historia.backup(); // Guardo con sol
        lienzo.addShape("Cuadrado Azul (Casa)");
        
        historia.backup(); // Guardo con sol y casa
        lienzo.addShape("Triángulo Verde (Árbol)");
        
        lienzo.printCanvas(); // [Sol, Casa, Árbol]

        // --- Aplicando Deshacer ---
        historia.undo(); // Debería borrar el Árbol
        lienzo.printCanvas(); // [Sol, Casa]
        
        historia.undo(); // Debería borrar la Casa
        lienzo.printCanvas(); // [Sol]
        
        // --- Aplicando Rehacer ---
        historia.redo(); // Debería devolverme la Casa
        lienzo.printCanvas(); // [Sol, Casa]
        
        // --- Nueva Acción Borra el Futuro ---
        System.out.println("\n--- El usuario pinta algo diferente ---");
        historia.backup();
        lienzo.addShape("Línea Negra (Camino)");
        lienzo.printCanvas(); // [Sol, Casa, Camino]
        
        // Intenta rehacer, pero ya no se puede recuperar el Árbol porque la línea temporal se ramificó
        historia.redo(); 
    }
}
