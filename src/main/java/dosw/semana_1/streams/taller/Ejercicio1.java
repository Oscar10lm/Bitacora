package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.stream.Collectors;

    /**PATRÓN: FILTRADO Y CREACIÓN DE NUEVA LISTA
     * Uso:
     * "Dada una lista, saca los elementos que cumplan X condición y guárdalos".
     *
     * ESTRUCTURA CLAVE:
     * 1. .stream() -> Abre la lista para empezar a procesar elemento por elemento.
     * 2. .filter( e -> condición ) -> Tu condición 'if'. Solo pasan los que den true.
     * 3. .collect(Collectors.toList()) -> Cierra el proceso empacando los que pasaron en una nueva List.
     */

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
