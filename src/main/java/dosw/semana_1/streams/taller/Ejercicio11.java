package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.Comparator;
import java.util.Optional;

public class Ejercicio11 {

    /**
     * Busca y obtiene el precio más bajo de toda la lista,
     * usando .get() al final para sacar el número de la caja Optional.
     */

    public static void ejercicio11 () {
        List<Integer> precios = List.of(12000, 5000, 18000, 7500, 3000);

        Optional<Integer> precioMinimo = precios.stream().min(Comparator.naturalOrder());
        System.out.println("Precio minimo: " + precioMinimo.get());
    }

    public static void main(String[] args) {
        ejercicio11();
    }
}
