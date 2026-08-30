package dosw.semana_4.taller.ejercicio4;

public class BaseCharacter implements Character {
    private String name;
    private String armor;
    private String weapon;
    private String skill;

    public BaseCharacter(String name, String armor, String weapon, String skill) {
        this.name = name;
        this.armor = armor;
        this.weapon = weapon;
        this.skill = skill;
    }

    @Override
    public void attack() {
        System.out.println(name + " ataca usando " + weapon + " y habilidad " + skill + "!");
    }

    @Override
    public String getDescription() {
        return name + " [Armadura: " + armor + ", Arma: " + weapon + "]";
    }
}
