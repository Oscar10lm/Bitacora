package dosw.semana_1.streams.uso.optional;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Guía práctica de Optional — Para el parcial.
 *
 * ¿QUÉ ES OPTIONAL?
 * Es un contenedor que puede tener un valor... o no tenerlo (estar vacío).
 * Sirve para evitar el NullPointerException cuando una operación puede no retornar nada.
 * Streams como max(), min(), findFirst(), average() siempre devuelven Optional.
 */
public class OptionalGuia {

    record Persona(String nombre, int edad, double salario, String ciudad) {}

    static List<Persona> personas = List.of(
        new Persona("Ana Torres",    28, 4_500_000, "Bogotá"),
        new Persona("Luis Gómez",    19, 1_800_000, "Medellín"),
        new Persona("María López",   35, 6_200_000, "Bogotá"),
        new Persona("Carlos Ruiz",   42, 5_000_000, "Cali"),
        new Persona("Sofía Herrera", 22, 2_500_000, "Medellín")
    );

    // ═══════════════════════════════════════════════════════
    // 1. CREAR UN OPTIONAL manualmente
    // ═══════════════════════════════════════════════════════
    static void crearOptional() {
        System.out.println("═══ 1. CREAR OPTIONAL ═══");

        // Con valor
        Optional<String> conValor = Optional.of("Hola Mundo");
        System.out.println("Con valor: " + conValor);       // Optional[Hola Mundo]

        // Vacío
        Optional<String> vacio = Optional.empty();
        System.out.println("Vacío:     " + vacio);          // Optional.empty

        // ofNullable — cuando no sabes si el valor puede ser null
        String dato = null;
        Optional<String> nullable = Optional.ofNullable(dato);
        System.out.println("Nullable:  " + nullable);       // Optional.empty
    }

    // ═══════════════════════════════════════════════════════
    // 2. isPresent() e isEmpty() — Verificar si tiene valor
    // ═══════════════════════════════════════════════════════
    static void verificarPresencia() {
        System.out.println("\n═══ 2. isPresent() e isEmpty() ═══");

        Optional<Persona> resultado = personas.stream()
                .filter(p -> p.ciudad().equals("Cali"))
                .findFirst();

        if (resultado.isPresent()) {                        // ¿tiene valor?
            System.out.println("Encontrado: " + resultado.get().nombre());
        }

        Optional<Persona> noExiste = personas.stream()
                .filter(p -> p.ciudad().equals("Cartagena"))
                .findFirst();

        System.out.println("¿Está vacío?: " + noExiste.isEmpty()); // true
    }

    // ═══════════════════════════════════════════════════════
    // 3. get() — Obtener el valor (⚠️ riesgoso si está vacío)
    // ═══════════════════════════════════════════════════════
    static void usarGet() {
        System.out.println("\n═══ 3. get() — Solo úsalo si sabes que hay valor ═══");

        Optional<Persona> mayor = personas.stream()
                .max((a, b) -> Double.compare(a.salario(), b.salario()));

        // SIEMPRE verificar antes de llamar get()
        if (mayor.isPresent()) {
            System.out.println("Mayor salario: " + mayor.get().nombre()
                    + " → $" + String.format("%,.0f", mayor.get().salario()));
        }
    }

    // ═══════════════════════════════════════════════════════
    // 4. orElse() — Valor por defecto si está vacío ← EL MÁS USADO
    // ═══════════════════════════════════════════════════════
    static void usarOrElse() {
        System.out.println("\n═══ 4. orElse() — Valor por defecto ← EL MÁS ÚTIL ═══");

        Optional<Persona> enCartagena = personas.stream()
                .filter(p -> p.ciudad().equals("Cartagena"))
                .findFirst();

        // Si no hay persona en Cartagena, usa una por defecto
        Persona resultado = enCartagena.orElse(
                new Persona("Sin datos", 0, 0, "Desconocida")
        );
        System.out.println("Resultado: " + resultado.nombre()); // Sin datos

        // También funciona para Strings y números
        Optional<String> nombreOpt = Optional.empty();
        String nombre = nombreOpt.orElse("Anónimo");
        System.out.println("Nombre: " + nombre); // Anónimo
    }

    // ═══════════════════════════════════════════════════════
    // 5. orElseGet() — Igual que orElse pero con lambda (lazy)
    // ═══════════════════════════════════════════════════════
    static void usarOrElseGet() {
        System.out.println("\n═══ 5. orElseGet() — Default con lógica ═══");

        Optional<Persona> opt = Optional.empty();

        // orElseGet recibe un Supplier (lambda sin parámetros que retorna algo)
        Persona resultado = opt.orElseGet(() -> {
            System.out.println("  (ejecutando lógica para crear el default...)");
            return new Persona("Default", 0, 0, "N/A");
        });

        System.out.println("Persona: " + resultado.nombre());
    }

    // ═══════════════════════════════════════════════════════
    // 6. orElseThrow() — Lanzar excepción si está vacío
    // ═══════════════════════════════════════════════════════
    static void usarOrElseThrow() {
        System.out.println("\n═══ 6. orElseThrow() — Lanzar error si no hay valor ═══");

        try {
            Persona enCartagena = personas.stream()
                    .filter(p -> p.ciudad().equals("Cartagena"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No hay personas en Cartagena"));

        } catch (RuntimeException e) {
            System.out.println("Error capturado: " + e.getMessage());
        }
    }

    // ═══════════════════════════════════════════════════════
    // 7. ifPresent() — Ejecutar código solo si hay valor
    // ═══════════════════════════════════════════════════════
    static void usarIfPresent() {
        System.out.println("\n═══ 7. ifPresent() — Ejecutar acción si hay valor ═══");

        Optional<Persona> menorDe20 = personas.stream()
                .filter(p -> p.edad() < 20)
                .findFirst();

        // Solo ejecuta si hay un resultado, sin if explícito
        menorDe20.ifPresent(p ->
                System.out.println("Menor de 20: " + p.nombre() + " (" + p.edad() + ")")
        );

        Optional<Persona> mayor70 = personas.stream()
                .filter(p -> p.edad() > 70)
                .findFirst();

        // No pasa nada si está vacío, no explota
        mayor70.ifPresent(p -> System.out.println("Mayor de 70: " + p.nombre()));
        System.out.println("(Si no imprimió nada arriba, el Optional estaba vacío)");
    }

    // ═══════════════════════════════════════════════════════
    // 8. map() sobre Optional — Transformar el valor si existe
    // ═══════════════════════════════════════════════════════
    static void usarMapEnOptional() {
        System.out.println("\n═══ 8. map() en Optional — Transformar sin riesgo ═══");

        Optional<Persona> opt = personas.stream()
                .filter(p -> p.ciudad().equals("Bogotá"))
                .findFirst();

        // En vez de opt.get().nombre(), usa map para transformar de forma segura
        Optional<String> nombreOpt = opt.map(Persona::nombre);
        System.out.println("Nombre: " + nombreOpt.orElse("No encontrado")); // Ana Torres

        // Encadenado: buscar y obtener directamente el salario como String
        String salario = opt
                .map(p -> "$" + String.format("%,.0f", p.salario()))
                .orElse("Sin salario");
        System.out.println("Salario: " + salario);
    }

    // ═══════════════════════════════════════════════════════
    // 9. Ejemplos combinados — Como en el parcial real
    // ═══════════════════════════════════════════════════════
    static void ejemplosCombinados() {
        System.out.println("\n═══ 9. EJEMPLOS COMBINADOS ═══");

        // max() devuelve Optional → usamos orElseThrow
        Persona masRico = personas.stream()
                .max((a, b) -> Double.compare(a.salario(), b.salario()))
                .orElseThrow();
        System.out.println("Más rico: " + masRico.nombre());

        // min() con orElse
        Persona masjoven = personas.stream()
                .min((a, b) -> Integer.compare(a.edad(), b.edad()))
                .orElse(new Persona("N/A", 0, 0, "N/A"));
        System.out.println("Más joven: " + masjoven.nombre());

        // average() devuelve OptionalDouble
        OptionalDouble promedio = personas.stream()
                .mapToDouble(Persona::salario)
                .average();
        System.out.printf("Promedio salario: $%,.0f%n", promedio.orElse(0));

        // findFirst() — primero que cumple condición
        String primeraBogotana = personas.stream()
                .filter(p -> p.ciudad().equals("Bogotá"))
                .map(Persona::nombre)
                .findFirst()
                .orElse("Ninguna persona de Bogotá");
        System.out.println("Primera bogotana: " + primeraBogotana);
    }

    // ═══════════════════════════════════════════════════════
    //                       MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║   GUÍA COMPLETA DE OPTIONAL      ║");
        System.out.println("╚══════════════════════════════════╝\n");

        crearOptional();
        verificarPresencia();
        usarGet();
        usarOrElse();
        usarOrElseGet();
        usarOrElseThrow();
        usarIfPresent();
        usarMapEnOptional();
        ejemplosCombinados();
    }
}
