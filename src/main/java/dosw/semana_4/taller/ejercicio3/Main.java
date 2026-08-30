package dosw.semana_4.taller.ejercicio3;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Sistema de Reportes Empresariales ---");

        System.out.println("\nGenerando Reporte PDF:");
        ReportGenerator pdf = ReportFactory.create("PDF");
        pdf.generate();

        System.out.println("\nGenerando Reporte Excel:");
        ReportGenerator excel = ReportFactory.create("Excel");
        excel.generate();
        
        System.out.println("\nGenerando Reporte CSV:");
        ReportGenerator csv = ReportFactory.create("CSV");
        csv.generate();
    }
}
