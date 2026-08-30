package dosw.semana_3.extra.patrones.comportamiento.mediator;

/**
 * ============================================================================
 * EJERCICIO PRÁCTICO M2: FORMULARIO CON VALIDACIÓN CRUZADA (Mediator)
 * ============================================================================
 *
 * Un formulario tiene varios campos (Checkbox "Suscribirse", TextBox "Email", 
 * Button "Enviar") donde la habilitación de unos depende del estado de otros.
 * 
 * En vez de que cada campo conozca directamente a los demás (Código espagueti 
 * de UI), un mediador central coordina esas interacciones.
 */
public class FormValidationMediatorSystem {

    // ==========================================
    // 1. LA INTERFAZ MEDIADOR
    // ==========================================
    public interface FormMediator {
        // Método genérico para que cualquier componente avise que cambió su estado
        void notifyComponentChanged(UIComponent sender, String event);
    }

    // ==========================================
    // 2. MEDIADOR CONCRETO (El Gestor del Formulario)
    // ==========================================
    public static class RegistrationFormMediator implements FormMediator {
        
        // El mediador sí conoce a todos los componentes concretos
        private Checkbox subscribeCheckbox;
        private TextBox emailTextBox;
        private Button submitButton;

        public void setSubscribeCheckbox(Checkbox subscribeCheckbox) {
            this.subscribeCheckbox = subscribeCheckbox;
        }

        public void setEmailTextBox(TextBox emailTextBox) {
            this.emailTextBox = emailTextBox;
        }

        public void setSubmitButton(Button submitButton) {
            this.submitButton = submitButton;
        }

        @Override
        public void notifyComponentChanged(UIComponent sender, String event) {
            // Regla de Negocio 1: Si marcan el checkbox, habilito el campo de texto
            if (sender == subscribeCheckbox && event.equals("check")) {
                if (subscribeCheckbox.isChecked()) {
                    System.out.println("[Mediador]: Checkbox marcado -> Habilitando caja de Email.");
                    emailTextBox.setEnabled(true);
                } else {
                    System.out.println("[Mediador]: Checkbox desmarcado -> Deshabilitando caja de Email y limpiándola.");
                    emailTextBox.setEnabled(false);
                    emailTextBox.setText(""); 
                    submitButton.setEnabled(false); // Por si acaso también apago el botón
                }
            }

            // Regla de Negocio 2: Si escriben en el texto, evaluo prender el botón
            if (sender == emailTextBox && event.equals("text_changed")) {
                if (emailTextBox.getText().contains("@")) {
                    System.out.println("[Mediador]: Email válido detectado -> Habilitando botón Enviar.");
                    submitButton.setEnabled(true);
                } else {
                    System.out.println("[Mediador]: Email inválido -> Deshabilitando botón Enviar.");
                    submitButton.setEnabled(false);
                }
            }
        }
    }

    // ==========================================
    // 3. LA CLASE COLEGA (Componente de UI Base)
    // ==========================================
    public static abstract class UIComponent {
        protected FormMediator mediator;
        protected boolean enabled = true;

        public UIComponent(FormMediator mediator) {
            this.mediator = mediator;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }

    // ==========================================
    // 4. COLEGAS CONCRETOS (Los Widgets)
    // ==========================================
    
    public static class Checkbox extends UIComponent {
        private boolean checked = false;

        public Checkbox(FormMediator mediator) {
            super(mediator);
        }

        public void toggle() {
            checked = !checked;
            System.out.println("\n[Usuario]: Hace clic en el Checkbox de Suscripción (Estado: " + checked + ")");
            // No le habla a la caja de texto. Le avisa al mediador.
            mediator.notifyComponentChanged(this, "check");
        }

        public boolean isChecked() { return checked; }
    }

    public static class TextBox extends UIComponent {
        private String text = "";

        public TextBox(FormMediator mediator) {
            super(mediator);
            this.enabled = false; // Arranca deshabilitado por defecto
        }

        public void setText(String text) {
            if (this.enabled) {
                this.text = text;
                System.out.println("\n[Usuario]: Escribe '" + text + "' en la caja de Email");
                // No le habla al botón. Le avisa al mediador.
                mediator.notifyComponentChanged(this, "text_changed");
            } else {
                System.out.println("\n[Usuario]: Intenta escribir, pero la caja de Email está BLOQUEADA.");
            }
        }

        public String getText() { return text; }
    }

    public static class Button extends UIComponent {
        
        public Button(FormMediator mediator) {
            super(mediator);
            this.enabled = false; // Arranca deshabilitado por defecto
        }

        public void click() {
            if (this.enabled) {
                System.out.println("\n[Usuario]: Hace clic en [ENVIAR]");
                System.out.println("-> FORMULARIO ENVIADO CORRECTAMENTE AL SERVIDOR <-");
            } else {
                System.out.println("\n[Usuario]: Intenta hacer clic en [ENVIAR], pero está GRIS/BLOQUEADO.");
            }
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        System.out.println(">>> CARGANDO INTERFAZ DE USUARIO CON REACT/ANGULAR <<<\n");
        
        // 1. Instanciamos el Mediador
        RegistrationFormMediator formController = new RegistrationFormMediator();
        
        // 2. Instanciamos los componentes pasándoles su mediador
        Checkbox chkSubscribe = new Checkbox(formController);
        TextBox txtEmail = new TextBox(formController);
        Button btnSubmit = new Button(formController);
        
        // 3. Registramos los componentes dentro del mediador
        formController.setSubscribeCheckbox(chkSubscribe);
        formController.setEmailTextBox(txtEmail);
        formController.setSubmitButton(btnSubmit);

        // --- SIMULACIÓN DEL COMPORTAMIENTO DEL USUARIO ---
        
        // Intenta enviar de una
        btnSubmit.click(); 
        
        // Intenta escribir sin marcar la casilla
        txtEmail.setText("juan@correo.com");
        
        // Marca la casilla (Esto habilita la caja)
        chkSubscribe.toggle();
        
        // Escribe algo incorrecto (El botón sigue bloqueado)
        txtEmail.setText("usuario_invalido");
        btnSubmit.click();
        
        // Escribe algo correcto (Se habilita el botón)
        txtEmail.setText("juan@correo.com");
        
        // Desmarca la casilla (Debería bloquear caja, limpiar texto y bloquear botón)
        chkSubscribe.toggle();
        btnSubmit.click();
        
        // Lo hace bien de corrido
        chkSubscribe.toggle();
        txtEmail.setText("juan@correo.com");
        btnSubmit.click(); // ¡Éxito!
    }
}
