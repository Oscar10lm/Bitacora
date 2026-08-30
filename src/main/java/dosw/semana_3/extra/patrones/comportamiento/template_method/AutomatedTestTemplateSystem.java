package dosw.semana_3.extra.patrones.comportamiento.template_method;

/**
 * ============================================================================
 * EJERCICIO PRÁCTICO T3: PRUEBAS AUTOMATIZADAS (Template Method)
 * ============================================================================
 *
 * Ejecutar una prueba automatizada (Testing) sigue el mismo esqueleto:
 * 1. Preparar el entorno (Setup)
 * 2. Ejecutar prueba (Run/Assert - El paso que varía fuertemente)
 * 3. Verificar resultado (Comprobación de métricas base)
 * 4. Limpiar entorno (Teardown)
 */
public class AutomatedTestTemplateSystem {

    // ==========================================
    // 1. LA CLASE ABSTRACTA (El Framework de Pruebas)
    // ==========================================
    public static abstract class AutomatedTest {
        
        // El Template Method
        public final void runTestPipeline(String testName) {
            System.out.println("--- Ejecutando Test: " + testName + " ---");
            setupEnvironment();
            boolean passed = executeTestLogic();
            verifyBaseMetrics(passed);
            teardownEnvironment();
            System.out.println("----------------------------------------\n");
        }

        // Pasos Comunes / Esqueleto del Ciclo de Vida
        private void setupEnvironment() {
            System.out.println("[SETUP]: Cargando variables de entorno e inicializando dependencias de prueba...");
        }

        private void verifyBaseMetrics(boolean passed) {
            System.out.println("[VERIFY]: " + (passed ? "ASSERTION SUCCESS (verde \u2705)" : "ASSERTION FAILED (rojo \u274C)"));
        }

        private void teardownEnvironment() {
            System.out.println("[TEARDOWN]: Limpiando memoria, cerrando mocks y borrando bases de datos temporales...");
        }

        // Paso Abstracto Específico (El Test real)
        protected abstract boolean executeTestLogic();
    }

    // ==========================================
    // 2. SUBCLASES CONCRETAS
    // ==========================================
    
    // Prueba Unitaria (Rápida y aislada)
    public static class UnitTest extends AutomatedTest {
        @Override
        protected boolean executeTestLogic() {
            System.out.println("[RUN]: Ejecutando prueba unitaria sobre 'CalculadoraService.sumar()'. Aislado en memoria.");
            // Simulamos que la prueba pasa
            return true;
        }
    }

    // Prueba de Integración (Más pesada, toca base de datos/APIs)
    public static class IntegrationTest extends AutomatedTest {
        @Override
        protected boolean executeTestLogic() {
            System.out.println("[RUN]: Realizando petición HTTP falsa y comprobando escritura en Base de Datos MySQL...");
            // Simulamos que la prueba falla por un error de conexión
            return false; 
        }
    }

    // ==========================================
    // 3. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> PIPELINE DE CI/CD (GitHub Actions) <<<\n");

        // 1. Jenkins/GitHub ejecuta una prueba unitaria
        AutomatedTest unitTest = new UnitTest();
        unitTest.runTestPipeline("testSumarValoresPositivos");

        // 2. Jenkins/GitHub ejecuta una prueba de integración pesada
        AutomatedTest integrationTest = new IntegrationTest();
        integrationTest.runTestPipeline("testRegistroUsuarioEnBaseDeDatos");
    }
}
