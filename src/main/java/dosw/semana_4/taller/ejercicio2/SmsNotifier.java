package dosw.semana_4.taller.ejercicio2;

public class SmsNotifier implements NotificationObserver {
    private final MessageFactory factory = new SmsMessageFactory();

    @Override
    public void notify(OrderEvent event) {
        Message msg = factory.build(event);
        System.out.println("Enviando SMS: " + msg.getContent());
    }
}
