package dosw.semana_3.taller.solid;

/**
 * -------------------------------------------------------------------
 * #10 CourseManager — La Clase Que Lo Hace Todo
 * -------------------------------------------------------------------
 *
 * (1) SITUACIÓN:
 * Una única clase (`CourseManager`) es responsable de crear cursos, enviar 
 * correos, generar certificados, calcular estadísticas y administrar 
 * profesores. Modificar una parte (ej. certificados para marketing) afecta 
 * a la misma clase donde trabaja otro equipo (ej. estadísticas), generando 
 * conflictos constantes.
 *
 * (2) PRINCIPIO SOLID A APLICAR:
 * SRP - Single Responsibility Principle (Principio de Responsabilidad Única).
 *
 * (3) JUSTIFICACIÓN TÉCNICA:
 * El problema expuesto es el caso de uso clásico de una "God Class" o 
 * Clase Dios. El principio SRP nos dice que una clase debe tener una y 
 * solo una razón para cambiar (o un solo "actor" al que responder). En este 
 * caso, la clase responde a demasiados actores (marketing, métricas, recursos 
 * humanos). Si el equipo de marketing necesita cambiar el diseño del 
 * certificado, no deberían tener que tocar el mismo archivo que el equipo de 
 * métricas usa para sus algoritmos. La solución es dividir `CourseManager` en 
 * clases más pequeñas, especializadas y cohesivas.
 *
 * (4) SOLUCIÓN PROPUESTA (Estructura):
 * Descomponer la funcionalidad masiva en servicios separados: 
 * `CourseRepository` (gestión de cursos), `EmailSender` (notificaciones), 
 * `CertificateGenerator` (diplomas), `StatisticsCalculator` (métricas) y 
 * `TeacherManager` (docentes).
 */
public class Ejercicio10 {

    // --- ESQUELETO DE SOLUCIÓN BASADO EN SRP ---

    // 1. Responsabilidad: Enviar correos
    public static class EmailSender {
        public void sendWelcomeEmail(String studentEmail) {
            System.out.println("Enviando correo a: " + studentEmail);
        }
    }

    // 2. Responsabilidad: Generar Certificados (Actor: Marketing)
    public static class CertificateGenerator {
        public void generateCertificate(String studentName, String courseName) {
            // Si marketing cambia la plantilla, solo tocan esta clase
            System.out.println("Generando PDF de certificado para " + studentName);
        }
    }

    // 3. Responsabilidad: Calcular estadísticas (Actor: Equipo de Datos)
    public static class StatisticsCalculator {
        public double calculateAverageScore(String courseId) {
            // Si el equipo de datos cambia el algoritmo, solo tocan esta clase
            System.out.println("Calculando métricas complejas...");
            return 4.5;
        }
    }

    // 4. Responsabilidad: Administrar Profesores (Actor: RRHH)
    public static class TeacherManager {
        public void assignTeacherToCourse(String teacherId, String courseId) {
            System.out.println("Asignando profesor al curso...");
        }
    }

    // 5. Responsabilidad Principal original (ahora delegada a las otras)
    // Puede actuar como un Facade o Coordinador, pero no contiene la lógica pesada.
    public static class CourseManager {
        private final EmailSender emailSender;
        private final CertificateGenerator certificateGenerator;

        public CourseManager(EmailSender emailSender, CertificateGenerator certificateGenerator) {
            this.emailSender = emailSender;
            this.certificateGenerator = certificateGenerator;
        }

        public void completeCourse(String studentName, String studentEmail, String courseName) {
            System.out.println("Completando curso...");
            // Solo coordina
            certificateGenerator.generateCertificate(studentName, courseName);
            emailSender.sendWelcomeEmail(studentEmail);
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        CourseManager manager = new CourseManager(new EmailSender(), new CertificateGenerator());
        manager.completeCourse("Juan Pérez", "juan@correo.com", "Java Avanzado");
        
        // Ahora, los equipos pueden trabajar en paralelo sin romperse el código
        StatisticsCalculator stats = new StatisticsCalculator();
        stats.calculateAverageScore("JAVA101");
    }
}
