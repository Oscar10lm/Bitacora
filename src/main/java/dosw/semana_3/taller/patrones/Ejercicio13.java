package dosw.semana_3.taller.patrones;

/**
 * -------------------------------------------------------------------
 * PARTE III — IDENTIFICANDO PATRONES DE DISEÑO
 * #13 Generación de Reportes
 * -------------------------------------------------------------------
 *
 * (1) SITUACIÓN:
 * El sistema debe generar un reporte en PDF, Excel o CSV según una 
 * configuración dada. El cliente que pide el reporte no quiere lidiar 
 * con los detalles de cómo se construye (instancia) cada uno de ellos.
 *
 * (2) CATEGORÍA:
 * Patrón Creacional (Creational Pattern).
 *
 * (3) PATRÓN SELECCIONADO:
 * Factory Method (o Simple Factory).
 *
 * (4) ¿POR QUÉ?:
 * El requerimiento central habla sobre ocultar los detalles de **construcción** 
 * (instanciación). El patrón Factory delega la responsabilidad de utilizar el 
 * operador `new` a una clase especial (la Fábrica). El cliente simplemente 
 * le pasa a la fábrica un parámetro (ej. "PDF") y la fábrica le devuelve un 
 * objeto genérico (una interfaz `ReportGenerator`) listo para usar.
 *
 * (5) ¿APLICA MÁS DE UN PATRÓN? (Comparación):
 * - Podría confundirse con **Abstract Factory**, pero Abstract Factory se 
 *   usa para crear *familias* enteras de objetos relacionados (ej. crear 
 *   Botón Windows, Scroll Windows, Panel Windows vs Botón Mac, etc.). Aquí 
 *   solo creamos un único producto (el reporte), por lo que Factory Method 
 *   o Simple Factory es suficiente y más directo.
 * - Podría pensarse en **Strategy**, pero Strategy se enfoca en intercambiar
 *   comportamientos que ya fueron construidos e inyectados. Aquí el dolor está 
 *   en el momento mismo de **crear** el objeto según una configuración, terreno 
 *   exclusivo de los patrones creacionales.
 */
public class Ejercicio13 {

    // --- IMPLEMENTACIÓN DEL PATRÓN FACTORY ---

    // 1. El "Producto" (Interfaz común)
    public interface Report {
        void generate(String data);
    }

    // 2. Productos Concretos
    
    public static class PdfReport implements Report {
        @Override
        public void generate(String data) {
            System.out.println("Convirtiendo datos a binario... Renderizando fuente... Generado reporte en PDF.");
        }
    }

    public static class ExcelReport implements Report {
        @Override
        public void generate(String data) {
            System.out.println("Creando celdas... Aplicando fórmulas... Generado reporte en Excel (XLSX).");
        }
    }

    public static class CsvReport implements Report {
        @Override
        public void generate(String data) {
            System.out.println("Separando por comas... Generado reporte en CSV.");
        }
    }

    // 3. La "Fábrica" (Oculta la lógica de instanciación)
    
    public static class ReportFactory {
        
        // Método fábrica
        public static Report createReport(String formatType) {
            switch (formatType.toUpperCase()) {
                case "PDF":
                    return new PdfReport(); // Podría tener configuración compleja antes de retornar
                case "EXCEL":
                    return new ExcelReport();
                case "CSV":
                    return new CsvReport();
                default:
                    throw new IllegalArgumentException("Formato no soportado: " + formatType);
            }
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        // La configuración viene de la base de datos o el usuario
        String clientConfig = "EXCEL";
        String reportData = "{ ventas: 15000, periodo: 'Q3' }";

        // El cliente (la lógica de la app) NO usa 'new ExcelReport()'. 
        // Solo pide el objeto a la fábrica.
        Report myReport = ReportFactory.createReport(clientConfig);
        
        // El cliente usa la interfaz, ignorando cómo fue construido
        myReport.generate(reportData);
        
        System.out.println("---");
        
        // Otro cliente pide en PDF
        Report pdfReport = ReportFactory.createReport("PDF");
        pdfReport.generate(reportData);
    }
}
