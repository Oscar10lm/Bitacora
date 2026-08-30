package dosw.semana_3.extra.patrones.estructurales.bridge;

/**
 * ============================================================================
 * EJERCICIO PRÁCTICO: DISPOSITIVOS Y CONTROLES REMOTOS (Bridge)
 * ============================================================================
 *
 * Una empresa de electrónica vende distintos tipos de dispositivos (Televisor, 
 * Radio) que pueden ser controlados por distintos tipos de controles remotos 
 * (Básico, Avanzado). 
 * 
 * Si se usa herencia pura, la combinación crece con cada nuevo dispositivo o 
 * cada nuevo tipo de control (TelevisorControlBasico, RadioControlAvanzado, etc.).
 * 
 * Se aplica Bridge separando la jerarquía de Control Remoto (abstracción) de 
 * la jerarquía de Dispositivo (implementación). Un control remoto puede operar 
 * sobre cualquier dispositivo sin necesitar una subclase por cada combinación.
 */
public class RemoteDeviceBridgeSystem {

    // ==========================================
    // 1. IMPLEMENTACIÓN (La Plataforma / Dispositivos)
    // ==========================================
    // Define la interfaz estándar que la abstracción usará para gobernar el equipo.
    public interface Device {
        boolean isEnabled();
        void enable();
        void disable();
        int getVolume();
        void setVolume(int percent);
        void printStatus();
    }

    // ==========================================
    // 2. IMPLEMENTACIONES CONCRETAS
    // ==========================================
    
    public static class Television implements Device {
        private boolean on = false;
        private int volume = 30;

        @Override
        public boolean isEnabled() { return on; }

        @Override
        public void enable() { on = true; }

        @Override
        public void disable() { on = false; }

        @Override
        public int getVolume() { return volume; }

        @Override
        public void setVolume(int percent) {
            if (percent > 100) this.volume = 100;
            else if (percent < 0) this.volume = 0;
            else this.volume = percent;
        }
        
        @Override
        public void printStatus() {
            System.out.println("| TELEVISOR | Estado: " + (on ? "Encendido" : "Apagado") + " | Volumen: " + volume + "%");
        }
    }

    public static class Radio implements Device {
        private boolean on = false;
        private int volume = 10;

        @Override
        public boolean isEnabled() { return on; }

        @Override
        public void enable() { on = true; }

        @Override
        public void disable() { on = false; }

        @Override
        public int getVolume() { return volume; }

        @Override
        public void setVolume(int percent) {
            if (percent > 100) this.volume = 100;
            else if (percent < 0) this.volume = 0;
            else this.volume = percent;
        }

        @Override
        public void printStatus() {
            System.out.println("| RADIO | Estado: " + (on ? "Encendida" : "Apagada") + " | Volumen: " + volume + "%");
        }
    }

    // ==========================================
    // 3. ABSTRACCIÓN (Capa de Control / Controles Remotos)
    // ==========================================
    // La abstracción mantiene una referencia al dispositivo y delega la ejecución.
    public static class RemoteControl {
        
        // EL PUENTE (Composición)
        protected Device device;

        public RemoteControl(Device device) {
            this.device = device;
        }

        // Lógica de alto nivel (El control básico solo encender/apagar)
        public void togglePower() {
            System.out.println("Control Remoto: Presionando botón de encendido...");
            if (device.isEnabled()) {
                device.disable();
            } else {
                device.enable();
            }
        }
    }

    // ==========================================
    // 4. ABSTRACCIONES REFINADAS
    // ==========================================
    
    // El control avanzado hereda la funcionalidad básica y añade más cosas (ej. Mutear)
    public static class AdvancedRemoteControl extends RemoteControl {
        
        public AdvancedRemoteControl(Device device) {
            super(device);
        }

        // El control avanzado puede además ajustar el volumen directamente
        public void mute() {
            System.out.println("Control Avanzado: Presionando botón de MUTE...");
            device.setVolume(0);
        }
        
        public void volumeUp() {
            System.out.println("Control Avanzado: Subiendo volumen...");
            device.setVolume(device.getVolume() + 10);
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println("--- PRUEBA 1: Radio con Control Básico ---");
        Device myRadio = new Radio();
        RemoteControl basicRemote = new RemoteControl(myRadio);
        
        myRadio.printStatus();
        basicRemote.togglePower(); // La enciende
        myRadio.printStatus();

        System.out.println("\n--- PRUEBA 2: Televisor con Control Avanzado ---");
        Device myTv = new Television();
        AdvancedRemoteControl advancedRemote = new AdvancedRemoteControl(myTv);
        
        myTv.printStatus();
        advancedRemote.togglePower(); // Lo enciende
        advancedRemote.volumeUp();    // Le sube el volumen
        myTv.printStatus();
        advancedRemote.mute();        // Lo silencia
        myTv.printStatus();
    }
}
