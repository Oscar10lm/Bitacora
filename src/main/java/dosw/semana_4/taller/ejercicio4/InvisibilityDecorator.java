package dosw.semana_4.taller.ejercicio4;

public class InvisibilityDecorator extends CharacterDecorator {
    public InvisibilityDecorator(Character wrapper) {
        super(wrapper);
    }

    @Override
    public void attack() {
        System.out.println(" ↳ [Efecto]: Atacas por sorpresa desde las sombras...");
        super.attack();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + [Poder: Invisibilidad]";
    }
}
