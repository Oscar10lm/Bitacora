package dosw.semana_3.extra.patrones.comportamiento.state;

public class TrafficLightStateSystem {

    // ==========================================
    // 1. EL CONTEXTO (Context - El Semáforo Físico)
    // ==========================================
    public static class TrafficLight {
        private TrafficLightState state;

        public TrafficLight(TrafficLightState initialState) {
            this.state = initialState;
        }

        // Permite que un estado provoque la transición al siguiente estado
        public void changeState(TrafficLightState newState) {
            this.state = newState;
        }

        // Delega la acción al estado actual
        public void triggerNext() {
            state.handle(this);
        }
    }

    // ==========================================
    // 2. LA INTERFAZ ESTADO (State)
    // ==========================================
    public interface TrafficLightState {
        // Recibe el contexto para poder decirle a qué estado cambiar después
        void handle(TrafficLight context);
    }

    // ==========================================
    // 3. ESTADOS CONCRETOS (Lógica de transición aislada)
    // ==========================================
    
    // Estado Verde
    public static class GreenState implements TrafficLightState {
        @Override
        public void handle(TrafficLight context) {
            System.out.println("[\uD83D\uDFE2 LUZ VERDE]: Los vehículos pueden avanzar. (Duración: 40 segundos)");
            System.out.println(" -> Transición: Cambiando a luz Amarilla...\n");
            context.changeState(new YellowState());
        }
    }

    // Estado Amarillo
    public static class YellowState implements TrafficLightState {
        @Override
        public void handle(TrafficLight context) {
            System.out.println("[\uD83D\uDFE1 LUZ AMARILLA]: Precaución, bajando velocidad. (Duración: 5 segundos)");
            System.out.println(" -> Transición: Cambiando a luz Roja...\n");
            context.changeState(new RedState());
        }
    }

    // Estado Rojo
    public static class RedState implements TrafficLightState {
        @Override
        public void handle(TrafficLight context) {
            System.out.println("[\uD83D\uDD34 LUZ ROJA]: Alto total. Pase peatonal activo. (Duración: 30 segundos)");
            System.out.println(" -> Transición: Cambiando a luz Verde...\n");
            context.changeState(new GreenState());
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> ENCENDIENDO SISTEMA DE SEMÁFORO <<<\n");

        // 1. Iniciamos el semáforo en Rojo
        TrafficLight trafficLight = new TrafficLight(new RedState());

        // 2. Simulamos el paso del tiempo apretando un "botón" que dispara el ciclo natural
        // Fíjate que el semáforo (Contexto) no tiene NINGÚN if/else para saber qué color sigue.
        for (int i = 0; i < 4; i++) {
            trafficLight.triggerNext();
            
            try {
                Thread.sleep(1000); // Simulamos el paso del tiempo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
