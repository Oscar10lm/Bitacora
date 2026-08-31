package dosw.semana_2.pokemon.entrenador_intermedio_N2;

import java.util.List;

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
