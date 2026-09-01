package dosw.semana_3.ejercicios_diapositivas.patrones.comportamiento.command;

import java.util.Stack;

public class UniversalRemoteCommandSystem {

    // ==========================================
    // 1. LOS RECEPTORES (Receivers - El Hardware de la Casa)
    // ==========================================
    
    public static class Light {
        private String location;

        public Light(String location) {
            this.location = location;
        }

        public void turnOn() {
            System.out.println("Luz de " + location + ": [ENCENDIDA]");
        }

        public void turnOff() {
            System.out.println("Luz de " + location + ": [APAGADA]");
        }
    }

    public static class Television {
        private int volume = 10;

        public void volumeUp() {
            volume++;
            System.out.println("TV: Subiendo volumen a " + volume);
        }

        public void volumeDown() {
            volume--;
            System.out.println("TV: Bajando volumen a " + volume);
        }
    }

    // ==========================================
    // 2. LA INTERFAZ COMANDO (Con soporte para Undo)
    // ==========================================
    public interface Command {
        void execute();
        void undo(); // Habilidad de revertir la acción
    }

    // ==========================================
    // 3. COMANDOS CONCRETOS
    // ==========================================
    
    // Comando: Encender Luz
    public static class TurnOnLightCommand implements Command {
        private Light light;

        public TurnOnLightCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOn();
        }

        @Override
        public void undo() {
            // Lo contrario de encender es apagar
            System.out.print("[UNDO] -> ");
            light.turnOff();
        }
    }

    // Comando: Subir Volumen TV
    public static class VolumeUpTVCommand implements Command {
        private Television tv;

        public VolumeUpTVCommand(Television tv) {
            this.tv = tv;
        }

        @Override
        public void execute() {
            tv.volumeUp();
        }

        @Override
        public void undo() {
            // Lo contrario de subir es bajar el volumen
            System.out.print("[UNDO] -> ");
            tv.volumeDown();
        }
    }

    // ==========================================
    // 4. EL INVOCADOR (Invoker - El Control Remoto Universal)
    // ==========================================
    public static class UniversalRemote {
        
        // Mapeo simple de botones
        private Command button1;
        private Command button2;
        
        // Pila para guardar el historial y poder hacer 'Undo' múltiples veces
        private Stack<Command> history = new Stack<>();

        // Configuración dinámica (Parametrizar botones con diferentes solicitudes)
        public void setButton1(Command command) {
            this.button1 = command;
        }

        public void setButton2(Command command) {
            this.button2 = command;
        }

        public void pressButton1() {
            if (button1 != null) {
                button1.execute();
                history.push(button1); // Guardamos la acción en el historial
            }
        }

        public void pressButton2() {
            if (button2 != null) {
                button2.execute();
                history.push(button2);
            }
        }

        // El famoso botón "Deshacer"
        public void pressUndoButton() {
            if (!history.isEmpty()) {
                Command lastCommand = history.pop();
                lastCommand.undo();
            } else {
                System.out.println("[Control] No hay acciones en el historial para deshacer.");
            }
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // 1. Instanciamos los Receptores (Los aparatos de la casa)
        Light livingRoomLight = new Light("Sala Principal");
        Television samsungTV = new Television();

        // 2. Instanciamos los comandos encapsulados
        Command turnOnLivingRoom = new TurnOnLightCommand(livingRoomLight);
        Command volumeUp = new VolumeUpTVCommand(samsungTV);

        // 3. Instanciamos y configuramos el Invocador (El Control Remoto)
        UniversalRemote remote = new UniversalRemote();
        
        // Al Botón 1 le asignamos la luz, al Botón 2 le asignamos la TV
        remote.setButton1(turnOnLivingRoom);
        remote.setButton2(volumeUp);

        System.out.println("--- JUGANDO CON EL CONTROL REMOTO ---");
        
        System.out.println("\n>> Presionando Botón 1...");
        remote.pressButton1(); // Enciende la luz

        System.out.println(">> Presionando Botón 2 (x3 veces)...");
        remote.pressButton2(); // Sube volumen a 11
        remote.pressButton2(); // Sube volumen a 12
        remote.pressButton2(); // Sube volumen a 13

        System.out.println("\n--- PROBANDO BOTÓN DESHACER (UNDO) ---");
        // Debería bajar de 13 a 12, luego a 11, luego a 10, y finalmente apagar la luz.
        remote.pressUndoButton(); 
        remote.pressUndoButton(); 
        remote.pressUndoButton(); 
        remote.pressUndoButton(); 
        
        // Si seguimos apretando cuando ya no hay historial
        remote.pressUndoButton();
    }
}
