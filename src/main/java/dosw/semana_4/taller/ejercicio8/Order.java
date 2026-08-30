package dosw.semana_4.taller.ejercicio8;

import java.util.ArrayList;
import java.util.List;

public class Order {
    // Campos inmutables del pedido
    private final Size size;
    private final Meat meat;
    private final List<String> toppings;
    private final List<String> sides;

    // Observers
    private final List<OrderObserver> observers = new ArrayList<>();

    // Constructor accesible solo para el Builder
    protected Order(Size size, Meat meat, List<String> toppings, List<String> sides) {
        this.size = size;
        this.meat = meat;
        this.toppings = new ArrayList<>(toppings);
        this.sides = new ArrayList<>(sides);
    }

    public Size getSize() { return size; }
    public Meat getMeat() { return meat; }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void confirm() {
        System.out.println("\n[Order] El pedido ha sido confirmado por el cliente. Notificando subsistemas...");
        for (OrderObserver observer : observers) {
            observer.onOrderConfirmed(this);
        }
    }
}
