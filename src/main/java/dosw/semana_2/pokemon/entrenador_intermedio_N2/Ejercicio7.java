package src.main.dosw.semana_2.pokemon.entrenador_intermedio_N2;

import java.util.List;

/**PATRÓN: ORDENAMIENTO BÁSICO (SORTED)
 * Uso:
 * "Dada una lista desordenada, organiza sus elementos según su orden
 * natural (alfabéticamente para textos, de menor a mayor para números)".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista.
 * 2. .sorted() -> Ordena los elementos por defecto. Si necesitas un orden
 *    inverso o por un atributo, aquí dentro usarías un 'Comparator'.
 * 3. .toList() -> Empaca los elementos ya ordenados en una nueva lista.
 */

public class Ejercicio7 {

    /**
     * Toma una lista desordenada con los nombres de los Pokémon y utiliza
     * sorted() para organizarlos alfabéticamente, tal como lo solicitó
     * el Profesor Oak.
     */

    public static void ejercicio7() {
        List<String> pokemones = List.of(
                "Squirtle", "Pikachu", "Mewtwo",
                "Bulbasaur", "Charmander", "Abra"
        );

        List<String> ordenados = pokemones.stream()
                .sorted()
                .toList();

        System.out.println(ordenados);
    }

    public static void main(String[] args) {
        ejercicio7();
    }
}