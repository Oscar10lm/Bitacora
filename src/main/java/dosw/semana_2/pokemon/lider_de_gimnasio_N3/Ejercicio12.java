package dosw.semana_2.pokemon.lider_de_gimnasio_N3;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Ejercicio12 {

    /**
     * Evalúa toda la lista de Pokémon utilizando max() junto con un comparador
     * para encontrar al que tenga el mayor poder de combate (PC) y mostrarlo como Campeón.
     */

    public static void ejercicio12() {
        // Se utilizan los datos de entrada requeridos en la imagen
        List<Pokemon> pokemones = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 50, 320.0, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psíquico", 70, 680.0, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragón", 55, 530.0, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 50, 610.0, "Kanto", false)
        );

        Optional<Pokemon> campeonOpt = pokemones.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate));

        // Se extrae el objeto del Optional y se formatea para cumplir con la salida exacta
        Pokemon campeon = campeonOpt.get();
        System.out.println("Campeón: " + campeon.getNombre() + " con PC: " + (int)campeon.getPoderCombate());
    }

    public static void main(String[] args) {
        ejercicio12();
    }
}
