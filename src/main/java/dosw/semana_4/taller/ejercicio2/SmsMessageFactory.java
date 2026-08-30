package dosw.semana_4.taller.ejercicio2;

public class SmsMessageFactory implements MessageFactory {
    @Override
    public Message build(OrderEvent event) {
        return new Message("INFO: Pedido " + event.getOrderId() + " -> " + event.getStatus());
    }
}
