package dosw.semana_4.taller.ejercicio8;

public class DeliveryService implements OrderObserver {
    @Override
    public void onOrderConfirmed(Order order) {
        System.out.println("  [Domiciliario] Asignando repartidor y trazando ruta para el pedido #" + order.hashCode() + ".");
    }
}
