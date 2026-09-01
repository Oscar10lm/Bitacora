package dosw.semana_5.patrones.ejercicios.ejercicio14_combo_supremo;

import java.util.Scanner;

public class Ejercicio14Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("👑 === FÁBRICA SUPREMA DE ROBOTS === 👑");
        System.out.println("Combinando: Builder + Composite + Decorator + Chain of Responsibility");
        
        // FASE 1: BUILDER + COMPOSITE
        System.out.print("\n[Fase 1] ¿Qué tipo de cabeza desea? (Normal / Combate): ");
        String tipoCabeza = scanner.nextLine();
        
        System.out.println("Construyendo chasis y núcleo...");
        ComponenteRobot miRobot = new RobotBuilder()
            .agregarCabeza(tipoCabeza)
            .agregarTorso()
            .build();
            
        // FASE 2: DECORATOR
        System.out.print("\n[Fase 2] ¿Desea instalar Cañón Láser (15kg extra)? (1. Sí, 2. No): ");
        if (scanner.nextInt() == 1) {
            miRobot = new CanonLaserDecorator(miRobot);
        }
        
        // FASE 3: CHAIN OF RESPONSIBILITY
        System.out.println("\n[Fase 3] Iniciando Control de Calidad (CoR)...");
        ControlCalidadFiltro testPeso = new TestPeso();
        // Se podrían añadir más tests a la cadena (TestBateria, TestArmamento, etc.)
        
        boolean aprobado = testPeso.auditar(miRobot);
        
        System.out.println("\n=== RESULTADO FINAL ===");
        if (aprobado) {
            System.out.println("🤖 ROBOT APROBADO Y LISTO PARA DESPLIEGUE!");
            System.out.println("Estructura: " + miRobot.ensamblar());
            System.out.println("Peso Final: " + miRobot.calcularPeso() + " kg");
        } else {
            System.out.println("💥 EL ROBOT FUE RECHAZADO EN LA FÁBRICA.");
        }
        
        scanner.close();
    }
}
