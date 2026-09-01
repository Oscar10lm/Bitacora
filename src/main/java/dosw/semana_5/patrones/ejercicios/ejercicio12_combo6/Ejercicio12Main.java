package dosw.semana_5.patrones.ejercicios.ejercicio12_combo6;

import java.util.Scanner;

public class Ejercicio12Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== DISEÑADOR DE INTERFACES (Composite + Decorator) ===");
        
        Panel ventanaPrincipal = new Panel("Ventana Principal");
        Panel panelNavegacion = new Panel("Barra de Navegación");
        
        ElementoUI btnInicio = new Boton("Inicio");
        ElementoUI btnAyuda = new Boton("Ayuda");
        
        System.out.println("Configurando el Botón de Ayuda...");
        System.out.print("¿Agregar Borde Rojo de advertencia al botón de Ayuda? (1. Sí, 2. No): ");
        if (scanner.nextInt() == 1) {
            btnAyuda = new BordeRojoDecorator(btnAyuda);
        }
        
        System.out.print("¿Agregar Efecto Sombra al botón de Inicio? (1. Sí, 2. No): ");
        if (scanner.nextInt() == 1) {
            btnInicio = new SombraDecorator(btnInicio);
        }
        
        panelNavegacion.agregar(btnInicio);
        panelNavegacion.agregar(btnAyuda);
        ventanaPrincipal.agregar(panelNavegacion);
        
        System.out.println("\nRenderizando la interfaz de usuario...");
        ventanaPrincipal.dibujar(0);
        
        scanner.close();
    }
}
