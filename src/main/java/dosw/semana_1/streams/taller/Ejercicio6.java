package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.stream.Collectors;

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
