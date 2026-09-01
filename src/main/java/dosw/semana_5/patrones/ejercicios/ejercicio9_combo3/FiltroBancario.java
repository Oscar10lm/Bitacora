package dosw.semana_5.patrones.ejercicios.ejercicio9_combo3;

public abstract class FiltroBancario {
    protected FiltroBancario siguiente;

    public void setSiguiente(FiltroBancario siguiente) {
        this.siguiente = siguiente;
    }

    public abstract void procesar(String cuenta, double monto, boolean sospechoso);
}
