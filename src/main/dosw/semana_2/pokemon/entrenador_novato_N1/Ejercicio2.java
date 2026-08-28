package src.main.dosw.semana_2.pokemon.entrenador_novato_N1;

import java.util.List;

/**PATRÓN: TRANSFORMACIÓN BÁSICA (MAPEO)
 * Uso:
 * "Dada una lista de elementos (ej. textos), aplica un cambio uniforme
 * a todos y cada uno de ellos (como convertirlos a mayúsculas) y
 * guarda el resultado".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista para empezar a procesar.
 * 2. .map() -> Ejecuta la transformación. Puedes usar la referencia de
 *    método (String::toUpperCase) para que el código quede más limpio.
 * 3. .toList() -> Empaca los textos ya transformados en una nueva lista.
 */

public class Ejercicio2 {

    /**
     * Toma una lista con los nombres de los Pokémon iniciales y utiliza
     * map() para transformar cada cadena de texto a mayúsculas sostenidas.
     */

    public static void ejercicio2() {
        List<String> pokemones = List.of(
                "Pikachu", "Charmander", "Squirtle", "Bulbasaur"
        );

        List<String> pokedexGritona = pokemones.stream()
                .map(String::toUpperCase)
                .toList();

        System.out.println(pokedexGritona);
    }

    public static void main(String[] args) {
        ejercicio2();
    }
}