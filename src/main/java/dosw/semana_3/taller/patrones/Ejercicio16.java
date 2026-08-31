package dosw.semana_3.taller.patrones;

public class Ejercicio16 {

    // --- IMPLEMENTACIÓN DEL PATRÓN BRIDGE ---

    // 1. La Jerarquía de Implementación (Algoritmos de compresión)
    public interface Compressor {
        byte[] compress(String data);
    }

    public static class MP3Compressor implements Compressor {
        @Override
        public byte[] compress(String data) {
            System.out.println("Comprimiendo audio con formato MP3...");
            return data.getBytes();
        }
    }

    public static class H264Compressor implements Compressor {
        @Override
        public byte[] compress(String data) {
            System.out.println("Comprimiendo video con formato H.264...");
            return data.getBytes();
        }
    }

    public static class NoCompression implements Compressor {
        @Override
        public byte[] compress(String data) {
            System.out.println("Dejando texto en texto plano...");
            return data.getBytes();
        }
    }

    // 2. La Jerarquía de Abstracción (Tipos de mensajes)
    public static abstract class Message {
        // EL PUENTE: La abstracción contiene una referencia a la implementación
        protected Compressor compressor;

        public Message(Compressor compressor) {
            this.compressor = compressor;
        }

        public abstract void send(String content);
    }

    public static class VoiceMessage extends Message {
        public VoiceMessage(Compressor compressor) {
            super(compressor);
        }

        @Override
        public void send(String content) {
            System.out.println("Preparando mensaje de voz...");
            byte[] compressedData = compressor.compress(content);
            System.out.println("Enviando bytes de voz por la red...\n");
        }
    }

    public static class VideoMessage extends Message {
        public VideoMessage(Compressor compressor) {
            super(compressor);
        }

        @Override
        public void send(String content) {
            System.out.println("Preparando mensaje de video...");
            byte[] compressedData = compressor.compress(content);
            System.out.println("Enviando bytes de video por la red...\n");
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        
        // ¡Combinamos las dimensiones libremente en tiempo de ejecución!

        // Mensaje de voz comprimido en MP3
        Compressor mp3 = new MP3Compressor();
        Message voice = new VoiceMessage(mp3);
        voice.send("Hola, ¿cómo estás?");

        // Mensaje de video comprimido en H264
        Compressor h264 = new H264Compressor();
        Message video = new VideoMessage(h264);
        video.send("[Datos crudos del video de la cámara]");

        // Si mañana llega el formato AAC para voz, solo creamos la clase AACCompressor
        // y se lo inyectamos al VoiceMessage original. No tuvimos que crear un "VoiceMessageAAC".
    }
}
