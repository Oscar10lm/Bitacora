package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.Comparator;
import java.util.Optional;

/**PATRÓN: BÚSQUEDA DEL VALOR MÁXIMO
 * Uso:
 * "Dada una lista, encontrar el elemento más grande (el mayor número,
 * el sueldo más alto, etc.)".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista.
 * 2. .max(Comparator.naturalOrder()) -> Encuentra el máximo devolviendo un 'Optional'.
 *    Cierra el proceso.
 */

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
