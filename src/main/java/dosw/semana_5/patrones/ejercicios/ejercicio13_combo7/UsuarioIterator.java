package dosw.semana_5.patrones.ejercicios.ejercicio13_combo7;

import java.util.Iterator;

public class UsuarioIterator implements Iterator<UsuarioModerno> {
    private String[] datosBrutos;
    private int indice = 0;

    public UsuarioIterator(String[] datosBrutos) {
        this.datosBrutos = datosBrutos;
    }

    @Override
    public boolean hasNext() {
        return indice < datosBrutos.length;
    }

    @Override
    public UsuarioModerno next() {
        if (!hasNext()) return null;
        
        String crudo = datosBrutos[indice++];
        // crudo = "id:1|nombre:Ana|rol:Admin"
        String[] partes = crudo.split("\\|");
        int id = Integer.parseInt(partes[0].split(":")[1]);
        String nombre = partes[1].split(":")[1];
        String rol = partes[2].split(":")[1];
        
        return new UsuarioModerno(id, nombre, rol);
    }
}
