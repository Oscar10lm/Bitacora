package dosw.semana_4.taller.ejercicio7;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Flujo de Aprobación de Documentos ---");

        // 1. Configuramos la cadena de responsabilidad
        DocumentHandler lider = new LiderHandler();
        DocumentHandler juridico = new JuridicoHandler();
        DocumentHandler financiero = new FinancieroHandler();

        // Enlace: Líder -> Jurídico -> Financiero
        lider.setNext(juridico);
        juridico.setNext(financiero);

        // 2. Procesamos un Documento Técnico (solo le importa al líder)
        System.out.println("\nEnviando Documento 'Guía Técnica' (Tipo: Técnico)");
        Document docTecnico = new Document("Manual de instalación", "Tecnico");
        
        // Pasa de Borrador a 'En Revisión'
        docTecnico.approve(); 
        // Se lanza a la cadena
        lider.handle(docTecnico);


        // 3. Procesamos un Contrato (le importa al líder, jurídico y financiero)
        System.out.println("\nEnviando Documento 'Acuerdo NDA' (Tipo: Contrato)");
        Document docContrato = new Document("No divulgación de secretos", "Contrato");
        
        docContrato.approve(); // Borrador -> En Revisión
        lider.handle(docContrato);
    }
}
