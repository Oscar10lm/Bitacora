package dosw.semana_5.patrones.extra.ejercicio4;

public abstract class ManejadorPeligro {
    protected ManejadorPeligro siguiente;

    public void setSiguiente(ManejadorPeligro siguiente) {
        this.siguiente = siguiente;
    }

    public abstract void manejarTemperatura(int temperatura);
}
