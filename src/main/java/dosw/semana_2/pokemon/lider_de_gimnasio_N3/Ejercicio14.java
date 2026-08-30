package dosw.semana_2.pokemon.lider_de_gimnasio_N3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**PATRÓN: AGRUPACIÓN DE ELEMENTOS (GROUPINGBY)
 * Uso:
 * "Dada una lista de objetos, organizarlos en subgrupos o categorías
 * basándose en un atributo en común (en este caso, la región),
 * creando un Diccionario o Mapa".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista de objetos.
 * 2. .collect(Collectors.groupingBy( llave, valor_opcional )) -> Cierra el proceso agrupando.
 *    - 'llave' (Pokemon::getRegion): Es el atributo por el cual vas a agrupar.
 *    - 'valor' (Collectors.mapping(...)): Se anida para extraer solo el nombre y evitar guardar el objeto completo.
 */

public class Ejercicio14 {

    /**
     * Agrupa a los Pokémon basándose en su atributo 'region' y extrae
     * únicamente sus nombres para generar listas limpias separadas por cada región.
     */

    public static void ejercicio14() {
        // Se utilizan los datos de entrada de la imagen, rellenando los demás
        // atributos de la clase Pokemon con valores simulados para instanciarlos.
        List<Pokemon> pokemones = List.of(
                new Pokemon(1L, "Pikachu", "Eléctrico", 5, 320.0, "Kanto", false),
                new Pokemon(2L, "Chikorita", "Planta", 5, 318.0, "Johto", false),
                new Pokemon(3L, "Torchic", "Fuego", 5, 314.0, "Hoenn", false),
                new Pokemon(4L, "Piplup", "Agua", 5, 314.0, "Sinnoh", false),
                new Pokemon(5L, "Charmander", "Fuego", 5, 309.0, "Kanto", false),
                new Pokemon(6L, "Totodile", "Agua", 5, 314.0, "Johto", false)
        );

        Map<String, List<String>> agrupadosPorRegion = pokemones.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getRegion,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));

        // Se recorre el Mapa generado para imprimir el formato exacto de la salida
        agrupadosPorRegion.forEach((region, lista) ->
                System.out.println(region + ": " + lista)
        );
    }

    public static void main(String[] args) {
        ejercicio14();
    }
}
