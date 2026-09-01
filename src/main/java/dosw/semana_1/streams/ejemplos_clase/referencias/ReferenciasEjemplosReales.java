package dosw.semana_1.streams.ejemplos_clase.referencias;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Ejemplos REALES de Referencias a Métodos — Con contexto del mundo real.
 *
 * La referencia a método (::) es simplemente un atajo.
 * En vez de escribir la lambda completa, apuntas al método directamente.
 *
 *  Lambda normal:       p -> p.getNombre()
 *  Referencia:          Persona::getNombre   ← más limpio y legible
 */
public class ReferenciasEjemplosReales {

    record Producto(String nombre, String categoria, double precio, int stock) {}
    record Empleado(String nombre, String departamento, double salario, int experiencia) {}
    record Pedido(int id, String cliente, double total, String estado) {}

    static List<Producto> productos = List.of(
        new Producto("Laptop HP",       "Electrónica",  3_500_000, 10),
        new Producto("Mouse Logitech",  "Electrónica",    120_000, 50),
        new Producto("Silla Ergonómica","Mobiliario",   1_800_000,  5),
        new Producto("Escritorio",      "Mobiliario",   2_200_000,  3),
        new Producto("Audífonos Sony",  "Electrónica",    450_000, 20),
        new Producto("Webcam HD",       "Electrónica",    380_000, 15)
    );

    static List<Empleado> empleados = List.of(
        new Empleado("Ana Torres",    "Ingeniería",  4_500_000, 5),
        new Empleado("Luis Gómez",    "Marketing",   2_800_000, 2),
        new Empleado("María López",   "Ingeniería",  6_200_000, 8),
        new Empleado("Carlos Ruiz",   "Dirección",   8_000_000, 12),
        new Empleado("Sofía Herrera", "Diseño",      3_100_000, 4)
    );

    static List<Pedido> pedidos = List.of(
        new Pedido(1, "Oscar",   450_000, "ENVIADO"),
        new Pedido(2, "Camila",  120_000, "PENDIENTE"),
        new Pedido(3, "Luis",  3_500_000, "ENTREGADO"),
        new Pedido(4, "María",   890_000, "PENDIENTE"),
        new Pedido(5, "Carlos", 1_200_000, "ENVIADO")
    );

    // ══════════════════════════════════════════════
    // ESCENARIO 1 — Catálogo de productos
    // ══════════════════════════════════════════════
    static void escenario1_CatalogoPaProductos() {
        System.out.println("══════════════════════════════════════════");
        System.out.println("ESCENARIO 1: Catálogo de Productos");
        System.out.println("══════════════════════════════════════════");

        // Sin referencias (verboso):
        // .map(p -> p.nombre())
        // Con referencias (limpio):
        // .map(Producto::nombre)

        System.out.println("\n[1a] Lista de nombres (Producto::nombre):");
        productos.stream()
                 .map(Producto::nombre)          // ← referencia al método nombre()
                 .forEach(System.out::println);  // ← referencia a println

        System.out.println("\n[1b] Ordenados por precio (Producto::precio):");
        productos.stream()
                 .sorted(Comparator.comparingDouble(Producto::precio))
                 .map(p -> String.format("  %-25s $%,.0f", p.nombre(), p.precio()))
                 .forEach(System.out::println);

        System.out.println("\n[1c] Solo electrónica, nombres en mayúscula:");
        productos.stream()
                 .filter(p -> p.categoria().equals("Electrónica"))
                 .map(Producto::nombre)
                 .map(String::toUpperCase)       // ← referencia a método de String
                 .forEach(System.out::println);

        System.out.println("\n[1d] Nombres unidos con coma (Collectors.joining):");
        String catalogo = productos.stream()
                .map(Producto::nombre)
                .collect(Collectors.joining(", "));
        System.out.println("  " + catalogo);
    }

    // ══════════════════════════════════════════════
    // ESCENARIO 2 — Nómina de empleados
    // ══════════════════════════════════════════════
    static void escenario2_Nomina() {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("ESCENARIO 2: Nómina de Empleados");
        System.out.println("══════════════════════════════════════════");

        // Predicate reutilizable con referencia implícita
        Predicate<Empleado> esDeIngenieria = e -> e.departamento().equals("Ingeniería");
        Predicate<Empleado> ganaMasDe5M    = e -> e.salario() > 5_000_000;

        System.out.println("\n[2a] Ingenieros que ganan más de $5M:");
        empleados.stream()
                .filter(esDeIngenieria.and(ganaMasDe5M))
                .map(Empleado::nombre)              // ← referencia
                .forEach(System.out::println);

        System.out.println("\n[2b] Ordenados por experiencia DESC, luego nombre ASC:");
        empleados.stream()
                .sorted(Comparator.comparingInt(Empleado::experiencia)
                        .reversed()
                        .thenComparing(Empleado::nombre))  // ← referencias en Comparator
                .map(e -> e.nombre() + " (" + e.experiencia() + " años)")
                .forEach(System.out::println);

        // Function para formatear el reporte
        Function<Empleado, String> formatoReporte = e ->
                String.format("  %-18s | %-12s | $%,.0f",
                        e.nombre(), e.departamento(), e.salario());

        System.out.println("\n[2c] Reporte completo:");
        System.out.println("  " + "-".repeat(50));
        empleados.stream()
                .sorted(Comparator.comparing(Empleado::nombre))
                .map(formatoReporte)                // ← Function como variable
                .forEach(System.out::println);
        System.out.println("  " + "-".repeat(50));
    }

    // ══════════════════════════════════════════════
    // ESCENARIO 3 — Sistema de pedidos
    // ══════════════════════════════════════════════
    static void escenario3_Pedidos() {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("ESCENARIO 3: Sistema de Pedidos");
        System.out.println("══════════════════════════════════════════");

        // Consumer para imprimir un pedido (reutilizable)
        Consumer<Pedido> imprimirPedido = p ->
                System.out.printf("  [#%d] %-10s → $%,10.0f | %s%n",
                        p.id(), p.cliente(), p.total(), p.estado());

        System.out.println("\n[3a] Todos los pedidos:");
        pedidos.forEach(imprimirPedido);    // ← Consumer como variable

        System.out.println("\n[3b] Solo pedidos PENDIENTES:");
        pedidos.stream()
               .filter(p -> p.estado().equals("PENDIENTE"))
               .forEach(imprimirPedido);   // ← reutilizamos el mismo Consumer

        System.out.println("\n[3c] Total recaudado (reduce):");
        double totalRecaudado = pedidos.stream()
                .mapToDouble(Pedido::total)         // ← referencia a total()
                .sum();
        System.out.printf("  Total: $%,.0f%n", totalRecaudado);

        System.out.println("\n[3d] Clientes con pedidos enviados:");
        pedidos.stream()
               .filter(p -> p.estado().equals("ENVIADO"))
               .map(Pedido::cliente)                // ← referencia a cliente()
               .forEach(System.out::println);
    }

    // ══════════════════════════════════════════════
    // ESCENARIO 4 — Conversión de datos
    // ══════════════════════════════════════════════
    static void escenario4_ConversionDatos() {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("ESCENARIO 4: Conversión de datos");
        System.out.println("══════════════════════════════════════════");

        // Convertir Strings a números con referencia a método estático
        List<String> numerosStr = List.of("100", "250", "80", "430", "150");

        System.out.println("\n[4a] Strings a enteros y filtrar > 200:");
        numerosStr.stream()
                  .map(Integer::parseInt)     // ← referencia a método estático
                  .filter(n -> n > 200)
                  .forEach(System.out::println);

        // Convertir a mayúsculas y ordenar
        List<String> ciudades = List.of("bogotá", "medellín", "cali", "barranquilla");

        System.out.println("\n[4b] Ciudades formateadas y ordenadas:");
        ciudades.stream()
                .map(String::toUpperCase)       // ← método estático del tipo String
                .sorted(String::compareTo)      // ← referencia para comparar
                .forEach(System.out::println);

        // Verificar si todos tienen email válido
        List<String> emails = List.of("oscar@mail.com", "camila@mail.com", "invalido", "juan@mail.com");

        Predicate<String> emailValido = e -> e.contains("@");

        System.out.println("\n[4c] Emails válidos:");
        emails.stream()
              .filter(emailValido)
              .forEach(System.out::println);

        boolean todosValidos = emails.stream().allMatch(emailValido);
        System.out.println("  ¿Todos válidos? " + todosValidos);
    }

    // ═══════════════════════════════════════════════════════
    //                       MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  REFERENCIAS A MÉTODOS — EJEMPLOS REALES ║");
        System.out.println("╚══════════════════════════════════════════╝");

        escenario1_CatalogoPaProductos();
        escenario2_Nomina();
        escenario3_Pedidos();
        escenario4_ConversionDatos();
    }
}
