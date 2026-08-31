package dosw.semana_5.patrones.ejercicios.ejercicio5_cor;

public abstract class SoporteHandler {
    protected SoporteHandler siguiente;

    public void setSiguiente(SoporteHandler siguiente) {
        this.siguiente = siguiente;
    }

    public abstract void manejarTicket(int gravedad, String descripcion);
}
