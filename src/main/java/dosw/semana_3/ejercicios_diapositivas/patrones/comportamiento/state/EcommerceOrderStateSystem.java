package dosw.semana_3.ejercicios_diapositivas.patrones.comportamiento.state;

public class EcommerceOrderStateSystem {

    // ==========================================
    // 1. EL CONTEXTO (El Pedido)
    // ==========================================
    public static class Order {
        private OrderState state;

        public Order() {
            // Estado inicial por defecto
            this.state = new PendingState();
        }

        public void setState(OrderState state) {
            this.state = state;
        }

        // Delegación de acciones a los estados
        public void pay() {
            state.pay(this);
        }

        public void ship() {
            state.ship(this);
        }

        public void deliver() {
            state.deliver(this);
        }

        public void cancel() {
            state.cancel(this);
        }
    }

    // ==========================================
    // 2. LA INTERFAZ ESTADO
    // ==========================================
    public interface OrderState {
        void pay(Order order);
        void ship(Order order);
        void deliver(Order order);
        void cancel(Order order);
    }

    // ==========================================
    // 3. ESTADOS CONCRETOS
    // ==========================================
    
    public static class PendingState implements OrderState {
        @Override
        public void pay(Order order) {
            System.out.println("[Pendiente] -> Pago procesado correctamente. Pasando a estado 'PAGADO'.");
            order.setState(new PaidState());
        }

        @Override
        public void ship(Order order) {
            System.out.println("[Pendiente] -> ERROR: No se puede enviar un pedido que no ha sido pagado.");
        }

        @Override
        public void deliver(Order order) {
            System.out.println("[Pendiente] -> ERROR: No se puede entregar un pedido no enviado.");
        }

        @Override
        public void cancel(Order order) {
            System.out.println("[Pendiente] -> El pedido ha sido CANCELADO exitosamente sin cobros.");
            // order.setState(new CancelledState()); // Asumiendo que existiera un estado Cancelado
        }
    }

    public static class PaidState implements OrderState {
        @Override
        public void pay(Order order) {
            System.out.println("[Pagado] -> ERROR: Este pedido ya fue pagado.");
        }

        @Override
        public void ship(Order order) {
            System.out.println("[Pagado] -> Productos empacados. El pedido está ahora 'EN CAMINO'.");
            order.setState(new ShippedState());
        }

        @Override
        public void deliver(Order order) {
            System.out.println("[Pagado] -> ERROR: Aún no ha sido enviado, no se puede entregar.");
        }

        @Override
        public void cancel(Order order) {
            System.out.println("[Pagado] -> Pedido CANCELADO. Procediendo a hacer el reembolso del dinero.");
        }
    }

    public static class ShippedState implements OrderState {
        @Override
        public void pay(Order order) {
            System.out.println("[Enviado] -> ERROR: Ya fue pagado.");
        }

        @Override
        public void ship(Order order) {
            System.out.println("[Enviado] -> ERROR: Ya está en camino.");
        }

        @Override
        public void deliver(Order order) {
            System.out.println("[Enviado] -> Cliente confirmó recepción. El pedido está ahora 'ENTREGADO'.");
            order.setState(new DeliveredState());
        }

        @Override
        public void cancel(Order order) {
            System.out.println("[Enviado] -> ERROR: No se puede cancelar porque el paquete ya está con el transportista.");
        }
    }

    public static class DeliveredState implements OrderState {
        @Override
        public void pay(Order order) {
            System.out.println("[Entregado] -> ERROR: Ciclo finalizado.");
        }

        @Override
        public void ship(Order order) {
            System.out.println("[Entregado] -> ERROR: Ciclo finalizado.");
        }

        @Override
        public void deliver(Order order) {
            System.out.println("[Entregado] -> ERROR: Ya fue entregado.");
        }

        @Override
        public void cancel(Order order) {
            System.out.println("[Entregado] -> ERROR: Ya se entregó. Debe gestionar una devolución en su lugar.");
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        System.out.println(">>> AMAZON CLON: PROCESANDO PEDIDO #1 <<<\n");
        
        Order myOrder = new Order(); // Arranca en 'Pendiente'
        
        System.out.println("--- Intento de enviar sin pagar ---");
        myOrder.ship(); 
        
        System.out.println("\n--- Cliente realiza el pago ---");
        myOrder.pay(); 
        
        System.out.println("\n--- Cliente se arrepiente e intenta pagar doble ---");
        myOrder.pay();
        
        System.out.println("\n--- Bodega hace el envío ---");
        myOrder.ship();
        
        System.out.println("\n--- Cliente intenta cancelar a mitad de camino ---");
        myOrder.cancel();
        
        System.out.println("\n--- Paquete llega a la casa ---");
        myOrder.deliver();
    }
}
