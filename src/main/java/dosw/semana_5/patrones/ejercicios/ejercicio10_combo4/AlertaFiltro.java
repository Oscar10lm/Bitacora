package dosw.semana_5.patrones.ejercicios.ejercicio10_combo4;

public abstract class AlertaFiltro {
    protected AlertaFiltro siguiente;

    public void setSiguiente(AlertaFiltro siguiente) {
        this.siguiente = siguiente;
    }

    public abstract void manejar(Alerta alerta);
}
