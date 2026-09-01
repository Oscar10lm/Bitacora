package dosw.semana_5.patrones.ejercicios.ejercicio10_combo4;

public class AlertaEmailFiltro extends AlertaFiltro {
    @Override
    public void manejar(Alerta alerta) {
        if (alerta.getGravedad() >= 2) {
            System.out.println("[Email] ✉ Enviando correo al admin: " + alerta.getMensaje());
        }
        if (siguiente != null) {
            siguiente.manejar(alerta);
        }
    }
}
