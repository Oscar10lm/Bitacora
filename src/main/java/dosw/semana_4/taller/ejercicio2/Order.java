package dosw.semana_4.taller.ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String id;
    private String status;
    private final List<NotificationObserver> observers = new ArrayList<>();

    public Order(String id) {
        this.id = id;
        this.status = "creado";
    }

    public void addObserver(NotificationObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    private void notifyObservers() {
        OrderEvent event = new OrderEvent(id, status);
        for (NotificationObserver observer : observers) {
            observer.notify(event);
        }
    }
}
