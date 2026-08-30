package dosw.semana_4.taller.ejercicio9;

public class CredentialValidator extends SecurityValidator {
    @Override
    protected void check(AuthResult result) {
        System.out.println("  [Chain: 1] Verificando credenciales...");
        if (!result.isSuccess()) {
            throw new AccessDeniedException("Credenciales inválidas. Acceso Denegado.");
        }
        System.out.println("  [Chain: 1] Credenciales OK.");
    }
}
