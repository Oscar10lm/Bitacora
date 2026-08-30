package dosw.semana_4.taller.ejercicio7;

public class RejectedState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("  [State] No se puede aprobar, el documento fue Rechazado definitivamente.");
    }

    @Override
    public void reject(Document doc) {
        System.out.println("  [State] El documento ya se encuentra Rechazado.");
    }
}
