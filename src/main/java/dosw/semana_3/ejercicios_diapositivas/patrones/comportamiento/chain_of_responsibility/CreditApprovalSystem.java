package dosw.semana_3.ejercicios_diapositivas.patrones.comportamiento.chain_of_responsibility;

public class CreditApprovalSystem {

    // ==========================================
    // 1. LA SOLICITUD (El Objeto Request)
    // ==========================================
    public static class CreditRequest {
        private String applicantName;
        private boolean identityValid;
        private int creditScore;
        private double monthlyIncome;
        private boolean approved;

        public CreditRequest(String applicantName, boolean identityValid, int creditScore, double monthlyIncome) {
            this.applicantName = applicantName;
            this.identityValid = identityValid;
            this.creditScore = creditScore;
            this.monthlyIncome = monthlyIncome;
            this.approved = false; 
        }

        public String getApplicantName() { return applicantName; }
        public boolean isIdentityValid() { return identityValid; }
        public int getCreditScore() { return creditScore; }
        public double getMonthlyIncome() { return monthlyIncome; }
        public boolean isApproved() { return approved; }
        public void setApproved(boolean approved) { this.aprobado = approved; }
        private boolean aprobado;
        public void setAprobado(boolean aprobado) { this.aprobado = aprobado; this.approved = aprobado; }
    }

    // ==========================================
    // 2. INTERFAZ DEL MANEJADOR (Handler)
    // ==========================================
    public interface CreditValidator {
        void setNext(CreditValidator nextValidator);
        void process(CreditRequest request);
    }

    // ==========================================
    // 3. MANEJADOR BASE (Base Handler)
    // ==========================================
    public static abstract class BaseCreditValidator implements CreditValidator {
        private CreditValidator nextValidator;

        @Override
        public void setNext(CreditValidator nextValidator) {
            this.nextValidator = nextValidator;
        }

        @Override
        public void process(CreditRequest request) {
            if (nextValidator != null) {
                nextValidator.process(request);
            }
        }
    }

    // ==========================================
    // 4. MANEJADORES CONCRETOS (Los Filtros del Banco)
    // ==========================================
    
    // Eslabón 1: Identidad
    public static class IdentityValidator extends BaseCreditValidator {
        @Override
        public void process(CreditRequest request) {
            System.out.println("Validación 1: Verificando identidad de " + request.getApplicantName() + "...");
            if (request.isIdentityValid()) {
                System.out.println(" -> Identidad confirmada (Cédula válida).");
                super.process(request); // Pasa al siguiente filtro
            } else {
                System.out.println(" -> RECHAZADO: Inconsistencias en el documento de identidad. (Cadena detenida)\n");
            }
        }
    }

    // Eslabón 2: Historial Crediticio (DataCrédito/Equifax)
    public static class CreditHistoryValidator extends BaseCreditValidator {
        @Override
        public void process(CreditRequest request) {
            System.out.println("Validación 2: Consultando buró de crédito de " + request.getApplicantName() + "...");
            // Regla del banco: Score debe ser mayor a 650
            if (request.getCreditScore() >= 650) {
                System.out.println(" -> Score aceptable (" + request.getCreditScore() + " puntos).");
                super.process(request);
            } else {
                System.out.println(" -> RECHAZADO: Score crediticio muy bajo (" + request.getCreditScore() + " puntos). Riesgo alto. (Cadena detenida)\n");
            }
        }
    }

    // Eslabón 3: Capacidad de Pago
    public static class IncomeValidator extends BaseCreditValidator {
        @Override
        public void process(CreditRequest request) {
            System.out.println("Validación 3: Evaluando capacidad de endeudamiento de " + request.getApplicantName() + "...");
            // Regla del banco: Ingreso debe ser mayor a $1,500,000
            if (request.getMonthlyIncome() >= 1500000.0) {
                System.out.println(" -> Capacidad de pago suficiente ($" + request.getMonthlyIncome() + ").");
                super.process(request);
            } else {
                System.out.println(" -> RECHAZADO: Ingresos insuficientes para soportar la cuota del préstamo. (Cadena detenida)\n");
            }
        }
    }

    // Eslabón 4: Oficial de Crédito (Aprobación Final)
    public static class FinalApprovalValidator extends BaseCreditValidator {
        @Override
        public void process(CreditRequest request) {
            System.out.println("Validación 4: Revisión final por parte del Oficial de Crédito...");
            request.setAprobado(true);
            System.out.println(" -> ¡FELICIDADES! Su solicitud de crédito ha sido APROBADA y el dinero será desembolsado pronto.\n");
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // 1. EL BANCO ARMA LA CADENA DE ESTUDIO DE CRÉDITO
        CreditValidator v1 = new IdentityValidator();
        CreditValidator v2 = new CreditHistoryValidator();
        CreditValidator v3 = new IncomeValidator();
        CreditValidator v4 = new FinalApprovalValidator();
        
        // Se enlazan en orden estricto
        v1.setNext(v2);
        v2.setNext(v3);
        v3.setNext(v4);

        System.out.println("=============================================");
        
        // 2. CLIENTE 1: Todo perfecto
        CreditRequest req1 = new CreditRequest("María Fernanda", true, 720, 3000000.0);
        System.out.println("--- Evaluando Solicitud de Crédito: María Fernanda ---");
        v1.process(req1); // Se envía SOLO al primer eslabón
        
        System.out.println("=============================================");

        // 3. CLIENTE 2: Fraude o Documento Falso (Falla en el primer paso)
        CreditRequest req2 = new CreditRequest("Sujeto Desconocido", false, 800, 5000000.0);
        System.out.println("--- Evaluando Solicitud de Crédito: Sujeto Desconocido ---");
        v1.process(req2);
        
        System.out.println("=============================================");

        // 4. CLIENTE 3: Identidad y Score bien, pero sin capacidad de pago (Falla en el paso 3)
        CreditRequest req3 = new CreditRequest("Estudiante Universitario", true, 680, 500000.0);
        System.out.println("--- Evaluando Solicitud de Crédito: Estudiante Universitario ---");
        v1.process(req3);
    }
}
