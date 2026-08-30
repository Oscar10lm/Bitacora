package dosw.semana_4.taller.ejercicio9;

public class AuthService {
    private AuthStrategy strategy;

    public void setStrategy(AuthStrategy strategy) {
        this.strategy = strategy;
    }

    public AuthResult authenticate(Credentials credentials) {
        if (strategy == null) {
            throw new IllegalStateException("AuthStrategy no configurada");
        }
        return strategy.authenticate(credentials);
    }
}
