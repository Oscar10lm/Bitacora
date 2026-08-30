package dosw.semana_4.taller.ejercicio4;

public abstract class CharacterDecorator implements Character {
    protected Character wrapper;

    public CharacterDecorator(Character wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void attack() {
        wrapper.attack();
    }

    @Override
    public String getDescription() {
        return wrapper.getDescription();
    }
}
