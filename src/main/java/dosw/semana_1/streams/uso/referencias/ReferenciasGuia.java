package dosw.semana_1.streams.uso.referencias;

import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Guía práctica de Referencias a Métodos (::) e Interfaces Funcionales.
 *
 * ¿QUÉ ES UNA REFERENCIA A MÉTODO?
 * Es un atajo para escribir lambdas. En vez de escribir el cuerpo completo,
 * apuntas directamente al método que quieres usar.
 *
 * SINTAXIS:  Clase::metodo  o  objeto::metodo
 *
 * HAY 4 TIPOS:
 * 1. Referencia a método ESTÁTICO          →  Clase::metodoEstatico
 * 2. Referencia a método de INSTANCIA      →  objeto::metodoInstancia
 * 3. Referencia a método de TIPO (objeto)  →  Clase::metodoDeInstancia
 * 4. Referencia a CONSTRUCTOR              →  Clase::new
 */
public class ReferenciasGuia {

    record Persona(String nombre, int edad, double salario) {}

    static List<Persona> personas = List.of(
        new Persona("Ana Torres",    28, 4_500_000),
        new Persona("Luis Gómez",    19, 1_800_000),
        new Persona("María López",   35, 6_200_000),
        new Persona("Carlos Ruiz",   42, 5_000_000),
        new Persona("Sofía Herrera", 22, 2_500_000)
    );

    // ═══════════════════════════════════════════════════════
    // TIPO 1 — Referencia a método ESTÁTICO
    // ═══════════════════════════════════════════════════════
    static void tipo1_MetodoEstatico() {
        System.out.println("═══ TIPO 1: Referencia a método estático ═══");

        // Lambda:              x -> System.out.println(x)
        // Referencia:          System.out::println
        System.out.println("-- Con lambda:");
        personas.forEach(p -> System.out.println("  " + p.nombre()));

        System.out.println("-- Con referencia (System.out::println sobre nombres):");
        personas.stream()
                .map(Persona::nombre)
                .forEach(System.out::println);  // ← referencia al método println
    }

    // ═══════════════════════════════════════════════════════
    // TIPO 2 — Referencia a método de INSTANCIA de un objeto específico
    // ═══════════════════════════════════════════════════════
    static void tipo2_MetodoDeInstancia() {
        System.out.println("\n═══ TIPO 2: Referencia a instancia específica ═══");

        String prefijo = "Bienvenido, ";

        // Lambda:       s -> prefijo.concat(s)
        // Referencia:   prefijo::concat
        List<String> saludos = personas.stream()
                .map(Persona::nombre)
                .map(prefijo::concat)           // ← prefijo es la instancia
                .toList();

        saludos.forEach(System.out::println);
    }

    // ═══════════════════════════════════════════════════════
    // TIPO 3 — Referencia a método de INSTANCIA del tipo (el más común en Streams)
    // ═══════════════════════════════════════════════════════
    static void tipo3_MetodoDelTipo() {
        System.out.println("\n═══ TIPO 3: Referencia a método de instancia del tipo ← EL MÁS USADO ═══");

        // Lambda:       p -> p.nombre()
        // Referencia:   Persona::nombre
        List<String> nombres = personas.stream()
                .map(Persona::nombre)           // ← método de instancia del tipo Persona
                .toList();
        System.out.println("Nombres: " + nombres);

        // Lambda:       p -> p.edad()
        // Referencia:   Persona::edad
        personas.stream()
                .sorted(Comparator.comparingInt(Persona::edad))  // ← también aquí
                .map(p -> p.nombre() + " (" + p.edad() + ")")
                .forEach(System.out::println);

        // Con Strings también funciona
        List<String> palabras = List.of("banana", "apple", "cherry", "date");
        List<String> ordenadas = palabras.stream()
                .sorted(String::compareTo)      // ← String::compareTo
                .toList();
        System.out.println("Palabras ordenadas: " + ordenadas);
    }

    // ═══════════════════════════════════════════════════════
    // TIPO 4 — Referencia a CONSTRUCTOR
    // ═══════════════════════════════════════════════════════
    static void tipo4_Constructor() {
        System.out.println("\n═══ TIPO 4: Referencia a constructor (::new) ═══");

        // Imagina que tienes nombres y quieres convertirlos a objetos Persona
        List<String> nombres = List.of("Juan", "Elena", "Pablo");

        // Usando lambda:
        // .map(n -> new PersonaSimple(n))
        // Usando referencia al constructor:
        // .map(PersonaSimple::new)

        record PersonaSimple(String nombre) {}

        List<PersonaSimple> personas2 = nombres.stream()
                .map(PersonaSimple::new)        // ← llama al constructor con cada nombre
                .toList();

        personas2.forEach(p -> System.out.println("  Creado: " + p.nombre()));
    }

    // ═══════════════════════════════════════════════════════
    // INTERFACES FUNCIONALES — Lo que hay detrás de cada lambda
    // ═══════════════════════════════════════════════════════
    static void interfacesFuncionales() {
        System.out.println("\n═══ INTERFACES FUNCIONALES ═══");

        // PREDICATE<T> — recibe T, devuelve boolean. Usado en .filter()
        Predicate<Persona> esMayor30 = p -> p.edad() > 30;
        Predicate<Persona> ganaMucho = p -> p.salario() > 4_000_000;

        System.out.println("-- Predicate combinados (and):");
        personas.stream()
                .filter(esMayor30.and(ganaMucho))   // combinar con .and() .or() .negate()
                .map(Persona::nombre)
                .forEach(n -> System.out.println("  " + n));

        // FUNCTION<T, R> — recibe T, devuelve R. Usado en .map()
        Function<Persona, String> presentar = p ->
                p.nombre().toUpperCase() + " — $" + String.format("%,.0f", p.salario());

        System.out.println("\n-- Function en map:");
        personas.stream()
                .map(presentar)
                .limit(3)
                .forEach(System.out::println);

        // CONSUMER<T> — recibe T, no devuelve nada. Usado en .forEach()
        Consumer<Persona> imprimirPersona = p ->
                System.out.println("  [" + p.edad() + "] " + p.nombre());

        System.out.println("\n-- Consumer en forEach:");
        personas.forEach(imprimirPersona);

        // SUPPLIER<T> — no recibe nada, devuelve T. Usado en orElseGet()
        Supplier<Persona> personaDefault = () -> new Persona("Sin datos", 0, 0);

        Persona resultado = personas.stream()
                .filter(p -> p.edad() > 100)    // nadie cumple
                .findFirst()
                .orElseGet(personaDefault);      // ← Supplier aquí

        System.out.println("\n-- Supplier en orElseGet:");
        System.out.println("  Resultado: " + resultado.nombre());
    }

    // ═══════════════════════════════════════════════════════
    // COMPARATOR — Ordenar objetos con referencia a método
    // ═══════════════════════════════════════════════════════
    static void comparatorConReferencias() {
        System.out.println("\n═══ COMPARATOR CON REFERENCIAS ═══");

        // Por nombre alfabético
        System.out.println("-- Por nombre:");
        personas.stream()
                .sorted(Comparator.comparing(Persona::nombre))
                .map(Persona::nombre)
                .forEach(n -> System.out.println("  " + n));

        // Por salario descendente
        System.out.println("\n-- Por salario (mayor a menor):");
        personas.stream()
                .sorted(Comparator.comparingDouble(Persona::salario).reversed())
                .map(p -> p.nombre() + " → $" + String.format("%,.0f", p.salario()))
                .forEach(System.out::println);

        // Doble orden: ciudad ASC, luego nombre ASC
        System.out.println("\n-- Por edad ASC, luego nombre ASC:");
        personas.stream()
                .sorted(Comparator.comparingInt(Persona::edad)
                        .thenComparing(Persona::nombre))
                .map(p -> p.nombre() + " (" + p.edad() + ")")
                .forEach(System.out::println);
    }

    // ═══════════════════════════════════════════════════════
    // TABLA RESUMEN — Lambda vs Referencia
    // ═══════════════════════════════════════════════════════
    static void tablaResumen() {
        System.out.println("\n═══ TABLA RESUMEN: Lambda vs Referencia ═══");
        System.out.println("  Lambda                           │ Referencia");
        System.out.println("  ─────────────────────────────────┼────────────────────────────");
        System.out.println("  x -> System.out.println(x)       │ System.out::println");
        System.out.println("  p -> p.nombre()                  │ Persona::nombre");
        System.out.println("  p -> p.edad()                    │ Persona::edad");
        System.out.println("  s -> s.toUpperCase()             │ String::toUpperCase");
        System.out.println("  (a,b) -> a.compareTo(b)          │ String::compareTo");
        System.out.println("  n -> new Persona(n)              │ Persona::new");
        System.out.println("  s -> Integer.parseInt(s)         │ Integer::parseInt");
    }

    // ═══════════════════════════════════════════════════════
    //                       MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║  GUÍA: REFERENCIAS A MÉTODOS (::)       ║");
        System.out.println("║  + INTERFACES FUNCIONALES                ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        tipo1_MetodoEstatico();
        tipo2_MetodoDeInstancia();
        tipo3_MetodoDelTipo();
        tipo4_Constructor();
        interfacesFuncionales();
        comparatorConReferencias();
        tablaResumen();
    }
}
