package dosw.semana_5.patrones.ejercicios.ejercicio3_builder;

public class Pizza {
    private String masa;
    private String salsa;
    private boolean pepperoni;

    public void setMasa(String masa) { this.masa = masa; }
    public void setSalsa(String salsa) { this.salsa = salsa; }
    public void setPepperoni(boolean pepperoni) { this.pepperoni = pepperoni; }

    @Override
    public String toString() {
        return "Pizza [Masa=" + masa + ", Salsa=" + salsa + ", Pepperoni=" + (pepperoni ? "Sí" : "No") + "]";
    }
}
