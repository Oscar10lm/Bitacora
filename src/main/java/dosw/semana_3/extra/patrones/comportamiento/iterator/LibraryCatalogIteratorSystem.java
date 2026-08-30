package dosw.semana_3.extra.patrones.comportamiento.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * EJERCICIO PRÁCTICO: CATÁLOGO DE BIBLIOTECA (Iterator)
 * ============================================================================
 *
 * Una biblioteca tiene un catálogo de libros disponibles:
 * - Libro A, Libro B, Libro C, Libro D, Libro E
 *
 * El sistema debe poder recorrer el catálogo libro por libro sin exponer la 
 * estructura interna, permitiendo además que dos bibliotecarios recorran el 
 * catálogo al mismo tiempo de forma independiente (cada uno en su propia posición).
 */
public class LibraryCatalogIteratorSystem {

    // ==========================================
    // 1. EL OBJETO DE DATOS (El Libro)
    // ==========================================
    public static class Book {
        private String title;

        public Book(String title) {
            this.title = title;
        }

        public String getTitle() { return title; }

        @Override
        public String toString() {
            return "'" + title + "'";
        }
    }

    // ==========================================
    // 2. LA INTERFAZ ITERADORA (Iterator)
    // ==========================================
    public interface CatalogIterator {
        boolean hasMore();
        Book getNext();
    }

    // ==========================================
    // 3. LA INTERFAZ COLECCIÓN (Iterable)
    // ==========================================
    public interface IterableCatalog {
        CatalogIterator createIterator();
    }

    // ==========================================
    // 4. COLECCIÓN CONCRETA (El Catálogo)
    // ==========================================
    public static class LibraryCatalog implements IterableCatalog {
        
        private List<Book> books = new ArrayList<>();

        public LibraryCatalog() {
            books.add(new Book("Libro A (Ficción)"));
            books.add(new Book("Libro B (Historia)"));
            books.add(new Book("Libro C (Ciencias)"));
            books.add(new Book("Libro D (Filosofía)"));
            books.add(new Book("Libro E (Arte)"));
        }

        public List<Book> getBooks() {
            return books;
        }

        @Override
        public CatalogIterator createIterator() {
            // El catálogo simplemente crea un nuevo iterador y le pasa su lista
            return new BookIterator(this);
        }
    }

    // ==========================================
    // 5. ITERADOR CONCRETO
    // ==========================================
    // Maneja el estado de la iteración. Cada vez que se hace 'new BookIterator', 
    // se crea un estado completamente nuevo (currentPosition arranca en 0).
    public static class BookIterator implements CatalogIterator {
        
        private LibraryCatalog catalog;
        private int currentPosition = 0;

        public BookIterator(LibraryCatalog catalog) {
            this.catalog = catalog;
        }

        @Override
        public boolean hasMore() {
            return currentPosition < catalog.getBooks().size();
        }

        @Override
        public Book getNext() {
            if (this.hasMore()) {
                Book book = catalog.getBooks().get(currentPosition);
                currentPosition++;
                return book;
            }
            return null;
        }
    }

    // ==========================================
    // 6. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> INVENTARIO DE BIBLIOTECA <<<");

        // 1. Instanciamos la Colección (El Catálogo Único)
        LibraryCatalog catalogoPrincipal = new LibraryCatalog();

        // 2. Simulamos a dos bibliotecarios que necesitan revisar el catálogo
        CatalogIterator bibliotecarioJuan = catalogoPrincipal.createIterator();
        CatalogIterator bibliotecariaMaria = catalogoPrincipal.createIterator();

        System.out.println("\n--- COMIENZA EL INVENTARIO SIMULTÁNEO ---\n");
        
        // 3. Demostramos la iteración independiente
        
        // Juan revisa el primer libro
        System.out.println("Juan revisa   : " + bibliotecarioJuan.getNext());
        
        // María apenas empieza a revisar (arranca en el Libro A también)
        System.out.println("María revisa  : " + bibliotecariaMaria.getNext());
        
        // Juan avanza más rápido (Libro B y C)
        System.out.println("Juan revisa   : " + bibliotecarioJuan.getNext());
        System.out.println("Juan revisa   : " + bibliotecarioJuan.getNext());
        
        // María revisa su segundo libro (Libro B)
        System.out.println("María revisa  : " + bibliotecariaMaria.getNext());

        System.out.println("\n>>> ESTADO ACTUAL DE LOS ITERADORES <<<");
        // Juan va por la posición 3, María por la posición 2. 
        // ¡El mismo catálogo está siendo recorrido de forma paralela y segura!
        
        if(bibliotecarioJuan.hasMore()) {
            System.out.println("El próximo libro de Juan será: " + bibliotecarioJuan.getNext());
        }
        
        if(bibliotecariaMaria.hasMore()) {
            System.out.println("El próximo libro de María será: " + bibliotecariaMaria.getNext());
        }
    }
}
