package dosw.semana_3.extra.patrones.comportamiento.observer;

import java.util.ArrayList;
import java.util.List;

public class WeatherStationObserverSystem {

    // ==========================================
    // 1. LA INTERFAZ OBSERVADOR (El Panel / Display)
    // ==========================================
    // Define el método que la Estación llamará para enviar los datos nuevos
    public interface DisplayObserver {
        void updateTemperature(float newTemperature);
    }

    // ==========================================
    // 2. OBSERVADORES CONCRETOS (Los diferentes paneles)
    // ==========================================
    
    // Panel 1: Portal Web Climatológico
    public static class WebDisplay implements DisplayObserver {
        @Override
        public void updateTemperature(float newTemperature) {
            System.out.println("[WEB BROWSER] Actualizando gráfico de temperatura en el dashboard: " 
                               + newTemperature + "°C");
        }
    }

    // Panel 2: Notificación en App Móvil
    public static class MobileAppDisplay implements DisplayObserver {
        @Override
        public void updateTemperature(float newTemperature) {
            System.out.println("[MOBILE APP] Notificación Push: 'El clima ha cambiado a " 
                               + newTemperature + "°C. ¡Lleva paraguas o gafas de sol!'");
        }
    }

    // Panel 3: Letrero LED en la Autopista
    public static class LedDisplay implements DisplayObserver {
        @Override
        public void updateTemperature(float newTemperature) {
            System.out.println("[LED AUTOPISTA] \u2600\uFE0F TEMPERATURA ACTUAL: >>> " 
                               + newTemperature + "°C <<< Maneje con precaución.");
        }
    }

    // ==========================================
    // 3. LA INTERFAZ SUJETO (Observable)
    // ==========================================
    // Declara los métodos de gestión de los paneles
    public interface WeatherStationSubject {
        void registerDisplay(DisplayObserver display);
        void removeDisplay(DisplayObserver display);
        void notifyDisplays();
    }

    // ==========================================
    // 4. SUJETO CONCRETO (La Estación Central Física)
    // ==========================================
    public static class CentralWeatherStation implements WeatherStationSubject {
        
        // Estado interno crucial
        private float currentTemperature;
        
        // La lista de pantallas registradas para recibir actualizaciones
        private List<DisplayObserver> displays = new ArrayList<>();

        public CentralWeatherStation() {
            this.currentTemperature = 0.0f; // Temperatura inicial por defecto
        }

        @Override
        public void registerDisplay(DisplayObserver display) {
            displays.add(display);
            System.out.println(" -> Nuevo panel de visualización conectado al servidor del clima.");
        }

        @Override
        public void removeDisplay(DisplayObserver display) {
            displays.remove(display);
            System.out.println(" -> Panel de visualización desconectado.");
        }

        @Override
        public void notifyDisplays() {
            System.out.println("\n*** TRANSMITIENDO NUEVOS DATOS A " + displays.size() + " PANTALLAS ***");
            // Iterar y notificar a todos, sin saber si es Web, Móvil o LED
            for (DisplayObserver display : displays) {
                display.updateTemperature(this.currentTemperature);
            }
            System.out.println("******************************************************\n");
        }

        // Lógica principal: Lectura del sensor físico
        public void setTemperature(float newTemperature) {
            System.out.println("\n[SENSOR FÍSICO]: Se ha detectado un cambio en la temperatura ambiental.");
            System.out.println("   - Anterior: " + this.currentTemperature + "°C");
            System.out.println("   - Actual:   " + newTemperature + "°C");
            
            // Actualizamos el estado interno
            this.currentTemperature = newTemperature;
            
            // EL MOMENTO CLAVE: Como el estado cambió, se avisa a todos
            notifyDisplays();
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> INICIANDO SISTEMA METEOROLÓGICO <<<");

        // 1. Instanciamos la estación central (Sujeto)
        CentralWeatherStation weatherStation = new CentralWeatherStation();

        // 2. Instanciamos los diferentes paneles (Observadores)
        DisplayObserver webDashboard = new WebDisplay();
        DisplayObserver iosApp = new MobileAppDisplay();
        DisplayObserver highwayLed = new LedDisplay();

        System.out.println("\n--- Conectando pantallas a la estación central ---");
        // 3. Registramos los paneles
        weatherStation.registerDisplay(webDashboard);
        weatherStation.registerDisplay(iosApp);
        weatherStation.registerDisplay(highwayLed);

        // 4. Simulamos cambios en el sensor físico (ej. pasa el tiempo)
        
        // Medición de la mañana
        weatherStation.setTemperature(18.5f);

        // Medición del mediodía (¡Hace mucho calor!)
        weatherStation.setTemperature(32.1f);
        
        // 5. El letrero LED entra en mantenimiento, así que lo desconectamos
        System.out.println("--- El letrero LED de la autopista entra en mantenimiento ---");
        weatherStation.removeDisplay(highwayLed);

        // Medición de la tarde/noche (Baja la temperatura)
        // Esta vez el Web y la App recibirán datos, pero el LED ya no, porque fue des-registrado
        weatherStation.setTemperature(14.0f);
    }
}
