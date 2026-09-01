package dosw.semana_5.patrones.ejercicios.ejercicio3_builder;

public class PizzaBuilder {
    private Pizza pizza;

    public PizzaBuilder() {
        this.pizza = new Pizza();
    }

    public PizzaBuilder construirMasa(String masa) {
        pizza.setMasa(masa);
        return this;
    }

    public PizzaBuilder construirSalsa(String salsa) {
        pizza.setSalsa(salsa);
        return this;
    }

    public PizzaBuilder construirPepperoni(boolean pepperoni) {
        pizza.setPepperoni(pepperoni);
        return this;
    }

    public Pizza build() {
        return pizza;
    }
}
