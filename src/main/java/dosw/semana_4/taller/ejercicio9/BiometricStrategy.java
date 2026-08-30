package dosw.semana_4.taller.ejercicio9;

public class BiometricStrategy implements AuthStrategy {
    @Override
    public AuthResult authenticate(Credentials c) {
        System.out.println("  [Strategy] Autenticando vía Escáner Biométrico (Huella/Rostro)...");
        return new AuthResult(true, c.getUserId());
    }
}
