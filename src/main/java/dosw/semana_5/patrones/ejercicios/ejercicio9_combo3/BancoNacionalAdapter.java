package dosw.semana_5.patrones.ejercicios.ejercicio9_combo3;

public class BancoNacionalAdapter {
    private BancoNacionalLegacy bancoLegacy;

    public BancoNacionalAdapter() {
        this.bancoLegacy = new BancoNacionalLegacy();
    }

    public void procesarPagoModerno(String cuenta, double monto) {
        // El adaptador convierte los datos simples a un formato XML que el banco viejo entiende
        System.out.println("[Adapter] Convirtiendo datos modernos a XML Legacy...");
        String xml = "<Transaccion>\n" +
                     "  <Cuenta>" + cuenta + "</Cuenta>\n" +
                     "  <Monto>" + monto + "</Monto>\n" +
                     "</Transaccion>";
        
        bancoLegacy.procesarPagoXML(xml);
    }
}
