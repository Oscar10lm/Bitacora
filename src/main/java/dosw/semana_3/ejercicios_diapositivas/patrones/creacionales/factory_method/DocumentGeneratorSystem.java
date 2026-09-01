package dosw.semana_3.ejercicios_diapositivas.patrones.creacionales.factory_method;

public class DocumentGeneratorSystem {

    // ==========================================
    // 1. PRODUCTO (La interfaz común)
    // ==========================================
    public interface Document {
        void generate(String reportName);
    }

    // ==========================================
    // 2. PRODUCTOS CONCRETOS (Implementaciones)
    // ==========================================
    public static class PdfDocument implements Document {
        @Override
        public void generate(String reportName) {
            System.out.println("Generando documento en formato PDF: " + reportName);
        }
    }

    public static class WordDocument implements Document {
        @Override
        public void generate(String reportName) {
            System.out.println("Generando documento en formato WORD: " + reportName);
        }
    }

    public static class ExcelDocument implements Document {
        @Override
        public void generate(String reportName) {
            System.out.println("Generando documento en formato EXCEL: " + reportName);
        }
    }

    // ==========================================
    // 3. CREADOR (Clase Abstracta / Superclase)
    // ==========================================
    public static abstract class DocumentGenerator {
        
        // El "Factory Method"
        protected abstract Document createDocument();

        // Lógica principal: Exporta el reporte usando el producto abstracto
        public void exportReport(String reportName) {
            System.out.println("Recopilando datos del reporte '" + reportName + "'...");
            
            // Se invoca a la fábrica para obtener la instancia concreta
            Document document = createDocument();
            
            // Delegamos la creación al objeto polimórfico
            document.generate(reportName);
            
            System.out.println("Exportación finalizada.\n");
        }
    }

    // ==========================================
    // 4. CREADORES CONCRETOS (Subclases)
    // ==========================================
    public static class PdfGenerator extends DocumentGenerator {
        @Override
        protected Document createDocument() {
            return new PdfDocument();
        }
    }

    public static class WordGenerator extends DocumentGenerator {
        @Override
        protected Document createDocument() {
            return new WordDocument();
        }
    }

    public static class ExcelGenerator extends DocumentGenerator {
        @Override
        protected Document createDocument() {
            return new ExcelDocument();
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // El cliente de la aplicación de oficina elige exportar en distintos formatos
        
        DocumentGenerator pdfGenerator = new PdfGenerator();
        pdfGenerator.exportReport("Balance_Financiero_2026");

        DocumentGenerator wordGenerator = new WordGenerator();
        wordGenerator.exportReport("Carta_Despido");

        DocumentGenerator excelGenerator = new ExcelGenerator();
        excelGenerator.exportReport("Nomina_Empleados");
    }
}
