package dosw.semana_3.taller.solid;

/**
 * -------------------------------------------------------------------
 * #9 Aplicación Bancaria — Interfaz Monolítica
 * -------------------------------------------------------------------
 *
 * (1) SITUACIÓN:
 * Una interfaz gigantesca (`BankUser`) agrupa los métodos de todos los 
 * roles del banco (clientes, gerentes, auditores). Cuando se hace un 
 * cambio para un rol, todos los demás módulos se ven afectados y deben 
 * recompilarse innecesariamente.
 *
 * (2) PRINCIPIO SOLID A APLICAR:
 * ISP - Interface Segregation Principle (Principio de Segregación de Interfaces).
 *
 * (3) JUSTIFICACIÓN TÉCNICA:
 * El principio ISP establece claramente que "ningún cliente debe ser forzado a 
 * depender de métodos que no usa". Al tener una interfaz "gorda" o monolítica, 
 * estamos acoplando módulos completamente independientes entre sí. Los auditores 
 * no tienen por qué sufrir las consecuencias de un cambio en la firma de un 
 * método que solo le interesa a los gerentes. La solución es dividir (segregar) 
 * esa gran interfaz en interfaces más pequeñas y específicas por rol o capacidad.
 *
 * (4) SOLUCIÓN PROPUESTA (Estructura):
 * Eliminar `BankUser` y reemplazarla por interfaces orientadas al cliente 
 * (el que usa la interfaz): `ClientOperations`, `ManagerOperations` y 
 * `AuditOperations`. Aunque haya una clase central que implemente todas, 
 * cada rol del sistema solo consumirá la interfaz que le corresponde.
 */
public class Ejercicio9 {

    // --- ESQUELETO DE SOLUCIÓN BASADO EN ISP ---

    // 1. Interfaces segregadas por rol/necesidad

    public interface ClientOperations {
        void transferMoney();
        void checkBalance();
        // ... otros 8 métodos ...
    }

    public interface ManagerOperations {
        void approveLoan();
        void overrideLimit();
        // ... otros 13 métodos ...
    }

    public interface AuditOperations {
        void generateComplianceReport();
        void inspectTransactionLogs();
        // ... otros 3 métodos ...
    }

    // 2. La clase central implementa lo que necesita proveer,
    //    pero expone diferentes "caras" a cada cliente.
    public static class CoreBankingSystem implements ClientOperations, ManagerOperations, AuditOperations {

        @Override
        public void transferMoney() {
            System.out.println("Cliente: Transfiriendo dinero...");
        }

        @Override
        public void checkBalance() {
            System.out.println("Cliente: Consultando saldo...");
        }

        @Override
        public void approveLoan() {
            System.out.println("Gerente: Aprobando crédito...");
        }

        @Override
        public void overrideLimit() {
            System.out.println("Gerente: Autorizando sobregiro...");
        }

        @Override
        public void generateComplianceReport() {
            System.out.println("Auditor: Generando reporte de cumplimiento...");
        }

        @Override
        public void inspectTransactionLogs() {
            System.out.println("Auditor: Revisando logs transaccionales...");
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        
        CoreBankingSystem core = new CoreBankingSystem();

        // El módulo del cliente solo conoce los 10 métodos que le importan
        ClientOperations clientModule = core; 
        clientModule.checkBalance();
        // clientModule.approveLoan(); // ERROR: El cliente no puede ver ni tocar métodos del gerente

        // El módulo del auditor solo ve lo suyo
        AuditOperations auditModule = core;
        auditModule.generateComplianceReport();
        
        // Si mañana cambia la firma de `approveLoan()` en ManagerOperations,
        // ni el código del cliente ni el del auditor tendrán que ser modificados
        // ni recompilados.
    }
}
