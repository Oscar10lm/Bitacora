package dosw.semana_5.patrones.ejercicios.ejercicio2_composite;

import java.util.ArrayList;
import java.util.List;

public class Carpeta implements Componente {
    private String nombre;
    private List<Componente> hijos = new ArrayList<>();

    public Carpeta(String nombre) {
        this.nombre = nombre;
    }

    public void agregar(Componente componente) {
        hijos.add(componente);
    }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public int getTamano() {
        int tamanoTotal = 0;
        for (Componente c : hijos) {
            tamanoTotal += c.getTamano();
        }
        return tamanoTotal;
    }

    @Override
    public void mostrarEstructura(String sangria) {
        System.out.println(sangria + "📁 " + nombre);
        for (Componente c : hijos) {
            c.mostrarEstructura(sangria + "  ");
        }
    }
}
