package dosw.semana_5.patrones.ejercicios.ejercicio9_combo3;

public class VerificarSaldo extends FiltroBancario {
    private double saldoDisponible = 5000.0;

    @Override
    public void procesar(String cuenta, double monto, boolean sospechoso) {
        System.out.println("[CoR] Verificando saldo...");
        if (monto > saldoDisponible) {
            System.out.println("❌ Error: Saldo insuficiente. Transacción rechazada.");
            return;
        }
        System.out.println("✅ Saldo verificado.");
        if (siguiente != null) {
            siguiente.procesar(cuenta, monto, sospechoso);
        }
    }
}
