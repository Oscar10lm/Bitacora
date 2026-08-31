package dosw.semana_3.extra.patrones.estructurales.facade;

public class EcommerceFacadeSystem {

    // ==========================================
    // 1. EL SUBSISTEMA COMPLEJO (Microservicios)
    // ==========================================
    
    public static class InventorySystem {
        public boolean checkStock(String productId) {
            System.out.println("  [Inventario]: Verificando stock para el producto " + productId + "... (¡Hay 5 unidades!)");
            return true;
        }
        
        public void updateStock(String productId) {
            System.out.println("  [Inventario]: Restando 1 unidad del producto " + productId + " de la base de datos.");
        }
    }

    public static class PaymentSystem {
        public boolean chargeCard(String cardNumber, double amount) {
            System.out.println("  [Pagos]: Conectando con pasarela Stripe...");
            System.out.println("  [Pagos]: Cobrando $" + amount + " a la tarjeta " + cardNumber + " (APROBADO).");
            return true;
        }
    }

    public static class BillingSystem {
        public void generateInvoice(String customerEmail) {
            System.out.println("  [Facturación]: Generando PDF con impuestos y facturación electrónica.");
            System.out.println("  [Facturación]: Enviando factura al correo " + customerEmail + ".");
        }
    }

    public static class ShippingSystem {
        public void scheduleDelivery(String productId, String address) {
            System.out.println("  [Despachos]: Creando guía de envío con FedEx/Servientrega.");
            System.out.println("  [Despachos]: El producto " + productId + " será enviado a '" + address + "'.");
        }
    }

    // ==========================================
    // 2. LA FACHADA (Facade)
    // ==========================================
    public static class OrderProcessorFacade {
        private InventorySystem inventory = new InventorySystem();
        private PaymentSystem payment = new PaymentSystem();
        private BillingSystem billing = new BillingSystem();
        private ShippingSystem shipping = new ShippingSystem();

        // El Frontend (Cliente) solo interactúa con este método limpio y claro
        public boolean placeOrder(String productId, String customerEmail, String cardNumber, String address, double price) {
            System.out.println("\n>>> INICIANDO PROCESO DE COMPRA (Backend) <<<");
            
            // 1. Verifica Inventario
            if (!inventory.checkStock(productId)) {
                System.out.println("ERROR: Producto sin stock.");
                return false;
            }

            // 2. Procesa Pago
            if (!payment.chargeCard(cardNumber, price)) {
                System.out.println("ERROR: Tarjeta rechazada.");
                return false;
            }

            // 3. Post-Pago (Inventario, Facturación y Envíos)
            inventory.updateStock(productId);
            billing.generateInvoice(customerEmail);
            shipping.scheduleDelivery(productId, address);

            System.out.println(">>> COMPRA FINALIZADA EXITOSAMENTE <<<\n");
            return true;
        }
    }

    // ==========================================
    // 3. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        System.out.println(">>> TIENDA ONLINE / FRONTEND (React/Angular) <<<\n");

        OrderProcessorFacade api = new OrderProcessorFacade();

        System.out.println("--- El usuario presiona el botón rojo de [PAGAR AHORA] ---");
        
        // En lugar de que el App Móvil o la página web tenga que hacer 5 peticiones HTTP a 
        // 5 subsistemas distintos, solo hace 1 llamada a la Fachada.
        
        boolean success = api.placeOrder(
            "LAPTOP_GAMER_X1", 
            "cliente@gmail.com", 
            "4111-2222-3333-4444", 
            "Calle 123 #45-67, Bogotá", 
            1500.00
        );

        if (success) {
            System.out.println("[App Móvil]: '¡Gracias por su compra! Su pedido está en camino.'");
        }
    }
}
