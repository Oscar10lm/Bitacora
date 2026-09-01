package dosw.semana_1.streams.ejemplos_clase.streams;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Guía completa de Streams con ejemplos prácticos y ejecutables.
 * Cada método demuestra una operación distinta sobre la misma lista de personas.
 */
public class StreamsGuia {

    // ─── RECORD: estructura de datos para los ejemplos ───
                    record Persona(String nombre, int edad, String ciudad, double salario, String profesion) {}

    // ─── LISTA BASE (usada por todos los métodos) ───
    static List<Persona> personas = List.of(
        new Persona("Ana Torres",      28, "Bogotá",    4_500_000, "Ingeniera"),
        new Persona("Luis Gómez",      19, "Medellín",  1_800_000, "Estudiante"),
        new Persona("María López",     35, "Bogotá",    6_200_000, "Abogada"),
        new Persona("Carlos Ruiz",     42, "Cali",      5_000_000, "Ingeniero"),
        new Persona("Sofía Herrera",   22, "Medellín",  2_500_000, "Diseñadora"),
        new Persona("Pedro Díaz",      31, "Bogotá",    3_100_000, "Contador"),
        new Persona("Laura Méndez",    45, "Cali",      7_000_000, "Directora"),
        new Persona("Andrés Vargas",   19, "Medellín",  1_500_000, "Estudiante"),
        new Persona("Camila Ríos",     27, "Bogotá",    4_800_000, "Ingeniera"),
        new Persona("Diego Morales",   33, "Cali",      3_900_000, "Contador")
    );

    // ═══════════════════════════════════════════════════════
    // 1. FILTER — Filtrar elementos que cumplan una condición
    // ═══════════════════════════════════════════════════════
    static void filtrarMayoresDe30() {
        System.out.println("═══ 1. FILTER: Personas mayores de 30 ═══");
        List<Persona> resultado = personas.stream()
                .filter(p -> p.edad() > 30)
                .toList();

        resultado.forEach(p -> System.out.println("  - " + p.nombre() + " (" + p.edad() + ")"));
    }

    // ═══════════════════════════════════════════════════════
    // 2. FILTER COMPUESTO — Dos o más condiciones
    // ═══════════════════════════════════════════════════════
    static void filtrarJovenesConBuenSalario() {
        System.out.println("\n═══ 2. FILTER COMPUESTO: Menores de 30 Y salario > 3M ═══");
        List<Persona> resultado = personas.stream()
                .filter(p -> p.edad() < 30 && p.salario() > 3_000_000)
                .toList();

        resultado.forEach(p -> System.out.println("  - " + p.nombre() + " | $" + p.salario()));
    }

    // ═══════════════════════════════════════════════════════
    // 3. MAP — Transformar cada elemento
    // ═══════════════════════════════════════════════════════
    static void nombresEnMayusculas() {
        System.out.println("\n═══ 3. MAP: Nombres en MAYÚSCULAS ═══");
        List<String> nombres = personas.stream()
                .map(p -> p.nombre().toUpperCase())
                .toList();

        nombres.forEach(n -> System.out.println("  - " + n));
    }

    // ═══════════════════════════════════════════════════════
    // 4. MAP + FILTER — Transformar después de filtrar
    // ═══════════════════════════════════════════════════════
    static void nombresDeIngenieros() {
        System.out.println("\n═══ 4. MAP + FILTER: Nombres de ingenieros ═══");
        List<String> nombres = personas.stream()
                .filter(p -> p.profesion().toLowerCase().contains("ingenier"))
                .map(Persona::nombre)   // Referencia a método (equivale a p -> p.nombre())
                .toList();

        nombres.forEach(n -> System.out.println("  - " + n));
    }

    // ═══════════════════════════════════════════════════════
    // 5. SORTED — Ordenar alfabéticamente
    // ═══════════════════════════════════════════════════════
    static void ordenarAlfabeticamente() {
        System.out.println("\n═══ 5. SORTED: Orden alfabético por nombre ═══");
        List<Persona> resultado = personas.stream()
                .sorted(Comparator.comparing(Persona::nombre))
                .toList();

        resultado.forEach(p -> System.out.println("  - " + p.nombre()));
    }

    // ═══════════════════════════════════════════════════════
    // 6. SORTED DESCENDENTE — Ordenar de mayor a menor
    // ═══════════════════════════════════════════════════════
    static void ordenarPorSalarioDescendente() {
        System.out.println("\n═══ 6. SORTED DESC: Por salario de mayor a menor ═══");
        List<Persona> resultado = personas.stream()
                .sorted(Comparator.comparingDouble(Persona::salario).reversed())
                .toList();

        resultado.forEach(p -> System.out.println("  - " + p.nombre() + " → $" + String.format("%,.0f", p.salario())));
    }

    // ═══════════════════════════════════════════════════════
    // 7. DISTINCT — Eliminar duplicados
    // ═══════════════════════════════════════════════════════
    static void ciudadesUnicas() {
        System.out.println("\n═══ 7. DISTINCT: Ciudades únicas ═══");
        List<String> ciudades = personas.stream()
                .map(Persona::ciudad)
                .distinct()
                .toList();

        ciudades.forEach(c -> System.out.println("  - " + c));
    }

    // ═══════════════════════════════════════════════════════
    // 8. COUNT — Contar elementos que cumplen condición
    // ═══════════════════════════════════════════════════════
    static void contarEstudiantes() {
        System.out.println("\n═══ 8. COUNT: ¿Cuántos estudiantes hay? ═══");
        long cantidad = personas.stream()
                .filter(p -> p.profesion().equals("Estudiante"))
                .count();

        System.out.println("  Estudiantes: " + cantidad);
    }

    // ═══════════════════════════════════════════════════════
    // 9. MAX / MIN — Encontrar el mayor o menor
    // ═══════════════════════════════════════════════════════
    static void personaMayorYMenorSalario() {
        System.out.println("\n═══ 9. MAX / MIN: Salario más alto y más bajo ═══");

        Optional<Persona> masAlto = personas.stream()
                .max(Comparator.comparingDouble(Persona::salario));

        Optional<Persona> masBajo = personas.stream()
                .min(Comparator.comparingDouble(Persona::salario));

        masAlto.ifPresent(p -> System.out.println("  Mayor salario: " + p.nombre() + " → $" + String.format("%,.0f", p.salario())));
        masBajo.ifPresent(p -> System.out.println("  Menor salario: " + p.nombre() + " → $" + String.format("%,.0f", p.salario())));
    }

    // ═══════════════════════════════════════════════════════
    // 10. REDUCE — Acumular/sumar valores
    // ═══════════════════════════════════════════════════════
    static void sumaTotal() {
        System.out.println("\n═══ 10. REDUCE: Suma total de salarios ═══");
        double total = personas.stream()
                .map(Persona::salario)
                .reduce(0.0, Double::sum);   // valor inicial + acumulador

        System.out.println("  Total nómina: $" + String.format("%,.0f", total));
    }

    // ═══════════════════════════════════════════════════════
    // 11. AVERAGE — Promedio de un valor numérico
    // ═══════════════════════════════════════════════════════
    static void promedioEdad() {
        System.out.println("\n═══ 11. AVERAGE: Promedio de edad ═══");
        OptionalDouble promedio = personas.stream()
                .mapToInt(Persona::edad)
                .average();

        promedio.ifPresent(p -> System.out.println("  Edad promedio: " + String.format("%.1f", p) + " años"));
    }

    // ═══════════════════════════════════════════════════════
    // 12. GROUPING BY — Agrupar por una propiedad
    // ═══════════════════════════════════════════════════════
    static void agruparPorCiudad() {
        System.out.println("\n═══ 12. GROUPING BY: Personas por ciudad ═══");
        Map<String, List<Persona>> porCiudad = personas.stream()
                .collect(Collectors.groupingBy(Persona::ciudad));

        porCiudad.forEach((ciudad, lista) -> {
            System.out.println("  " + ciudad + ":");
            lista.forEach(p -> System.out.println("    - " + p.nombre()));
        });
    }

    // ═══════════════════════════════════════════════════════
    // 13. GROUPING BY + COUNTING — Agrupar y contar
    // ═══════════════════════════════════════════════════════
    static void contarPorProfesion() {
        System.out.println("\n═══ 13. GROUPING BY + COUNTING: Cantidad por profesión ═══");
        Map<String, Long> porProfesion = personas.stream()
                .collect(Collectors.groupingBy(Persona::profesion, Collectors.counting()));

        porProfesion.forEach((prof, cant) -> System.out.println("  " + prof + ": " + cant));
    }

    // ═══════════════════════════════════════════════════════
    // 14. GROUPING BY + MAPPING — Agrupar nombres por ciudad
    // ═══════════════════════════════════════════════════════
    static void nombresPorCiudad() {
        System.out.println("\n═══ 14. GROUPING BY + MAPPING: Nombres agrupados por ciudad ═══");
        Map<String, List<String>> nombresPorCiudad = personas.stream()
                .collect(Collectors.groupingBy(
                        Persona::ciudad,
                        Collectors.mapping(Persona::nombre, Collectors.toList())
                ));

        nombresPorCiudad.forEach((ciudad, nombres) ->
                System.out.println("  " + ciudad + ": " + nombres));
    }

    // ═══════════════════════════════════════════════════════
    // 15. PARTITIONING BY — Dividir en true / false
    // ═══════════════════════════════════════════════════════
    static void particionarPorEdad() {
        System.out.println("\n═══ 15. PARTITIONING BY: ¿Mayor de 30? ═══");
        Map<Boolean, List<Persona>> particion = personas.stream()
                .collect(Collectors.partitioningBy(p -> p.edad() > 30));

        System.out.println("  Mayores de 30:");
        particion.get(true).forEach(p -> System.out.println("    - " + p.nombre() + " (" + p.edad() + ")"));
        System.out.println("  Menores o iguales a 30:");
        particion.get(false).forEach(p -> System.out.println("    - " + p.nombre() + " (" + p.edad() + ")"));
    }

    // ═══════════════════════════════════════════════════════
    // 16. ANYMATCH / ALLMATCH / NONEMATCH — Verificaciones
    // ═══════════════════════════════════════════════════════
    static void verificaciones() {
        System.out.println("\n═══ 16. MATCH: Verificaciones booleanas ═══");

        boolean hayMenores = personas.stream()
                .anyMatch(p -> p.edad() < 20);
        System.out.println("  ¿Hay algún menor de 20? → " + hayMenores);

        boolean todosGananAlgo = personas.stream()
                .allMatch(p -> p.salario() > 0);
        System.out.println("  ¿Todos ganan más de $0? → " + todosGananAlgo);

        boolean nadieGana10M = personas.stream()
                .noneMatch(p -> p.salario() > 10_000_000);
        System.out.println("  ¿Nadie gana más de $10M? → " + nadieGana10M);
    }

    // ═══════════════════════════════════════════════════════
    // 17. LIMIT / SKIP — Paginación
    // ═══════════════════════════════════════════════════════
    static void topTresMejorPagados() {
        System.out.println("\n═══ 17. LIMIT + SORTED: Top 3 mejor pagados ═══");
        List<Persona> top3 = personas.stream()
                .sorted(Comparator.comparingDouble(Persona::salario).reversed())
                .limit(3)
                .toList();

        top3.forEach(p -> System.out.println("  🏆 " + p.nombre() + " → $" + String.format("%,.0f", p.salario())));
    }

    static void paginacion() {
        System.out.println("\n═══ 17b. SKIP + LIMIT: Página 2 (elementos 4-6) ═══");
        List<Persona> pagina2 = personas.stream()
                .skip(3)    // Saltar los primeros 3
                .limit(3)   // Tomar los siguientes 3
                .toList();

        pagina2.forEach(p -> System.out.println("  - " + p.nombre()));
    }

    // ═══════════════════════════════════════════════════════
    // 18. JOINING — Unir strings con separador
    // ═══════════════════════════════════════════════════════
    static void unirNombres() {
        System.out.println("\n═══ 18. JOINING: Unir nombres en un solo String ═══");
        String nombres = personas.stream()
                .map(Persona::nombre)
                .collect(Collectors.joining(", "));

        System.out.println("  Todos: " + nombres);
    }

    // ═══════════════════════════════════════════════════════
    // 19. TOMAP — Crear un mapa clave-valor
    // ═══════════════════════════════════════════════════════
    static void crearMapaNombreSalario() {
        System.out.println("\n═══ 19. TOMAP: Mapa Nombre → Salario ═══");
        Map<String, Double> mapa = personas.stream()
                .collect(Collectors.toMap(
                        Persona::nombre,    // clave
                        Persona::salario    // valor
                ));

        mapa.forEach((nombre, salario) ->
                System.out.println("  " + nombre + " → $" + String.format("%,.0f", salario)));
    }

    // ═══════════════════════════════════════════════════════
    // 20. FOREACH — Ejecutar acción sobre cada elemento
    // ═══════════════════════════════════════════════════════
    static void presentarPersonas() {
        System.out.println("\n═══ 20. FOREACH: Presentación de cada persona ═══");
        personas.stream()
                .filter(p -> p.ciudad().equals("Bogotá"))
                .forEach(p -> System.out.println("  Hola, soy " + p.nombre() + ", trabajo como " + p.profesion() + " en " + p.ciudad()));
    }

    // ═══════════════════════════════════════════════════════
    // 21. SUMMARIZING — Estadísticas completas de un golpe
    // ═══════════════════════════════════════════════════════
    static void estadisticasSalario() {
        System.out.println("\n═══ 21. SUMMARIZING: Estadísticas completas de salarios ═══");
        DoubleSummaryStatistics stats = personas.stream()
                .collect(Collectors.summarizingDouble(Persona::salario));

        System.out.println("  Cantidad: " + stats.getCount());
        System.out.println("  Suma:     $" + String.format("%,.0f", stats.getSum()));
        System.out.println("  Promedio: $" + String.format("%,.0f", stats.getAverage()));
        System.out.println("  Mínimo:   $" + String.format("%,.0f", stats.getMin()));
        System.out.println("  Máximo:   $" + String.format("%,.0f", stats.getMax()));
    }

    // ═══════════════════════════════════════════════════════
    // 22. FLATMAP — Aplanar listas anidadas
    // ═══════════════════════════════════════════════════════
    static void flatMapEjemplo() {
        System.out.println("\n═══ 22. FLATMAP: Aplanar listas de habilidades ═══");

        record Desarrollador(String nombre, List<String> lenguajes) {}

        List<Desarrollador> devs = List.of(
                new Desarrollador("Ana", List.of("Java", "Python", "SQL")),
                new Desarrollador("Luis", List.of("JavaScript", "TypeScript")),
                new Desarrollador("María", List.of("Java", "C++", "Rust", "Python"))
        );

        List<String> todosLosLenguajes = devs.stream()
                .flatMap(d -> d.lenguajes().stream())   // Aplana cada lista interna
                .distinct()                              // Elimina repetidos
                .sorted()                                // Orden alfabético
                .toList();

        System.out.println("  Lenguajes únicos: " + todosLosLenguajes);
    }

    // ═══════════════════════════════════════════════════════
    //                       MAIN
    // ═══════════════════════════════════════════════════════
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║     GUÍA COMPLETA DE STREAMS EN JAVA        ║");
        System.out.println("╚══════════════════════════════════════════════╝\n");

        // --- Operaciones de filtrado ---
        filtrarMayoresDe30();
        filtrarJovenesConBuenSalario();

        // --- Transformación ---
        nombresEnMayusculas();
        nombresDeIngenieros();

        // --- Ordenamiento ---
        ordenarAlfabeticamente();
        ordenarPorSalarioDescendente();

        // --- Únicos ---
        ciudadesUnicas();

        // --- Conteo y estadísticas ---
        contarEstudiantes();
        personaMayorYMenorSalario();
        sumaTotal();
        promedioEdad();
        estadisticasSalario();

        // --- Agrupación ---
        agruparPorCiudad();
        contarPorProfesion();
        nombresPorCiudad();
        particionarPorEdad();

        // --- Verificaciones ---
        verificaciones();

        // --- Paginación ---
        topTresMejorPagados();
        paginacion();

        // --- Conversión a otros formatos ---
        unirNombres();
        crearMapaNombreSalario();

        // --- Acción directa ---
        presentarPersonas();

        // --- Avanzado ---
        flatMapEjemplo();
    }
}
