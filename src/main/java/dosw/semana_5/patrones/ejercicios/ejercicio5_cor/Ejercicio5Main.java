package dosw.semana_5.patrones.ejercicios.ejercicio5_cor;

import java.util.Scanner;

public class Ejercicio5Main {
    public static void main(String[] args) {
        // 1. Configurar Cadena
        SoporteHandler bot = new BotSoporte();
        SoporteHandler asesor = new AsesorSoporte();
        SoporteHandler ingeniero = new IngenieroSoporte();
        SoporteHandler gerente = new GerenteSoporte();

        bot.setSiguiente(asesor);
        asesor.setSiguiente(ingeniero);
        ingeniero.setSiguiente(gerente);

        // 2. Interacción con Scanner
        Scanner scanner = new Scanner(System.in);
        System.out.println("🏢 CENTRO DE SOPORTE DE IT 🏢");
        System.out.println("Ingresa la descripción de tu problema:");
        String desc = scanner.nextLine();
        
        System.out.println("Ingresa la gravedad del problema (1 a 5):");
        int gravedad = scanner.nextInt();

        System.out.println("\nEnviando ticket a la cadena...");
        bot.manejarTicket(gravedad, desc);
        
        scanner.close();
    }
}
