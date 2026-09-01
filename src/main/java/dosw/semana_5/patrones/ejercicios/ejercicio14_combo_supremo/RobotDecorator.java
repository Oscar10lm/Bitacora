package dosw.semana_5.patrones.ejercicios.ejercicio14_combo_supremo;

public abstract class RobotDecorator implements ComponenteRobot {
    protected ComponenteRobot robotEnvoltura;

    public RobotDecorator(ComponenteRobot robot) {
        this.robotEnvoltura = robot;
    }

    @Override
    public String ensamblar() {
        return robotEnvoltura.ensamblar();
    }

    @Override
    public double calcularPeso() {
        return robotEnvoltura.calcularPeso();
    }
}
