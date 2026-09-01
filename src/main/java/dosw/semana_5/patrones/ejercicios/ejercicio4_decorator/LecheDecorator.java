package dosw.semana_5.patrones.ejercicios.ejercicio4_decorator;

public class LecheDecorator extends BebidaDecorator {
    public LecheDecorator(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + ", Leche";
    }

    @Override
    public double getCosto() {
        return super.getCosto() + 1000.0;
    }
}
