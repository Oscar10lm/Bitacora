package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class Ejercicio5 {

    /**
     * Extrae los correos de una lista con duplicados y los empaqueta en un Set
     * (el cual por naturaleza rechaza repetidos) manteniendo su orden original.
     */

    public static void ejercicio5 () {
        List<String> correos = List.of(
                "a@correo.com", "b@correo.com",
                "a@correo.com", "c@correo.com",
                "b@correo.com"
        );

        Set<String> correosUnicos = correos.stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(correosUnicos);
    }

    public static void main(String[] args) {
        ejercicio5();
    }
}
