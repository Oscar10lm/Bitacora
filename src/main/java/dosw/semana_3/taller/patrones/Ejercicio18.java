package dosw.semana_3.taller.patrones;

public class Ejercicio18 {

    // --- IMPLEMENTACIÓN DEL PATRÓN CHAIN OF RESPONSIBILITY ---

    // 1. La clase base para todos los eslabones de la cadena (El Handler)
    public static abstract class SecurityHandler {
        private SecurityHandler nextHandler;

        // Establece quién es el siguiente en la cadena y retorna ese mismo eslabón 
        // para facilitar la construcción (fluent interface)
        public SecurityHandler setNext(SecurityHandler nextHandler) {
            this.nextHandler = nextHandler;
            return nextHandler;
        }

        // Método que invoca al siguiente eslabón si existe
        protected boolean checkNext(String request) {
            if (nextHandler == null) {
                // Si no hay más eslabones, pasó todas las validaciones
                return true; 
            }
            return nextHandler.handle(request);
        }

        // El método que cada filtro concreto debe implementar
        public abstract boolean handle(String request);
    }

    // 2. Filtros Concretos

    public static class AuthenticationHandler extends SecurityHandler {
        @Override
        public boolean handle(String request) {
            System.out.println("-> Validando Autenticación (Credenciales)...");
            if (request.contains("bad_password")) {
                System.out.println("   [X] Error: Contraseña incorrecta. (Cadena detenida)");
                return false; // Corto-circuito
            }
            return checkNext(request);
        }
    }

    public static class RoleHandler extends SecurityHandler {
        @Override
        public boolean handle(String request) {
            System.out.println("-> Validando Roles...");
            if (request.contains("guest")) {
                System.out.println("   [X] Error: El usuario es invitado, no tiene permisos. (Cadena detenida)");
                return false;
            }
            return checkNext(request);
        }
    }

    public static class MfaHandler extends SecurityHandler {
        @Override
        public boolean handle(String request) {
            System.out.println("-> Validando Autenticación Multi-Factor (MFA)...");
            if (!request.contains("mfa_token_ok")) {
                System.out.println("   [X] Error: Falta token MFA. (Cadena detenida)");
                return false;
            }
            return checkNext(request);
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        
        // 3. Ensamblaje de la cadena
        // El orden se puede definir dinámicamente según el entorno
        SecurityHandler authenticationChain = new AuthenticationHandler();
        
        // Conectamos: Auth -> Role -> MFA
        authenticationChain
            .setNext(new RoleHandler())
            .setNext(new MfaHandler());

        System.out.println("--- INTENTO 1 (Contraseña mala) ---");
        boolean result1 = authenticationChain.handle("user_request_bad_password");
        System.out.println("Resultado de acceso: " + (result1 ? "PERMITIDO" : "DENEGADO"));

        System.out.println("\n--- INTENTO 2 (Falta MFA) ---");
        boolean result2 = authenticationChain.handle("user_request_good_password");
        System.out.println("Resultado de acceso: " + (result2 ? "PERMITIDO" : "DENEGADO"));

        System.out.println("\n--- INTENTO 3 (Todo correcto) ---");
        boolean result3 = authenticationChain.handle("user_request_good_password_mfa_token_ok");
        System.out.println("Resultado de acceso: " + (result3 ? "PERMITIDO" : "DENEGADO"));
    }
}
