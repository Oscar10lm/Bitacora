package dosw.semana_4.taller.ejercicio5;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Integración con Sistema Bancario Antiguo ---");
        
        // El desarrollador simplemente usa la Facade, sin lidiar con los 8 pasos
        // ni con la conversión de centavos.
        BankFacade facade = new BankFacade();
        
        System.out.println("\nDesarrollador: \"Quiero cobrar $150.75\"");
        facade.procesarPago(150.75);
    }
}
