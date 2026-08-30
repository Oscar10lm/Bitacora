package src.main.dosw.semana_1.streams.taller;

import java.util.List;

/**PATRÓN: COMPROBACIÓN TOTAL (¿TODOS CUMPLEN?)
 * Uso:
 * "Dada una lista, confirmar obligatoriamente que TODOS y cada uno de los
 * elementos cumplen con una regla. Si uno solo falla, devuelve false".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista.
 * 2. .allMatch( e -> condición ) -> Revisa a todos. Si todos dan true, devuelve true.
 *    Cierra el proceso.
 */

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