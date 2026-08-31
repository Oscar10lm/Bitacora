package dosw.semana_2.pokemon.lider_de_gimnasio_N3;

import java.util.List;
import java.util.OptionalDouble;

public class Ejercicio11 {

    /**
     * Extrae el poder de combate de cada Pokémon en la lista, calcula su
     * promedio global e imprime el resultado redondeado a dos decimales.
     */

    public static void ejercicio11() {
        // Utilizamos la misma lista de objetos basada en la clase Pokemon
        List<Pokemon> pokemones = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 50, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 15, 210.0, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 45, 495.0, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 50, 610.0, "Kanto", false)
        );

        double promedio = pokemones.stream()
                .mapToDouble(Pokemon::getPoderCombate)
                .average()
                .getAsDouble();

        // Se usa printf para limitar la salida a 2 decimales y cumplir con la imagen
        System.out.printf("Poder de combate promedio: %.2f\n", promedio);
    }

    public static void main(String[] args) {
        ejercicio11();
    }
}
