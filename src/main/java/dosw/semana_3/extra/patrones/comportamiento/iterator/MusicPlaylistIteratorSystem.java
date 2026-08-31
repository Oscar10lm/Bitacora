package dosw.semana_3.extra.patrones.comportamiento.iterator;

public class MusicPlaylistIteratorSystem {

    // ==========================================
    // 1. EL OBJETO DE DATOS (La Canción)
    // ==========================================
    public static class Song {
        private String title;
        private String artist;

        public Song(String title, String artist) {
            this.title = title;
            this.artist = artist;
        }

        public String getTitle() { return title; }
        public String getArtist() { return artist; }

        @Override
        public String toString() {
            return "'" + title + "' por " + artist;
        }
    }

    // ==========================================
    // 2. LA INTERFAZ ITERADORA (Iterator)
    // ==========================================
    public interface PlaylistIterator {
        boolean hasMore();
        Song getNext();
    }

    // ==========================================
    // 3. LA INTERFAZ COLECCIÓN (Iterable)
    // ==========================================
    public interface Playlist {
        PlaylistIterator createIterator();
    }

    // ==========================================
    // 4. COLECCIÓN CONCRETA (La Lista de Reproducción real)
    // ==========================================
    // NOTA: Para demostrar la independencia, esta vez guardaremos las canciones
    // en un Arreglo Primitivo (Array) de tamaño fijo, en vez de un ArrayList.
    public static class RockPlaylist implements Playlist {
        
        private Song[] songs;
        private int count;

        public RockPlaylist() {
            // Un arreglo fijo tradicional
            songs = new Song[4]; 
            count = 0;
            
            // Llenamos la colección
            addSong(new Song("Canción 1 - Bohemian Rhapsody", "Queen"));
            addSong(new Song("Canción 2 - Stairway to Heaven", "Led Zeppelin"));
            addSong(new Song("Canción 3 - Hotel California", "Eagles"));
            addSong(new Song("Canción 4 - Sweet Child O' Mine", "Guns N' Roses"));
        }

        private void addSong(Song song) {
            if (count < songs.length) {
                songs[count] = song;
                count++;
            }
        }

        public Song[] getSongs() {
            return songs;
        }

        @Override
        public PlaylistIterator createIterator() {
            // Retorna un iterador configurado para leer Arreglos (Arrays)
            return new ArrayPlaylistIterator(this);
        }
    }

    // ==========================================
    // 5. ITERADOR CONCRETO
    // ==========================================
    // Este iterador específico sabe cómo recorrer un Arreglo primitivo.
    public static class ArrayPlaylistIterator implements PlaylistIterator {
        
        private RockPlaylist playlist;
        private int currentPosition = 0;

        public ArrayPlaylistIterator(RockPlaylist playlist) {
            this.playlist = playlist;
        }

        @Override
        public boolean hasMore() {
            // Verifica que no nos salgamos del límite del arreglo y que no sea nulo
            return currentPosition < playlist.getSongs().length && 
                   playlist.getSongs()[currentPosition] != null;
        }

        @Override
        public String getNext() {
            if (this.hasMore()) {
                Song song = playlist.getSongs()[currentPosition];
                currentPosition++;
                return song;
            }
            return null;
        }
    }

    // ==========================================
    // 6. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> ABRIENDO REPRODUCTOR DE MÚSICA <<<");

        // 1. El cliente (El reproductor visual) obtiene la colección
        Playlist miListaDeRock = new RockPlaylist();

        // 2. Extraemos su Iterador
        PlaylistIterator reproductorActivo = miListaDeRock.createIterator();

        System.out.println("\n--- Presionando PLAY ---");
        
        // 3. El cliente itera sin saber que por detrás hay un Song[] estricto
        while (reproductorActivo.hasMore()) {
            Song cancionActual = reproductorActivo.getNext();
            
            System.out.println("\u25B6 Reproduciendo ahora: " + cancionActual.toString());
            
            // Simulamos que la canción dura un momento
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n--- FIN DE LA LISTA DE REPRODUCCIÓN ---");
    }
}
