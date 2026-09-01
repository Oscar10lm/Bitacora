package dosw.semana_5.patrones.ejercicios.ejercicio14_combo_supremo;

public class RobotBuilder {
    private ModuloRobot robot = new ModuloRobot("Robot Nucleo");

    public RobotBuilder agregarCabeza(String tipo) {
        ModuloRobot cabeza = new ModuloRobot("Cabeza");
        cabeza.agregar(new PiezaBase("Ojos " + tipo, 2.5));
        cabeza.agregar(new PiezaBase("Procesador Central", 5.0));
        robot.agregar(cabeza);
        return this;
    }

    public RobotBuilder agregarTorso() {
        ModuloRobot torso = new ModuloRobot("Torso");
        torso.agregar(new PiezaBase("Motor Interno", 25.0));
        torso.agregar(new PiezaBase("Chasis", 40.0));
        robot.agregar(torso);
        return this;
    }

    public ComponenteRobot build() {
        return robot;
    }
}
