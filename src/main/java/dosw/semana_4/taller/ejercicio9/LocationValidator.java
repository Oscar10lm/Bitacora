package dosw.semana_4.taller.ejercicio9;

public class LocationValidator extends SecurityValidator {
    @Override
    protected void check(AuthResult result) {
        System.out.println("  [Chain: 3] Verificando ubicación (IP/Geo)...");
        // Simulamos que la ubicación está OK
        System.out.println("  [Chain: 3] Ubicación OK.");
    }
}
