package dosw.semana_4.taller.ejercicio7;

public class DraftState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("  [State] El borrador está listo. Pasando a 'En Revisión'.");
        doc.setState(new InReviewState());
    }

    @Override
    public void reject(Document doc) {
        System.out.println("  [State] El borrador ha sido descartado. Pasando a 'Rechazado'.");
        doc.setState(new RejectedState());
    }
}
