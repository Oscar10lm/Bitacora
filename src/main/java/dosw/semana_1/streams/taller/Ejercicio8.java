package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio8 {

    /**
     * Filtra una lista de códigos usando distinct() para obtener solo
     * los códigos únicos, eliminando los repetidos del resultado final.
     */

    public static void ejercicio8 () {
        List<String> codigos = List.of(
                "P01","P02","P01",
                "P03","P02","P04"
        );

        List<String> codigosUnicos = codigos.stream().distinct().collect(Collectors
                .toList());
        System.out.println(codigosUnicos);
    }

    public static void main(String[] args) {
        ejercicio8();
    }
}
