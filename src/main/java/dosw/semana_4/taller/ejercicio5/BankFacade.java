package dosw.semana_4.taller.ejercicio5;

public class BankFacade {
    private final LegacyBankService legacyService;
    private final PaymentProcessor adapter;

    public BankFacade() {
        this.legacyService = new LegacyBankService();
        this.adapter = new LegacyBankAdapter(this.legacyService);
    }

    public void procesarPago(double monto) {
        System.out.println("=== Facade: Iniciando proceso de pago simplificado ===");
        
        // Ejecución de los 8 pasos complejos requeridos por el banco antiguo
        legacyService.initConnection();
        legacyService.authenticate();
        legacyService.openSession();
        legacyService.allocateBuffer();
        legacyService.checkNetwork();
        legacyService.lockDatabase();
        legacyService.startAudit();
        legacyService.ready();
        
        // Uso del adaptador para hacer el pago usando la interfaz moderna
        adapter.pay(monto);
        
        System.out.println("=== Facade: Pago procesado exitosamente ===");
    }
}
