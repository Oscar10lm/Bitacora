package dosw.semana_5.patrones.ejercicios.ejercicio10_combo4;

import java.util.Iterator;
import java.util.List;

public class AlertaIterator implements Iterator<Alerta> {
    private List<Alerta> alertas;
    private int indice = 0;

    public AlertaIterator(List<Alerta> alertas) {
        this.alertas = alertas;
    }

    @Override
    public boolean hasNext() {
        return indice < alertas.size();
    }

    @Override
    public Alerta next() {
        if (hasNext()) {
            return alertas.get(indice++);
        }
        return null;
    }
}
