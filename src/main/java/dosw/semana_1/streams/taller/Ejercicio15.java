package dosw.semana_1.streams.taller;

import java.util.List;

/**PATRÓN: COMPROBACIÓN NEGATIVA (¿NINGUNO CUMPLE?)
 * Uso:
 * "Dada una lista, garantizar que NINGÚN elemento cumpla con una condición
 * (asegurarse de que la lista esté 'limpia' de algo)".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista.
 * 2. .noneMatch( e -> condición ) -> Devuelve true SÓLO si ningún elemento coincide.
 *    Cierra el proceso.
 */

public class Ejercicio15 {

    /**
     * Verifica que el usuario "root" NO exista en la lista de nombres.
     * Devuelve true porque efectivamente la lista no lo tiene.
     */

    public static void ejercicio15 () {
        List<String> nombres = List.of(
                "juan", "maria", "admin",
                "pedro", "soporte"
        );
        boolean verificar = nombres.stream().noneMatch(n -> n.equals("root"));
        System.out.println("¿Ninguno es root? " + verificar);
    }

    public static void main(String[] args) {
        ejercicio15();
    }
}
