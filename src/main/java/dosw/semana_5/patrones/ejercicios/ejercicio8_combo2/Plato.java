package dosw.semana_5.patrones.ejercicios.ejercicio8_combo2;

public class Plato implements MenuComponente {
    private String nombre;
    private double precio;

    public Plato(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void imprimir(int nivel) {
        String sangria = "  ".repeat(nivel);
        System.out.println(sangria + "- " + nombre + " ($" + precio + ")");
    }
}
