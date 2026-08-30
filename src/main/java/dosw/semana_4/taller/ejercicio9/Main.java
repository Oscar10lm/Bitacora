package dosw.semana_4.taller.ejercicio9;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Sistema de Autenticación Empresarial ---");

        // 1. Configurar la Cadena de Responsabilidad (Autorización)
        SecurityValidator credentialValidator = new CredentialValidator();
        SecurityValidator permissionValidator = new PermissionValidator();
        SecurityValidator locationValidator = new LocationValidator();

        // cred -> perm -> loc
        credentialValidator.setNext(permissionValidator).setNext(locationValidator);

        // 2. Crear servicio de autenticación
        AuthService authService = new AuthService();

        // Caso A: Empleado usando huella
        System.out.println("\n[Intento A] Empleado con Biometría:");
        authService.setStrategy(new BiometricStrategy());
        AuthResult resultA = authService.authenticate(new Credentials("emp_001", "huella123"));
        
        try {
            credentialValidator.validate(resultA);
            System.out.println("--> Acceso Concedido al sistema.");
        } catch (AccessDeniedException e) {
            System.out.println("--> " + e.getMessage());
        }

        // Caso B: Invitado usando contraseña
        System.out.println("\n[Intento B] Invitado con Usuario/Contraseña:");
        authService.setStrategy(new PasswordStrategy());
        AuthResult resultB = authService.authenticate(new Credentials("guest", "1234"));
        
        try {
            credentialValidator.validate(resultB);
            System.out.println("--> Acceso Concedido al sistema.");
        } catch (AccessDeniedException e) {
            System.out.println("--> " + e.getMessage());
        }
    }
}
