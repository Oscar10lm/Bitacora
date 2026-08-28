package src.main.dosw.semana_2.pokemon.entrenador_novato_N1;

import java.util.List;

/**PATRÓN: FILTRADO Y EXTRACCIÓN (PROYECCIÓN)
 * Uso:
 * "Dada una lista de objetos, quédate únicamente con los que cumplan una
 * condición en particular (ej. un tipo específico) y luego extrae solo
 * el atributo que te interesa (el nombre) para la respuesta final".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista de objetos.
 * 2. .filter( p -> p.tipo().equals("Fuego") ) -> Filtra evaluando el atributo.
 * 3. .map(Pokemon::nombre) -> Extrae el nombre de los que pasaron el filtro.
 * 4. .toList() -> Empaca los nombres en una lista.
 */

public class Ejercicio1 {

    // Record para estructurar los datos de entrada de la imagen
    record Pokemon(String nombre, String tipo) {}

    /**
     * Filtra la lista para dejar únicamente a los Pokémon cuyo tipo es "Fuego"
     * y obtiene una lista con sus nombres para cumplir con la salida esperada.
     */

    public static void ejercicio1() {
        List<Pokemon> pokemones = List.of(
                new Pokemon("Pikachu", "Eléctrico"),
                new Pokemon("Charmander", "Fuego"),
                new Pokemon("Squirtle", "Agua"),
                new Pokemon("Vulpix", "Fuego"),
                new Pokemon("Bulbasaur", "Planta"),
                new Pokemon("Flareon", "Fuego")
        );

        List<String> tipoFuego = pokemones.stream()
                .filter(p -> p.tipo().equals("Fuego"))
                .map(Pokemon::nombre)
                .toList();

        System.out.println(tipoFuego);
    }

    public static void main(String[] args) {
        ejercicio1();
    }
}