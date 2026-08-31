package dosw.semana_5.patrones.ejercicios.ejercicio4_decorator;

public class CarameloDecorator extends BebidaDecorator {
    public CarameloDecorator(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + ", Caramelo";
    }

    @Override
    public double getCosto() {
        return super.getCosto() + 1500.0;
    }
}
