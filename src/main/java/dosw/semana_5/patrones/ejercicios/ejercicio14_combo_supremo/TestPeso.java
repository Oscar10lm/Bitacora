package dosw.semana_5.patrones.ejercicios.ejercicio14_combo_supremo;

public class TestPeso extends ControlCalidadFiltro {
    @Override
    public boolean auditar(ComponenteRobot robot) {
        System.out.println("[Test de Peso] Verificando... Peso actual: " + robot.calcularPeso() + " kg");
        if (robot.calcularPeso() > 100) {
            System.out.println("❌ Falla Control de Calidad: El robot es demasiado pesado (Max 100kg).");
            return false;
        }
        System.out.println("✅ Test de Peso superado.");
        if (siguiente != null) {
            return siguiente.auditar(robot);
        }
        return true;
    }
}
