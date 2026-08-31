package dosw.semana_3.extra.patrones.comportamiento.mediator;

import java.util.ArrayList;
import java.util.List;

public class AirportTowerMediatorSystem {

    // ==========================================
    // 1. LA INTERFAZ MEDIADOR
    // ==========================================
    public interface AirTrafficControlTower {
        void registerFlight(Airplane airplane);
        void requestLanding(Airplane airplane);
        void notifyLandingComplete(Airplane airplane);
    }

    // ==========================================
    // 2. MEDIADOR CONCRETO (La Torre de Control del Dorado)
    // ==========================================
    public static class AirportTower implements AirTrafficControlTower {
        
        private List<Airplane> airplanesInSpace = new ArrayList<>();
        private boolean runwayBusy = false; // La pista de aterrizaje compartida

        @Override
        public void registerFlight(Airplane airplane) {
            airplanesInSpace.add(airplane);
            System.out.println("[Torre]: El vuelo " + airplane.getFlightNumber() + " ha entrado en nuestro espacio aéreo.");
        }

        @Override
        public void requestLanding(Airplane airplane) {
            System.out.println("\n[Piloto " + airplane.getFlightNumber() + "]: Torre, solicitando permiso para aterrizar.");
            
            if (runwayBusy) {
                // Si la pista está ocupada, le niega el aterrizaje
                System.out.println("[Torre]: NEGATIVO vuelo " + airplane.getFlightNumber() + ", la pista está ocupada. Manténgase en patrón de espera en el aire.");
            } else {
                // Si está libre, se la asigna
                runwayBusy = true;
                System.out.println("[Torre]: AUTORIZADO vuelo " + airplane.getFlightNumber() + ". Pista libre, proceda a aterrizar.");
                airplane.executeLanding(); // Permite que el avión haga su maniobra
            }
        }

        @Override
        public void notifyLandingComplete(Airplane airplane) {
            System.out.println("[Piloto " + airplane.getFlightNumber() + "]: Aterrizaje completado. Despejando pista.");
            runwayBusy = false; // Libera la pista
            airplanesInSpace.remove(airplane);
            
            System.out.println("[Torre]: Recibido. Pista liberada para el siguiente turno.");
        }
    }

    // ==========================================
    // 3. LA CLASE COLEGA (Componente Base)
    // ==========================================
    public static abstract class Airplane {
        protected AirTrafficControlTower tower;
        protected String flightNumber;

        public Airplane(AirTrafficControlTower tower, String flightNumber) {
            this.tower = tower;
            this.flightNumber = flightNumber;
        }

        public String getFlightNumber() { return flightNumber; }

        public void requestToLand() {
            tower.requestLanding(this);
        }

        public abstract void executeLanding();
    }

    // ==========================================
    // 4. COLEGAS CONCRETOS (Tipos de Aviones)
    // ==========================================
    
    public static class CommercialFlight extends Airplane {
        public CommercialFlight(AirTrafficControlTower tower, String flightNumber) {
            super(tower, flightNumber);
        }

        @Override
        public void executeLanding() {
            System.out.println("  -> [Vuelo Comercial]: Desplegando tren de aterrizaje y flaps. Aterrizando suavemente con pasajeros.");
            // Cuando termina la maniobra, le avisa de nuevo al mediador
            tower.notifyLandingComplete(this);
        }
    }

    public static class CargoFlight extends Airplane {
        public CargoFlight(AirTrafficControlTower tower, String flightNumber) {
            super(tower, flightNumber);
        }

        @Override
        public void executeLanding() {
            System.out.println("  -> [Vuelo de Carga]: Desplegando reversos a máxima potencia. Aterrizaje pesado completado.");
            tower.notifyLandingComplete(this);
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> AEROPUERTO INTERNACIONAL INICIANDO OPERACIONES <<<\n");

        // 1. Instanciamos el Mediador Central (La Torre)
        AirTrafficControlTower bogotaTower = new AirportTower();

        // 2. Instanciamos los Colegas (Los Aviones), conectándolos a la torre
        Airplane avianca001 = new CommercialFlight(bogotaTower, "Avianca 001");
        Airplane fedexCarga = new CargoFlight(bogotaTower, "FedEx 400");
        Airplane latam099 = new CommercialFlight(bogotaTower, "Latam 099");

        // 3. Los aviones entran al espacio aéreo
        bogotaTower.registerFlight(avianca001);
        bogotaTower.registerFlight(fedexCarga);
        bogotaTower.registerFlight(latam099);

        // 4. Avianca solicita aterrizar. La pista está libre.
        avianca001.requestToLand();

        // 5. Al mismo tiempo, FedEx intenta aterrizar.
        // La torre lo detendrá sin que Avianca y FedEx tengan que hablar entre ellos.
        fedexCarga.requestToLand();

        // 6. Ahora Latam también intenta aterrizar. También lo mandan a esperar.
        latam099.requestToLand();
        
        // 7. Eventualmente FedEx vuelve a pedir permiso cuando la pista ya se liberó.
        fedexCarga.requestToLand();
    }
}
