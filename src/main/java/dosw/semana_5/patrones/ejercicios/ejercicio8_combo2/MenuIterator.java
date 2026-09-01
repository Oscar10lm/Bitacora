package dosw.semana_5.patrones.ejercicios.ejercicio8_combo2;

import java.util.Iterator;
import java.util.Stack;

public class MenuIterator implements Iterator<MenuComponente> {
    private Stack<Iterator<MenuComponente>> pila = new Stack<>();

    public MenuIterator(Iterator<MenuComponente> iteradorBase) {
        pila.push(iteradorBase);
    }

    @Override
    public boolean hasNext() {
        if (pila.isEmpty()) {
            return false;
        }
        Iterator<MenuComponente> iteradorActual = pila.peek();
        if (!iteradorActual.hasNext()) {
            pila.pop();
            return hasNext();
        }
        return true;
    }

    @Override
    public MenuComponente next() {
        if (hasNext()) {
            Iterator<MenuComponente> iteradorActual = pila.peek();
            MenuComponente componente = iteradorActual.next();
            if (componente instanceof Categoria) {
                pila.push(((Categoria) componente).getComponentes().iterator());
            }
            return componente;
        }
        return null;
    }
}
