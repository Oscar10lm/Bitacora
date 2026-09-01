package dosw.semana_3.ejercicios_diapositivas.patrones.comportamiento.mediator;

import java.util.ArrayList;
import java.util.List;

public class GroupChatMediatorSystem {

    // ==========================================
    // 1. LA INTERFAZ MEDIADOR
    // ==========================================
    public interface ChatMediator {
        void sendMessage(String msg, User user);
        void addUser(User user);
    }

    // ==========================================
    // 2. MEDIADOR CONCRETO (La Sala de Chat)
    // ==========================================
    public static class ChatRoom implements ChatMediator {
        private List<User> users;

        public ChatRoom() {
            this.users = new ArrayList<>();
        }

        @Override
        public void addUser(User user) {
            this.users.add(user);
            System.out.println("-> [Servidor]: " + user.name + " se ha unido a la sala.");
        }

        @Override
        public void sendMessage(String msg, User sender) {
            System.out.println("\n[" + sender.name + " envía mensaje]: " + msg);
            
            // El mediador distribuye el mensaje a todos los usuarios, excepto al que lo envió
            for (User u : users) {
                // No se debe recibir el propio mensaje
                if (u != sender) {
                    u.receive(msg);
                }
            }
        }
    }

    // ==========================================
    // 3. LA CLASE COLEGA (Componente Base)
    // ==========================================
    // Todos los colegas deben tener una referencia a su mediador
    public static abstract class User {
        protected ChatMediator mediator;
        protected String name;

        public User(ChatMediator mediator, String name) {
            this.mediator = mediator;
            this.name = name;
        }

        public abstract void send(String msg);
        public abstract void receive(String msg);
    }

    // ==========================================
    // 4. COLEGAS CONCRETOS (Los Usuarios)
    // ==========================================
    public static class ChatUser extends User {

        public ChatUser(ChatMediator mediator, String name) {
            super(mediator, name);
        }

        @Override
        public void send(String msg) {
            // El usuario NO busca la lista de amigos para enviarles el mensaje.
            // Simplemente le lanza el mensaje ciegamente al Mediador.
            mediator.sendMessage(msg, this);
        }

        @Override
        public void receive(String msg) {
            System.out.println(this.name + " recibió el mensaje.");
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        System.out.println(">>> SERVIDOR DE DISCORD / WHATSAPP INICIADO <<<\n");

        // 1. Instanciamos el Mediador Central
        ChatMediator generalChannel = new ChatRoom();

        // 2. Creamos los usuarios pasándoles la referencia del Mediador
        User user1 = new ChatUser(generalChannel, "Alice");
        User user2 = new ChatUser(generalChannel, "Bob");
        User user3 = new ChatUser(generalChannel, "Charlie");
        User user4 = new ChatUser(generalChannel, "David");

        // 3. Los añadimos a la sala (Esto en la vida real lo haría el servidor interno)
        generalChannel.addUser(user1);
        generalChannel.addUser(user2);
        generalChannel.addUser(user3);
        generalChannel.addUser(user4);

        // 4. Alice envía un mensaje al Mediador (Alice NO conoce ni interactúa con Bob, Charlie o David)
        user1.send("¡Hola a todos! ¿Están listos para la reunión?");
        
        // 5. Charlie responde al Mediador
        user3.send("¡Hola Alice! Yo ya estoy listo.");
    }
}
