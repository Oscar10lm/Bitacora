package dosw.semana_4.taller.ejercicio4;

public class MageBuilder implements CharacterBuilder {
    private String name = "Mago Desconocido";
    private String armor = "Túnica de Tela";
    private String weapon = "Báculo Básico";
    private String skill = "Ninguna";

    @Override
    public CharacterBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public CharacterBuilder setArmor(String armor) {
        this.armor = armor;
        return this;
    }

    @Override
    public CharacterBuilder setWeapon(String weapon) {
        this.weapon = weapon;
        return this;
    }

    @Override
    public CharacterBuilder setSkill(String skill) {
        this.skill = skill;
        return this;
    }

    @Override
    public Character build() {
        return new BaseCharacter(name, armor, weapon, skill);
    }
}
