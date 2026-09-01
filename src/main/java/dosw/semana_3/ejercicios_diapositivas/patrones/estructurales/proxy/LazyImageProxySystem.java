package dosw.semana_3.ejercicios_diapositivas.patrones.estructurales.proxy;

public class LazyImageProxySystem {

    // ==========================================
    // 1. LA INTERFAZ SUJETO (Compartida por Real y Proxy)
    // ==========================================
    public interface Image {
        void display();
    }

    // ==========================================
    // 2. EL SUJETO REAL (El objeto pesado original)
    // ==========================================
    public static class RealImage implements Image {
        private String filename;

        public RealImage(String filename) {
            this.filename = filename;
            loadFromDisk(); // Operación pesada que ocurre al instanciar
        }

        private void loadFromDisk() {
            System.out.println("  [VRAM]: Cargando imagen de alta resolución desde el disco duro... (" + filename + ")");
            try {
                Thread.sleep(1500); // Simulamos que es pesado (1.5 segundos)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void display() {
            System.out.println("  [PANTALLA]: Mostrando imagen (" + filename + ") renderizada a 4K.");
        }
    }

    // ==========================================
    // 3. EL PROXY (El Sustituto Inteligente)
    // ==========================================
    public static class ProxyImage implements Image {
        private String filename;
        // El proxy mantiene una referencia al objeto real, inicializada en nulo
        private RealImage realImage;

        public ProxyImage(String filename) {
            this.filename = filename;
            // ¡NO cargamos la imagen en el constructor!
        }

        @Override
        public void display() {
            // Lazy Initialization (Carga Diferida)
            if (realImage == null) {
                System.out.println("  [Proxy]: Es la primera vez que piden la imagen. Instanciando RealImage...");
                // Solo ahora pagamos el costo de cargarla
                realImage = new RealImage(filename);
            } else {
                System.out.println("  [Proxy]: La imagen ya está en memoria. Mostrando caché.");
            }
            
            // Finalmente, delegamos el trabajo al objeto real
            realImage.display();
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        System.out.println(">>> INICIANDO GALERÍA DE FOTOS (Virtual Proxy) <<<\n");

        // El usuario abre la galería con 3 fotos
        // Gracias al proxy, esta creación es INSTANTÁNEA
        Image foto1 = new ProxyImage("vacaciones_playa.jpg");
        Image foto2 = new ProxyImage("boda_hermano.png");
        Image foto3 = new ProxyImage("graduacion.jpg");

        System.out.println("-> Galería cargada en 0.01s (No se han leído archivos de disco aún)\n");

        System.out.println("--- Usuario hace clic en la 'Foto 1' ---");
        // El proxy detecta que es la primera vez y carga el objeto real
        foto1.display(); 

        System.out.println("\n--- Usuario hace clic en la 'Foto 2' ---");
        // El proxy carga la Foto 2 por primera vez
        foto2.display(); 

        System.out.println("\n--- Usuario vuelve a ver la 'Foto 1' ---");
        // ¡Magia! El proxy ya tiene la Foto 1 en memoria, la muestra de inmediato sin leer disco
        foto1.display();
        
        // Nótese que la 'Foto 3' jamás se cargó a memoria porque el usuario nunca la abrió.
    }
}
