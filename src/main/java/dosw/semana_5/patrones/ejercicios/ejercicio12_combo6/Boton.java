package dosw.semana_5.patrones.ejercicios.ejercicio12_combo6;

public class Boton implements ElementoUI {
    private String texto;

    public Boton(String texto) {
        this.texto = texto;
    }

    @Override
    public void dibujar(int indentacion) {
        String espacio = "  ".repeat(indentacion);
        System.out.println(espacio + "[Botón: " + texto + "]");
    }
}
