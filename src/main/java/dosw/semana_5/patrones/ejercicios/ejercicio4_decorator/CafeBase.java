package dosw.semana_5.patrones.ejercicios.ejercicio4_decorator;

public class CafeBase implements Bebida {
    @Override
    public String getDescripcion() {
        return "Café Base";
    }

    @Override
    public double getCosto() {
        return 5000.0;
    }
}
