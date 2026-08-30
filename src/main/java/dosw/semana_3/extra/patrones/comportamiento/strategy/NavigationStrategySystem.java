package dosw.semana_3.extra.patrones.comportamiento.strategy;

/**
 * ============================================================================
 * PATRÓN DE DISEÑO: STRATEGY (COMPORTAMIENTO)
 * ============================================================================
 *
 * DEFINICIÓN:
 * Es un patrón de diseño de comportamiento que te permite definir una familia 
 * de algoritmos, colocar cada uno de ellos en una clase separada y hacer sus 
 * objetos intercambiables.
 *
 * VENTAJAS CLAVE:
 * - Puedes cambiar de un algoritmo a otro durante el tiempo de ejecución.
 * - Aísla los detalles de implementación de un algoritmo del código que lo usa.
 * - Reemplaza la herencia (o los horribles bloques gigantes de if/switch) 
 *   por composición.
 * - Principio de Abierto/Cerrado: puedes introducir nuevas estrategias sin 
 *   cambiar el contexto.
 *
 * ----------------------------------------------------------------------------
 * EJERCICIO PRÁCTICO: APLICACIÓN DE NAVEGACIÓN (GPS)
 * ----------------------------------------------------------------------------
 * Una aplicación de navegación puede calcular rutas de distintas maneras.
 * El algoritmo de cálculo de ruta no debe estar acoplado a la aplicación, 
 * ya que puede cambiar según la preferencia del usuario en tiempo real:
 * - Ruta más Rápida (Prioriza autopistas)
 * - Ruta Panorámica/Escénica (Prioriza paisajes y carreteras secundarias)
 * - Ruta más Barata (Evita peajes)
 */
public class NavigationStrategySystem {

    // ==========================================
    // 1. LA INTERFAZ ESTRATEGIA (Strategy)
    // ==========================================
    // Declara el método que todas las estrategias concretas deben implementar.
    public interface RouteStrategy {
        void calculateRoute(String origin, String destination);
    }

    // ==========================================
    // 2. ESTRATEGIAS CONCRETAS (Los algoritmos separados)
    // ==========================================
    
    // Algoritmo 1: Ruta más Rápida
    public static class FastestRoute implements RouteStrategy {
        @Override
        public void calculateRoute(String origin, String destination) {
            System.out.println("Calculando la Ruta más RÁPIDA de " + origin + " a " + destination + "...");
            System.out.println(" -> Estrategia aplicada: Usando autopistas principales e ignorando peajes.");
        }
    }

    // Algoritmo 2: Ruta Panorámica
    public static class ScenicRoute implements RouteStrategy {
        @Override
        public void calculateRoute(String origin, String destination) {
            System.out.println("Calculando la Ruta ESCÉNICA de " + origin + " a " + destination + "...");
            System.out.println(" -> Estrategia aplicada: Buscando carreteras con miradores y desvíos turísticos.");
        }
    }

    // Algoritmo 3: Ruta más Barata
    public static class CheapestRoute implements RouteStrategy {
        @Override
        public void calculateRoute(String origin, String destination) {
            System.out.println("Calculando la Ruta más ECONÓMICA de " + origin + " a " + destination + "...");
            System.out.println(" -> Estrategia aplicada: Evitando peajes estrictamente. Calculando vías alternas.");
        }
    }

    // ==========================================
    // 3. EL CONTEXTO (Context)
    // ==========================================
    // Mantiene una referencia a una de las estrategias y se comunica con ella 
    // a través de la interfaz. El contexto no sabe cómo se ejecuta la estrategia.
    public static class NavigationApp {
        
        private RouteStrategy routeStrategy;

        // Inyección de la estrategia a través del constructor (Opcional)
        public NavigationApp(RouteStrategy initialStrategy) {
            this.routeStrategy = initialStrategy;
        }

        // Permite cambiar la estrategia en TIEMPO DE EJECUCIÓN (Vital en este patrón)
        public void setRouteStrategy(RouteStrategy routeStrategy) {
            this.routeStrategy = routeStrategy;
        }

        // Delega el trabajo pesado al objeto estrategia
        public void startNavigation(String origin, String destination) {
            if (routeStrategy == null) {
                System.out.println("Error: No se ha configurado ninguna estrategia de enrutamiento.");
                return;
            }
            routeStrategy.calculateRoute(origin, destination);
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> GOOGLE MAPS CLON INICIADO <<<");

        // 1. El usuario abre la app con la configuración por defecto (Ruta Rápida)
        NavigationApp gps = new NavigationApp(new FastestRoute());
        
        System.out.println("\n--- Viaje de Negocios (Hay prisa) ---");
        gps.startNavigation("Aeropuerto El Dorado", "Centro Financiero");

        // 2. El usuario cambia de opinión a mitad del viaje y quiere ahorrar
        System.out.println("\n--- Viaje de fin de mes (Poco dinero) ---");
        System.out.println("[Usuario entra a Ajustes -> Evitar Peajes]");
        
        // ¡Magia del patrón Strategy! Cambiamos el algoritmo en caliente
        gps.setRouteStrategy(new CheapestRoute());
        gps.startNavigation("Bogotá", "Medellín");

        // 3. El usuario se va de vacaciones
        System.out.println("\n--- Viaje de Vacaciones (Prioriza disfrutar) ---");
        System.out.println("[Usuario entra a Ajustes -> Ruta Escénica]");
        
        // Volvemos a cambiar el algoritmo sin reiniciar la app ni tocar código de NavigationApp
        gps.setRouteStrategy(new ScenicRoute());
        gps.startNavigation("Cartagena", "Santa Marta");
    }
}
