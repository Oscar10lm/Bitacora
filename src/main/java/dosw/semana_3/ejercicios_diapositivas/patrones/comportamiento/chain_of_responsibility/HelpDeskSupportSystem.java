package dosw.semana_3.ejercicios_diapositivas.patrones.comportamiento.chain_of_responsibility;

public class HelpDeskSupportSystem {

    // ==========================================
    // 1. LA SOLICITUD (El Objeto Ticket)
    // ==========================================
    public enum Complexity {
        BASIC, INTERMEDIATE, ADVANCED, PRODUCT_BUG
    }

    public static class SupportTicket {
        private int ticketId;
        private String description;
        private Complexity complexity;

        public SupportTicket(int ticketId, String description, Complexity complexity) {
            this.ticketId = ticketId;
            this.description = description;
            this.complexity = complexity;
        }

        public int getTicketId() { return ticketId; }
        public String getDescription() { return description; }
        public Complexity getComplexity() { return complexity; }
    }

    // ==========================================
    // 2. INTERFAZ DEL MANEJADOR (Handler)
    // ==========================================
    public interface SupportHandler {
        void setNext(SupportHandler nextHandler);
        void handleTicket(SupportTicket ticket);
    }

    // ==========================================
    // 3. MANEJADOR BASE (Base Handler)
    // ==========================================
    public static abstract class BaseSupportHandler implements SupportHandler {
        private SupportHandler nextHandler;

        @Override
        public void setNext(SupportHandler nextHandler) {
            this.nextHandler = nextHandler;
        }

        @Override
        public void handleTicket(SupportTicket ticket) {
            // Si el manejador actual no lo pudo resolver, se escala al siguiente (si existe)
            if (nextHandler != null) {
                nextHandler.handleTicket(ticket);
            } else {
                System.out.println(" -> ALERTA: Nadie en la cadena pudo resolver el Ticket #" + ticket.getTicketId());
            }
        }
    }

    // ==========================================
    // 4. MANEJADORES CONCRETOS (Los Niveles de Soporte)
    // ==========================================
    
    // Nivel 1: Problemas Básicos
    public static class Level1Support extends BaseSupportHandler {
        @Override
        public void handleTicket(SupportTicket ticket) {
            System.out.println("Nivel 1: Analizando Ticket #" + ticket.getTicketId() + "...");
            
            if (ticket.getComplexity() == Complexity.BASIC) {
                System.out.println(" -> [RESUELTO POR NIVEL 1] Acción: Reseteo de contraseña / Reinicio de equipo.\n");
                // Ya no llama a super.handleTicket(), deteniendo el escalamiento.
            } else {
                System.out.println(" -> Nivel 1 no tiene los conocimientos. Escalando a Nivel 2...");
                super.handleTicket(ticket); // Pasa la responsabilidad
            }
        }
    }

    // Nivel 2: Configuración
    public static class Level2Support extends BaseSupportHandler {
        @Override
        public void handleTicket(SupportTicket ticket) {
            System.out.println("Nivel 2: Analizando Ticket #" + ticket.getTicketId() + "...");
            
            if (ticket.getComplexity() == Complexity.INTERMEDIATE) {
                System.out.println(" -> [RESUELTO POR NIVEL 2] Acción: Ajuste de configuraciones de red y software.\n");
            } else {
                System.out.println(" -> Nivel 2 no tiene los conocimientos. Escalando a Nivel 3...");
                super.handleTicket(ticket);
            }
        }
    }

    // Nivel 3: Técnicos Avanzados
    public static class Level3Support extends BaseSupportHandler {
        @Override
        public void handleTicket(SupportTicket ticket) {
            System.out.println("Nivel 3: Analizando Ticket #" + ticket.getTicketId() + "...");
            
            if (ticket.getComplexity() == Complexity.ADVANCED) {
                System.out.println(" -> [RESUELTO POR NIVEL 3] Acción: Reconstrucción de base de datos y parche en caliente.\n");
            } else {
                System.out.println(" -> Nivel 3 detectó una anomalía estructural. Escalando a Ingeniería...");
                super.handleTicket(ticket);
            }
        }
    }

    // Ingeniería: Bugs del producto (Fin de la cadena)
    public static class EngineeringTeam extends BaseSupportHandler {
        @Override
        public void handleTicket(SupportTicket ticket) {
            System.out.println("Ingeniería (Desarrollo): Analizando Ticket #" + ticket.getTicketId() + "...");
            
            if (ticket.getComplexity() == Complexity.PRODUCT_BUG) {
                System.out.println(" -> [RESUELTO POR INGENIERÍA] Acción: Bug reproducido, corregido en código fuente y desplegado en nueva versión.\n");
            } else {
                // Si ni siquiera ingeniería puede, se avisa al cliente
                super.handleTicket(ticket);
            }
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // 1. LA EMPRESA ARMA LA CADENA DE SOPORTE TÉCNICO
        SupportHandler l1 = new Level1Support();
        SupportHandler l2 = new Level2Support();
        SupportHandler l3 = new Level3Support();
        SupportHandler eng = new EngineeringTeam();
        
        // Encadenamiento: L1 -> L2 -> L3 -> Ingeniería
        l1.setNext(l2);
        l2.setNext(l3);
        l3.setNext(eng);

        System.out.println("=============================================");
        
        // 2. CASO A: Un problema simple de contraseña
        SupportTicket t1 = new SupportTicket(1001, "No puedo entrar a mi cuenta", Complexity.BASIC);
        System.out.println("--- Cliente reporta Ticket #1001 (Olvidó Contraseña) ---");
        // El sistema de mesa de ayuda siempre envía todos los tickets nuevos al Nivel 1
        l1.handleTicket(t1); 
        
        System.out.println("=============================================");

        // 3. CASO B: Un problema de red que L1 no puede resolver
        SupportTicket t2 = new SupportTicket(1002, "El programa no conecta a la BD", Complexity.INTERMEDIATE);
        System.out.println("--- Cliente reporta Ticket #1002 (Error de Conexión) ---");
        l1.handleTicket(t2);
        
        System.out.println("=============================================");

        // 4. CASO C: Un bug fatal de la aplicación
        SupportTicket t3 = new SupportTicket(1003, "Pantalla azul al hacer clic en 'Guardar'", Complexity.PRODUCT_BUG);
        System.out.println("--- Cliente reporta Ticket #1003 (Pantalla Azul) ---");
        l1.handleTicket(t3);
    }
}
