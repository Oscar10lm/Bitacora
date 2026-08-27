package src.main.dosw.semana_1.streams.taller;

import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

    /**PATRÓN: ELIMINACIÓN DE DUPLICADOS EN UNA COLECCIÓN ESPECÍFICA (SET)
     * Uso:
     * "Dada una lista con elementos repetidos, guárdalos en una colección
     * que no permita duplicados, pero que mantenga el orden de inserción".
     *
     * ESTRUCTURA CLAVE:
     * 1. .stream() -> Abre la lista.
     * 2. .collect(Collectors.toCollection(Coleccion::new)) -> Empaca el resultado
     *    forzando que el contenedor sea del tipo exacto que necesitas (ej. LinkedHashSet).
     */

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