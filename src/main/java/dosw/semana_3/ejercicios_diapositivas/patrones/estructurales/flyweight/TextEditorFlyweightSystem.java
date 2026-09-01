package dosw.semana_3.ejercicios_diapositivas.patrones.estructurales.flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextEditorFlyweightSystem {

    // ==========================================
    // 1. EL FLYWEIGHT (Estado Intrínseco)
    // ==========================================
    // Información de formato (Fuente, Tamaño, Color).
    public static class CharacterFormat {
        private String fontStyle;
        private int fontSize;
        private String color;

        public CharacterFormat(String fontStyle, int fontSize, String color) {
            this.fontStyle = fontStyle;
            this.fontSize = fontSize;
            this.color = color;
        }

        public void printFormatDetails() {
            System.out.print("[" + fontStyle + ", " + fontSize + "px, " + color + "]");
        }
    }

    // ==========================================
    // 2. EL CONTEXTO (Estado Extrínseco)
    // ==========================================
    // La letra individual que se dibuja en la pantalla
    public static class TextCharacter {
        private char symbol;
        private int positionX;
        private int positionY;
        private CharacterFormat format; // Referencia al Flyweight

        public TextCharacter(char symbol, int positionX, int positionY, CharacterFormat format) {
            this.symbol = symbol;
            this.positionX = positionX;
            this.positionY = positionY;
            this.format = format;
        }

        public void draw() {
            System.out.print("Dibujando '" + symbol + "' en (" + positionX + "," + positionY + ") con formato ");
            format.printFormatDetails();
            System.out.println();
        }
    }

    // ==========================================
    // 3. LA FÁBRICA FLYWEIGHT
    // ==========================================
    public static class FormatFactory {
        static Map<String, CharacterFormat> formats = new HashMap<>();

        public static CharacterFormat getFormat(String fontStyle, int fontSize, String color) {
            // Creamos una clave única combinando los atributos
            String key = fontStyle + "_" + fontSize + "_" + color;
            
            CharacterFormat result = formats.get(key);
            if (result == null) {
                System.out.println("  [FÁBRICA]: Cargando a memoria un nuevo formato -> " + key);
                result = new CharacterFormat(fontStyle, fontSize, color);
                formats.put(key, result);
            }
            return result;
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static class Document {
        private List<TextCharacter> characters = new ArrayList<>();

        public void addCharacter(char symbol, int x, int y, String fontStyle, int fontSize, String color) {
            CharacterFormat format = FormatFactory.getFormat(fontStyle, fontSize, color);
            characters.add(new TextCharacter(symbol, x, y, format));
        }

        public void renderDocument() {
            for (TextCharacter c : characters) {
                c.draw();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(">>> EDITOR DE TEXTO MS WORD CLON <<<\n");

        Document doc = new Document();

        System.out.println("--- Escribiendo título (Arial 24 Rojo) ---");
        doc.addCharacter('H', 0, 0, "Arial", 24, "Rojo");
        doc.addCharacter('o', 1, 0, "Arial", 24, "Rojo");
        doc.addCharacter('l', 2, 0, "Arial", 24, "Rojo");
        doc.addCharacter('a', 3, 0, "Arial", 24, "Rojo");

        System.out.println("\n--- Escribiendo cuerpo (Times New Roman 12 Negro) ---");
        doc.addCharacter('M', 0, 1, "Times New Roman", 12, "Negro");
        doc.addCharacter('u', 1, 1, "Times New Roman", 12, "Negro");
        doc.addCharacter('n', 2, 1, "Times New Roman", 12, "Negro");
        doc.addCharacter('d', 3, 1, "Times New Roman", 12, "Negro");
        doc.addCharacter('o', 4, 1, "Times New Roman", 12, "Negro");

        System.out.println("\n--- Renderizando Página ---");
        doc.renderDocument();

        System.out.println("\n[RENDIMIENTO]:");
        System.out.println("Letras dibujadas en pantalla    : 9");
        System.out.println("Formatos guardados en RAM       : " + FormatFactory.formats.size());
    }
}
