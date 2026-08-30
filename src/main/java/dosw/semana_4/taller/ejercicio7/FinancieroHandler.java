package dosw.semana_4.taller.ejercicio7;

public class FinancieroHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(Document doc) {
        // Revisa presupuestos o contratos que impliquen dinero
        return doc.getType().equalsIgnoreCase("Presupuesto") || doc.getType().equalsIgnoreCase("Contrato");
    }

    @Override
    protected void process(Document doc) {
        System.out.println("  [Financiero] Verificando viabilidad económica...");
        doc.approve();
        super.handle(doc);
    }
}
