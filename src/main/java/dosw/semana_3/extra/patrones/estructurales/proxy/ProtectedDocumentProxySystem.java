package dosw.semana_3.extra.patrones.estructurales.proxy;

public class ProtectedDocumentProxySystem {

    // ==========================================
    // 1. LA INTERFAZ SUJETO
    // ==========================================
    public interface Document {
        void open();
    }

    // ==========================================
    // 2. EL SUJETO REAL (El documento que hay que proteger)
    // ==========================================
    public static class RealDocument implements Document {
        private String documentName;

        public RealDocument(String documentName) {
            this.documentName = documentName;
        }

        @Override
        public void open() {
            // Este objeto es "tonto", asume que quien lo llama tiene permiso
            System.out.println("  [Documento Real]: Abriendo contenido secreto de -> " + documentName);
            System.out.println("  [Contenido]: 'Las contraseñas del servidor AWS son: admin/1234'");
        }
    }

    // ==========================================
    // 3. EL PROXY (El Guardián / Protection Proxy)
    // ==========================================
    public static class ProtectedDocumentProxy implements Document {
        private RealDocument realDocument;
        private String documentName;
        private String userRole;

        public ProtectedDocumentProxy(String documentName, String userRole) {
            this.documentName = documentName;
            this.userRole = userRole;
        }

        @Override
        public void open() {
            System.out.println("  [Proxy de Seguridad]: Verificando credenciales del rol '" + userRole + "'...");
            
            // La capa de seguridad extra antes de delegar
            if ("ADMIN".equalsIgnoreCase(userRole) || "GERENTE".equalsIgnoreCase(userRole)) {
                System.out.println("  [Proxy de Seguridad]: ¡Acceso Concedido! Delegando al documento real.");
                
                if (realDocument == null) {
                    realDocument = new RealDocument(documentName);
                }
                realDocument.open();
                
            } else {
                System.out.println("  [Proxy de Seguridad]: ¡ACCESO DENEGADO! Usted no tiene nivel de seguridad suficiente.");
            }
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        System.out.println(">>> SISTEMA DE INTRANET CORPORATIVO (Protection Proxy) <<<\n");

        System.out.println("--- Sesión iniciada como: PASANTE ---");
        // El cliente (Pasante) interactúa con la interfaz Document normal
        Document docParaPasante = new ProtectedDocumentProxy("claves_servidor.txt", "PASANTE");
        docParaPasante.open();

        System.out.println("\n--- Sesión iniciada como: ADMIN ---");
        // El cliente (Admin) interactúa con la misma interfaz
        Document docParaAdmin = new ProtectedDocumentProxy("claves_servidor.txt", "ADMIN");
        docParaAdmin.open();
    }
}
