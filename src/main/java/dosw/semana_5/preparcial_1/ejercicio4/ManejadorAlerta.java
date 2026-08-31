package dosw.semana_5.preparcial_1.ejercicio4;

public class ManejadorAlerta extends ManejadorPeligro {
    @Override
    public void manejarTemperatura(int temperatura) {
        if (temperatura > 100 && temperatura <= 500) {
            System.out.println("⚠️ [Nivel 2] Activando bombas de enfriamiento secundarias.");
        } else if (siguiente != null) {
            siguiente.manejarTemperatura(temperatura);
        }
    }
}
