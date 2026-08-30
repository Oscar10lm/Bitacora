package dosw.semana_1.streams.taller;

import java.util.List;

/**PATRÓN: COMPROBACIÓN DE EXISTENCIA (¿HAY ALGUNO?)
 * Uso:
 * "Dada una lista, saber si AL MENOS UN elemento cumple con una condición.
 * Te responde con un booleano (true/false) inmediato".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista.
 * 2. .anyMatch( e -> condición ) -> Devuelve true en el instante que encuentra el primero.
 *    Cierra el proceso.
 */

public class Ejercicio13 {

    /**
     * Verifica si existe por lo menos un número par en toda la lista.
     * Apenas vea el "20", dejará de buscar y devolverá true.
     */

    public static void ejercicio13 () {
        List<Integer> numeros = List.of(7, 11, 13, 20, 25);
        boolean par = numeros.stream().anyMatch(n -> n % 2 == 0);
        System.out.println("¿Hay algún par?: " + par);
    }

    public static void main(String[] args) {
        ejercicio13();
    }
}
