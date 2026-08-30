package dosw.semana_4.taller.ejercicio8;

public class KitchenService implements OrderObserver {
    @Override
    public void onOrderConfirmed(Order order) {
        System.out.println("  [Cocina] Recibido pedido #" + order.hashCode() + ". Preparando hamburguesa " + order.getSize() + "...");
    }
}
