package dosw.semana_4.taller.ejercicio4;

public class ShieldDecorator extends CharacterDecorator {
    public ShieldDecorator(Character wrapper) {
        super(wrapper);
    }

    @Override
    public void attack() {
        super.attack();
        System.out.println(" ↳ [Efecto]: ¡El ataque rebota ligeramente por tu Escudo de Hielo!");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + [Poder: Escudo de Hielo]";
    }
}
