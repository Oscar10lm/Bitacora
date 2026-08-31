package dosw.semana_1.streams.taller;

import java.util.List;

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
