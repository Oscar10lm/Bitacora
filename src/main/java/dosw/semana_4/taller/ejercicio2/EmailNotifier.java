package dosw.semana_4.taller.ejercicio2;

public class EmailNotifier implements NotificationObserver {
    private final MessageFactory factory = new EmailMessageFactory();

    @Override
    public void notify(OrderEvent event) {
        Message msg = factory.build(event);
        System.out.println("Enviando EMAIL: " + msg.getContent());
    }
}
