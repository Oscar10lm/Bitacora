package dosw.semana_4.taller.ejercicio4;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Plataforma de Videojuegos - Personajes ---");

        // 1. Construcción del personaje inicial (Builder)
        System.out.println("\n[1] Creando el personaje inicial...");
        CharacterBuilder builder = new WarriorBuilder();
        Character warrior = builder.setName("Grom Hellscream")
                                   .setArmor("Acero Pesado")
                                   .setWeapon("Hacha de Batalla")
                                   .setSkill("Furia de Sangre")
                                   .build();

        System.out.println("Personaje: " + warrior.getDescription());
        warrior.attack();

        // 2. Modificación dinámica (Decorator)
        System.out.println("\n[2] ¡El jugador encuentra un Escudo de Hielo y una poción de Velocidad!");
        
        // Envolvemos el personaje original con los decoradores
        Character poweredWarrior = new ShieldDecorator(new SpeedDecorator(warrior));
        
        System.out.println("Estado actual: " + poweredWarrior.getDescription());
        poweredWarrior.attack();

        // 3. Modificación adicional en runtime
        System.out.println("\n[3] ¡El jugador lanza un hechizo de Invisibilidad!");
        Character invisiblePoweredWarrior = new InvisibilityDecorator(poweredWarrior);
        
        System.out.println("Estado final: " + invisiblePoweredWarrior.getDescription());
        invisiblePoweredWarrior.attack();
    }
}
