package dosw.semana_3.ejercicios_diapositivas.patrones.estructurales.adapter;

public class MediaAdapterSystem {

    // ==========================================
    // 1. INTERFAZ DEL CLIENTE (Lo que el Reproductor entiende)
    // ==========================================
    public interface MediaPlayer {
        void reproducir(String archivo);
    }

    // ==========================================
    // 2. SERVICIOS INCOMPATIBLES (Adaptees - Librerías Externas)
    // ==========================================
    
    // Librería antigua de VLC (Requiere saber el formato explícitamente)
    public static class VlcEngineLegacy {
        public void playMedia(String path, String format) {
            System.out.println("VlcEngine [Librería Externa]: Reproduciendo '" + path + "' usando códec " + format.toUpperCase());
        }
    }

    // Decodificador Nativo MP4
    public static class Mp4NativeDecoder {
        public void startDecoding(String filePath) {
            System.out.println("Mp4Decoder [Librería Externa]: Extrayendo frames y audio de '" + filePath + "'...");
        }
    }

    // ==========================================
    // 3. ADAPTADORES (Los Traductores)
    // ==========================================
    
    // Adaptador para el motor de VLC
    public static class VlcAdapter implements MediaPlayer {
        private VlcEngineLegacy vlcEngine;

        public VlcAdapter(VlcEngineLegacy vlcEngine) {
            this.vlcEngine = vlcEngine;
        }

        @Override
        public void reproducir(String archivo) {
            System.out.println("Adaptador [VLC]: Preparando el archivo...");
            // Como el motor VLC exige el formato por separado, lo extraemos del string del archivo
            String format = "desconocido";
            if(archivo.contains(".")) {
                format = archivo.substring(archivo.lastIndexOf(".") + 1);
            }
            
            // Llamamos a la interfaz ajena con los dos parámetros que exige
            vlcEngine.playMedia(archivo, format);
        }
    }

    // Adaptador para el decodificador MP4
    public static class Mp4Adapter implements MediaPlayer {
        private Mp4NativeDecoder mp4Decoder;

        public Mp4Adapter(Mp4NativeDecoder mp4Decoder) {
            this.mp4Decoder = mp4Decoder;
        }

        @Override
        public void reproducir(String archivo) {
            System.out.println("Adaptador [MP4]: Enlazando flujo de video...");
            // Llamamos a la interfaz ajena
            mp4Decoder.startDecoding(archivo);
        }
    }

    // ==========================================
    // 4. CLIENTE CENTRAL (El Reproductor Base)
    // ==========================================
    public static class AdvancedAudioPlayer {
        // El reproductor es "tonto", solo sabe decirle al MediaPlayer que reproduzca algo.
        public void escucharMusica(MediaPlayer formatoAdapter, String nombreArchivo) {
            System.out.println("\n--- Intentando reproducir: " + nombreArchivo + " ---");
            formatoAdapter.reproducir(nombreArchivo);
            System.out.println("--- Fin de la reproducción ---");
        }
    }

    // ==========================================
    // 5. DEMOSTRACIÓN (MainClass)
    // ==========================================
    public static void main(String[] args) {
        AdvancedAudioPlayer reproductor = new AdvancedAudioPlayer();

        // 1. El usuario intenta reproducir un archivo MKV pesado (Usará el motor VLC)
        VlcEngineLegacy vlcLib = new VlcEngineLegacy();
        MediaPlayer mkvAdapter = new VlcAdapter(vlcLib);
        reproductor.escucharMusica(mkvAdapter, "pelicula_matrix.mkv");

        // 2. El usuario intenta reproducir un archivo MP4 nativo
        Mp4NativeDecoder mp4Lib = new Mp4NativeDecoder();
        MediaPlayer mp4Adapter = new Mp4Adapter(mp4Lib);
        reproductor.escucharMusica(mp4Adapter, "concierto_en_vivo.mp4");
        
        // El reproductor central hizo exactamente la misma llamada 'reproducir(archivo)' en ambos casos.
    }
}
