package dosw.semana_1.streams.taller;

import java.util.List;

public class Ejercicio14 {

    /**
     * Verifica si absolutamente todas las notas de la lista
     * son mayores o iguales a 3.0 (todas aprobadas).
     */

    public static void ejercicio14 () {
        List<Double> notas = List.of(4.0, 3.5, 4.2, 5.0, 3.8);
        boolean aprobadas = notas.stream().allMatch(n -> n >= 3.0);
        System.out.println("¿Todas >= 3.0? " + aprobadas);
    }

    public static void main(String[] args) {
        ejercicio14();
    }
}
