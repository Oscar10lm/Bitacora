package dosw.semana_5.patrones.ejercicios.ejercicio9_combo3;

public class ProcesarPago extends FiltroBancario {
    private BancoNacionalAdapter adapter;

    public ProcesarPago() {
        this.adapter = new BancoNacionalAdapter();
    }

    @Override
    public void procesar(String cuenta, double monto, boolean sospechoso) {
        System.out.println("[CoR] Iniciando procesamiento final de pago...");
        adapter.procesarPagoModerno(cuenta, monto);
    }
}
