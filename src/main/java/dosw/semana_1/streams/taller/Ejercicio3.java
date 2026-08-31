package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio3 {

    /**
     * Toma una lista de ciudades y transforma cada texto a mayúsculas,
     * guardando el resultado en una lista nueva.
     */

    public static void ejercicio3 () {
        List<String> ciudades = List.of(
                "Bogotá", "Medellín", "Cali",
                "Barranquilla"
        );

        List<String> ciudadesMay = ciudades.stream().map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(ciudadesMay);
    }

    public static void main(String[] args) {
        ejercicio3();
    }
}
