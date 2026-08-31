package dosw.semana_2.pokemon.entrenador_novato_N1;

import java.util.List;

public class Ejercicio3 {

    /**
     * Calcula el poder total de un equipo Pokémon sumando todos los
     * niveles de la lista utilizando la función terminal reduce.
     */

    public static void ejercicio3() {
        List<Integer> niveles = List.of(45, 62, 38, 71, 55, 29);

        int sumaTotal = niveles.stream()
                .reduce(0, Integer::sum);

        System.out.println("Suma total de niveles: " + sumaTotal);
    }

    public static void main(String[] args) {
        ejercicio3();
    }
}
