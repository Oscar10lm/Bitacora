package dosw.semana_5.patrones.ejercicios.ejercicio14_combo_supremo;

public abstract class ControlCalidadFiltro {
    protected ControlCalidadFiltro siguiente;

    public void setSiguiente(ControlCalidadFiltro siguiente) {
        this.siguiente = siguiente;
    }

    public abstract boolean auditar(ComponenteRobot robot);
}
