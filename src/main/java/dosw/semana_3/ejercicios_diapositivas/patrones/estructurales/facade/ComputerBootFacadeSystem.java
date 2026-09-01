package dosw.semana_3.ejercicios_diapositivas.patrones.estructurales.facade;

public class ComputerBootFacadeSystem {

    // ==========================================
    // 1. EL SUBSISTEMA COMPLEJO (Componentes de Hardware)
    // ==========================================
    
    public static class CPU {
        public void freeze() { System.out.println("  [CPU]: Procesador congelado, esperando instrucciones..."); }
        public void jump(long position) { System.out.println("  [CPU]: Saltando al sector de arranque " + position + "."); }
        public void execute() { System.out.println("  [CPU]: Ejecutando ciclos de reloj (Procesando)."); }
    }

    public static class Memory {
        public void load(long position, byte[] data) {
            System.out.println("  [Memoria RAM]: Cargando " + data.length + " bytes de datos en la posición de memoria " + position + ".");
        }
    }

    public static class HardDrive {
        public byte[] read(long lba, int size) {
            System.out.println("  [Disco Duro]: Leyendo " + size + " bytes del sector LBA " + lba + " (Sector de Boot).");
            return new byte[size]; // Simulamos devolver datos crudos
        }
    }

    public static class OperatingSystem {
        public void boot() {
            System.out.println("  [Sistema Operativo]: Cargando Kernel de Windows/Linux...");
            System.out.println("  [Sistema Operativo]: Interfaz gráfica (GUI) cargada. ¡Bienvenido!");
        }
    }

    // ==========================================
    // 2. LA FACHADA (Facade - La Tarjeta Madre / BIOS)
    // ==========================================
    public static class ComputerFacade {
        // Constantes estáticas internas que el cliente no debe conocer
        private static final long BOOT_ADDRESS = 0x0000000;
        private static final long BOOT_SECTOR = 0x0B00000;
        private static final int SECTOR_SIZE = 512;

        private CPU cpu = new CPU();
        private Memory ram = new Memory();
        private HardDrive hdd = new HardDrive();
        private OperatingSystem os = new OperatingSystem();

        // El único método expuesto al exterior
        public void start() {
            System.out.println("\n>>> BIOS: SECUENCIA DE ARRANQUE INICIADA <<<");
            
            // 1. Preparamos el procesador
            cpu.freeze();
            
            // 2. Leemos los datos del disco duro (El sector de arranque)
            byte[] bootData = hdd.read(BOOT_SECTOR, SECTOR_SIZE);
            
            // 3. Cargamos los datos crudos en la Memoria RAM
            ram.load(BOOT_ADDRESS, bootData);
            
            // 4. Le decimos al procesador dónde está el código y que empiece a ejecutar
            cpu.jump(BOOT_ADDRESS);
            cpu.execute();
            
            // 5. El sistema operativo arranca finalmente
            os.boot();
            
            System.out.println(">>> COMPUTADOR LISTO PARA USAR <<<\n");
        }
    }

    // ==========================================
    // 3. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> USUARIO FRENTE AL ESCRITORIO <<<\n");

        // El usuario solo interactúa con la fachada (la caja del PC/Botón Power)
        ComputerFacade myPC = new ComputerFacade();
        
        System.out.println("--- El usuario presiona el botón físico de [POWER] ---");
        
        // ¡El usuario no sabe NADA sobre CPUs, saltos de memoria hexadecimales, 
        // LBA, ni tamaños de sector (512)!
        myPC.start();
    }
}
