package dosw.semana_2.pokemon.lider_de_gimnasio_N3;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio10 {

    /**
     * Toma una lista de objetos Pokemon completos y genera una Pokédex compacta
     * que solo contiene sus nombres, utilizando la operación de mapeo.
     */

    public static void ejercicio10() {
        // Se utiliza la lista de objetos Pokemon previamente definida
        List<Pokemon> pokemones = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 50, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 15, 210.0, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 45, 495.0, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 50, 610.0, "Kanto", false)
        );

        // Para cumplir exactamente con la vista de salida (que incluye comillas dobles en cada String),
        // mapeamos agregando las comillas. Si solo necesitas la lista normal, basta con Pokemon::getNombre.
        List<String> pokedexCompacta = pokemones.stream()
                .map(p -> "\"" + p.getNombre() + "\"")
                .collect(Collectors.toList());

        System.out.println(pokedexCompacta);
    }

    public static void main(String[] args) {
        ejercicio10();
    }
}
