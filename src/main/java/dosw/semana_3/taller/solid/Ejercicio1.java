package dosw.semana_3.taller.solid;

/**
 * -------------------------------------------------------------------
 * #1 El Sistema de Facturación
 * -------------------------------------------------------------------
 *
 * (2) PROBLEMA PRINCIPAL:
 * La clase original (InvoiceManager) está altamente acoplada a demasiadas tareas.
 * Un cambio en los impuestos, en la base de datos o en la librería de PDF
 * obligará a modificar esta misma clase.
 *
 * (3) PRINCIPIO SOLID VIOLADO:
 * SRP - Single Responsibility Principle (Principio de Responsabilidad Única).
 * "Una clase debe tener una, y solo una, razón para cambiar."
 *
 * (4) JUSTIFICACIÓN TÉCNICA:
 * InvoiceManager tiene múltiples razones para cambiar:
 * 1. Si cambian las leyes de impuestos (lógica de negocio).
 * 2. Si cambia el diseño visual de la factura (presentación/UI).
 * 3. Si se cambia de proveedor de email (infraestructura externa).
 * 4. Si se migra la base de datos (persistencia).
 * Esto hace que la clase sea frágil, difícil de probar (unit tests) y
 * propensa a errores por efectos secundarios al modificar código no relacionado.
 *
 * (5) SOLUCIÓN PROPUESTA (Refactorización):
 * Se divide la funcionalidad en clases especializadas, cada una con una
 * única responsabilidad, delegando el trabajo específico. De esta forma, 
 * si cambia la ley de impuestos, solo se modifica el TaxCalculator.
 */
public class Ejercicio1 {

    // --- SOLUCIÓN REFACTORIZADA ---

    /**
     * 1. Modelo de Dominio: Representa los datos.
     */
    public static class Invoice {
        private double amount;
        
        public Invoice(double amount) {
            this.amount = amount;
        }
        public double getAmount() {
            return amount;
        }
    }

    /**
     * 2. Lógica de Negocio: Única razón para cambiar -> Leyes tributarias.
     */
    public static class TaxCalculator {
        public double calculateTaxes(Invoice invoice) {
            System.out.println("Calculando impuestos...");
            return invoice.getAmount() * 0.19; // IVA ejemplo
        }
    }

    /**
     * 3. Presentación/UI: Única razón para cambiar -> Diseño del PDF.
     */
    public static class InvoicePDFGenerator {
        public void generate(Invoice invoice) {
            System.out.println("Generando formato PDF de la factura...");
        }
    }

    /**
     * 4. Persistencia: Única razón para cambiar -> Cambio de Base de Datos.
     */
    public static class InvoiceRepository {
        public void save(Invoice invoice) {
            System.out.println("Guardando datos de la factura en la BD...");
        }
    }

    /**
     * 5. Infraestructura Externa: Única razón para cambiar -> Proveedor de correos.
     */
    public static class EmailService {
        public void sendInvoice(Invoice invoice, String email) {
            System.out.println("Enviando PDF por correo a " + email + "...");
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        Invoice invoice = new Invoice(1000.0);
        
        // Cada clase se encarga de lo suyo
        TaxCalculator calculator = new TaxCalculator();
        calculator.calculateTaxes(invoice);
        
        InvoicePDFGenerator generator = new InvoicePDFGenerator();
        generator.generate(invoice);
        
        InvoiceRepository repo = new InvoiceRepository();
        repo.save(invoice);
        
        EmailService emailService = new EmailService();
        emailService.sendInvoice(invoice, "cliente@empresa.com");
    }
}
