package dosw.semana_3.extra.patrones.comportamiento.template_method;

public class ReportGeneratorTemplateSystem {

    // ==========================================
    // 1. LA CLASE ABSTRACTA (El Esqueleto)
    // ==========================================
    public static abstract class ReportGenerator {
        
        // El algoritmo orquestador, inmutable
        public final void generateReport(String query) {
            System.out.println("=== Iniciando Pipeline de Reporte ===");
            String rawData = fetchRawData(query);
            String formattedData = applyFormat(rawData);
            saveFile(formattedData);
            notifyUser();
            System.out.println("=== Proceso Finalizado ===\n");
        }

        // Pasos Estándar
        private String fetchRawData(String query) {
            System.out.println("1. [DB]: Ejecutando consulta '" + query + "' y extrayendo datos brutos...");
            return "DATASET_ROW_1, DATASET_ROW_2";
        }

        private void saveFile(String formattedData) {
            System.out.println("3. [IO]: Escribiendo bytes en el almacenamiento local...");
        }

        private void notifyUser() {
            System.out.println("4. [MAIL]: Enviando notificación de finalización al correo del usuario.");
        }

        // Paso Variable
        protected abstract String applyFormat(String rawData);
    }

    // ==========================================
    // 2. SUBCLASES CONCRETAS
    // ==========================================
    
    // Generador de PDF
    public static class PdfReportGenerator extends ReportGenerator {
        @Override
        protected String applyFormat(String rawData) {
            System.out.println("2. [FORMATO]: Convirtiendo datos a binario PDF, aplicando logos y fuentes Arial...");
            return "[PDF_BYTES]";
        }
    }

    // Generador de Excel
    public static class ExcelReportGenerator extends ReportGenerator {
        @Override
        protected String applyFormat(String rawData) {
            System.out.println("2. [FORMATO]: Estructurando celdas, generando hojas .xlsx y macros...");
            return "[EXCEL_BYTES]";
        }
    }

    // ==========================================
    // 3. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> SISTEMA CENTRAL DE REPORTES (ERP) <<<\n");

        System.out.println("--- El gerente solicita el reporte anual en PDF ---");
        ReportGenerator pdfReport = new PdfReportGenerator();
        pdfReport.generateReport("SELECT * FROM Ventas2026");

        System.out.println("--- El contador solicita el reporte de inventario en EXCEL ---");
        ReportGenerator excelReport = new ExcelReportGenerator();
        excelReport.generateReport("SELECT * FROM InventarioGeneral");
    }
}
