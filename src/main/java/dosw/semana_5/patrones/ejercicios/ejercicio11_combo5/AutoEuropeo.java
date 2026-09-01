package dosw.semana_5.patrones.ejercicios.ejercicio11_combo5;

public class AutoEuropeo {
    private double kilometraje;
    private double capacidadTanqueLitros;
    private String modelo;

    public AutoEuropeo(String modelo, double kilometraje, double capacidadTanqueLitros) {
        this.modelo = modelo;
        this.kilometraje = kilometraje;
        this.capacidadTanqueLitros = capacidadTanqueLitros;
    }

    public double getKilometraje() { return kilometraje; }
    public double getCapacidadTanqueLitros() { return capacidadTanqueLitros; }
    public String getModelo() { return modelo; }
    
    public void mostrarInfo() {
        System.out.println("Auto Europeo [" + modelo + "] -> " + kilometraje + " KM, " + capacidadTanqueLitros + " Litros.");
    }
}
