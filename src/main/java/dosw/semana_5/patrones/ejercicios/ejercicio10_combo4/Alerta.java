package dosw.semana_5.patrones.ejercicios.ejercicio10_combo4;

public class Alerta {
    private String mensaje;
    private int gravedad; // 1 = Info, 2 = Warning, 3 = Critical

    public Alerta(String mensaje, int gravedad) {
        this.mensaje = mensaje;
        this.gravedad = gravedad;
    }

    public String getMensaje() { return mensaje; }
    public int getGravedad() { return gravedad; }
}
