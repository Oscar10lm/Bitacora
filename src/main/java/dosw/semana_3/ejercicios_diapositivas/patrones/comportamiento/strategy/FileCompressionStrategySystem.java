package dosw.semana_3.ejercicios_diapositivas.patrones.comportamiento.strategy;

public class FileCompressionStrategySystem {

    // ==========================================
    // 1. LA INTERFAZ ESTRATEGIA (Strategy)
    // ==========================================
    public interface CompressionStrategy {
        String compress(String fileName);
    }

    // ==========================================
    // 2. ESTRATEGIAS CONCRETAS (Los algoritmos separados)
    // ==========================================
    
    // Algoritmo 1: ZIP (Rápido, compresión moderada)
    public static class ZipCompressionStrategy implements CompressionStrategy {
        @Override
        public String compress(String fileName) {
            System.out.println(" -> Aplicando Estrategia: [ZIP]");
            System.out.println("    - Comprimiendo '" + fileName + "' usando el algoritmo Deflate (Rápido)...");
            return fileName.substring(0, fileName.lastIndexOf('.')) + ".zip";
        }
    }

    // Algoritmo 2: RAR (Lento, alta compresión)
    public static class RarCompressionStrategy implements CompressionStrategy {
        @Override
        public String compress(String fileName) {
            System.out.println(" -> Aplicando Estrategia: [RAR]");
            System.out.println("    - Comprimiendo '" + fileName + "' usando diccionarios LZSS (Lento, Máxima compresión)...");
            return fileName.substring(0, fileName.lastIndexOf('.')) + ".rar";
        }
    }

    // Algoritmo 3: Sin Compresión
    public static class NoCompressionStrategy implements CompressionStrategy {
        @Override
        public String compress(String fileName) {
            System.out.println(" -> Aplicando Estrategia: [SIN COMPRIMIR]");
            System.out.println("    - Manteniendo tamaño original de '" + fileName + "'...");
            return fileName; // Devuelve el nombre intacto
        }
    }

    // ==========================================
    // 3. EL CONTEXTO (Context)
    // ==========================================
    // El Gestor de Subidas (Uploader)
    public static class FileUploader {
        
        private CompressionStrategy compressionStrategy;

        public FileUploader(CompressionStrategy initialStrategy) {
            this.compressionStrategy = initialStrategy;
        }

        // Permite inyectar una nueva estrategia antes de procesar otros archivos
        public void setCompressionStrategy(CompressionStrategy compressionStrategy) {
            this.compressionStrategy = compressionStrategy;
        }

        // Método principal de la clase Contexto
        public void uploadFile(String fileName) {
            System.out.println("Iniciando proceso de subida para: " + fileName);
            
            // 1. Delega la compresión a la estrategia actual
            String processedFile = compressionStrategy.compress(fileName);
            
            // 2. Continúa con la lógica normal que no cambia (Subida al servidor)
            System.out.println("Conectando al servidor AWS S3...");
            System.out.println("Subiendo archivo resultante: " + processedFile);
            System.out.println("[COMPLETADO]\n");
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> GESTOR DE ARCHIVOS EN LA NUBE (Google Drive Clon) <<<\n");

        // 1. Por defecto, el sistema usará ZIP para ahorrar espacio
        FileUploader uploader = new FileUploader(new ZipCompressionStrategy());
        
        // Subimos un archivo de Word
        System.out.println("--- Usuario sube un documento ---");
        uploader.uploadFile("Tesis_Final.docx");

        // 2. El usuario va a subir un video muy pesado y cambia la configuración a RAR
        System.out.println("--- Usuario sube un video pesado (Cambia a RAR) ---");
        uploader.setCompressionStrategy(new RarCompressionStrategy());
        uploader.uploadFile("Video_Boda_4K.mp4");

        // 3. El usuario sube una imagen que no quiere que pierda calidad, así que deshabilita la compresión
        System.out.println("--- Usuario sube una foto profesional (Desactiva compresión) ---");
        uploader.setCompressionStrategy(new NoCompressionStrategy());
        uploader.uploadFile("Foto_Estudio.png");
        
        /*
         * NOTA ARQUITECTÓNICA:
         * La clase 'FileUploader' nunca fue modificada, pero su comportamiento
         * al procesar los archivos cambió radicalmente 3 veces gracias a 
         * que la lógica de compresión se encapsuló en las estrategias.
         */
    }
}
