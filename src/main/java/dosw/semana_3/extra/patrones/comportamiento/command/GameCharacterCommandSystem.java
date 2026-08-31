package dosw.semana_3.extra.patrones.comportamiento.command;

import java.util.ArrayList;
import java.util.List;

public class GameCharacterCommandSystem {

    // ==========================================
    // 1. EL RECEPTOR (Receiver - Quien hace el trabajo real)
    // ==========================================
    public static class GameCharacter {
        private String name;

        public GameCharacter(String name) {
            this.name = name;
        }

        public void walk() {
            System.out.println(name + " avanza hacia adelante.");
        }

        public void jump() {
            System.out.println(name + " da un salto acrobático.");
        }

        public void attack() {
            System.out.println(name + " balancea su espada con fuerza.");
        }

        public void defend() {
            System.out.println(name + " levanta su escudo en posición defensiva.");
        }
    }

    // ==========================================
    // 2. LA INTERFAZ COMANDO (Command)
    // ==========================================
    public interface Command {
        void execute();
    }

    // ==========================================
    // 3. COMANDOS CONCRETOS (Encapsulan la llamada al receptor)
    // ==========================================
    
    public static class WalkCommand implements Command {
        private GameCharacter character;

        public WalkCommand(GameCharacter character) {
            this.character = character;
        }

        @Override
        public void execute() {
            character.walk();
        }
    }

    public static class JumpCommand implements Command {
        private GameCharacter character;

        public JumpCommand(GameCharacter character) {
            this.character = character;
        }

        @Override
        public void execute() {
            character.jump();
        }
    }

    public static class AttackCommand implements Command {
        private GameCharacter character;

        public AttackCommand(GameCharacter character) {
            this.character = character;
        }

        @Override
        public void execute() {
            character.attack();
        }
    }

    public static class DefendCommand implements Command {
        private GameCharacter character;

        public DefendCommand(GameCharacter character) {
            this.character = character;
        }

        @Override
        public void execute() {
            character.defend();
        }
    }

    // ==========================================
    // 4. EL INVOCADOR (Invoker - El Control del Juego)
    // ==========================================
    public static class GameController {
        
        // Historial de comandos para hacer repeticiones (Macros) o deshacer
        private List<Command> commandHistory = new ArrayList<>();

        // El invocador no sabe qué comando le están pasando ni qué hará.
        // Simplemente sabe que puede llamar a 'execute()'.
        public void pressButton(Command command) {
            command.execute();
            commandHistory.add(command);
        }

        // Ventaja del patrón Command: Podemos re-ejecutar el historial
        public void replayMacro() {
            System.out.println("\n--- [REPLAY MACRO] Ejecutando últimos movimientos ---");
            for (Command cmd : commandHistory) {
                cmd.execute();
            }
            System.out.println("--- [FIN MACRO] ---\n");
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // 1. Instanciamos el Receptor
        GameCharacter hero = new GameCharacter("Link");

        // 2. Instanciamos los comandos y los vinculamos al receptor
        Command walk = new WalkCommand(hero);
        Command jump = new JumpCommand(hero);
        Command attack = new AttackCommand(hero);
        Command defend = new DefendCommand(hero);

        // 3. Instanciamos el Invocador (El control de la consola)
        GameController controller = new GameController();

        System.out.println(">>> El jugador comienza a presionar botones...");
        
        // 4. Se asignan y ejecutan comandos libremente
        // Imagina que esto ocurre cuando el jugador aprieta (A, B, X, Y)
        controller.pressButton(walk);
        controller.pressButton(jump);
        controller.pressButton(attack);
        controller.pressButton(defend);
        controller.pressButton(attack);

        // 5. Demostrando el poder de encapsular peticiones como objetos
        controller.replayMacro();
    }
}
