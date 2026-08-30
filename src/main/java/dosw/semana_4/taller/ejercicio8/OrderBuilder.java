package dosw.semana_4.taller.ejercicio8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderBuilder {
    private Size size;
    private Meat meat;
    private final List<String> toppings = new ArrayList<>();
    private final List<String> sides = new ArrayList<>();

    public OrderBuilder setSize(Size size) {
        this.size = size;
        return this;
    }

    public OrderBuilder setMeat(Meat meat) {
        this.meat = meat;
        return this;
    }

    public OrderBuilder addTopping(String... newToppings) {
        this.toppings.addAll(Arrays.asList(newToppings));
        return this;
    }

    public OrderBuilder addSide(String... newSides) {
        this.sides.addAll(Arrays.asList(newSides));
        return this;
    }

    public Order build() {
        // Validación de invariantes (asegura que el pedido esté completo y válido)
        if (size == null) {
            throw new IllegalStateException("El tamaño (Size) es obligatorio.");
        }
        if (meat == null) {
            throw new IllegalStateException("El tipo de carne (Meat) es obligatorio.");
        }
        
        return new Order(size, meat, toppings, sides);
    }
}
