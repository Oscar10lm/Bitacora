package dosw.semana_2.pokemon.lider_de_gimnasio_N3;

import java.util.List;

public class Ejercicio9 {

    /**
     * Filtra la lista de Pokémon verificando que el poder de combate (PC) sea
     * mayor estricto a 500, ordenando y mapeando el resultado para la salida final.
     */

    public static void ejercicio9() {
        // Se instancian los objetos usando la nueva clase Pokemon
        List<Pokemon> pokemones = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 50, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(4L, "Squirtle", "Agua", 15, 210.0, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 45, 495.0, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 50, 610.0, "Kanto", false)
        );

        List<String> equipoElite = pokemones.stream()
                .filter(p -> p.getPoderCombate() > 500)
                // Orden descendente para asegurar que coincida con la imagen (680, 610, 530)
                .sorted((p1, p2) -> Double.compare(p2.getPoderCombate(), p1.getPoderCombate()))
                .map(p -> p.getNombre() + "(" + (int)p.getPoderCombate() + ")")
                .toList();

        System.out.println("Equipo Élite (PC > 500):");
        System.out.println(equipoElite);
    }

    public static void main(String[] args) {
        ejercicio9();
    }
}
