package dosw.semana_5.patrones.ejercicios.ejercicio12_combo6;

public class BordeRojoDecorator extends ElementoUIDecorator {
    public BordeRojoDecorator(ElementoUI elemento) {
        super(elemento);
    }

    @Override
    public void dibujar(int indentacion) {
        super.dibujar(indentacion);
        System.out.println("  ".repeat(indentacion) + " -> (Con Borde Rojo 🔴)");
    }
}
