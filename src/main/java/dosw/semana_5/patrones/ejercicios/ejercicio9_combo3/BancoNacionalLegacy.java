package dosw.semana_5.patrones.ejercicios.ejercicio9_combo3;

public class BancoNacionalLegacy {
    // API antigua que solo procesa XML
    public void procesarPagoXML(String xmlData) {
        System.out.println("\n[LEGACY API] Conectando con Banco Nacional...");
        System.out.println("Recibiendo payload XML:\n" + xmlData);
        System.out.println("Pago procesado con éxito por el Banco Nacional en sistema Legacy.");
    }
}
