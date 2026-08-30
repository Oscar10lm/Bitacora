package dosw.semana_4.taller.ejercicio3;

public class CsvReport extends ReportGenerator {
    @Override
    protected void applyFormat() {
        System.out.println("Paso 3: Separando valores por comas (formato CSV)...");
    }

    @Override
    protected void exportFile() {
        System.out.println("Paso 4: Exportando archivo -> reporte.csv");
    }
}
