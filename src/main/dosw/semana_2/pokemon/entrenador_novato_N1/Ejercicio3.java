package src.main.dosw.semana_2.pokemon.entrenador_novato_N1;

import java.util.List;

/**PATRÓN: ACUMULACIÓN / REDUCCIÓN A UN ÚNICO VALOR
 * Uso:
 * "Dada una lista de números, combinarlos todos consecutivamente para
 * obtener un único resultado final (como sumar el nivel de todo un equipo)".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista de números.
 * 2. .reduce( valorInicial, operación ) -> Toma un valor inicial (0 para sumas)
 *    y una operación (Integer::sum) para ir acumulando cada número de la lista
 *    hasta devolver un único entero. Cierra el proceso.
 */

public class Ejercicio3 {

    /**
     * Calcula el poder total de un equipo Pokémon sumando todos los
     * niveles de la lista utilizando la función terminal reduce.
     */

    public static void ejercicio3() {
        List<Integer> niveles = List.of(45, 62, 38, 71, 55, 29);

        int sumaTotal = niveles.stream()
                .reduce(0, Integer::sum);

        System.out.println("Suma total de niveles: " + sumaTotal);
    }

    public static void main(String[] args) {
        ejercicio3();
    }
}