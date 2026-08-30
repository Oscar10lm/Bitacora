package dosw.semana_3.extra.patrones.creacionales.abstract_factory;

/**
 * ============================================================================
 * EJERCICIO PRÁCTICO: INTERFAZ MULTIPLATAFORMA (Abstract Factory)
 * ============================================================================
 *
 * Una aplicación debe verse "nativa" en distintos sistemas operativos. 
 * Cada sistema operativo ofrece una familia de componentes visuales compatibles 
 * entre sí:
 * - Botón
 * - Checkbox
 * - Barra de desplazamiento (Scrollbar)
 *
 * SO soportados inicialmente:
 * - Windows
 * - MacOS
 *
 * La aplicación no debe conocer las implementaciones concretas de estos 
 * componentes — solo debe poder renderizar el botón, checkbox y scrollbar, 
 * sin importar el sistema operativo subyacente.
 */
public class CrossPlatformUISystem {

    // ==========================================
    // 1. PRODUCTOS ABSTRACTOS (Interfaces de la familia)
    // ==========================================
    public interface Button {
        void click();
    }

    public interface Checkbox {
        void check();
    }

    public interface Scrollbar {
        void scroll();
    }

    // ==========================================
    // 2. PRODUCTOS CONCRETOS - FAMILIA WINDOWS
    // ==========================================
    public static class WindowsButton implements Button {
        @Override
        public void click() {
            System.out.println("Clickeando un Botón estilo Windows (bordes cuadrados).");
        }
    }

    public static class WindowsCheckbox implements Checkbox {
        @Override
        public void check() {
            System.out.println("Marcando un Checkbox estilo Windows (ícono de palomita negra).");
        }
    }

    public static class WindowsScrollbar implements Scrollbar {
        @Override
        public void scroll() {
            System.out.println("Desplazando una Scrollbar estilo Windows (gris clásica).");
        }
    }

    // ==========================================
    // 3. PRODUCTOS CONCRETOS - FAMILIA MACOS
    // ==========================================
    public static class MacButton implements Button {
        @Override
        public void click() {
            System.out.println("Clickeando un Botón estilo MacOS (bordes redondeados, estilo Aqua).");
        }
    }

    public static class MacCheckbox implements Checkbox {
        @Override
        public void check() {
            System.out.println("Marcando un Checkbox estilo MacOS (azul brillante).");
        }
    }

    public static class MacScrollbar implements Scrollbar {
        @Override
        public void scroll() {
            System.out.println("Desplazando una Scrollbar estilo MacOS (oculta hasta que pasas el mouse).");
        }
    }

    // ==========================================
    // 4. FÁBRICA ABSTRACTA (La interfaz creadora de familias)
    // ==========================================
    public interface UIFactory {
        Button createButton();
        Checkbox createCheckbox();
        Scrollbar createScrollbar();
    }

    // ==========================================
    // 5. FÁBRICAS CONCRETAS
    // ==========================================
    public static class WindowsUIFactory implements UIFactory {
        @Override
        public Button createButton() {
            return new WindowsButton();
        }

        @Override
        public Checkbox createCheckbox() {
            return new WindowsCheckbox();
        }

        @Override
        public Scrollbar createScrollbar() {
            return new WindowsScrollbar();
        }
    }

    public static class MacUIFactory implements UIFactory {
        @Override
        public Button createButton() {
            return new MacButton();
        }

        @Override
        public Checkbox createCheckbox() {
            return new MacCheckbox();
        }

        @Override
        public Scrollbar createScrollbar() {
            return new MacScrollbar();
        }
    }

    // ==========================================
    // 6. CLIENTE (La Aplicación)
    // ==========================================
    public static class Application {
        private final Button button;
        private final Checkbox checkbox;
        private final Scrollbar scrollbar;

        // La aplicación recibe la fábrica inyectada según el SO detectado
        public Application(UIFactory factory) {
            this.button = factory.createButton();
            this.checkbox = factory.createCheckbox();
            this.scrollbar = factory.createScrollbar();
        }

        // Simula la interacción del usuario con la UI
        public void simulateUserInteraction() {
            System.out.println("--- Renderizando Ventana de la App ---");
            button.click();
            checkbox.check();
            scrollbar.scroll();
            System.out.println("--- Fin de la Interacción ---\n");
        }
    }

    // ==========================================
    // 7. DEMOSTRACIÓN (MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // Simulamos que el sistema detecta que corre en Windows
        System.out.println(">>> Inicializando App en entorno Windows");
        UIFactory winFactory = new WindowsUIFactory();
        Application windowsApp = new Application(winFactory);
        windowsApp.simulateUserInteraction();

        // Simulamos que el sistema detecta que corre en MacOS
        System.out.println(">>> Inicializando App en entorno MacOS");
        UIFactory macFactory = new MacUIFactory();
        Application macApp = new Application(macFactory);
        macApp.simulateUserInteraction();
    }
}
