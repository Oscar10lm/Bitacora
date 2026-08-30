package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.stream.Collectors;

/**PATRÓN: RECORTAR / LIMITAR LA LISTA (TOP N)
 * Uso:
 * "Dada una lista, quédate únicamente con los primeros N elementos
 * y descarta absolutamente todo lo demás".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista.
 * 2. .limit(N) -> Corta el flujo, solo deja pasar los primeros N elementos.
 * 3. .collect(Collectors.toList()) -> Empaca ese top en una nueva lista.
 */

public class Ejercicio9 {

    /**
     * Toma los primeros 5 elementos de una lista de puntajes para
     * crear un Top 5 y guardarlo.
     */

    public static void ejercicio9 () {
        List<Integer> puntajes =  List.of(
                980, 950, 910, 890, 870,
                840, 800, 790, 760, 740,
                720, 700, 680, 650, 630,
                600, 580, 550, 520, 500
        );

        List<Integer> top5 = puntajes.stream().limit(5).collect(Collectors.toList());
        System.out.println(top5);
    }

    public static void main(String[] args) {
        ejercicio9();
    }
}
