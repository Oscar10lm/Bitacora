package dosw.semana_2.pokemon.entrenador_intermedio_N2;

import java.util.List;

/**PATRÓN: ELIMINACIÓN RÁPIDA DE DUPLICADOS (DISTINCT)
 * Uso:
 * "Dada una lista con elementos repetidos, limpiar la colección filtrando
 * automáticamente cualquier elemento que ya exista, para que quede solo una copia".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista.
 * 2. .distinct() -> Identifica y bloquea los elementos duplicados (usa el método equals() por debajo).
 * 3. .toList() -> Empaca los elementos únicos resultantes en una nueva lista.
 */

public class Ejercicio6 {

    /**
     * Toma una lista de Pokémon con registros repetidos y utiliza distinct()
     * para generar una Pokédex limpia donde cada especie aparece una sola vez.
     */

    public static void ejercicio6() {
        List<String> pokemones = List.of(
                "Pikachu", "Charmander", "Pikachu",
                "Squirtle", "Charmander", "Mewtwo"
        );

        List<String> pokedexLimpia = pokemones.stream()
                .distinct()
                .toList();

        System.out.println(pokedexLimpia);
    }

    public static void main(String[] args) {
        ejercicio6();
    }
}
