package dosw.semana_4.taller.ejercicio9;

public abstract class SecurityValidator {
    private SecurityValidator next;

    public SecurityValidator setNext(SecurityValidator next) {
        this.next = next;
        return next;
    }

    public void validate(AuthResult result) {
        // Ejecuta la validación propia de este eslabón
        check(result);
        
        // Si no lanza excepción y hay siguiente, avanza en la cadena
        if (next != null) {
            next.validate(result);
        }
    }

    protected abstract void check(AuthResult result);
}
