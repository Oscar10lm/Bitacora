package dosw.semana_3.extra.patrones.estructurales.composite;

import java.util.ArrayList;
import java.util.List;

public class WarehouseCompositeSystem {

    // ==========================================
    // 1. COMPONENTE (La Interfaz Común)
    // ==========================================
    // Describe operaciones comunes a elementos simples y complejos del árbol.
    public interface WarehouseItem {
        double getPrice();
        void printDescription(String indent);
    }

    // ==========================================
    // 2. HOJA (Elemento Básico)
    // ==========================================
    // Un elemento básico del árbol que no tiene subelementos. Realiza el trabajo real.
    public static class Product implements WarehouseItem {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public double getPrice() {
            // El producto retorna su propio precio base
            return price;
        }

        @Override
        public void printDescription(String indent) {
            System.out.println(indent + "- Producto: " + name + " ( $" + price + " )");
        }
    }

    // ==========================================
    // 3. COMPUESTO / CONTENEDOR (Elemento Complejo)
    // ==========================================
    // Un contenedor que tiene subelementos (Hojas u otros Contenedores).
    public static class Box implements WarehouseItem {
        
        // Mantiene una lista de los componentes hijos (Hojas o Cajas)
        private List<WarehouseItem> children = new ArrayList<>();
        private String boxName;

        public Box(String boxName) {
            this.boxName = boxName;
        }

        // Métodos de gestión de hijos
        public void addItem(WarehouseItem item) {
            children.add(item);
        }

        public void removeItem(WarehouseItem item) {
            children.remove(item);
        }

        @Override
        public double getPrice() {
            // DELEGA EL TRABAJO A SUS HIJOS (RECURSIÓN)
            double total = 0;
            for (WarehouseItem item : children) {
                total += item.getPrice(); // Polimorfismo mágico: no sabe si suma productos o cajas
            }
            // Agregamos un costo extra por la caja misma (opcional, simulando empaque)
            total += 2.0; 
            return total;
        }

        @Override
        public void printDescription(String indent) {
            System.out.println(indent + "[CAJA] " + boxName);
            for (WarehouseItem item : children) {
                // Llama al print de sus hijos aumentando la indentación
                item.printDescription(indent + "   "); 
            }
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println("--- ARMANDO EL PEDIDO EN BODEGA ---\n");

        // 1. Creamos elementos simples (Hojas)
        WarehouseItem celular = new Product("Smartphone", 800.0);
        WarehouseItem audifonos = new Product("Audífonos Bluetooth", 150.0);
        WarehouseItem cargador = new Product("Cargador Rápido", 25.0);
        WarehouseItem laptop = new Product("Laptop Gamer", 1500.0);

        // 2. Creamos una caja pequeña para accesorios
        Box cajaAccesorios = new Box("Caja Pequeña (Accesorios)");
        cajaAccesorios.addItem(audifonos);
        cajaAccesorios.addItem(cargador);

        // 3. Creamos una caja mediana para el celular y sus accesorios
        Box cajaMediana = new Box("Caja Mediana (Smartphone Kit)");
        cajaMediana.addItem(celular);
        cajaMediana.addItem(cajaAccesorios); // ¡Una caja dentro de otra caja!

        // 4. Creamos la caja de envío principal que incluye todo el pedido
        Box cajaEnvio = new Box("CAJA DE ENVÍO PRINCIPAL (Pedido #1042)");
        cajaEnvio.addItem(laptop);
        cajaEnvio.addItem(cajaMediana); // Guardamos la caja mediana adentro

        // 5. El cliente opera con toda la estructura de forma transparente
        System.out.println(">>> ESTRUCTURA DEL PEDIDO:");
        cajaEnvio.printDescription("");

        System.out.println("\n>>> CALCULANDO PRECIO TOTAL...");
        // El cliente solo llama a getPrice() en el nodo raíz. 
        // La recursión hace el resto del trabajo.
        double total = cajaEnvio.getPrice();
        
        System.out.println("TOTAL A PAGAR (Incluye $2 por caja): $" + total);
    }
}
