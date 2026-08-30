package dosw.semana_3.extra.patrones.creacionales.prototype;

/**
 * ============================================================================
 * PATRÓN DE DISEÑO: PROTOTYPE (CREACIONAL)
 * ============================================================================
 *
 * DEFINICIÓN:
 * Crea objetos clonando una instancia existente en vez de construirla desde cero.
 * 
 * ÚTIL CUANDO:
 * a) Crear el objeto es costoso (requiere consultar una base de datos, 
 *    hacer cálculos pesados, cargar recursos gráficos pesados, etc.).
 * b) Necesitas muchas variantes de un objeto base con pequeños cambios, sin 
 *    repetir todo el proceso de construcción.
 *
 * ----------------------------------------------------------------------------
 * EJERCICIO PRÁCTICO: EDITOR DE DOCUMENTOS CON PLANTILLAS
 * ----------------------------------------------------------------------------
 * Un editor permite crear nuevos documentos a partir de plantillas ya diseñadas.
 * Crear la plantilla desde cero es costoso (carga de estilos, imágenes, etc.).
 * El sistema permite clonar una plantilla existente para generar un documento 
 * nuevo, modificando solo el contenido sin rehacer el diseño pesado.
 */
public class DocumentTemplateSystem {

    // ==========================================
    // 1. INTERFAZ PROTOTIPO
    // ==========================================
    // Aunque Java tiene su propia interfaz `Cloneable`, crear nuestra propia 
    // interfaz nos permite definir el tipo de retorno exacto y evitar lanzar 
    // CloneNotSupportedException en todos lados.
    public interface DocumentPrototype {
        DocumentPrototype cloneDocument();
    }

    // ==========================================
    // 2. PROTOTIPO CONCRETO (El Documento)
    // ==========================================
    public static class Document implements DocumentPrototype {
        
        // Elementos "pesados" o de diseño (simulan costo de carga)
        private String headerImage;
        private String footerStyle;
        private String pageFormat;
        
        // Elemento ligero y cambiante
        private String contentText;

        // Constructor estándar (Representa la carga "costosa")
        public Document(String formatType) {
            System.out.println(">>> [COSTOSO] Creando plantilla base de tipo: " + formatType + "...");
            simulateHeavyLoad();
            
            this.pageFormat = formatType;
            this.headerImage = "logo_empresa.png";
            this.footerStyle = "Página X de Y - Confidencial";
            this.contentText = ""; // Vacío por defecto
            
            System.out.println("    Plantilla creada en memoria.\n");
        }

        // Constructor privado utilizado internamente para la clonación
        private Document(Document target) {
            if (target != null) {
                this.headerImage = target.headerImage;
                this.footerStyle = target.footerStyle;
                this.pageFormat = target.pageFormat;
                this.contentText = target.contentText;
            }
        }

        // Simula un retardo al cargar imágenes o conectarse a BD
        private void simulateHeavyLoad() {
            try {
                Thread.sleep(1500); // 1.5 segundos de retardo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // ==========================================
        // 3. IMPLEMENTACIÓN DE LA CLONACIÓN
        // ==========================================
        @Override
        public DocumentPrototype cloneDocument() {
            // Llama al constructor de copia, el cual es instantáneo
            // y no pasa por el 'simulateHeavyLoad()' del constructor estándar
            return new Document(this);
        }

        // Getters y Setters
        public void setContentText(String contentText) {
            this.contentText = contentText;
        }

        @Override
        public String toString() {
            return "Documento [" + pageFormat + "] \n" +
                   "  Encabezado: " + headerImage + "\n" +
                   "  Contenido: " + contentText + "\n" +
                   "  Pie: " + footerStyle + "\n";
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // 1. INICIO DE LA APLICACIÓN: Se cargan las plantillas base en el registro.
        // Esto tarda, pero solo se hace una vez.
        Document cartaFormalTemplate = new Document("Carta A4 Formal");
        Document facturaTemplate = new Document("Factura Comercial");
        
        System.out.println("--- Sistema Listo. El usuario comienza a trabajar ---\n");

        // 2. El usuario pide redactar una carta de despido
        System.out.println("Usuario: 'Quiero crear una nueva carta formal'");
        
        // EN LUGAR DE: new Document("Carta A4 Formal"); (Tardaría 1.5s otra vez)
        // HACEMOS CLONACIÓN:
        long startTime = System.currentTimeMillis();
        
        Document myNewLetter = (Document) cartaFormalTemplate.cloneDocument();
        
        long endTime = System.currentTimeMillis();
        System.out.println("¡Clonación realizada en " + (endTime - startTime) + " milisegundos!");

        // 3. El usuario modifica solo el contenido del clon
        myNewLetter.setContentText("Estimado empleado, queda despedido...");
        
        System.out.println("\nDocumento Final Resultante:");
        System.out.println(myNewLetter);

        // 4. Demostramos que la plantilla original permanece intacta
        System.out.println("Plantilla Original Intacta:");
        System.out.println(cartaFormalTemplate);
    }
}
