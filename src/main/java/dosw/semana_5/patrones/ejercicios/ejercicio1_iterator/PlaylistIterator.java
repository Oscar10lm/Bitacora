package dosw.semana_5.patrones.ejercicios.ejercicio1_iterator;

import java.util.Iterator;
import java.util.List;

public class PlaylistIterator implements Iterator<String> {
    private List<String> canciones;
    private int index = 0;

    public PlaylistIterator(List<String> canciones) {
        this.canciones = canciones;
    }

    @Override
    public boolean hasNext() {
        return index < canciones.size();
    }

    @Override
    public String next() {
        if (this.hasNext()) {
            return canciones.get(index++);
        }
        return null;
    }

    public String current() {
        if (index < canciones.size()) {
            return canciones.get(index);
        }
        return "Fin de la lista";
    }
}
