package dosw.semana_1.streams.taller;

import java.util.List;

    /**PATRÓN: ACUMULACIÓN / REDUCCIÓN A UN ÚNICO VALOR
     * Uso:
     * "Dada una lista de números o valores, combínalos todos (sumando,
     * multiplicando, etc.) para obtener un único resultado final".
     *
     * ESTRUCTURA CLAVE:
     * 1. .stream() -> Abre la lista.
     * 2. .reduce( valorInicial, operación ) -> Acumula los valores empezando desde
     *    el 'valorInicial' (ej. 0 para sumas) aplicando la operación dada. Cierra el proceso.
     */

public class Ejercicio4 {

    /**
     * Toma una lista de números enteros y los suma todos para obtener
     * el total consolidado en una sola variable int.
     */

    public static void ejercicio4 () {
        List<Integer> numeros = List.of(12, 8, 5, 10, 15);
        int sumaTotal = numeros.stream().reduce(0, Integer::sum);
        System.out.println(sumaTotal);
    }

    public static void main(String[] args) {
        ejercicio4();
    }
}
