package dosw.semana_3.taller.patrones;

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
