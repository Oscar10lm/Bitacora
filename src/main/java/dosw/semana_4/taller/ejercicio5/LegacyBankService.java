package dosw.semana_4.taller.ejercicio5;

public class LegacyBankService {
    public void executeTransaction(String accountType, int amountInCents) {
        System.out.println("  [LegacyBank] Procesando transacción en cuenta " + accountType + " por " + amountInCents + " centavos.");
    }
    
    public void initConnection() { System.out.println("  [LegacyBank] 1. Inicializando conexión..."); }
    public void authenticate() { System.out.println("  [LegacyBank] 2. Autenticando credenciales..."); }
    public void openSession() { System.out.println("  [LegacyBank] 3. Abriendo sesión segura..."); }
    public void allocateBuffer() { System.out.println("  [LegacyBank] 4. Reservando memoria buffer..."); }
    public void checkNetwork() { System.out.println("  [LegacyBank] 5. Verificando latencia de red..."); }
    public void lockDatabase() { System.out.println("  [LegacyBank] 6. Bloqueando base de datos transaccional..."); }
    public void startAudit() { System.out.println("  [LegacyBank] 7. Iniciando log de auditoría..."); }
    public void ready() { System.out.println("  [LegacyBank] 8. Sistema listo para transacciones."); }
}
