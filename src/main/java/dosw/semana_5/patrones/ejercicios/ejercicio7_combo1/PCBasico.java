package dosw.semana_5.patrones.ejercicios.ejercicio7_combo1;

public class PCBasico implements PC {
    private String cpu;
    private String ram;
    private double precioBase;

    public PCBasico(String cpu, String ram, double precioBase) {
        this.cpu = cpu;
        this.ram = ram;
        this.precioBase = precioBase;
    }

    @Override
    public String getDescripcion() {
        return "PC [CPU: " + cpu + ", RAM: " + ram + "]";
    }

    @Override
    public double getPrecio() {
        return precioBase;
    }
}
