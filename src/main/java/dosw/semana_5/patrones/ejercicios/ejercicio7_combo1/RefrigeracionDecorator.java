package dosw.semana_5.patrones.ejercicios.ejercicio7_combo1;

public class RefrigeracionDecorator extends PCDecorator {
    public RefrigeracionDecorator(PC pc) {
        super(pc);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Refrigeración Líquida";
    }

    @Override
    public double getPrecio() {
        return super.getPrecio() + 150.0; // Costo extra
    }
}
