package dosw.semana_2.pokemon.entrenador_novato_N1;

import java.util.List;

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
