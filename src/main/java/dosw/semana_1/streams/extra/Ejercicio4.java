package dosw.semana_1.streams.extra;

import java.util.List;

public class Ejercicio4 {

    // Se define el Record Usuario basado en la imagen para poder ejecutar el código
    record Usuario(int id, String name, int age, boolean isActive) {}

    /**
     * Filtra los usuarios para quedarse solo con los mayores de edad (edad >= 18)
     * y luego extrae sus nombres para guardarlos en una nueva lista de Strings.
     */

    public static void ejercicio4() {
        List<Usuario> users = List.of(
                new Usuario(1, "carlos", 17, true),
                new Usuario(2, "ana", 30, false),
                new Usuario(3, "miguel", 15, true),
                new Usuario(4, "beatriz", 28, false),
                new Usuario(5, "juan", 35, true)
        );

        // Opción 1: Extraer el nombre usando Referencia de Método (Más elegante)
        List<String> resultado = users.stream()
                .filter(u -> u.age() >= 18)
                .map(Usuario::name)
                .toList();

        System.out.println("Opción 1 (Referencia de método): " + resultado);

        // Opción 2: Extraer el nombre usando una expresión Lambda tradicional
        List<String> resultadoLambda = users.stream()
                .filter(u -> u.age() >= 18)
                .map(u -> u.name())
                .toList();

        System.out.println("Opción 2 (Expresión Lambda): " + resultadoLambda);
    }

    public static void main(String[] args) {
        ejercicio4();
    }
}
