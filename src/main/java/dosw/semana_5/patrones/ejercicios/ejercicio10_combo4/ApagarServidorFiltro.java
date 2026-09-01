package dosw.semana_5.patrones.ejercicios.ejercicio10_combo4;

public class ApagarServidorFiltro extends AlertaFiltro {
    @Override
    public void manejar(Alerta alerta) {
        if (alerta.getGravedad() == 3) {
            System.out.println("[Servidor] 🚨 GRAVEDAD MÁXIMA DETECTADA. INICIANDO APAGADO DE EMERGENCIA 🚨");
            return; // Cortocircuito, detiene la cadena
        }
        if (siguiente != null) {
            siguiente.manejar(alerta);
        }
    }
}
