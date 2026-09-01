package dosw.semana_5.patrones.ejercicios.ejercicio14_combo_supremo;

public class PiezaBase implements ComponenteRobot {
    private String nombre;
    private double peso;

    public PiezaBase(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    @Override
    public String ensamblar() {
        return nombre;
    }

    @Override
    public double calcularPeso() {
        return peso;
    }
}
