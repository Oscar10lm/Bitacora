package dosw.semana_5.patrones.ejercicios.ejercicio7_combo1;

public class PCBuilder {
    private String cpu = "Estándar";
    private String ram = "8GB";
    private double precio = 500.0;

    public PCBuilder setCpu(String cpu, double costoExtra) {
        this.cpu = cpu;
        this.precio += costoExtra;
        return this;
    }

    public PCBuilder setRam(String ram, double costoExtra) {
        this.ram = ram;
        this.precio += costoExtra;
        return this;
    }

    public PC build() {
        return new PCBasico(cpu, ram, precio);
    }
}
