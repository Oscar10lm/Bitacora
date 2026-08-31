package dosw.semana_3.taller.patrones;

public class Ejercicio15 {

    // --- IMPLEMENTACIÓN DEL PATRÓN ADAPTER ---

    // 1. La interfaz moderna que nuestro sistema SÍ entiende
    public interface PaymentProcessor {
        void modernPay(String account, double amount);
    }

    // 2. El sistema legado incompatible (NO SE PUEDE TOCAR)
    public static class LegacyBankService {
        public boolean verifyBalance(String account) {
            System.out.println("[Legacy] Verificando saldo cuenta: " + account);
            return true;
        }

        public void executeTransaction(String account, int cents) {
            System.out.println("[Legacy] Transacción ejecutada: " + cents + " centavos en cuenta " + account);
        }
    }

    // 3. El Adaptador (Traduce de lo moderno a lo antiguo)
    public static class LegacyBankAdapter implements PaymentProcessor {
        
        // El adaptador envuelve al sistema antiguo
        private final LegacyBankService legacyService;

        public LegacyBankAdapter(LegacyBankService legacyService) {
            this.legacyService = legacyService;
        }

        @Override
        public void modernPay(String account, double amount) {
            System.out.println("Adaptador intercepta el pago moderno de $" + amount + "...");
            
            // 1. Traduce los datos (Double a Entero en centavos)
            int cents = (int) Math.round(amount * 100);
            
            // 2. Llama a los métodos antiguos en el orden requerido
            if (legacyService.verifyBalance(account)) {
                legacyService.executeTransaction(account, cents);
            }
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        
        // Instancia del sistema viejo que no podemos modificar
        LegacyBankService oldBank = new LegacyBankService();
        
        // Nuestro sistema moderno espera un PaymentProcessor.
        // Le pasamos el Adaptador, pasándole el sistema viejo por debajo.
        PaymentProcessor processor = new LegacyBankAdapter(oldBank);
        
        // El sistema moderno hace la llamada como le gusta (con decimales)
        // ¡Magia! El adaptador se encarga de traducirlo a centavos y llamar a verifyBalance.
        processor.modernPay("ACC-12345", 45.50);
    }
}
