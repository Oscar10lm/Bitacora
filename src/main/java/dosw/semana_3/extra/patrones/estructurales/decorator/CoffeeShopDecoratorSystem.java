package dosw.semana_3.extra.patrones.estructurales.decorator;

public class CoffeeShopDecoratorSystem {

    // ==========================================
    // 1. COMPONENTE (La Interfaz Común)
    // ==========================================
    public interface Bebida {
        String getDescripcion();
        double getCosto();
    }

    // ==========================================
    // 2. COMPONENTES CONCRETOS (Bebidas Base)
    // ==========================================
    public static class Cafe implements Bebida {
        @Override
        public String getDescripcion() {
            return "Café Tostado Tradicional";
        }

        @Override
        public double getCosto() {
            return 3000.0; // Precio base
        }
    }

    public static class Te implements Bebida {
        @Override
        public String getDescripcion() {
            return "Té Verde Orgánico";
        }

        @Override
        public double getCosto() {
            return 2500.0; // Precio base
        }
    }

    // ==========================================
    // 3. DECORADOR BASE (El Envoltorio)
    // ==========================================
    public static abstract class BebidaDecorador implements Bebida {
        
        protected Bebida bebidaEnvoltorio;

        public BebidaDecorador(Bebida bebida) {
            this.bebidaEnvoltorio = bebida;
        }

        @Override
        public String getDescripcion() {
            return bebidaEnvoltorio.getDescripcion();
        }

        @Override
        public double getCosto() {
            return bebidaEnvoltorio.getCosto();
        }
    }

    // ==========================================
    // 4. DECORADORES CONCRETOS (Los Extras)
    // ==========================================
    
    public static class Leche extends BebidaDecorador {
        public Leche(Bebida bebida) {
            super(bebida);
        }

        @Override
        public String getDescripcion() {
            return super.getDescripcion() + ", Leche Deslactosada";
        }

        @Override
        public double getCosto() {
            return super.getCosto() + 1500.0;
        }
    }

    public static class EspressoExtra extends BebidaDecorador {
        public EspressoExtra(Bebida bebida) {
            super(bebida);
        }

        @Override
        public String getDescripcion() {
            return super.getDescripcion() + ", Extra Shot de Espresso";
        }

        @Override
        public double getCosto() {
            return super.getCosto() + 2500.0;
        }
    }

    public static class SiropeVainilla extends BebidaDecorador {
        public SiropeVainilla(Bebida bebida) {
            super(bebida);
        }

        @Override
        public String getDescripcion() {
            return super.getDescripcion() + ", Sirope de Vainilla";
        }

        @Override
        public double getCosto() {
            return super.getCosto() + 1000.0;
        }
    }

    public static class CremaBatida extends BebidaDecorador {
        public CremaBatida(Bebida bebida) {
            super(bebida);
        }

        @Override
        public String getDescripcion() {
            return super.getDescripcion() + ", Cubierta de Crema Batida";
        }

        @Override
        public double getCosto() {
            return super.getCosto() + 1800.0;
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    
    // Método auxiliar para imprimir el ticket
    public static void imprimirTicket(String cliente, Bebida bebida) {
        System.out.println("----------------------------------------");
        System.out.println("Cliente : " + cliente);
        System.out.println("Detalle : " + bebida.getDescripcion());
        System.out.println("Total   : $" + bebida.getCosto() + " COP");
        System.out.println("----------------------------------------\n");
    }

    public static void main(String[] args) {
        
        System.out.println(">>> BIENVENIDO A LA CAFETERÍA (Sistema Decorator) <<<\n");

        // 1. Pedido sencillo: Té sin nada
        Bebida pedido1 = new Te();
        imprimirTicket("Juan", pedido1);

        // 2. Pedido compuesto: Café con leche
        Bebida pedido2 = new Cafe();
        pedido2 = new Leche(pedido2);
        imprimirTicket("María", pedido2);

        // 3. Pedido especial (Múltiples decoradores): 
        // Café + 2 Extra Shots de Espresso + Vainilla + Crema Batida
        Bebida pedido3 = new Cafe();
        pedido3 = new EspressoExtra(pedido3);
        pedido3 = new EspressoExtra(pedido3); // ¡Se puede aplicar el mismo decorador 2 veces!
        pedido3 = new SiropeVainilla(pedido3);
        pedido3 = new CremaBatida(pedido3);
        
        imprimirTicket("Camilo (Programador Trasnochado)", pedido3);
        
        /*
         * EL PODER DEL DECORATOR:
         * Imagina crear estáticamente la clase 'CafeConDosEspressosVainillaYCremaBatida'.
         * Sería una pesadilla de mantener. El decorador permite componer todo esto
         * "al vuelo" justo en la caja registradora, alterando el precio y la 
         * descripción dinámicamente como capas de cebolla.
         */
    }
}
