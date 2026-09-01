package dosw.semana_5.patrones.ejercicios.ejercicio12_combo6;

import java.util.ArrayList;
import java.util.List;

public class Panel implements ElementoUI {
    private String id;
    private List<ElementoUI> elementos = new ArrayList<>();

    public Panel(String id) {
        this.id = id;
    }

    public void agregar(ElementoUI elemento) {
        elementos.add(elemento);
    }

    @Override
    public void dibujar(int indentacion) {
        String espacio = "  ".repeat(indentacion);
        System.out.println(espacio + "<Panel: " + id + ">");
        for (ElementoUI ui : elementos) {
            ui.dibujar(indentacion + 1);
        }
        System.out.println(espacio + "</Panel>");
    }
}
