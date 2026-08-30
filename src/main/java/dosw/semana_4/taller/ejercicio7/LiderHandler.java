package dosw.semana_4.taller.ejercicio7;

public class LiderHandler extends DocumentHandler {
    @Override
    protected boolean canHandle(Document doc) {
        // El líder revisa todos los documentos
        return true;
    }

    @Override
    protected void process(Document doc) {
        System.out.println("  [Líder] Revisando contenido técnico del documento...");
        // Simulamos aprobación
        doc.approve();
        
        // Pasa al siguiente en la cadena tras procesar su parte
        // (En un caso real, podría llamar a next.handle(doc) solo si aprueba)
        super.handle(doc); 
    }
}
