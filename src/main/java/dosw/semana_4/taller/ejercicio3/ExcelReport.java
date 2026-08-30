package dosw.semana_4.taller.ejercicio3;

public class ExcelReport extends ReportGenerator {
    @Override
    protected void applyFormat() {
        System.out.println("Paso 3: Aplicando formato de celdas y fórmulas para Excel...");
    }

    @Override
    protected void exportFile() {
        System.out.println("Paso 4: Exportando archivo -> reporte.xlsx");
    }
}
