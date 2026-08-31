package dosw.semana_3.extra.patrones.creacionales.abstract_factory;

public class GameEngineSystem {

    // ==========================================
    // 1. PRODUCTOS ABSTRACTOS (Interfaces de la familia)
    // ==========================================
    public interface Controller {
        void connect();
    }

    public interface Game {
        void start();
    }

    public interface UI {
        void render();
    }

    // ==========================================
    // 2. PRODUCTOS CONCRETOS - FAMILIA PLAYSTATION
    // ==========================================
    public static class PlayStationController implements Controller {
        @Override
        public void connect() {
            System.out.println("DualShock/DualSense conectado vía Bluetooth.");
        }
    }

    public static class PlayStationGame implements Game {
        @Override
        public void start() {
            System.out.println("Iniciando binario del juego optimizado para PlayStation...");
        }
    }

    public static class PlayStationUI implements UI {
        @Override
        public void render() {
            System.out.println("Renderizando interfaz con estilo visual de Sony PlayStation.");
        }
    }

    // ==========================================
    // 3. PRODUCTOS CONCRETOS - FAMILIA XBOX
    // ==========================================
    public static class XboxController implements Controller {
        @Override
        public void connect() {
            System.out.println("Control de Xbox conectado vía Xbox Wireless Protocol.");
        }
    }

    public static class XboxGame implements Game {
        @Override
        public void start() {
            System.out.println("Iniciando binario del juego optimizado para Xbox (DirectX)...");
        }
    }

    public static class XboxUI implements UI {
        @Override
        public void render() {
            System.out.println("Renderizando interfaz con estilo visual de Microsoft Xbox.");
        }
    }

    // ==========================================
    // 4. FÁBRICA ABSTRACTA (La interfaz creadora de familias)
    // ==========================================
    public interface ConsoleFactory {
        Controller createController();
        Game createGame();
        UI createUI();
    }

    // ==========================================
    // 5. FÁBRICAS CONCRETAS
    // ==========================================
    public static class PlayStationFactory implements ConsoleFactory {
        @Override
        public Controller createController() {
            return new PlayStationController();
        }

        @Override
        public Game createGame() {
            return new PlayStationGame();
        }

        @Override
        public UI createUI() {
            return new PlayStationUI();
        }
    }

    public static class XboxFactory implements ConsoleFactory {
        @Override
        public Controller createController() {
            return new XboxController();
        }

        @Override
        public Game createGame() {
            return new XboxGame();
        }

        @Override
        public UI createUI() {
            return new XboxUI();
        }
    }

    // ==========================================
    // 6. CLIENTE (GameEngine)
    // ==========================================
    public static class GameEngine {
        private final Controller controller;
        private final Game game;
        private final UI ui;

        // El motor recibe la fábrica y usa sus métodos para crear la familia
        public GameEngine(ConsoleFactory factory) {
            this.controller = factory.createController();
            this.game = factory.createGame();
            this.ui = factory.createUI();
        }

        // El motor ejecuta el juego sin importar qué consola sea por debajo
        public void run() {
            System.out.println("--- Booting Game Engine ---");
            controller.connect();
            ui.render();
            game.start();
            System.out.println("--- Engine Running ---\n");
        }
    }

    // ==========================================
    // 7. DEMOSTRACIÓN (MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // El entorno detecta que estamos en una PlayStation
        System.out.println(">>> Entorno detectado: PlayStation");
        ConsoleFactory psFactory = new PlayStationFactory();
        GameEngine engine1 = new GameEngine(psFactory);
        engine1.run();

        // El entorno detecta que estamos en una Xbox
        System.out.println(">>> Entorno detectado: Xbox");
        ConsoleFactory xboxFactory = new XboxFactory();
        GameEngine engine2 = new GameEngine(xboxFactory);
        engine2.run();

        /*
         * NOTA:
         * El GameEngine jamás interactúa con 'PlayStationGame' o 'XboxController'.
         * Siempre interactúa con las interfaces 'Game' y 'Controller'.
         * Si mañana agregamos 'NintendoFactory', GameEngine NO sufrirá ninguna modificación.
         */
    }
}
