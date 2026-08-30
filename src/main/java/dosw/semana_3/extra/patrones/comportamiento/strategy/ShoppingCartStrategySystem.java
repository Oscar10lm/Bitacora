package dosw.semana_3.extra.patrones.comportamiento.strategy;

/**
 * ============================================================================
 * EJERCICIO PRÁCTICO: CÁLCULO DE DESCUENTOS (Strategy)
 * ============================================================================
 *
 * Una tienda en línea aplica descuentos al total de la compra, pero la forma 
 * de calcular el descuento puede variar según la promoción activa:
 * - Descuento por porcentaje fijo (ej. 10% del total)
 * - Descuento por monto fijo (ej. $5000 menos, sin importar el total)
 * - Sin descuento (precio normal)
 *
 * El algoritmo de cálculo de descuento no debe estar acoplado al carrito de 
 * compras, ya que la promoción activa puede cambiar en cualquier momento 
 * (incluso durante la sesión del usuario).
 */
public class ShoppingCartStrategySystem {

    // ==========================================
    // 1. LA INTERFAZ ESTRATEGIA (Strategy)
    // ==========================================
    // Define el contrato para calcular el precio final
    public interface DiscountStrategy {
        double calculateFinalPrice(double originalPrice);
    }

    // ==========================================
    // 2. ESTRATEGIAS CONCRETAS (Los algoritmos separados)
    // ==========================================
    
    // Algoritmo 1: Sin descuento (Retorna el precio tal cual)
    public static class NoDiscountStrategy implements DiscountStrategy {
        @Override
        public double calculateFinalPrice(double originalPrice) {
            System.out.println(" -> Aplicando Estrategia: [Sin Descuento]");
            return originalPrice;
        }
    }

    // Algoritmo 2: Descuento por Porcentaje (Requiere el % en su constructor)
    public static class PercentageDiscountStrategy implements DiscountStrategy {
        private double percentage;

        public PercentageDiscountStrategy(double percentage) {
            this.percentage = percentage;
        }

        @Override
        public double calculateFinalPrice(double originalPrice) {
            System.out.println(" -> Aplicando Estrategia: [" + percentage + "% de descuento]");
            double discountAmount = originalPrice * (percentage / 100);
            return originalPrice - discountAmount;
        }
    }

    // Algoritmo 3: Descuento Fijo (Requiere el monto a restar en su constructor)
    public static class FixedAmountDiscountStrategy implements DiscountStrategy {
        private double fixedDiscount;

        public FixedAmountDiscountStrategy(double fixedDiscount) {
            this.fixedDiscount = fixedDiscount;
        }

        @Override
        public double calculateFinalPrice(double originalPrice) {
            System.out.println(" -> Aplicando Estrategia: [Descuento fijo de $" + fixedDiscount + "]");
            double finalPrice = originalPrice - fixedDiscount;
            // No podemos tener precios negativos
            return Math.max(finalPrice, 0.0);
        }
    }

    // ==========================================
    // 3. EL CONTEXTO (Context)
    // ==========================================
    // El Carrito de Compras
    public static class ShoppingCart {
        
        private double totalAmount = 0.0;
        private DiscountStrategy discountStrategy;

        public ShoppingCart(DiscountStrategy initialStrategy) {
            this.discountStrategy = initialStrategy;
        }

        public void addItemPrice(double price) {
            totalAmount += price;
        }

        // Permite inyectar una nueva promoción en tiempo de ejecución
        public void setDiscountStrategy(DiscountStrategy discountStrategy) {
            this.discountStrategy = discountStrategy;
        }

        // Delega el cálculo de la factura final a la estrategia actual
        public double checkout() {
            if (discountStrategy == null) {
                return totalAmount; // Prevención de errores
            }
            return discountStrategy.calculateFinalPrice(totalAmount);
        }
        
        public double getTotalAmount() {
            return totalAmount;
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> CARRITO DE COMPRAS AMAZON CLON <<<");

        // 1. El usuario empieza con un carrito sin descuentos
        ShoppingCart cart = new ShoppingCart(new NoDiscountStrategy());
        
        // Agrega un par de productos (100k y 50k)
        cart.addItemPrice(100000.0);
        cart.addItemPrice(50000.0);
        
        System.out.println("\nSubtotal en el carrito: $" + cart.getTotalAmount());
        
        // 2. El usuario va a pagar sin cupones
        System.out.println("\n--- Checkout Inicial ---");
        double finalPrice = cart.checkout();
        System.out.println("Total a Pagar: $" + finalPrice);

        // 3. El usuario encuentra un cupón del 20% en internet y lo aplica
        System.out.println("\n--- Cliente aplica cupón 'BLACKFRIDAY20' ---");
        // ¡Magia del Strategy! Cambiamos el algoritmo dinámicamente
        cart.setDiscountStrategy(new PercentageDiscountStrategy(20.0));
        finalPrice = cart.checkout();
        System.out.println("Total a Pagar con 20% OFF: $" + finalPrice);

        // 4. El sistema detecta que el usuario es cliente VIP y le regala $15,000 en vez del %
        System.out.println("\n--- Sistema detecta membresía VIP y aplica bono fijo ---");
        cart.setDiscountStrategy(new FixedAmountDiscountStrategy(15000.0));
        finalPrice = cart.checkout();
        System.out.println("Total a Pagar con bono de $15,000: $" + finalPrice);
    }
}
