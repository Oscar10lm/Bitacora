package dosw.semana_5.patrones.ejercicios.ejercicio10_combo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio10Main {
    public static void main(String[] args) {
        // Configurar Cadena de Responsabilidad
        AlertaFiltro logger = new LoggerFiltro();
        AlertaFiltro email = new AlertaEmailFiltro();
        AlertaFiltro apagar = new ApagarServidorFiltro();
        
        logger.setSiguiente(email);
        email.setSiguiente(apagar);
        
        List<Alerta> listaAlertas = new ArrayList<>();
        listaAlertas.add(new Alerta("Usuario inició sesión.", 1));
        listaAlertas.add(new Alerta("Espacio en disco al 85%.", 2));
        listaAlertas.add(new Alerta("Memoria RAM agotada. Fallo de sistema.", 3));
        listaAlertas.add(new Alerta("Copia de seguridad completada.", 1));
        
        AlertaIterator iterador = new AlertaIterator(listaAlertas);
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SISTEMA DE LOGS (Iterator + CoR) ===");
        System.out.println("Procesando lote masivo de alertas...");
        System.out.println("Presione ENTER para procesar la siguiente alerta...");
        
        while (iterador.hasNext()) {
            scanner.nextLine();
            Alerta alertaActual = iterador.next();
            System.out.println("\n--- Procesando Alerta (Gravedad: " + alertaActual.getGravedad() + ") ---");
            logger.manejar(alertaActual);
        }
        
        System.out.println("\nNo hay más alertas en la cola.");
        scanner.close();
    }
}
