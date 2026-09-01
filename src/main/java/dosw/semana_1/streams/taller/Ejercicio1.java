package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio1 {

    /**
     *Toma una lista de nombres (Strings) y extrae únicamente aquellos
     * que comienzan con la letra "A", guardándolos en una lista nueva.
     */

    public static void ejercicio1 () {
        List<String> nombres = List.of(
                "Ana", "Carlos", "Andres","Pedro", "Alejandra",
                "Juan", "Amanda"
        );

        List<String> nombresConA = nombres.stream()
                .filter(nombre -> nombre.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println(nombresConA);
    }

    public static void main(String[] args) {
        ejercicio1();
    }
}
