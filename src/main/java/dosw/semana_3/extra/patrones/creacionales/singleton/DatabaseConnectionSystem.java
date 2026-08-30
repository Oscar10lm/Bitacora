package dosw.semana_3.extra.patrones.creacionales.singleton;

/**
 * ============================================================================
 * EJERCICIO PRÁCTICO: GESTOR DE CONEXIÓN A BASE DE DATOS (Singleton)
 * ============================================================================
 *
 * Una aplicación necesita conectarse a una base de datos. Crear una conexión 
 * es costoso (tiempo de red, autenticación, recursos en el motor de BD).
 * 
 * Abrir múltiples conexiones innecesarias agotaría el "pool" de conexiones 
 * del servidor de base de datos. El sistema debe garantizar que exista una 
 * única conexión compartida (el canal físico) que sea reutilizada por todas 
 * las partes del programa (Servicio de Usuarios, Servicio de Productos, etc.) 
 * que necesiten ejecutar consultas.
 */
public class DatabaseConnectionSystem {

    // ==========================================
    // 1. LA CLASE SINGLETON (El Gestor de Conexión)
    // ==========================================
    public static class DatabaseConnection {
        
        // 1. Instancia estática única y protegida para multihilos
        private static volatile DatabaseConnection instance;
        
        // Estado de la conexión (Simulado)
        private boolean isConnected;
        private String connectionId;

        // 2. Constructor privado: Oculto para evitar múltiples conexiones
        private DatabaseConnection() {
            System.out.println("[DB] >>> Iniciando protocolo de Handshake con el servidor...");
            simulateCostlyConnection(); // Retardo por red y validación
            
            this.isConnected = true;
            // Generamos un ID aleatorio para demostrar que siempre es la misma conexión
            this.connectionId = "DB-CONN-" + (int)(Math.random() * 10000); 
            
            System.out.println("[DB] >>> ¡Conexión establecida exitosamente! ID: " + this.connectionId + "\n");
        }

        private void simulateCostlyConnection() {
            try {
                // Simula latencia de red, validación de credenciales y asignación de buffer
                Thread.sleep(1200); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 3. Método estático global de creación controlada (Double-Checked Locking)
        public static DatabaseConnection getInstance() {
            if (instance == null) {
                synchronized (DatabaseConnection.class) {
                    if (instance == null) {
                        instance = new DatabaseConnection();
                    }
                }
            }
            return instance;
        }

        // Método de negocio principal: Ejecutar consultas SQL
        public void executeQuery(String sql) {
            if (isConnected) {
                System.out.println("  [Ejecutando en " + connectionId + "] -> " + sql);
            } else {
                System.out.println("  [Error] No hay conexión activa para ejecutar la consulta.");
            }
        }

        // Método para cerrar la conexión limpiamente al apagar el servidor
        public void disconnect() {
            if (isConnected) {
                System.out.println("[DB] Cerrando la conexión " + connectionId + " de forma segura...");
                isConnected = false;
                instance = null; // Permite crear una nueva si se vuelve a iniciar
            }
        }
    }

    // ==========================================
    // 2. SIMULACIÓN DE DISTINTOS SERVICIOS DE LA APLICACIÓN
    // ==========================================
    
    // Servicio que maneja la lógica de usuarios
    public static class UserService {
        public void fetchUserProfile(int userId) {
            System.out.println("UserService: Solicitando perfil del usuario " + userId);
            // El servicio pide la conexión global y la usa
            DatabaseConnection db = DatabaseConnection.getInstance();
            db.executeQuery("SELECT * FROM users WHERE id = " + userId);
        }
    }
    
    // Servicio que maneja el inventario
    public static class InventoryService {
        public void reduceStock(String productCode, int quantity) {
            System.out.println("\nInventoryService: Actualizando inventario para " + productCode);
            // El servicio pide la conexión global y la usa
            DatabaseConnection db = DatabaseConnection.getInstance();
            db.executeQuery("UPDATE products SET stock = stock - " + quantity + " WHERE code = '" + productCode + "'");
        }
    }

    // ==========================================
    // 3. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println("--- Servidor de Aplicación Iniciado ---\n");
        
        // 1. Un usuario entra a su perfil (Dispara la primera conexión lenta)
        UserService userService = new UserService();
        userService.fetchUserProfile(405);
        
        // 2. El usuario compra un producto (Reutiliza la conexión al instante)
        InventoryService inventoryService = new InventoryService();
        inventoryService.reduceStock("LAPTOP-X1", 1);
        
        // 3. Otro usuario se loguea (Sigue reutilizando la conexión al instante)
        System.out.println();
        userService.fetchUserProfile(992);
        
        // 4. Apagado del sistema
        System.out.println("\n--- Apagando Servidor ---");
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.disconnect();
    }
}
