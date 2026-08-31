package dosw.semana_1.streams.uso.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Guía práctica de Scanner — Todos los casos que pueden salir en el parcial.
 *
 * REGLA DE ORO:
 * Después de nextInt() o nextDouble(), el ENTER queda atrapado en el buffer.
 * Siempre pon un scanner.nextLine() vacío después para limpiarlo
 * antes de leer un String con nextLine().
 */
public class ScannerGuia {

    // ═══════════════════════════════════════════════════════
    // EJEMPLO 1 — Leer tipos de datos básicos (el más común)
    // ═══════════════════════════════════════════════════════
    static void ejemplo1_DatosBasicos() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingresa tu nombre: ");
        String nombre = sc.nextLine();          // Lee toda la línea (con espacios)

        System.out.print("Ingresa tu edad: ");
        int edad = sc.nextInt();                // Lee entero
        sc.nextLine();                          // ⚠️ Limpia el \n del buffer

        System.out.print("Ingresa tu salario: ");
        double salario = sc.nextDouble();        // Lee decimal
        sc.nextLine();                          // ⚠️ Limpia el \n del buffer

        System.out.print("¿Eres estudiante? (si/no): ");
        String respuesta = sc.nextLine();
        boolean esEstudiante = respuesta.equalsIgnoreCase("si");

        // Resultado
        System.out.println("\n=== Resumen de tu perfil ===");
        System.out.println("Nombre:      " + nombre);
        System.out.println("Edad:        " + edad + " años");
        System.out.printf( "Salario:     $%,.0f%n", salario);
        System.out.println("Estudiante:  " + (esEstudiante ? "✅ Sí" : "❌ No"));

        sc.close();
    }

    // ═══════════════════════════════════════════════════════
    // EJEMPLO 2 — Leer N personas y guardarlas (con Streams)
    // ═══════════════════════════════════════════════════════
    static void ejemplo2_LeerListaPersonas() {
        Scanner sc = new Scanner(System.in);
        record Persona(String nombre, int edad, double salario) {}

        System.out.print("¿Cuántas personas quieres registrar? ");
        int n = sc.nextInt();
        sc.nextLine(); // ⚠️ Limpia el buffer

        List<Persona> personas = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            System.out.println("\n--- Persona " + i + " ---");

            System.out.print("  Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("  Edad: ");
            int edad = sc.nextInt();
            sc.nextLine(); // ⚠️

            System.out.print("  Salario: ");
            double salario = sc.nextDouble();
            sc.nextLine(); // ⚠️

            personas.add(new Persona(nombre, edad, salario));
        }

        // Ahora usamos Streams sobre lo que ingresó el usuario
        System.out.println("\n=== Resultados con Streams ===");

        System.out.println("Mayores de 25:");
        personas.stream()
                .filter(p -> p.edad() > 25)
                .map(Persona::nombre)
                .forEach(n2 -> System.out.println("  - " + n2));

        double promedio = personas.stream()
                .mapToDouble(Persona::salario)
                .average()
                .orElse(0);
        System.out.printf("Salario promedio: $%,.0f%n", promedio);

        sc.close();
    }

    // ═══════════════════════════════════════════════════════
    // EJEMPLO 3 — Menú interactivo con switch (muy típico en parciales)
    // ═══════════════════════════════════════════════════════
    static void ejemplo3_MenuInteractivo() {
        Scanner sc = new Scanner(System.in);
        boolean seguir = true;

        while (seguir) {
            System.out.println("\n╔══════════════════╗");
            System.out.println("║   CALCULADORA    ║");
            System.out.println("╠══════════════════╣");
            System.out.println("║ 1. Sumar         ║");
            System.out.println("║ 2. Restar        ║");
            System.out.println("║ 3. Multiplicar   ║");
            System.out.println("║ 4. Dividir       ║");
            System.out.println("║ 0. Salir         ║");
            System.out.println("╚══════════════════╝");
            System.out.print("Elige una opción: ");

            int opcion = sc.nextInt();

            if (opcion == 0) {
                System.out.println("¡Hasta luego!");
                seguir = false;
                continue;
            }

            System.out.print("Primer número: ");
            double a = sc.nextDouble();
            System.out.print("Segundo número: ");
            double b = sc.nextDouble();

            double resultado = switch (opcion) {
                case 1 -> a + b;
                case 2 -> a - b;
                case 3 -> a * b;
                case 4 -> b != 0 ? a / b : Double.NaN;
                default -> { System.out.println("Opción no válida."); yield 0; }
            };

            if (opcion == 4 && b == 0) {
                System.out.println("❌ No se puede dividir entre cero.");
            } else {
                System.out.printf("Resultado: %.2f%n", resultado);
            }
        }

        sc.close();
    }

    // ═══════════════════════════════════════════════════════
    // EJEMPLO 4 — Validar entrada del usuario (bucle do-while)
    // ═══════════════════════════════════════════════════════
    static void ejemplo4_ValidarEntrada() {
        Scanner sc = new Scanner(System.in);

        // Validar que la edad sea positiva
        int edad = 0;
        do {
            System.out.print("Ingresa tu edad (debe ser > 0): ");
            while (!sc.hasNextInt()) {           // Verifica que sea un entero
                System.out.print("⚠️ Eso no es un número. Intenta de nuevo: ");
                sc.next();                        // Descarta la entrada inválida
            }
            edad = sc.nextInt();
            if (edad <= 0) System.out.println("❌ La edad debe ser positiva.");
        } while (edad <= 0);

        // Validar categoría entre opciones
        sc.nextLine(); // ⚠️ Limpia buffer
        String categoria;
        do {
            System.out.print("Ingresa categoría (A, B o C): ");
            categoria = sc.nextLine().toUpperCase().trim();
            if (!categoria.equals("A") && !categoria.equals("B") && !categoria.equals("C")) {
                System.out.println("❌ Solo se aceptan A, B o C.");
            }
        } while (!categoria.equals("A") && !categoria.equals("B") && !categoria.equals("C"));

        System.out.println("\n✅ Datos válidos: edad=" + edad + ", categoría=" + categoria);
        sc.close();
    }

    // ═══════════════════════════════════════════════════════
    // EJEMPLO 5 — Ingresar datos hasta que el usuario escriba "fin"
    // ═══════════════════════════════════════════════════════
    static void ejemplo5_LeerHastaFin() {
        Scanner sc = new Scanner(System.in);
        List<String> nombres = new ArrayList<>();

        System.out.println("Ingresa nombres (escribe 'fin' para terminar):");

        while (true) {
            System.out.print("  Nombre: ");
            String entrada = sc.nextLine().trim();
            if (entrada.equalsIgnoreCase("fin")) break;
            if (!entrada.isEmpty()) nombres.add(entrada);
        }

        System.out.println("\n=== Nombres registrados ===");
        System.out.println("Total: " + nombres.size());

        // Ordenados alfabéticamente con Stream
        System.out.println("Ordenados: " + nombres.stream().sorted().collect(Collectors.joining(", ")));
        sc.close();
    }

    // ═══════════════════════════════════════════════════════
    // EJEMPLO 6 — Registro completo de estudiante con nota
    // ═══════════════════════════════════════════════════════
    static void ejemplo6_RegistroEstudiante() {
        Scanner sc = new Scanner(System.in);
        record Estudiante(String nombre, String codigo, double nota) {
            String estado() { return nota >= 3.0 ? "✅ Aprobado" : "❌ Reprobado"; }
        }

        System.out.println("=== Registro de Estudiante ===");

        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine();

        System.out.print("Código: ");
        String codigo = sc.nextLine();

        System.out.print("Nota final (0.0 - 5.0): ");
        double nota = sc.nextDouble();
        sc.nextLine(); // ⚠️

        System.out.print("¿Deseas confirmar el registro? (si/no): ");
        String confirmar = sc.nextLine();

        if (confirmar.equalsIgnoreCase("si")) {
            Estudiante est = new Estudiante(nombre, codigo, nota);
            System.out.println("\n=== Registro guardado ===");
            System.out.println("Nombre:  " + est.nombre());
            System.out.println("Código:  " + est.codigo());
            System.out.printf( "Nota:    %.1f%n", est.nota());
            System.out.println("Estado:  " + est.estado());
        } else {
            System.out.println("Registro cancelado.");
        }

        sc.close();
    }

    // ═══════════════════════════════════════════════════════
    // EJEMPLO 7 — Buscar en lista usando Scanner + Streams
    // ═══════════════════════════════════════════════════════
    static void ejemplo7_BuscarEnLista() {
        Scanner sc = new Scanner(System.in);

        List<String> productos = List.of(
                "Laptop", "Mouse", "Teclado", "Monitor", "Audífonos",
                "Webcam", "Silla Ergonómica", "Escritorio"
        );

        System.out.println("=== Buscador de Productos ===");
        System.out.println("Productos disponibles: " + productos.size());
        System.out.print("Buscar: ");
        String busqueda = sc.nextLine().toLowerCase();

        List<String> resultados = productos.stream()
                .filter(p -> p.toLowerCase().contains(busqueda))
                .toList();

        if (resultados.isEmpty()) {
            System.out.println("❌ No se encontraron productos con \"" + busqueda + "\"");
        } else {
            System.out.println("✅ Encontrados (" + resultados.size() + "):");
            resultados.forEach(p -> System.out.println("  - " + p));
        }

        sc.close();
    }

    // ═══════════════════════════════════════════════════════
    //                       MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("╔═════════════════════════════╗");
        System.out.println("║   GUÍA PRÁCTICA SCANNER     ║");
        System.out.println("╠═════════════════════════════╣");
        System.out.println("║ 1. Leer tipos básicos        ║");
        System.out.println("║ 2. Leer lista de personas    ║");
        System.out.println("║ 3. Menú calculadora          ║");
        System.out.println("║ 4. Validar entrada           ║");
        System.out.println("║ 5. Leer hasta 'fin'          ║");
        System.out.println("║ 6. Registro de estudiante    ║");
        System.out.println("║ 7. Buscar en lista           ║");
        System.out.println("╚═════════════════════════════╝");
        System.out.print("¿Cuál ejemplo quieres ejecutar? ");

        int opcion = sc.nextInt();
        sc.close(); // Cerramos antes de pasar al método que abre su propio Scanner

        switch (opcion) {
            case 1 -> ejemplo1_DatosBasicos();
            case 2 -> ejemplo2_LeerListaPersonas();
            case 3 -> ejemplo3_MenuInteractivo();
            case 4 -> ejemplo4_ValidarEntrada();
            case 5 -> ejemplo5_LeerHastaFin();
            case 6 -> ejemplo6_RegistroEstudiante();
            case 7 -> ejemplo7_BuscarEnLista();
            default -> System.out.println("Opción no válida.");
        }
    }
}
