package dosw.semana_2.pokemon.entrenador_novato_N1;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**PATRÓN: BÚSQUEDA DEL ELEMENTO MÁXIMO (EN OBJETOS)
 * Uso:
 * "Dada una lista de objetos, encontrar aquel que tenga el valor
 * más alto o grande basándose en uno de sus atributos (ej. el nivel)".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista de objetos.
 * 2. .max(Comparator.comparingInt( Clase::metodo )) -> Busca el máximo usando
 *    un comparador enfocado en el atributo numérico que te interesa.
 *    Devuelve un 'Optional' (una caja). Cierra el proceso.
 * 3. .get() -> Extrae el objeto ganador de la caja Optional.
 */

public class Ejercicio4 {

    // Record para estructurar los datos de entrada
    record Pokemon(String nombre, int nivel) {}

    /**
     * Busca dentro del equipo al Pokémon con el nivel más alto utilizando
     * la función max() y lo extrae para imprimir su nombre y nivel.
     */

    public static void ejercicio4() {
        List<Pokemon> pokemones = List.of(
                new Pokemon("Pikachu", 45),
                new Pokemon("Charmander", 62),
                new Pokemon("Squirtle", 38),
                new Pokemon("Snorlax", 90),
                new Pokemon("Mewtwo", 88)
        );

        Optional<Pokemon> pokemonAlfa = pokemones.stream()
                .max(Comparator.comparingInt(Pokemon::nivel));

        // Se extrae el Pokémon de la caja Optional usando .get() y se formatea la salida
        Pokemon alfa = pokemonAlfa.get();
        System.out.println("Pokémon Alfa: " + alfa.nombre() + " (nivel " + alfa.nivel() + ")");
    }

    public static void main(String[] args) {
        ejercicio4();
    }
}
