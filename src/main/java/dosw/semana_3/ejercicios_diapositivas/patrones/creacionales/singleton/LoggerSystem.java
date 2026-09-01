package dosw.semana_3.ejercicios_diapositivas.patrones.creacionales.singleton;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerSystem {

    // ==========================================
    // 1. LA CLASE SINGLETON (El Logger)
    // ==========================================
    public static class Logger {
        
        // 1. Variable estática privada para almacenar la única instancia
        // Usamos 'volatile' como buena práctica para escenarios Multi-hilo en Java
        private static volatile Logger instance;
        
        private final DateTimeFormatter formatter;
        private int logCount; // Estado interno compartido

        // 2. Constructor privado: Nadie fuera de esta clase puede usar 'new Logger()'
        private Logger() {
            // Inicializamos la configuración del Logger
            this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            this.logCount = 0;
            System.out.println("[SISTEMA] >>> Instancia Única de Logger inicializada.\n");
        }

        // 3. Método estático de acceso global (Patrón Double-Checked Locking para Multi-hilo)
        public static Logger getInstance() {
            if (instance == null) {
                // Sincronizamos solo la primera vez para no afectar el rendimiento después
                synchronized (Logger.class) {
                    if (instance == null) {
                        instance = new Logger();
                    }
                }
            }
            return instance;
        }

        // Métodos de negocio de la clase
        public void info(String message) {
            log("INFO", message);
        }

        public void error(String message) {
            log("ERROR", message);
        }
        
        public void warning(String message) {
            log("WARN", message);
        }

        private void log(String level, String message) {
            logCount++;
            String timestamp = LocalDateTime.now().format(formatter);
            System.out.printf("[%s] [%s] (#%d) - %s\n", timestamp, level, logCount, message);
        }
    }

    // ==========================================
    // 2. SIMULACIÓN DE DISTINTOS MÓDULOS DEL SISTEMA
    // ==========================================
    
    public static class DatabaseModule {
        public void connect() {
            // El módulo de base de datos pide la instancia del Logger
            Logger log = Logger.getInstance();
            log.info("Módulo de BD iniciando conexión...");
            log.info("Conexión a base de datos establecida con éxito.");
        }
    }
    
    public static class PaymentModule {
        public void processPayment() {
            // El módulo de pagos pide la misma instancia
            Logger log = Logger.getInstance();
            log.info("Iniciando procesamiento de pago...");
            log.error("Fallo de conexión con la pasarela del banco.");
        }
    }

    // ==========================================
    // 3. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println("--- Inicio de la Aplicación ---");
        
        // No se puede hacer: Logger myLog = new Logger(); (Error de compilación)
        
        // Llamada desde el Main
        Logger mainLogger = Logger.getInstance();
        mainLogger.info("Aplicación iniciada.");

        // Simulamos actividad en otros módulos
        DatabaseModule db = new DatabaseModule();
        db.connect();
        
        PaymentModule pay = new PaymentModule();
        pay.processPayment();
        
        // Verificamos que todos usaron exactamente la misma instancia
        Logger testLogger = Logger.getInstance();
        testLogger.info("Cerrando aplicación...");
        
        // Comprobación de identidad en memoria
        System.out.println("\n--- Verificación de Instancia Única ---");
        if (mainLogger == testLogger) {
            System.out.println("ÉXITO: mainLogger y testLogger apuntan exactamente al mismo objeto en memoria.");
        }
    }
}
