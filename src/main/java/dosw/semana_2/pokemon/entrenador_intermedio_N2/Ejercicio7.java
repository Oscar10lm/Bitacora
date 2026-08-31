package dosw.semana_2.pokemon.entrenador_intermedio_N2;

import java.util.List;

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
