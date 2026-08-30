package dosw.semana_4.taller.ejercicio2;

public interface MessageFactory {
    Message build(OrderEvent event);
}
