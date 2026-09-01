package dosw.semana_5.patrones.extra.ejercicio4;

public class ManejadorNormal extends ManejadorPeligro {
    @Override
    public void manejarTemperatura(int temperatura) {
        if (temperatura <= 100) {
            System.out.println("✅ [Nivel 1] Todo en orden.");
        } else if (siguiente != null) {
            siguiente.manejarTemperatura(temperatura);
        }
    }
}
