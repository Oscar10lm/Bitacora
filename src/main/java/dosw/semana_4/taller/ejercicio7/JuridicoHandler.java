package dosw.semana_4.taller.ejercicio7;

public class JuridicoHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(Document doc) {
        // Solo revisa documentos legales o contratos
        return doc.getType().equalsIgnoreCase("Contrato");
    }

    @Override
    protected void process(Document doc) {
        System.out.println("  [Jurídico] Revisando cláusulas legales del contrato...");
        doc.approve();
        super.handle(doc);
    }
}
