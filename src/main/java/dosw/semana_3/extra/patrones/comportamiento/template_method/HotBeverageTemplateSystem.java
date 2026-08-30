package dosw.semana_3.extra.patrones.comportamiento.template_method;

/**
 * ============================================================================
 * PATRÓN DE DISEÑO: TEMPLATE METHOD (COMPORTAMIENTO)
 * ============================================================================
 *
 * DEFINICIÓN:
 * Define el esqueleto de un algoritmo en una operación, delegando algunos pasos 
 * a las subclases. Template Method permite a las subclases redefinir ciertos 
 * pasos de un algoritmo sin cambiar la estructura o el esqueleto del mismo.
 *
 * VENTAJAS CLAVE:
 * - Evita la duplicación de código en procesos similares.
 * - Inversión de Control ("Principio de Hollywood"): "No nos llames, nosotros 
 *   te llamaremos". La clase base llama a los métodos de las subclases y no al revés.
 * - Asegura que el proceso principal siempre siga el mismo orden de ejecución.
 *
 * ----------------------------------------------------------------------------
 * EJERCICIO PRÁCTICO T1: PREPARACIÓN DE BEBIDAS CALIENTES
 * ----------------------------------------------------------------------------
 * Preparar café y té sigue el mismo esqueleto general (hervir agua, preparar 
 * el ingrediente, verter en taza, agregar condimentos).
 * 
 * Los pasos "preparar ingrediente" y "agregar condimentos" varían, pero el 
 * orden general es inmutable.
 */
public class HotBeverageTemplateSystem {

    // ==========================================
    // 1. LA CLASE ABSTRACTA (El Plantilla / Esqueleto)
    // ==========================================
    public static abstract class HotBeverage {
        
        // EL TEMPLATE METHOD
        // Se declara 'final' para evitar que las subclases alteren el orden de la receta
        public final void prepareRecipe() {
            boilWater();
            brew();
            pourInCup();
            addCondiments();
            System.out.println("-> ¡Bebida lista para servir!\n");
        }

        // Pasos COMPARTIDOS (Implementados en la clase base)
        private void boilWater() {
            System.out.println("Paso 1: Hirviendo agua...");
        }

        private void pourInCup() {
            System.out.println("Paso 3: Sirviendo la bebida caliente en una taza...");
        }

        // Pasos VARIABLES (Delegados a las subclases)
        protected abstract void brew();
        protected abstract void addCondiments();
    }

    // ==========================================
    // 2. SUBCLASES CONCRETAS
    // ==========================================
    
    // Receta para el Té
    public static class Tea extends HotBeverage {
        @Override
        protected void brew() {
            System.out.println("Paso 2: Remojando las hojas de té en el agua...");
        }

        @Override
        protected void addCondiments() {
            System.out.println("Paso 4: Añadiendo rodajas de limón y miel...");
        }
    }

    // Receta para el Café
    public static class Coffee extends HotBeverage {
        @Override
        protected void brew() {
            System.out.println("Paso 2: Pasando el agua por el filtro de café molido...");
        }

        @Override
        protected void addCondiments() {
            System.out.println("Paso 4: Añadiendo leche y azúcar al gusto...");
        }
    }

    // ==========================================
    // 3. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> MÁQUINA DISPENSADORA (Template Method) <<<\n");

        System.out.println("--- Preparando un Té ---");
        HotBeverage myTea = new Tea();
        // El cliente solo llama al método esqueleto
        myTea.prepareRecipe();

        System.out.println("--- Preparando un Café ---");
        HotBeverage myCoffee = new Coffee();
        myCoffee.prepareRecipe();
        
        /*
         * Fíjate cómo la clase base impuso la disciplina del orden 
         * (Agua -> Brew -> Taza -> Condimentos) para ambas bebidas.
         */
    }
}
