package dosw.semana_5.patrones.ejercicios.ejercicio8_combo2;

import java.util.ArrayList;
import java.util.List;

public class Categoria implements MenuComponente {
    private String nombre;
    private List<MenuComponente> componentes = new ArrayList<>();

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public void agregar(MenuComponente componente) {
        componentes.add(componente);
    }

    public List<MenuComponente> getComponentes() {
        return componentes;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void imprimir(int nivel) {
        String sangria = "  ".repeat(nivel);
        System.out.println(sangria + "[" + nombre.toUpperCase() + "]");
        for (MenuComponente c : componentes) {
            c.imprimir(nivel + 1);
        }
    }
}
