package dosw.semana_5.patrones.ejercicios.ejercicio13_combo7;

import java.util.Iterator;

public class BDAdapter implements Iterable<UsuarioModerno> {
    private BaseDeDatosLegacy legacy;

    public BDAdapter(BaseDeDatosLegacy legacy) {
        this.legacy = legacy;
    }

    @Override
    public Iterator<UsuarioModerno> iterator() {
        return new UsuarioIterator(legacy.getUsuariosString());
    }
}
