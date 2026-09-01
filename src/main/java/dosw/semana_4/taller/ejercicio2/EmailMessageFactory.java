package dosw.semana_4.taller.ejercicio2;

public class EmailMessageFactory implements MessageFactory {
    @Override
    public Message build(OrderEvent event) {
        String contenido = "=========================================\n" +
                           "📧 ACTUALIZACIÓN DE TU PEDIDO\n" +
                           "=========================================\n\n" +
                           "Hola,\n\n" +
                           "Te informamos que tu pedido #" + event.getOrderId() + "\n" +
                           "ha cambiado su estado a: [" + event.getStatus() + "].\n\n" +
                           "¡Gracias por tu compra!\n" +
                           "=========================================";
                
        return new Message(contenido);
    }
}
