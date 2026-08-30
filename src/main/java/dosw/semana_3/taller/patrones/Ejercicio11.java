package dosw.semana_3.taller.patrones;

/**
 * -------------------------------------------------------------------
 * PARTE III — IDENTIFICANDO PATRONES DE DISEÑO
 * #11 El Único Administrador
 * -------------------------------------------------------------------
 *
 * (1) SITUACIÓN:
 * La aplicación necesita gestionar la configuración global del sistema con 
 * un único objeto compartido por toda la aplicación. Múltiples instancias 
 * crearían inconsistencias.
 *
 * (2) CATEGORÍA:
 * Patrón Creacional (Creational Pattern).
 *
 * (3) PATRÓN SELECCIONADO:
 * Singleton.
 *
 * (4) ¿POR QUÉ?:
 * El propósito fundamental del patrón Singleton es garantizar que una clase 
 * tenga una, y solo una, instancia en todo el ciclo de vida de la aplicación, 
 * y proporcionar un punto de acceso global a ella. Esto encaja perfectamente 
 * con la necesidad de un "único objeto compartido" para configuraciones globales.
 *
 * (5) ¿APLICA MÁS DE UN PATRÓN? (Alternativa Moderna):
 * Sí. En el desarrollo moderno (con frameworks como Spring o Jakarta EE), 
 * rara vez se escribe un Singleton clásico a mano. En su lugar, se utiliza:
 * 
 * - **Inyección de Dependencias (DI)** configurada con alcance "Singleton" 
 *   (Singleton Scope).
 * 
 * ¿Por qué es mejor usar Inyección de Dependencias en lugar del Singleton clásico?
 * El Singleton clásico (implementado con `static`) introduce un acoplamiento 
 * global muy fuerte en el código, lo que dificulta enormemente las pruebas 
 * unitarias (mocking) y viola principios SOLID (como DIP). Al usar DI, delegamos 
 * la responsabilidad de crear y mantener esa única instancia al contenedor 
 * (framework), manteniendo nuestras clases limpias y fáciles de testear.
 */
public class Ejercicio11 {

    // --- IMPLEMENTACIÓN DEL PATRÓN SINGLETON (Clásico Thread-Safe) ---

    public static class GlobalConfiguration {
        
        // 1. Variable estática privada que almacena la única instancia
        private static GlobalConfiguration instance;
        
        // Propiedad de ejemplo
        private String dbUrl;

        // 2. Constructor privado para evitar instanciación externa con 'new'
        private GlobalConfiguration() {
            // Valores por defecto
            this.dbUrl = "jdbc:mysql://localhost:3306/mydb";
        }

        // 3. Método estático público que devuelve la instancia (haciendo Lazy Initialization)
        // Se usa 'synchronized' para evitar que 2 hilos creen instancias distintas al mismo tiempo
        public static synchronized GlobalConfiguration getInstance() {
            if (instance == null) {
                instance = new GlobalConfiguration();
            }
            return instance;
        }

        // Métodos de negocio
        public String getDbUrl() {
            return dbUrl;
        }

        public void setDbUrl(String dbUrl) {
            this.dbUrl = dbUrl;
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        
        // No se puede hacer: new GlobalConfiguration();
        
        GlobalConfiguration config1 = GlobalConfiguration.getInstance();
        System.out.println("URL original: " + config1.getDbUrl());
        
        // Alguien cambia la configuración en otra parte del sistema
        GlobalConfiguration config2 = GlobalConfiguration.getInstance();
        config2.setDbUrl("jdbc:postgresql://remote:5432/proddb");
        
        // Comprobamos que config1 refleja el cambio, demostrando que son el MISMO objeto
        System.out.println("URL tras cambio: " + config1.getDbUrl());
        
        // Comprobación de identidad
        if (config1 == config2) {
            System.out.println("Ambas variables apuntan a la misma y única instancia en memoria.");
        }
    }
}
