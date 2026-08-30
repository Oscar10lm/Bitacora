package dosw.semana_4.taller.ejercicio3;

public abstract class ReportGenerator {

    // Template Method
    public final void generate() {
        fetchData();
        processData();
        applyFormat();
        exportFile();
    }

    private void fetchData() {
        System.out.println("Paso 1: Obteniendo datos de la base de datos...");
    }

    private void processData() {
        System.out.println("Paso 2: Procesando la información (agrupando y sumando totales)...");
    }

    protected abstract void applyFormat();
    
    protected abstract void exportFile();
}
