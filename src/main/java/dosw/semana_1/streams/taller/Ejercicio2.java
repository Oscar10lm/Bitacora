package dosw.semana_1.streams.taller;

import java.util.List;

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
