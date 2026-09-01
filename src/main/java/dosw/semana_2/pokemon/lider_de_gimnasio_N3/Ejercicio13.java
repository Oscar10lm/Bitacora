package dosw.semana_2.pokemon.lider_de_gimnasio_N3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio13 {

    /**
     * Agrupa a los Pokémon por su atributo 'tipo' y extrae específicamente
     * sus nombres para generar listas limpias separadas por categoría.
     */

    public static void ejercicio13() {
        // Se utilizan los datos de entrada requeridos en la imagen
        List<Pokemon> pokemones = List.of(
                new Pokemon(1L, "Squirtle", "Agua", 15, 210.0, "Kanto", false),
                new Pokemon(2L, "Psyduck", "Agua", 20, 250.0, "Kanto", false),
                new Pokemon(3L, "Charmander", "Fuego", 15, 220.0, "Kanto", false),
                new Pokemon(4L, "Vulpix", "Fuego", 18, 230.0, "Kanto", false)
        );

        Map<String, List<String>> agrupadosPorTipo = pokemones.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getTipo,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));

        // Se recorre el Mapa generado para imprimir el formato exacto de la salida
        agrupadosPorTipo.forEach((tipo, lista) ->
                System.out.println(tipo + ": " + lista)
        );
    }

    public static void main(String[] args) {
        ejercicio13();
    }
}
