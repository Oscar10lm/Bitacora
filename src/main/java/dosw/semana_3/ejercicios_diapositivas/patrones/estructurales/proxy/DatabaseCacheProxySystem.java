package dosw.semana_3.ejercicios_diapositivas.patrones.estructurales.proxy;

import java.util.HashMap;
import java.util.Map;

public class DatabaseCacheProxySystem {

    // ==========================================
    // 1. LA INTERFAZ SUJETO
    // ==========================================
    public interface DatabaseService {
        String queryReport(String sqlQuery);
    }

    // ==========================================
    // 2. EL SUJETO REAL (La Base de Datos Lenta)
    // ==========================================
    public static class RealDatabaseService implements DatabaseService {
        @Override
        public String queryReport(String sqlQuery) {
            System.out.println("  [MySQL Real]: Conectando al servidor...");
            System.out.println("  [MySQL Real]: Ejecutando query pesada ('" + sqlQuery + "')...");
            try {
                // Simulamos una consulta que tarda 2 segundos
                Thread.sleep(2000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("  [MySQL Real]: ¡Consulta finalizada con éxito!");
            return "RESULTADO_FILTRADO_DE_DATOS_ANUALES";
        }
    }

    // ==========================================
    // 3. EL PROXY (El Gestor de Caché)
    // ==========================================
    public static class CacheDatabaseProxy implements DatabaseService {
        private RealDatabaseService realDatabase;
        private Map<String, String> cacheMemory; // El diccionario del Caché

        public CacheDatabaseProxy() {
            this.cacheMemory = new HashMap<>();
        }

        @Override
        public String queryReport(String sqlQuery) {
            System.out.println("  [Proxy Caché]: Interceptando solicitud '" + sqlQuery + "'");

            // Si la consulta ya está en caché, retornarla inmediatamente
            if (cacheMemory.containsKey(sqlQuery)) {
                System.out.println("  [Proxy Caché]: ¡HIT! Devolviendo resultado instantáneo desde la memoria Caché.");
                return cacheMemory.get(sqlQuery);
            }

            // Si no está, instanciamos (si es necesario) y llamamos al DB Real
            System.out.println("  [Proxy Caché]: MISS. El resultado no existe. Delegando al motor de DB...");
            if (realDatabase == null) {
                realDatabase = new RealDatabaseService();
            }
            
            String result = realDatabase.queryReport(sqlQuery);
            
            // Guardamos el resultado en la memoria caché para el futuro
            cacheMemory.put(sqlQuery, result);
            
            return result;
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        System.out.println(">>> SISTEMA DE REPORTES CONTABLES (Cache Proxy) <<<\n");

        // El cliente (API) usa el Proxy sin saber que está envuelto en un caché
        DatabaseService dbService = new CacheDatabaseProxy();

        System.out.println("--- Petición 1: Reporte de Enero (Nunca antes consultado) ---");
        // El proxy hará un MISS y tardará 2 segundos llamando al Real
        long startTime = System.currentTimeMillis();
        String result1 = dbService.queryReport("SELECT * FROM ventas WHERE mes = 'enero'");
        System.out.println("-> Recibido: " + result1 + " (Tiempo: " + (System.currentTimeMillis() - startTime) + "ms)\n");

        System.out.println("--- Petición 2: Reporte de Febrero (Nunca antes consultado) ---");
        // El proxy hará un MISS y tardará 2 segundos
        startTime = System.currentTimeMillis();
        String result2 = dbService.queryReport("SELECT * FROM ventas WHERE mes = 'febrero'");
        System.out.println("-> Recibido: " + result2 + " (Tiempo: " + (System.currentTimeMillis() - startTime) + "ms)\n");

        System.out.println("--- Petición 3: Reporte de Enero OTRA VEZ ---");
        // ¡Magia! El proxy intercepta y devuelve el resultado instantáneo (0ms)
        startTime = System.currentTimeMillis();
        String result3 = dbService.queryReport("SELECT * FROM ventas WHERE mes = 'enero'");
        System.out.println("-> Recibido: " + result3 + " (Tiempo: " + (System.currentTimeMillis() - startTime) + "ms)\n");
    }
}
