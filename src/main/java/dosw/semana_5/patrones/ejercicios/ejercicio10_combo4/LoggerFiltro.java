package dosw.semana_5.patrones.ejercicios.ejercicio10_combo4;

public class LoggerFiltro extends AlertaFiltro {
    @Override
    public void manejar(Alerta alerta) {
        System.out.println("[Logger] Registrando en archivo: " + alerta.getMensaje());
        if (siguiente != null) {
            siguiente.manejar(alerta);
        }
    }
}
