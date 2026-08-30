package dosw.semana_4.taller.ejercicio2;

public class PushMessageFactory implements MessageFactory {
    @Override
    public Message build(OrderEvent event) {
        return new Message("{ \"title\": \"Actualización\", \"body\": \"Tu pedido " 
                           + event.getOrderId() + " está " + event.getStatus() + "\" }");
    }
}
