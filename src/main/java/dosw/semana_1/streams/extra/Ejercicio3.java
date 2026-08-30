package dosw.semana_1.streams.extra;

import java.util.List;

/**PATRÓN: EXTRACCIÓN Y TRANSFORMACIÓN DE ATRIBUTOS (PROYECCIÓN)
 * Uso:
 * "Dada una lista de objetos (ej. Usuarios), filtrar por una condición,
 * extraer un atributo específico de ese objeto (ej. el nombre), transformarlo
 * y ordenar los resultados".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista.
 * 2. .filter() -> Selecciona los objetos que cumplen la regla. Se puede usar 'Referencia de Método' (Clase::metodoBooleano).
 * 3. .map() -> Convierte el objeto completo en solo una parte de él (ej. pasar de User a String).
 *    - Opción A: Encadenar múltiples .map() separando pasos (uno para extraer, otro para mayúsculas).
 *    - Opción B: Un solo .map() con una función lambda que hace todo el trabajo a la vez.
 * 4. .sorted() -> Ordena alfabéticamente (ya que ahora el flujo contiene Strings).
 * 5. .toList() -> Empaca los Strings resultantes en una nueva lista.
 */

public class Ejercicio3 {

    // Se define una estructura Record básica para simular los datos de entrada del ejercicio
    record User(int id, String name, int age, boolean isActive) {}

    /**
     * Filtra únicamente los usuarios activos, extrae sus nombres convirtiéndolos
     * a mayúsculas y ordena el resultado final alfabéticamente.
     */

    public static void ejercicio3() {
        List<User> users = List.of(
                new User(1, "Zacarias", 25, true),
                new User(2, "Pedro", 30, false),
                new User(3, "Ana", 22, true),
                new User(4, "Carlos", 28, true)
        );

        // Opción 1: Múltiples .map() y Referencias a Métodos (Method References)
        List<String> result = users.stream()
                .filter(User::isActive)
                .map(User::name)
                .map(String::toUpperCase)
                .sorted()
                .toList();

        System.out.println("Opción 1 (Múltiples steps): " + result);

        // Opción 2: Un solo .map() usando una expresión Lambda combinada
        List<String> resultado = users.stream()
                .filter(User::isActive)
                .map(u -> u.name().toUpperCase())
                .sorted()
                .toList();

        System.out.println("Opción 2 (Map combinado): " + resultado);
    }

    public static void main(String[] args) {
        ejercicio3();
    }
}
