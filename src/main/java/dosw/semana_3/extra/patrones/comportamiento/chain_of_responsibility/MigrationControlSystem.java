package dosw.semana_3.extra.patrones.comportamiento.chain_of_responsibility;

public class MigrationControlSystem {

    // ==========================================
    // 1. LA SOLICITUD (El Objeto Request)
    // ==========================================
    public static class IngresoRequest {
        private String nombrePasajero;
        private boolean pasaporteValido;
        private boolean antecedentesLimpios;
        private boolean motivoValido;
        private boolean aprobado;

        public IngresoRequest(String nombrePasajero, boolean pasaporteValido, boolean antecedentesLimpios, boolean motivoValido) {
            this.nombrePasajero = nombrePasajero;
            this.pasaporteValido = pasaporteValido;
            this.antecedentesLimpios = antecedentesLimpios;
            this.motivoValido = motivoValido;
            this.aprobado = false; // Por defecto rechazado hasta llegar al final
        }

        // Getters y Setters
        public String getNombrePasajero() { return nombrePasajero; }
        public boolean hasPasaporteValido() { return pasaporteValido; }
        public boolean hasAntecedentesLimpios() { return antecedentesLimpios; }
        public boolean hasMotivoValido() { return motivoValido; }
        public boolean isAprobado() { return aprobado; }
        public void setAprobado(boolean aprobado) { this.aprobado = aprobado; }
    }

    // ==========================================
    // 2. INTERFAZ DEL MANEJADOR (Handler)
    // ==========================================
    public interface ControlMigratorio {
        void setSiguiente(ControlMigratorio siguiente);
        void procesar(IngresoRequest request);
    }

    // ==========================================
    // 3. MANEJADOR BASE (Base Handler)
    // ==========================================
    // Implementa el encadenamiento por defecto para no duplicar código
    public static abstract class ControlMigratorioHandler implements ControlMigratorio {
        private ControlMigratorio siguiente;

        @Override
        public void setSiguiente(ControlMigratorio siguiente) {
            this.siguiente = siguiente;
        }

        @Override
        public void procesar(IngresoRequest request) {
            // Si hay un eslabón siguiente en la cadena, le pasamos la solicitud
            if (siguiente != null) {
                siguiente.procesar(request);
            }
        }
    }

    // ==========================================
    // 4. MANEJADORES CONCRETOS (Los Eslabones)
    // ==========================================
    
    // Eslabón 1: Pasaporte
    public static class PasaporteControl extends ControlMigratorioHandler {
        @Override
        public void procesar(IngresoRequest request) {
            System.out.println("Control 1: Revisando Pasaporte y Visa de " + request.getNombrePasajero() + "...");
            if (request.hasPasaporteValido()) {
                System.out.println(" -> Pasaporte VÁLIDO. Pasando al siguiente control.\n");
                super.procesar(request); // Pasa la pelota al siguiente
            } else {
                System.out.println(" -> RECHAZADO: Pasaporte o Visa inválidos. Proceso detenido.\n");
                // Al no llamar a super.procesar(), la cadena se corta aquí.
            }
        }
    }

    // Eslabón 2: Antecedentes
    public static class AntecedentesControl extends ControlMigratorioHandler {
        @Override
        public void procesar(IngresoRequest request) {
            System.out.println("Control 2: Revisando Antecedentes Penales de " + request.getNombrePasajero() + "...");
            if (request.hasAntecedentesLimpios()) {
                System.out.println(" -> Antecedentes LIMPIOS. Pasando al siguiente control.\n");
                super.procesar(request);
            } else {
                System.out.println(" -> RECHAZADO: Alerta de Interpol o antecedentes penales. Proceso detenido.\n");
            }
        }
    }

    // Eslabón 3: Motivo de Viaje
    public static class MotivoViajeControl extends ControlMigratorioHandler {
        @Override
        public void procesar(IngresoRequest request) {
            System.out.println("Control 3: Entrevista de Motivo de Viaje de " + request.getNombrePasajero() + "...");
            if (request.hasMotivoValido()) {
                System.out.println(" -> Motivo VÁLIDO (Turismo/Negocios). Pasando al siguiente control.\n");
                super.procesar(request);
            } else {
                System.out.println(" -> RECHAZADO: Sospecha de migración ilegal o mentiras en entrevista. Proceso detenido.\n");
            }
        }
    }

    // Eslabón 4: Aprobación Final (Fin de la cadena)
    public static class AprobacionFinalControl extends ControlMigratorioHandler {
        @Override
        public void procesar(IngresoRequest request) {
            System.out.println("Control 4: Aprobación Final por Oficial Jefe...");
            // Si la solicitud llegó hasta aquí, significa que sobrevivió a todos los filtros anteriores
            request.setAprobado(true);
            System.out.println(" -> ¡BIENVENIDO A ESTADOS UNIDOS! Ingreso Aprobado.\n");
            
            // Ya no llama a super.procesar() porque es el último eslabón
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // 1. CONFIGURAR LA CADENA DE MANDO (Pipeline)
        // Se instancian los manejadores
        ControlMigratorio paso1 = new PasaporteControl();
        ControlMigratorio paso2 = new AntecedentesControl();
        ControlMigratorio paso3 = new MotivoViajeControl();
        ControlMigratorio paso4 = new AprobacionFinalControl();
        
        // Se enlazan (Paso 1 -> Paso 2 -> Paso 3 -> Paso 4)
        paso1.setSiguiente(paso2);
        paso2.setSiguiente(paso3);
        paso3.setSiguiente(paso4);

        System.out.println("=============================================");
        
        // 2. CASO A: Pasajero ideal
        IngresoRequest pasajeroA = new IngresoRequest("Juan Pérez", true, true, true);
        System.out.println("--- Evaluando Pasajero A ---");
        // El cliente SIEMPRE entrega la petición solo al primer eslabón
        paso1.procesar(pasajeroA);
        
        System.out.println("=============================================");

        // 3. CASO B: Pasajero sin visa (Se detiene rápido)
        IngresoRequest pasajeroB = new IngresoRequest("Carlos López", false, true, true);
        System.out.println("--- Evaluando Pasajero B ---");
        paso1.procesar(pasajeroB);
        
        System.out.println("=============================================");

        // 4. CASO C: Pasajero con visa pero con antecedentes (Falla a mitad de cadena)
        IngresoRequest pasajeroC = new IngresoRequest("Pablo Escobar", true, false, true);
        System.out.println("--- Evaluando Pasajero C ---");
        paso1.procesar(pasajeroC);
    }
}
