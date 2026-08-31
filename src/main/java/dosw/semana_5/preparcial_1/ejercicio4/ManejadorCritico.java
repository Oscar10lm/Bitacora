package dosw.semana_5.preparcial_1.ejercicio4;

public class ManejadorCritico extends ManejadorPeligro {
    @Override
    public void manejarTemperatura(int temperatura) {
        if (temperatura > 500) {
            System.out.println("🚨 [Nivel 3] ¡PELIGRO! Apagando reactor e iniciando evacuación.");
        } else if (siguiente != null) {
            siguiente.manejarTemperatura(temperatura);
        }
    }
}
