package dosw.semana_4.taller.ejercicio3;

public class PdfReport extends ReportGenerator {
    @Override
    protected void applyFormat() {
        System.out.println("Paso 3: Aplicando formato visual PDF con tablas y logos...");
    }

    @Override
    protected void exportFile() {
        System.out.println("Paso 4: Exportando archivo -> reporte.pdf");
    }
}
