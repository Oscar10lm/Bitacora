package dosw.semana_4.taller.ejercicio9;

public class PermissionValidator extends SecurityValidator {
    @Override
    protected void check(AuthResult result) {
        System.out.println("  [Chain: 2] Verificando permisos del usuario " + result.getUserId() + "...");
        // Simulamos que todos tienen permisos menos el usuario 'guest'
        if ("guest".equals(result.getUserId())) {
            throw new AccessDeniedException("No tiene los permisos requeridos. Acceso Denegado.");
        }
        System.out.println("  [Chain: 2] Permisos OK.");
    }
}
