package dosw.semana_5.patrones.extra.ejercicio4;

import java.util.ArrayList;
import java.util.List;

public class Reactor {
    private int temperatura;
    private List<PanelDeControl> paneles = new ArrayList<>();

    public void agregarPanel(PanelDeControl panel) {
        paneles.add(panel);
    }

    public void setTemperatura(int temperatura) {
        System.out.println("\n🔥 [Reactor] La temperatura cambió a: " + temperatura + "°C");
        this.temperatura = temperatura;
        notificarPaneles();
    }

    private void notificarPaneles() {
        for (PanelDeControl panel : paneles) {
            panel.actualizarTemperatura(this.temperatura);
        }
    }
}
