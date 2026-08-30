package dosw.semana_3.taller.solid;

/**
 * -------------------------------------------------------------------
 * #8 Sistema de Sensores IoT
 * -------------------------------------------------------------------
 *
 * (1) SITUACIÓN:
 * Un sistema central recibe datos de sensores. Actualmente usan MQTT, HTTP 
 * o WebSocket, pero el sistema debe ignorar el medio de transporte porque 
 * a futuro pueden llegar nuevos protocolos (CoAP, LoRaWAN).
 *
 * (2) PRINCIPIO SOLID A APLICAR:
 * DIP - Dependency Inversion Principle (Principio de Inversión de Dependencias).
 * OCP - Open/Closed Principle (Principio de Abierto/Cerrado).
 *
 * (3) JUSTIFICACIÓN TÉCNICA:
 * - DIP: El sistema central de procesamiento es el "Módulo de Alto Nivel", 
 * mientras que los protocolos de red (MQTT, HTTP) son los "Módulos de Bajo 
 * Nivel" (detalles de infraestructura). Si el sistema central se acopla a 
 * implementaciones específicas, será frágil. La Inversión de Dependencias 
 * nos obliga a crear una interfaz abstracta (ej. `SensorTransport`) de la 
 * cual dependerán tanto el sistema central como los protocolos.
 * - OCP: Esta misma abstracción permite que el sistema esté cerrado a su 
 * modificación, pero abierto a su extensión. Si mañana llega LoRaWAN, solo 
 * se crea una nueva clase que implemente la interfaz, sin alterar el núcleo.
 *
 * (4) SOLUCIÓN PROPUESTA (Estructura):
 * Definir una interfaz `SensorTransport` con un método para recibir o escuchar 
 * los datos. Las clases `MqttClient`, `HttpClient` implementan esta interfaz.
 * El `CentralProcessor` recibe esta interfaz por inyección de dependencias.
 */
public class Ejercicio8 {

    // --- ESQUELETO DE SOLUCIÓN BASADO EN DIP Y OCP ---

    /**
     * Abstracción que independiza la capa de negocio de la red (DIP)
     */
    public interface SensorTransport {
        String receiveData();
    }

    // --- Módulos de Bajo Nivel (Detalles) ---

    public static class MqttClient implements SensorTransport {
        @Override
        public String receiveData() {
            // Lógica compleja de conexión a broker MQTT...
            return "{ 'sensor': 'temp', 'value': 24, 'protocol': 'MQTT' }";
        }
    }

    public static class HttpClient implements SensorTransport {
        @Override
        public String receiveData() {
            // Lógica de recibir peticiones POST REST...
            return "{ 'sensor': 'humidity', 'value': 60, 'protocol': 'HTTP' }";
        }
    }

    // Mañana agregamos LoRaWAN fácilmente (OCP)
    public static class LoraWanClient implements SensorTransport {
        @Override
        public String receiveData() {
            return "{ 'sensor': 'pressure', 'value': 1013, 'protocol': 'LoRaWAN' }";
        }
    }

    // --- Módulo de Alto Nivel ---

    /**
     * El Procesador Central NO depende de MQTT ni de HTTP directamente.
     * Depende de la interfaz abstracta, por lo que es inmune a los cambios de red.
     */
    public static class CentralProcessor {
        
        private final SensorTransport transport;

        // Inyección de dependencias
        public CentralProcessor(SensorTransport transport) {
            this.transport = transport;
        }

        public void processIncomingData() {
            // Recibe los datos sin importarle cómo llegaron
            String data = transport.receiveData();
            System.out.println("Procesando datos en la central: " + data);
            
            // Lógica de negocio (guardar en BD, disparar alertas, etc.)
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        
        // El sistema arranca con un sensor MQTT
        SensorTransport mqtt = new MqttClient();
        CentralProcessor processorMQTT = new CentralProcessor(mqtt);
        processorMQTT.processIncomingData();

        // En otra planta usan un sensor más nuevo con LoRaWAN
        SensorTransport lorawan = new LoraWanClient();
        CentralProcessor processorLora = new CentralProcessor(lorawan);
        processorLora.processIncomingData(); 
    }
}
