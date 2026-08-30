package dosw.semana_4.taller.ejercicio7;

public class ApprovedState implements DocumentState {
    @Override
    public void approve(Document doc) {
        System.out.println("  [State] El documento ya se encuentra Aprobado.");
    }

    @Override
    public void reject(Document doc) {
        System.out.println("  [State] No se puede rechazar, el documento ya fue Aprobado definitivamente.");
    }
}
