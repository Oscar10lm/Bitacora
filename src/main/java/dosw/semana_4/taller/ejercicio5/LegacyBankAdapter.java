package dosw.semana_4.taller.ejercicio5;

public class LegacyBankAdapter implements PaymentProcessor {
    private final LegacyBankService legacyService;

    public LegacyBankAdapter(LegacyBankService legacyService) {
        this.legacyService = legacyService;
    }

    @Override
    public void pay(double amount) {
        System.out.println("  [Adapter] Convirtiendo el monto a centavos...");
        int cents = (int) (amount * 100);
        
        legacyService.executeTransaction("ACC_DEFAULT", cents);
    }
}
