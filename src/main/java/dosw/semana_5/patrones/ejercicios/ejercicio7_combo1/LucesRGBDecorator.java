package dosw.semana_5.patrones.ejercicios.ejercicio7_combo1;

public class LucesRGBDecorator extends PCDecorator {
    public LucesRGBDecorator(PC pc) {
        super(pc);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " + Luces RGB Gamer";
    }

    @Override
    public double getPrecio() {
        return super.getPrecio() + 50.0; // Costo extra de las luces
    }
}
