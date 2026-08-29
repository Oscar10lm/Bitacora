package src.main.dosw.semana_1.streams.extra;

import java.util.List;

/**PATRÓN: DEPURACIÓN Y COMPROBACIÓN (PEEK + CORTOCIRCUITO)
 * Uso:
 * "Dada una lista, procesa los elementos imprimiéndolos para ver qué pasa
 * (debug) y verifica si AL MENOS UNO cumple una condición. Útil para validar
 * lotes de datos enteros".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista.
 * 2. .peek(System.out::println) -> Imprime el elemento tal cual va pasando por la cinta.
 * 3. .anyMatch( t -> condición ) -> Revisa la regla. OJO: Tiene "cortocircuito", lo que
 *    significa que apenas encuentre un 'true' (ej. la primera transacción denegada),
 *    detiene el Stream completo y no procesa los elementos restantes.
 */

public class Ejercicio5 {

    // Se usa un Record para representar la clase Transaction descrita en la imagen
    record Transaction(String id, double amount, boolean isApproved) {}

    /**
     * Recorre un lote de transacciones, imprimiendo cada una en consola.
     * Verifica si existe alguna que NO esté aprobada para determinar la validez
     * de todo el lote.
     */

    public static void ejercicio5() {
        List<Transaction> transactions = List.of(
                new Transaction("TX-001", 120.50, true),
                new Transaction("TX-002", 350.00, true),
                new Transaction("TX-003", 90.25, false),
                new Transaction("TX-004", 500.00, true)
        );

        System.out.println("--- Iniciando procesamiento ---");

        // El stream evalúa y el peek imprime. Notarás que la TX-004 nunca se
        // imprime porque el anyMatch se detiene en la TX-003 al dar 'true'.
        boolean hasUnapprovedTransaction = transactions.stream()
                .peek(System.out::println)
                .anyMatch(transaction -> !transaction.isApproved());

        System.out.println("--- Fin del procesamiento ---");

        // Si existe alguna transacción sin aprobar, el lote entero es inválido
        boolean isBatchValid = !hasUnapprovedTransaction;

        System.out.println("¿El lote de transacciones es válido? " + isBatchValid);
    }

    public static void main(String[] args) {
        ejercicio5();
    }
}