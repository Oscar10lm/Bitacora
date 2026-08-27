package src.main.dosw.semana_1.streams.taller;

import java.util.List;
import java.util.stream.Collectors;

/**PATRÓN: DEPURACIÓN / ESPIAR EL FLUJO SIN ALTERARLO
 * Uso:
 * "Dada una lista, transfórmala o fíltrala, pero imprime o ejecuta algo
 * en medio del proceso para ver qué está pasando paso a paso".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista.
 * 2. .map() / .filter() -> Tus operaciones normales.
 * 3. .peek( e -> acción ) -> Espía el elemento en ese punto e imprime,
 *    pero lo deja seguir su camino sin modificarlo.
 * 4. .collect(...) -> Empaca el resultado.
 */

public class Ejercicio6 {

    /**
     * Convierte nombres a mayúsculas, pero usa peek() para imprimir en consola
     * cada nombre justo en el momento en que es transformado, antes de guardarlo.
     */

    public static void ejercicio6 () {
        List<String> nombres =  List.of(
                "Laura", "Pedro", "Carlos", "Ana"
        );

        List<String> nombresMay = nombres.stream().map(String::toUpperCase)
                .peek(nombre -> System.out.println("Transformado: " + nombre)).
                collect(Collectors.toList());
        System.out.println("Resultado final: " + nombresMay);
    }

    public static void main(String[] args) {
        ejercicio6();
    }
}