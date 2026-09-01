package dosw.semana_5.patrones.ejercicios.ejercicio9_combo3;

public class VerificarFraude extends FiltroBancario {
    @Override
    public void procesar(String cuenta, double monto, boolean sospechoso) {
        System.out.println("[CoR] Verificando fraude...");
        if (sospechoso) {
            System.out.println("❌ Error: Transacción marcada como fraudulenta. Bloqueando cuenta.");
            return;
        }
        System.out.println("✅ Control antifraude superado.");
        if (siguiente != null) {
            siguiente.procesar(cuenta, monto, sospechoso);
        }
    }
}
