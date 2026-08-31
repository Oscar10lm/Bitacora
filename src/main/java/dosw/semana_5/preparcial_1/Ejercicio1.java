package dosw.semana_5.preparcial_1;
import java.util.*;
import java.util.stream.Collectors;

public class Ejercicio1 {
    record Transaccion(String emailCliente, double monto, String paisDestino, boolean esInternacional) {}

    public static void main(String[] args) {
        List<Transaccion> txs = List.of(
                new Transaccion("hacker@dark.net", 8_500_000, "Rusia", true),
                new Transaccion("abuela@gmail.com", 200_000, "Colombia", false),
                new Transaccion("ceo@empresa.com", 12_000_000, "Suiza", true),
                new Transaccion("hacker@dark.net", 6_000_000, "Rusia", true), // reincidente
                new Transaccion("oscar@dosw.com", 4_500_000, "USA", true)
        );

        // TODO: 1. Filtrar internacionales mayores a 5M
        List<Transaccion> filtradas = txs.stream().filter(n -> n.monto() > 5_000_000 &&
                n.esInternacional()).toList();

        // TODO: 2. Lista de correos únicos (sin duplicados)

        List<String> correosUnicos = filtradas.stream().map(t -> t.emailCliente())
                .distinct().toList();

        // TODO: 3. Optional: Transacción más alta y mensaje dinámico (usa ifPresentOrElse o isPresent)

        Optional<Transaccion> maxTransaccion = filtradas.stream().max(Comparator.comparing(Transaccion::monto));
        maxTransaccion.ifPresentOrElse(
                n -> {System.out.println("Alerta Máxima: " + n.emailCliente() + " movió: " +  n.monto());},
                () -> {System.out.println("Sistema seguro");}
        );

        // TODO: 4. Map<País, SumaTotalMonto> (Pista: Collectors.groupingBy y Collectors.summingDouble)

        Map<String, Double> sumaPorPais = txs.stream()
                .collect(Collectors.groupingBy(Transaccion::paisDestino, Collectors.summingDouble(Transaccion::monto)));
    }
}
