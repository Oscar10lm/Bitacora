package dosw.semana_1.streams.taller;

import java.util.List;

    /**PATRÓN: RECORRIDO Y EJECUCIÓN DE ACCIÓN (ITERACIÓN)
     * Uso:
     * "Dada una lista, recorre todos los elementos y ejecuta una acción
     * (como imprimir en consola) por cada uno de ellos, sin crear una nueva lista".
     *
     * ESTRUCTURA CLAVE:
     * 1. .stream() -> Abre la lista para empezar a procesar elemento por elemento.
     * 2. .forEach( e -> acción ) -> Tu ciclo 'for'. Ejecuta el bloque de código indicado para cada elemento. Cierra el proceso y no devuelve nada.
     */

public class Ejercicio2 {

    /**
     * Toma una lista de productos (Strings) y recorre cada uno para
     * imprimir el texto "Producto disponible:" seguido del nombre del producto.
     */

    public static void ejercicio2 () {
        List<String> productos = List.of("Laptop", "Mouse", "Teclado",
                "Monitor", "Impresora"
        );

        productos.stream().forEach(producto ->
                System.out.println("Producto disponible:" + producto));
    }

    public static void main(String[] args) {
        ejercicio2();
    }
}
