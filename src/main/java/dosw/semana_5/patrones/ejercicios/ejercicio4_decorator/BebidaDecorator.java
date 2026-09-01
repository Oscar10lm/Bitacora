package dosw.semana_5.patrones.ejercicios.ejercicio4_decorator;

public abstract class BebidaDecorator implements Bebida {
    protected Bebida bebidaEnvuelta;

    public BebidaDecorator(Bebida bebida) {
        this.bebidaEnvuelta = bebida;
    }

    @Override
    public String getDescripcion() {
        return bebidaEnvuelta.getDescripcion();
    }

    @Override
    public double getCosto() {
        return bebidaEnvuelta.getCosto();
    }
}
