package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.Comparator;
import java.util.Optional;

public class Ejercicio12 {

    /**
     * Busca y obtiene el salario más alto de toda la lista,
     * usando .get() al final para extraer el número.
     */

    public static void ejercicio12 () {
        List<Integer> salarios = List.of(1800000, 2500000, 3200000,
                2100000, 4000000);
        Optional<Integer> salarioMaximo = salarios.stream().max(Comparator.naturalOrder());
        System.out.println("Salario máximo: " + salarioMaximo.get());
    }

    public static void main(String[] args) {
        ejercicio12();
    }
}
