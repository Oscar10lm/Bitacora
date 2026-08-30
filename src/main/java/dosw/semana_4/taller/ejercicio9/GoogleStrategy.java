package dosw.semana_4.taller.ejercicio9;

public class GoogleStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("  [Strategy] Autenticando vía Google OAuth2...");
        return new AuthResult(true, c.getUserId());
    }
}
