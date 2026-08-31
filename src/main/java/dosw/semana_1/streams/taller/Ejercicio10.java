package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio10 {

    /**
     * Se salta las 2 primeras películas de la lista y guarda
     * todas las restantes en una lista nueva.
     */

    public static void ejercicio10 () {
        List<String> peliculas = List.of(
                "Avatar", "Titanic",
                "Interestelar",
                "Matrix", "Gladiador"
        );

        List<String> peliculasSkip = peliculas.stream().skip(2)
                .collect(Collectors.toList());
        System.out.println(peliculasSkip);
    }

    public static void main(String[] args) {
        ejercicio10();
    }
}
