package dosw.semana_3.ejercicios_diapositivas.patrones.creacionales.singleton;

public class GlobalConfigurationSystem {

    // ==========================================
    // 1. LA CLASE SINGLETON (El Gestor de Configuración)
    // ==========================================
    public static class ConfigurationManager {
        
        // 1. La instancia única estática
        private static volatile ConfigurationManager instance;
        
        // Atributos de configuración
        private String language;
        private String theme;
        private String dbHost;
        private String dbUser;

        // 2. Constructor privado: Simula la carga pesada desde un archivo .properties o .json
        private ConfigurationManager() {
            System.out.println("[SISTEMA] >>> Inicializando ConfigurationManager (Cargando datos del disco)...");
            simulateFileLoading();
            
            // Valores "leídos" del archivo
            this.language = "ES-ES";
            this.theme = "DARK_MODE";
            this.dbHost = "127.0.0.1:5432";
            this.dbUser = "admin_db";
            
            System.out.println("[SISTEMA] >>> Configuración cargada exitosamente.\n");
        }

        private void simulateFileLoading() {
            try {
                Thread.sleep(1000); // Tarda 1 segundo en "leer el archivo"
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 3. Método de acceso global
        public static ConfigurationManager getInstance() {
            if (instance == null) {
                synchronized (ConfigurationManager.class) {
                    if (instance == null) {
                        instance = new ConfigurationManager();
                    }
                }
            }
            return instance;
        }

        // Getters para que los módulos lean la configuración
        public String getLanguage() { return language; }
        public String getTheme() { return theme; }
        public String getDbHost() { return dbHost; }
        public String getDbUser() { return dbUser; }

        // Un Setter para demostrar que todos los módulos ven el cambio en tiempo real
        public void setTheme(String theme) {
            this.theme = theme;
            System.out.println("  [CONF] Tema global actualizado a: " + theme);
        }
    }

    // ==========================================
    // 2. SIMULACIÓN DE DISTINTOS MÓDULOS DEL SISTEMA
    // ==========================================
    
    public static class UIRendererModule {
        public void renderUI() {
            // El módulo UI pide la instancia de configuración para saber qué tema usar
            ConfigurationManager config = ConfigurationManager.getInstance();
            System.out.println("UIRenderer: Renderizando interfaz gráfica...");
            System.out.println("UIRenderer: Aplicando tema [" + config.getTheme() + "] y lenguaje [" + config.getLanguage() + "]");
        }
    }
    
    public static class DatabaseConnectorModule {
        public void connect() {
            // El módulo de BD pide la misma instancia para saber dónde conectarse
            ConfigurationManager config = ConfigurationManager.getInstance();
            System.out.println("\nDatabaseConnector: Preparando conexión a BD...");
            System.out.println("DatabaseConnector: Conectando al host [" + config.getDbHost() + "] como [" + config.getDbUser() + "]");
        }
    }

    // ==========================================
    // 3. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println("--- Inicio de la Aplicación ---");
        
        // Módulo 1 (UI) inicia y necesita la configuración
        UIRendererModule uiModule = new UIRendererModule();
        uiModule.renderUI(); // Esto provocará la carga pesada del archivo (1 segundo)
        
        // Módulo 2 (BD) inicia y necesita la configuración
        DatabaseConnectorModule dbModule = new DatabaseConnectorModule();
        dbModule.connect(); // Esto será instantáneo porque la instancia ya existe

        // Simulamos que el usuario cambia el tema desde el menú de opciones
        System.out.println("\n--- Usuario cambiando configuración desde el Menú ---");
        ConfigurationManager configMenu = ConfigurationManager.getInstance();
        configMenu.setTheme("LIGHT_MODE"); // Cambiamos el estado de la única instancia
        
        // Si el renderizador vuelve a pintar, verá el nuevo tema automáticamente 
        // sin que le hayamos pasado ninguna variable directamente.
        System.out.println("\n--- Aplicación refrescando interfaz ---");
        uiModule.renderUI();
    }
}
