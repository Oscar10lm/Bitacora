package dosw.semana_5.patrones.ejercicios.ejercicio14_combo_supremo;

import java.util.ArrayList;
import java.util.List;

public class ModuloRobot implements ComponenteRobot {
    private String nombre;
    private List<ComponenteRobot> componentes = new ArrayList<>();

    public ModuloRobot(String nombre) {
        this.nombre = nombre;
    }

    public void agregar(ComponenteRobot c) {
        componentes.add(c);
    }

    @Override
    public String ensamblar() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(nombre).append(": ");
        for (ComponenteRobot c : componentes) {
            sb.append(c.ensamblar()).append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public double calcularPeso() {
        double pesoTotal = 0;
        for (ComponenteRobot c : componentes) {
            pesoTotal += c.calcularPeso();
        }
        return pesoTotal;
    }
}
