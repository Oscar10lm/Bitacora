package dosw.semana_3.taller.patrones;

import java.util.ArrayList;
import java.util.List;

/**
 * -------------------------------------------------------------------
 * PARTE III — IDENTIFICANDO PATRONES DE DISEÑO
 * #14 Sistema de Eventos — Cambio de Estado de Pedido
 * -------------------------------------------------------------------
 *
 * (1) SITUACIÓN:
 * Cuando un pedido cambia de estado, hay que avisar a 4 sistemas distintos. 
 * Si mañana queremos que facturación también se entere, no queremos abrir 
 * ni tocar el código de la clase `Pedido`.
 *
 * (2) CATEGORÍA:
 * Patrón de Comportamiento (Behavioral Pattern).
 *
 * (3) PATRÓN SELECCIONADO:
 * Observer (Observador) / Publisher-Subscriber.
 *
 * (4) ¿POR QUÉ?:
 * El patrón Observer define una dependencia de uno a muchos entre objetos. 
 * El "Sujeto" (Pedido) mantiene una lista de "Observadores" (Inventario, 
 * Correo, etc.) que cumplen una interfaz común. Cuando el estado del Pedido 
 * cambia, este simplemente recorre la lista avisándoles a todos.
 * Agregar un nuevo observador (como Facturación) solo implica crear la 
 * nueva clase y añadirla a la lista del Pedido; la clase Pedido en sí nunca 
 * se modifica. Esto cumple perfectamente con el principio OCP.
 *
 * (5) ¿APLICA MÁS DE UN PATRÓN? (Comparación):
 * - Se podría pensar en el patrón **Mediator** (Mediador), que también 
 *   desacopla objetos. Sin embargo, Mediator se usa cuando hay una red 
 *   caótica de objetos que se comunican *entre sí* (todos contra todos). 
 *   Aquí tenemos un caso puro de "Uno hacia Muchos" de forma reactiva, lo 
 *   cual es el dominio absoluto del patrón Observer.
 */
public class Ejercicio14 {

    // --- IMPLEMENTACIÓN DEL PATRÓN OBSERVER ---

    // 1. Interfaz Observador (El que escucha)
    public interface OrderObserver {
        void update(String orderId, String newStatus);
    }

    // 2. Observadores Concretos
    
    public static class InventorySystem implements OrderObserver {
        @Override
        public void update(String orderId, String newStatus) {
            System.out.println("Inventario: Actualizando stock para el pedido " + orderId + " (Estado: " + newStatus + ")");
        }
    }

    public static class EmailSystem implements OrderObserver {
        @Override
        public void update(String orderId, String newStatus) {
            System.out.println("Correo: Enviando email al cliente sobre el pedido " + orderId);
        }
    }

    // El nuevo sistema que agregaremos en el futuro sin tocar el código original
    public static class BillingSystem implements OrderObserver {
        @Override
        public void update(String orderId, String newStatus) {
            if (newStatus.equals("ENTREGADO")) {
                System.out.println("Facturación: Generando factura automática para el pedido " + orderId);
            }
        }
    }

    // 3. Sujeto / Publisher (El que avisa)
    public static class Order {
        private String id;
        private String status;
        // Lista dinámica de observadores
        private List<OrderObserver> observers = new ArrayList<>();

        public Order(String id) {
            this.id = id;
            this.status = "CREADO";
        }

        // Métodos para gestionar los observadores (Suscribirse / Desuscribirse)
        public void attach(OrderObserver observer) {
            observers.add(observer);
        }

        public void detach(OrderObserver observer) {
            observers.remove(observer);
        }

        // Método que dispara el evento a todos los suscritos
        private void notifyObservers() {
            for (OrderObserver obs : observers) {
                obs.update(this.id, this.status);
            }
        }

        // Lógica de negocio que cambia el estado
        public void changeStatus(String newStatus) {
            this.status = newStatus;
            System.out.println("\n--- [PEDIDO " + id + "] cambió su estado a: " + newStatus + " ---");
            // Se notifica automáticamente al cambiar el estado
            notifyObservers();
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        Order myOrder = new Order("PED-9988");

        // Suscribimos los sistemas actuales
        myOrder.attach(new InventorySystem());
        myOrder.attach(new EmailSystem());
        
        // ¡Se agrega Facturación sin modificar ni una línea dentro de la clase Order!
        myOrder.attach(new BillingSystem());

        // Simulamos el ciclo de vida del pedido
        myOrder.changeStatus("PAGADO");
        myOrder.changeStatus("EN_TRANSITO");
        myOrder.changeStatus("ENTREGADO");
    }
}
