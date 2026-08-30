package dosw.semana_4.taller.ejercicio3;

public class ReportFactory {
    public static ReportGenerator create(String type) {
        if ("PDF".equalsIgnoreCase(type)) {
            return new PdfReport();
        } else if ("Excel".equalsIgnoreCase(type)) {
            return new ExcelReport();
        } else if ("CSV".equalsIgnoreCase(type)) {
            return new CsvReport();
        }
        throw new IllegalArgumentException("Tipo de reporte no soportado: " + type);
    }
}
