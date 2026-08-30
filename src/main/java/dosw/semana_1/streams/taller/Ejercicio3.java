package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.stream.Collectors;

    /**PATRÓN: TRANSFORMACIÓN DE ELEMENTOS (MAPEO)
     * Uso:
     * "Dada una lista, aplica un cambio, cálculo o conversión a cada elemento
     * y guarda los resultados transformados en una lista nueva".
     *
     * ESTRUCTURA CLAVE:
     * 1. .stream() -> Abre la lista para empezar a procesar.
     * 2. .map( transformacion ) -> Reemplaza cada elemento por su nueva versión.
     * 3. .collect(Collectors.toList()) -> Empaca los elementos ya transformados.
     */

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
