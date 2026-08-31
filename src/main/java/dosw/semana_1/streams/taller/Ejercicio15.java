package dosw.semana_1.streams.taller;

import java.util.List;

public class Ejercicio15 {

    /**
     * Verifica que el usuario "root" NO exista en la lista de nombres.
     * Devuelve true porque efectivamente la lista no lo tiene.
     */

    public static void ejercicio15 () {
        List<String> nombres = List.of(
                "juan", "maria", "admin",
                "pedro", "soporte"
        );
        boolean verificar = nombres.stream().noneMatch(n -> n.equals("root"));
        System.out.println("¿Ninguno es root? " + verificar);
    }

    public static void main(String[] args) {
        ejercicio15();
    }
}
