package dosw.semana_4.taller.ejercicio9;

public class PasswordStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("  [Strategy] Autenticando vía Usuario/Contraseña...");
        return new AuthResult(true, c.getUserId());
    }
}
