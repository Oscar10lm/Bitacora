package dosw.semana_1.streams.taller;

import java.util.List;

public class Ejercicio13 {

    /**
     * Verifica si existe por lo menos un número par en toda la lista.
     * Apenas vea el "20", dejará de buscar y devolverá true.
     */

    public static void ejercicio13 () {
        List<Integer> numeros = List.of(7, 11, 13, 20, 25);
        boolean par = numeros.stream().anyMatch(n -> n % 2 == 0);
        System.out.println("¿Hay algún par?: " + par);
    }

    public static void main(String[] args) {
        ejercicio13();
    }
}
