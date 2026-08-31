package dosw.semana_5.patrones.ejercicios.ejercicio2_composite;

public class Archivo implements Componente {
    private String nombre;
    private int tamano;

    public Archivo(String nombre, int tamano) {
        this.nombre = nombre;
        this.tamano = tamano;
    }

    @Override
    public String getNombre() { return nombre; }

    @Override
    public int getTamano() { return tamano; }

    @Override
    public void mostrarEstructura(String sangria) {
        System.out.println(sangria + "📄 " + nombre + " (" + tamano + " MB)");
    }
}
