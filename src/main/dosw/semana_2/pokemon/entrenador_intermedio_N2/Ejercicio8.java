package src.main.dosw.semana_2.pokemon.entrenador_intermedio_N2;

import java.util.List;

/**PATRÓN: FILTRADO POR CONDICIÓN BOOLEANA Y EXTRACCIÓN
 * Uso:
 * "Dada una lista de objetos, descarta los que tengan un atributo booleano
 * en falso, y luego extrae un dato específico (como el nombre) de los que sí cumplen (true)".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista.
 * 2. .filter( Pokemon::puedeEvolucionar ) -> Evalúa la condición. Al ser un booleano, no necesitas poner '== true'. Solo pasan los verdaderos.
 * 3. .map(Pokemon::nombre) -> Extrae el nombre de los que sobrevivieron al filtro.
 * 4. .toList() -> Empaca los nombres en una nueva lista.
 */

public class Ejercicio8 {

    // Record para estructurar los datos de entrada de la imagen
    record Pokemon(String nombre, boolean puedeEvolucionar) {}

    /**
     * Filtra la lista evaluando directamente el atributo booleano 'puedeEvolucionar'
     * para obtener los nombres de aquellos que están listos y hacer match con la salida.
     */

    public static void ejercicio8() {
        List<Pokemon> pokemones = List.of(
                new Pokemon("Pikachu", true),
                new Pokemon("Raichu", false),
                new Pokemon("Charmander", true),
                new Pokemon("Charizard", false),
                new Pokemon("Squirtle", true),
                new Pokemon("Blastoise", false)
        );

        List<String> listos = pokemones.stream()
                .filter(Pokemon::puedeEvolucionar)
                .map(Pokemon::nombre)
                .toList();

        System.out.println("Listos para evolucionar:");
        System.out.println(listos);
    }

    public static void main(String[] args) {
        ejercicio8();
    }
}