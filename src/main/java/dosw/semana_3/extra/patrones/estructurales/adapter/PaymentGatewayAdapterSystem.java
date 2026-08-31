package dosw.semana_3.extra.patrones.estructurales.adapter;

public class PaymentGatewayAdapterSystem {

    // ==========================================
    // 1. INTERFAZ DEL CLIENTE (Lo que el Checkout entiende)
    // ==========================================
    public interface PaymentProcessor {
        void procesarPago(double montoEnPesos);
    }

    // ==========================================
    // 2. SERVICIOS INCOMPATIBLES (Adaptees - APIs Externas)
    // ==========================================
    
    // API de Estados Unidos
    public static class PayGlobalAPI {
        public void charge(int amountInCents) {
            System.out.println("PayGlobal [API Externa]: Cobro exitoso por " + amountInCents + " centavos de dólar (USD).");
        }
    }

    // API Europea
    public static class EuroPay {
        public void debitar(double montoEnEuros) {
            System.out.printf("EuroPay [API Externa]: Débito aprobado por %.2f EUR.\n", montoEnEuros);
        }
    }

    // ==========================================
    // 3. ADAPTADORES (Los Traductores)
    // ==========================================
    
    // Adaptador para PayGlobal
    public static class PayGlobalAdapter implements PaymentProcessor {
        private PayGlobalAPI externalApi;

        public PayGlobalAdapter(PayGlobalAPI externalApi) {
            this.externalApi = externalApi;
        }

        @Override
        public void procesarPago(double montoEnPesos) {
            System.out.println("Adaptador [PayGlobal]: Convirtiendo COP " + montoEnPesos + " a centavos de USD...");
            
            // Conversión matemática (1 USD = 4000 COP)
            double dolares = montoEnPesos / 4000.0;
            // La API pide centavos enteros (multiplicamos por 100)
            int centavos = (int) Math.round(dolares * 100);
            
            // Llamamos a la interfaz ajena
            externalApi.charge(centavos);
        }
    }

    // Adaptador para EuroPay
    public static class EuroPayAdapter implements PaymentProcessor {
        private EuroPay externalApi;

        public EuroPayAdapter(EuroPay externalApi) {
            this.externalApi = externalApi;
        }

        @Override
        public void procesarPago(double montoEnPesos) {
            System.out.println("Adaptador [EuroPay]: Convirtiendo COP " + montoEnPesos + " a EUR...");
            
            // Conversión matemática (1 EUR = 4300 COP)
            double euros = montoEnPesos / 4300.0;
            
            // Llamamos a la interfaz ajena
            externalApi.debitar(euros);
        }
    }

    // ==========================================
    // 4. CLIENTE CENTRAL (Checkout)
    // ==========================================
    public static class CheckoutSystem {
        // El Checkout solo sabe de pesos y de su propia interfaz
        public void pagarCarrito(PaymentProcessor pasarela, double totalPesos) {
            System.out.println("\n--- Iniciando proceso de Checkout por COP $" + totalPesos + " ---");
            pasarela.procesarPago(totalPesos);
            System.out.println("--- Compra Finalizada ---");
        }
    }

    // ==========================================
    // 5. DEMOSTRACIÓN (MainClass)
    // ==========================================
    public static void main(String[] args) {
        CheckoutSystem checkout = new CheckoutSystem();
        double totalCompra = 125000.0; // 125,000 Pesos Colombianos

        // El usuario elige pagar usando la plataforma americana
        System.out.println(">> Usuario selecciona pagar con PayGlobal");
        PayGlobalAPI apiGringa = new PayGlobalAPI();
        PaymentProcessor adapterUSA = new PayGlobalAdapter(apiGringa);
        checkout.pagarCarrito(adapterUSA, totalCompra);

        // Otro usuario elige pagar con la plataforma europea
        System.out.println("\n>> Usuario selecciona pagar con EuroPay");
        EuroPay apiEuropea = new EuroPay();
        PaymentProcessor adapterEU = new EuroPayAdapter(apiEuropea);
        checkout.pagarCarrito(adapterEU, totalCompra);
    }
}
