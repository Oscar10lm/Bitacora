package dosw.semana_2.pokemon.entrenador_intermedio_N2;

import java.util.List;

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
