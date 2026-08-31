package dosw.semana_3.taller.patrones;

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
