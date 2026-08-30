package dosw.semana_3.taller.patrones;

/**
 * -------------------------------------------------------------------
 * PARTE III — IDENTIFICANDO PATRONES DE DISEÑO
 * #18 Sistema de Seguridad — Validaciones en Cadena
 * -------------------------------------------------------------------
 *
 * (1) SITUACIÓN:
 * Una solicitud pasa por múltiples validaciones (autenticación, roles, 
 * geografía, etc.). La cantidad y tipo de validaciones puede variar según 
 * el entorno (ej. agregar MFA). Cada validación evalúa la solicitud y decide 
 * si la rechaza inmediatamente o la pasa al siguiente filtro.
 *
 * (2) CATEGORÍA:
 * Patrón de Comportamiento (Behavioral Pattern).
 *
 * (3) PATRÓN SELECCIONADO:
 * Chain of Responsibility (Cadena de Responsabilidad).
 *
 * (4) ¿POR QUÉ?:
 * El patrón Cadena de Responsabilidad permite pasar solicitudes a lo largo 
 * de una cadena de manejadores (handlers). Al recibir una solicitud, cada 
 * manejador decide si la procesa (ej. rechazando el acceso) o si la pasa al 
 * siguiente eslabón de la cadena. Es perfecto aquí porque:
 * 1. Evita anidar un montón de bloques `if` en el código cliente.
 * 2. Permite ensamblar o modificar dinámicamente el orden o cantidad de 
 *    los filtros (ej. añadir MFA en producción pero no en desarrollo) en 
 *    tiempo de ejecución.
 *
 * (5) ¿APLICA MÁS DE UN PATRÓN? (Comparación):
 * - Se podría confundir con **Decorator** (Decorador), que también envuelve 
 *   objetos de forma recursiva. Sin embargo, la diferencia conceptual clave es:
 *   * Decorator busca *añadir comportamiento* a una petición sin detener la ejecución.
 *   * Chain of Responsibility está diseñado específicamente para tener la 
 *     autoridad de *detener el flujo* (corto-circuito). Si el filtro de 
 *     autenticación falla, la cadena se rompe y jamás llega a evaluar el rol.
 */
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
