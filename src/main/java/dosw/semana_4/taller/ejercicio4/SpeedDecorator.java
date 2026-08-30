package dosw.semana_4.taller.ejercicio4;

public class SpeedDecorator extends CharacterDecorator {
    public SpeedDecorator(Character wrapper) {
        super(wrapper);
    }

    @Override
    public void attack() {
        super.attack();
        System.out.println(" ↳ [Efecto]: ¡Atacas dos veces gracias a la Velocidad Extra!");
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + [Poder: Velocidad Extra]";
    }
}
