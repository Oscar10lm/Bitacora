package dosw.semana_4.taller.ejercicio8;

public class BillingService implements OrderObserver {
    @Override
    public void onOrderConfirmed(Order order) {
        System.out.println("  [Facturación] Generando ticket y cobro para el pedido #" + order.hashCode() + ".");
    }
}
