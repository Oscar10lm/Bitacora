package dosw.semana_2.pokemon.entrenador_novato_N1;

import java.util.List;

public class Ejercicio5 {

    // Record para estructurar los datos de entrada
    record Pokemon(String nombre, int nivel) {}

    /**
     * Filtra la lista para contar los Pokémon con nivel superior a 80 utilizando count().
     * Para coincidir exactamente con la salida esperada visual, también se realiza un
     * mapeo para extraer y mostrar sus nombres.
     */

    public static void ejercicio5() {
        List<Pokemon> pokemones = List.of(
                new Pokemon("Pikachu", 45),
                new Pokemon("Mewtwo", 88),
                new Pokemon("Dragonite", 82),
                new Pokemon("Squirtle", 38),
                new Pokemon("Mew", 85),
                new Pokemon("Charmander", 62)
        );

        // Aplicando exactamente el patrón filter() + count() requerido
        long cantidad = pokemones.stream()
                .filter(p -> p.nivel() > 80)
                .count();

        // Operación secundaria para obtener los nombres y cumplir con el formato de salida
        List<String> nombres = pokemones.stream()
                .filter(p -> p.nivel() > 80)
                .map(Pokemon::nombre)
                .toList();

        System.out.println("Pokémon con nivel > 80: " + cantidad);
        System.out.println("(" + String.join(", ", nombres) + ")");
    }

    public static void main(String[] args) {
        ejercicio5();
    }
}
