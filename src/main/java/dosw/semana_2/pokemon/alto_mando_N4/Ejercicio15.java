package dosw.semana_2.pokemon.alto_mando_N4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**PATRÓN: BÚSQUEDA DEL VALOR MÁXIMO EN OBJETOS (MAX)
 * Uso:
 * "Dada una lista de objetos complejos, buscar y extraer el objeto
 * completo que posea el valor más alto en un atributo numérico específico
 * (en este caso, la cantidad de medallas)".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista de objetos.
 * 2. .max(Comparator.comparingInt( Entrenador::getMedallas )) -> Busca el máximo
 *    evaluando el atributo entero. Devuelve un 'Optional' (caja de seguridad). Cierra el proceso.
 * 3. .get() -> Extrae el objeto ganador de la caja para acceder a sus datos.
 */

public class Ejercicio15 {

    /**
     * Evalúa la lista de entrenadores utilizando max() junto con un comparador
     * para encontrar al que tenga la mayor cantidad de medallas y lo extrae.
     */

    public static void ejercicio15() {
        // Se instancian los entrenadores requeridos en la imagen.
        // Se usa una lista vacía para el 'equipo' ya que este ejercicio no lo evalúa.
        List<Entrenador> entrenadores = List.of(
                new Entrenador(1L, "Ash", 8, new ArrayList<>()),
                new Entrenador(2L, "Misty", 5, new ArrayList<>()),
                new Entrenador(3L, "Brock", 6, new ArrayList<>()),
                new Entrenador(4L, "Gary", 10, new ArrayList<>())
        );

        Optional<Entrenador> maestroOpt = entrenadores.stream()
                .max(Comparator.comparingInt(Entrenador::getMedallas));

        // Se extrae el objeto del Optional y se formatea para cumplir con la salida exacta
        Entrenador maestro = maestroOpt.get();
        System.out.println("Campeón de gimnasios: " + maestro.getNombre());
        System.out.println("Medallas obtenidas: " + maestro.getMedallas());
    }

    public static void main(String[] args) {
        ejercicio15();
    }
}
