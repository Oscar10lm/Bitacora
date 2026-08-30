package dosw.semana_3.extra.patrones.comportamiento.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * PATRÓN DE DISEÑO: ITERATOR (COMPORTAMIENTO)
 * ============================================================================
 *
 * DEFINICIÓN:
 * Es un patrón de diseño de comportamiento que te permite recorrer elementos 
 * de una colección sin exponer su representación subyacente (lista, pila, 
 * árbol, etc.).
 *
 * VENTAJAS CLAVE:
 * - Extrae el comportamiento de recorrido de una colección y lo coloca en un 
 *   objeto independiente llamado iterador.
 * - Varios iteradores pueden recorrer la misma colección al mismo tiempo.
 * - Promueve el Single Responsibility Principle al limpiar el código de la 
 *   colección principal.
 * - Promueve el Open/Closed Principle: puedes implementar nuevos tipos de 
 *   recorridos sin modificar la colección original.
 *
 * ----------------------------------------------------------------------------
 * EJERCICIO PRÁCTICO: GUÍA TURÍSTICO EN ROMA
 * ----------------------------------------------------------------------------
 * Un turista quiere explorar distintos lugares emblemáticos de Roma.
 * El recorrido debe poder iterarse sin exponer la estructura interna que 
 * almacena los lugares.
 */
public class TouristGuideIteratorSystem {

    // ==========================================
    // 1. LA INTERFAZ ITERADORA (Iterator)
    // ==========================================
    // Declara las operaciones necesarias para recorrer la colección.
    public interface Iterator {
        boolean hasMore();
        String getNext();
    }

    // ==========================================
    // 2. LA INTERFAZ COLECCIÓN (IterableCollection)
    // ==========================================
    // Declara el método para obtener iteradores compatibles.
    public interface IterableCollection {
        Iterator createIterator();
    }

    // ==========================================
    // 3. COLECCIÓN CONCRETA (ConcreteCollection)
    // ==========================================
    // Almacena la colección de datos (en este caso usamos una Lista genérica)
    public static class RomeAttractionsCollection implements IterableCollection {
        
        // El cliente nunca accede directamente a esta lista privada.
        private List<String> attractions = new ArrayList<>();

        public RomeAttractionsCollection() {
            // Llenamos la colección con los datos del ejercicio
            attractions.add("Coliseo Romano");
            attractions.add("Foro Romano");
            attractions.add("Fontana di Trevi");
            attractions.add("Panteón de Agripa");
            attractions.add("Plaza de España");
        }

        public List<String> getAttractions() {
            return attractions;
        }

        @Override
        public Iterator createIterator() {
            // Devuelve una nueva instancia del iterador concreto acoplado a esta colección
            return new RomeAttractionsIterator(this);
        }
    }

    // ==========================================
    // 4. ITERADOR CONCRETO (ConcreteIterator)
    // ==========================================
    // Implementa el algoritmo específico para recorrer esta colección.
    public static class RomeAttractionsIterator implements Iterator {
        
        private RomeAttractionsCollection collection;
        private int currentPosition = 0; // Controla el estado del recorrido

        public RomeAttractionsIterator(RomeAttractionsCollection collection) {
            this.collection = collection;
        }

        @Override
        public boolean hasMore() {
            // ¿Quedan más elementos en la lista?
            return currentPosition < collection.getAttractions().size();
        }

        @Override
        public String getNext() {
            // Extrae el elemento actual y avanza el cursor a la siguiente posición
            if (this.hasMore()) {
                String attraction = collection.getAttractions().get(currentPosition);
                currentPosition++;
                return attraction;
            }
            return null; // O lanzar excepción si no hay más
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> INICIANDO TOUR POR ROMA <<<");

        // 1. Instanciamos la Colección (El Itinerario)
        RomeAttractionsCollection itinerarioRoma = new RomeAttractionsCollection();

        // 2. Le pedimos a la colección que nos dé un Iterador (El Guía Turístico)
        Iterator guiaTuristico = itinerarioRoma.createIterator();

        // 3. El cliente (el turista) recorre la colección usando la interfaz estándar del iterador
        // NOTA: Al turista no le importa si la colección por debajo es un Array, un HashSet, o un Árbol.
        // Solo sabe hacer dos preguntas: "¿Hay más?" y "Dame el siguiente".
        
        int parada = 1;
        while (guiaTuristico.hasMore()) {
            String lugarActual = guiaTuristico.getNext();
            System.out.println("Parada " + parada + ": Visitando el " + lugarActual);
            parada++;
        }

        System.out.println("\n>>> TOUR FINALIZADO <<<");
        
        /*
         * VENTAJA ADICIONAL:
         * Podríamos tener a un turista diferente usando un segundo iterador 
         * al mismo tiempo, y el estado de 'currentPosition' sería independiente.
         */
        System.out.println("\n--- Turista Rezaga ---");
        Iterator guiaTuristaLento = itinerarioRoma.createIterator();
        System.out.println("El turista lento recién está visitando: " + guiaTuristaLento.getNext());
    }
}
