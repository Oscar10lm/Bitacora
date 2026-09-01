package dosw.semana_5.patrones.extra.ejercicio2;
import dosw.semana_5.patrones.extra.ejercicio2.Ejercicio2.ClaseVuelo;

public class Tiquete {
    private String nombre;
    private int edad;
    private double pesoEquipaje;
    private ClaseVuelo claseVuelo;
    private double precioFinal;

    public Tiquete() {
    }

    public Tiquete conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Tiquete conEdad(int edad) {
        this.edad = edad;
        return this;
    }

    public Tiquete conPeso(double pesoEquipaje) {
        this.pesoEquipaje = pesoEquipaje;
        return this;
    }

    public Tiquete conClaseVuelo (ClaseVuelo clase){
        this.claseVuelo = clase;
        return this;
    }
    public Tiquete conPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
        return this;
    }
}
