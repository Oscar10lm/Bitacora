package dosw.semana_4.taller.ejercicio2;

public class EmailMessageFactory implements MessageFactory {
    @Override
    public Message build(OrderEvent event) {
        return new Message("<html><body><h1>Actualización de Pedido</h1><p>El pedido <b>" 
                           + event.getOrderId() + "</b> ahora está <b>" + event.getStatus() + "</b>.</p></body></html>");
    }
}
