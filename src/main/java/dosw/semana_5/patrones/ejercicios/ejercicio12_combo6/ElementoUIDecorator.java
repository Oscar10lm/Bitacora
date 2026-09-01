package dosw.semana_5.patrones.ejercicios.ejercicio12_combo6;

public abstract class ElementoUIDecorator implements ElementoUI {
    protected ElementoUI elementoEnvoltura;

    public ElementoUIDecorator(ElementoUI elemento) {
        this.elementoEnvoltura = elemento;
    }

    @Override
    public void dibujar(int indentacion) {
        elementoEnvoltura.dibujar(indentacion);
    }
}
