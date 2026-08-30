package dosw.semana_4.taller.ejercicio8;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Sistema de Pedidos en Restaurante ---");

        System.out.println("\n1. El cliente construye su hamburguesa...");
        
        // El Builder garantiza la construcción paso a paso y la validación
        Order order = new OrderBuilder()
                .setSize(Size.LARGE)
                .setMeat(Meat.DOUBLE_BEEF)
                .addTopping("queso", "lechuga", "tocineta")
                .addSide("papas fritas", "gaseosa")
                .build(); // Retorna un Order inmutable y válido

        // El cliente suscribe los servicios que deben enterarse cuando pague/confirme
        order.addObserver(new KitchenService());
        order.addObserver(new BillingService());
        order.addObserver(new DeliveryService());

        // 2. El cliente confirma el pedido (Observer dispara las reacciones)
        order.confirm();
    }
}
