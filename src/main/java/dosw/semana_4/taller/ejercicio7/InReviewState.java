package dosw.semana_4.taller.ejercicio7;

public class InReviewState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("  [State] Todas las revisiones exitosas. Documento 'Aprobado'.");
        doc.setState(new ApprovedState());
    }

    @Override
    public void reject(Document doc) {
        System.out.println("  [State] Documento rechazado durante la revisión. Pasando a 'Rechazado'.");
        doc.setState(new RejectedState());
    }
}
