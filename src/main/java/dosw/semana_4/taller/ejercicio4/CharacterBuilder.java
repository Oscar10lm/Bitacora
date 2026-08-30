package dosw.semana_4.taller.ejercicio4;

public interface CharacterBuilder {
    CharacterBuilder setName(String name);
    CharacterBuilder setArmor(String armor);
    CharacterBuilder setWeapon(String weapon);
    CharacterBuilder setSkill(String skill);
    Character build();
}
