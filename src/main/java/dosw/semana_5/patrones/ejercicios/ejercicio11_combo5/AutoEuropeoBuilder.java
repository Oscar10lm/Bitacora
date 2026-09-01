package dosw.semana_5.patrones.ejercicios.ejercicio11_combo5;

public class AutoEuropeoBuilder {
    private String modelo = "Genérico";
    private double km = 0;
    private double litros = 40;

    public AutoEuropeoBuilder setModelo(String modelo) { this.modelo = modelo; return this; }
    public AutoEuropeoBuilder setKm(double km) { this.km = km; return this; }
    public AutoEuropeoBuilder setLitros(double litros) { this.litros = litros; return this; }

    public AutoEuropeo build() {
        return new AutoEuropeo(modelo, km, litros);
    }
}
