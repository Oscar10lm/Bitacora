package dosw.semana_3.extra.patrones.estructurales.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * EJERCICIO PRÁCTICO: ESTRUCTURA ORGANIZACIONAL (Composite)
 * ============================================================================
 *
 * Una empresa modela su organigrama con empleados individuales y equipos que 
 * pueden contener:
 * - Empleados
 * - Otros equipos (equipos grandes divididos en sub-equipos)
 * 
 * El sistema debe poder calcular el costo total en salarios de cualquier 
 * elemento, sin importar si es un empleado individual o un departamento 
 * completo con sub-equipos anidados.
 * 
 * Para obtener el costo total de un equipo, es necesario recorrer recursivamente 
 * todos sus miembros (empleados y sub-equipos) y sumar sus salarios.
 */
public class CompanyOrganizationSystem {

    // ==========================================
    // 1. COMPONENTE (La Interfaz Común)
    // ==========================================
    public interface OrganizationComponent {
        double getSalaryCost();
        void showOrganization(String indent);
    }

    // ==========================================
    // 2. HOJA (El Empleado)
    // ==========================================
    // Elemento base del árbol. No tiene subordinados a cargo en este modelo.
    public static class Employee implements OrganizationComponent {
        private String name;
        private String position;
        private double salary;

        public Employee(String name, String position, double salary) {
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        @Override
        public double getSalaryCost() {
            return salary; // Devuelve su propio salario
        }

        @Override
        public void showOrganization(String indent) {
            System.out.println(indent + "- [Empleado] " + name + " (" + position + ") -> Salario: $" + salary);
        }
    }

    // ==========================================
    // 3. COMPUESTO / CONTENEDOR (El Equipo o Departamento)
    // ==========================================
    // Puede contener Empleados y también otros Equipos (Sub-equipos).
    public static class Team implements OrganizationComponent {
        private String teamName;
        private List<OrganizationComponent> members = new ArrayList<>();

        public Team(String teamName) {
            this.teamName = teamName;
        }

        public void addMember(OrganizationComponent member) {
            members.add(member);
        }

        public void removeMember(OrganizationComponent member) {
            members.remove(member);
        }

        @Override
        public double getSalaryCost() {
            double totalSalary = 0;
            // DELEGACIÓN RECURSIVA:
            // Si el miembro es empleado, devuelve su salario.
            // Si el miembro es sub-equipo, vuelve a hacer este bucle por dentro.
            for (OrganizationComponent member : members) {
                totalSalary += member.getSalaryCost();
            }
            return totalSalary;
        }

        @Override
        public void showOrganization(String indent) {
            System.out.println(indent + "+ [EQUIPO] " + teamName);
            for (OrganizationComponent member : members) {
                member.showOrganization(indent + "   ");
            }
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // 1. Creamos empleados base (Hojas)
        OrganizationComponent dev1 = new Employee("Juan Pérez", "Desarrollador Backend", 4000.0);
        OrganizationComponent dev2 = new Employee("María Gómez", "Desarrollador Frontend", 3800.0);
        OrganizationComponent qa1 = new Employee("Carlos Ruiz", "Ingeniero QA", 3200.0);
        
        OrganizationComponent devOps1 = new Employee("Ana López", "DevOps Engineer", 4500.0);
        OrganizationComponent sec1 = new Employee("Luis Torres", "Analista de Seguridad", 4200.0);

        OrganizationComponent cto = new Employee("Roberto Sánchez", "Director de Tecnología (CTO)", 12000.0);

        // 2. Creamos los sub-equipos (Contenedores Nivel 1)
        Team developmentTeam = new Team("Desarrollo de Software");
        developmentTeam.addMember(dev1);
        developmentTeam.addMember(dev2);
        developmentTeam.addMember(qa1);

        Team infraTeam = new Team("Infraestructura y Seguridad");
        infraTeam.addMember(devOps1);
        infraTeam.addMember(sec1);

        // 3. Creamos el equipo principal o departamento (Contenedor Nivel 2)
        Team techDepartment = new Team("DEPARTAMENTO DE TECNOLOGÍA (IT)");
        techDepartment.addMember(cto); // El CTO está directamente bajo el departamento
        techDepartment.addMember(developmentTeam); // Agregamos el sub-equipo
        techDepartment.addMember(infraTeam);       // Agregamos el sub-equipo

        // 4. El cliente (Finanzas) interactúa con todo el árbol fácilmente
        System.out.println(">>> ORGANIGRAMA DE LA EMPRESA:");
        techDepartment.showOrganization("");

        System.out.println("\n>>> CALCULANDO PRESUPUESTO SALARIAL DE IT...");
        // La recursión calcula el salario del CTO + salarios del equipo Dev + salarios del equipo Infra
        double totalCost = techDepartment.getSalaryCost();
        System.out.println("Costo total en salarios del departamento: $" + totalCost);
        
        // Podemos calcular también solo de un sub-equipo
        System.out.println("\n>>> PRESUPUESTO SALARIAL SOLO DE DESARROLLO...");
        System.out.println("Costo de Desarrollo: $" + developmentTeam.getSalaryCost());
    }
}
