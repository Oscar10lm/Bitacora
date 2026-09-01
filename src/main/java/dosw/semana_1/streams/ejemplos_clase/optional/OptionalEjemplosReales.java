package dosw.semana_1.streams.ejemplos_clase.optional;

import java.util.List;
import java.util.Optional;

/**
 * Ejemplos REALES de Optional — Situaciones del mundo real.
 *
 * Olvídate de la teoría por un momento. Esto es lo que pasa
 * cuando buscas algo en una base de datos, un carrito, o un usuario:
 * A VECES EXISTE, A VECES NO. Optional maneja esa incertidumbre.
 */
public class OptionalEjemplosReales {

    // ══════════════════════════════════════════════
    // ESCENARIO 1 — Sistema de tienda online
    // "Buscar un producto por su ID"
    // ══════════════════════════════════════════════
    record Producto(int id, String nombre, double precio, boolean disponible) {}

    static List<Producto> inventario = List.of(
        new Producto(1, "Laptop HP",      3_500_000, true),
        new Producto(2, "Mouse Logitech",   120_000, true),
        new Producto(3, "Teclado Mecánico", 250_000, false),
        new Producto(4, "Monitor LG",     1_200_000, true)
    );

    // Simula un método que busca en "base de datos" → puede no encontrar nada
    static Optional<Producto> buscarProducto(int id) {
        return inventario.stream()
                .filter(p -> p.id() == id)
                .findFirst();
    }

    static void escenario1_TiendaOnline() {
        System.out.println("══════════════════════════════════════════");
        System.out.println("ESCENARIO 1: Tienda Online — Buscar producto");
        System.out.println("══════════════════════════════════════════");

        // Caso A: Producto SÍ existe
        System.out.println("\n[Cliente busca ID=2 (Mouse)]:");
        buscarProducto(2)
            .ifPresent(p -> System.out.println("  ✅ Producto encontrado: " + p.nombre()
                    + " | Precio: $" + String.format("%,.0f", p.precio())));

        // Caso B: Producto NO existe → dar mensaje amigable
        System.out.println("\n[Cliente busca ID=99 (no existe)]:");
        Producto resultado = buscarProducto(99)
            .orElse(new Producto(0, "Producto no encontrado", 0, false));
        System.out.println("  Resultado: " + resultado.nombre());

        // Caso C: Verificar si está disponible antes de agregar al carrito
        System.out.println("\n[Agregar al carrito ID=3 (Teclado)]:");
        buscarProducto(3).ifPresent(p -> {
            if (p.disponible()) {
                System.out.println("  ✅ Agregado al carrito: " + p.nombre());
            } else {
                System.out.println("  ❌ " + p.nombre() + " no está disponible.");
            }
        });

        // Caso D: Obtener solo el nombre si existe, o "Sin producto"
        System.out.println("\n[Mostrar nombre ID=1 vs ID=50]:");
        String nombre1 = buscarProducto(1).map(Producto::nombre).orElse("Sin producto");
        String nombre2 = buscarProducto(50).map(Producto::nombre).orElse("Sin producto");
        System.out.println("  ID=1: " + nombre1);
        System.out.println("  ID=50: " + nombre2);
    }

    // ══════════════════════════════════════════════
    // ESCENARIO 2 — Sistema de usuarios / login
    // "Autenticar usuario"
    // ══════════════════════════════════════════════
    record Usuario(String email, String password, String rol) {}

    static List<Usuario> usuarios = List.of(
        new Usuario("admin@dosw.com",  "1234", "ADMIN"),
        new Usuario("oscar@dosw.com",  "abcd", "ESTUDIANTE"),
        new Usuario("profe@dosw.com",  "xyz9", "PROFESOR")
    );

    static Optional<Usuario> login(String email, String password) {
        return usuarios.stream()
                .filter(u -> u.email().equals(email) && u.password().equals(password))
                .findFirst();
    }

    static void escenario2_Login() {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("ESCENARIO 2: Sistema de Login");
        System.out.println("══════════════════════════════════════════");

        // Intento exitoso
        System.out.println("\n[Intento 1 — credenciales correctas]:");
        login("oscar@dosw.com", "abcd")
            .ifPresent(u -> System.out.println("  ✅ Bienvenido, " + u.email()
                    + " | Rol: " + u.rol()));

        // Intento fallido
        System.out.println("\n[Intento 2 — contraseña incorrecta]:");
        boolean loginExitoso = login("oscar@dosw.com", "wrongpass").isPresent();
        System.out.println("  " + (loginExitoso ? "✅ Acceso permitido" : "❌ Credenciales inválidas"));

        // Obtener rol o redirigir
        System.out.println("\n[Obtener rol del admin]:");
        String rol = login("admin@dosw.com", "1234")
                .map(Usuario::rol)
                .orElse("INVITADO");
        System.out.println("  Rol: " + rol);

        // Lanzar excepción si no hay usuario (útil en APIs)
        System.out.println("\n[Acceso a recurso protegido sin credenciales]:");
        try {
            Usuario u = login("hacker@x.com", "hack")
                    .orElseThrow(() -> new RuntimeException("401 - No autorizado"));
        } catch (RuntimeException e) {
            System.out.println("  Error: " + e.getMessage());
        }
    }

    // ══════════════════════════════════════════════
    // ESCENARIO 3 — Procesamiento de datos
    // "Leer configuración que puede faltar"
    // ══════════════════════════════════════════════
    record Configuracion(String clave, String valor) {}

    static List<Configuracion> config = List.of(
        new Configuracion("host",    "localhost"),
        new Configuracion("puerto",  "8080"),
        new Configuracion("timeout", "30")
        // ← "debug" no está configurado
    );

    static Optional<String> obtenerConfig(String clave) {
        return config.stream()
                .filter(c -> c.clave().equals(clave))
                .map(Configuracion::valor)
                .findFirst();
    }

    static void escenario3_Configuracion() {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("ESCENARIO 3: Configuración del sistema");
        System.out.println("══════════════════════════════════════════");

        // Valores que SÍ existen
        System.out.println("\n[Leer configuraciones]:");
        String host    = obtenerConfig("host").orElse("127.0.0.1");    // existe
        String puerto  = obtenerConfig("puerto").orElse("3000");       // existe
        String debug   = obtenerConfig("debug").orElse("false");       // NO existe → default
        String timeout = obtenerConfig("timeout").orElse("60");        // existe

        System.out.println("  Host:    " + host);
        System.out.println("  Puerto:  " + puerto);
        System.out.println("  Debug:   " + debug + " (usando default)");
        System.out.println("  Timeout: " + timeout + "s");

        // Convertir y validar
        System.out.println("\n[Puerto como número]:");
        int puertoNum = obtenerConfig("puerto")
                .map(Integer::parseInt)   // convierte String a int
                .orElse(8080);
        System.out.println("  Puerto (int): " + puertoNum);
    }

    // ══════════════════════════════════════════════
    // ESCENARIO 4 — Lista de estudiantes con notas
    // "Encontrar el mejor y el peor"
    // ══════════════════════════════════════════════
    record Estudiante(String nombre, double nota) {}

    static List<Estudiante> curso = List.of(
        new Estudiante("Ana",    4.7),
        new Estudiante("Luis",   2.8),
        new Estudiante("María",  3.5),
        new Estudiante("Carlos", 4.9),
        new Estudiante("Sofía",  1.5)
    );

    static void escenario4_Notas() {
        System.out.println("\n══════════════════════════════════════════");
        System.out.println("ESCENARIO 4: Curso — Notas de estudiantes");
        System.out.println("══════════════════════════════════════════");

        // Mejor nota
        curso.stream()
             .max((a, b) -> Double.compare(a.nota(), b.nota()))
             .ifPresent(e -> System.out.println("\n  🏆 Mejor nota: "
                     + e.nombre() + " con " + e.nota()));

        // Peor nota
        curso.stream()
             .min((a, b) -> Double.compare(a.nota(), b.nota()))
             .ifPresent(e -> System.out.println("  ⚠️  Peor nota:  "
                     + e.nombre() + " con " + e.nota()));

        // Primer aprobado (nota >= 3.0)
        String primerAprobado = curso.stream()
                .filter(e -> e.nota() >= 3.0)
                .map(Estudiante::nombre)
                .findFirst()
                .orElse("Nadie aprobó");
        System.out.println("  ✅ Primer aprobado: " + primerAprobado);

        // Promedio del curso
        double promedio = curso.stream()
                .mapToDouble(Estudiante::nota)
                .average()
                .orElse(0.0);
        System.out.printf("  📊 Promedio del curso: %.2f%n", promedio);
    }

    // ═══════════════════════════════════════════════════════
    //                       MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   OPTIONAL — EJEMPLOS REALES             ║");
        System.out.println("╚══════════════════════════════════════════╝");

        escenario1_TiendaOnline();
        escenario2_Login();
        escenario3_Configuracion();
        escenario4_Notas();
    }
}
