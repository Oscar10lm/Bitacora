package dosw.semana_5.patrones.ejercicios.ejercicio14_combo_supremo;

public class CanonLaserDecorator extends RobotDecorator {
    public CanonLaserDecorator(ComponenteRobot robot) {
        super(robot);
    }

    @Override
    public String ensamblar() {
        return super.ensamblar() + " -> [Cañón Láser 💥]";
    }

    @Override
    public double calcularPeso() {
        return super.calcularPeso() + 15.0; // Pesa 15kg extra
    }
}
