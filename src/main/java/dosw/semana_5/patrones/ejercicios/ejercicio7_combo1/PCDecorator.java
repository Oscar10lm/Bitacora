package dosw.semana_5.patrones.ejercicios.ejercicio7_combo1;

public abstract class PCDecorator implements PC {
    protected PC pcEnvoltura;

    public PCDecorator(PC pc) {
        this.pcEnvoltura = pc;
    }

    @Override
    public String getDescripcion() {
        return pcEnvoltura.getDescripcion();
    }

    @Override
    public double getPrecio() {
        return pcEnvoltura.getPrecio();
    }
}
