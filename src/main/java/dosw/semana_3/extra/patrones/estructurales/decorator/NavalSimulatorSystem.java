package dosw.semana_3.extra.patrones.estructurales.decorator;

public class NavalSimulatorSystem {

    // ==========================================
    // 1. COMPONENTE (La Interfaz Común)
    // ==========================================
    public interface Barco {
        String getDescripcion();
        int poderAtaque();
        int defensa();
    }

    // ==========================================
    // 2. COMPONENTE CONCRETO (El Objeto Base)
    // ==========================================
    public static class BarcoBase implements Barco {
        @Override
        public String getDescripcion() {
            return "Barco de Guerra Estándar";
        }

        @Override
        public int poderAtaque() {
            return 10; // Ataque base
        }

        @Override
        public int defensa() {
            return 10; // Defensa base
        }
    }

    // ==========================================
    // 3. DECORADOR BASE (La Clase Envoltorio)
    // ==========================================
    // Implementa la interfaz pero delega todas las llamadas al objeto envuelto
    public static abstract class BarcoBaseDecorador implements Barco {
        
        // El objeto original que estamos envolviendo (composición)
        protected Barco barcoEnvoltorio;

        public BarcoBaseDecorador(Barco barco) {
            this.barcoEnvoltorio = barco;
        }

        @Override
        public String getDescripcion() {
            return barcoEnvoltorio.getDescripcion();
        }

        @Override
        public int poderAtaque() {
            return barcoEnvoltorio.poderAtaque();
        }

        @Override
        public int defensa() {
            return barcoEnvoltorio.defensa();
        }
    }

    // ==========================================
    // 4. DECORADORES CONCRETOS (Las Mejoras)
    // ==========================================
    
    // Blindaje (+30 Defensa)
    public static class BlindajeDecorador extends BarcoBaseDecorador {
        public BlindajeDecorador(Barco barco) {
            super(barco);
        }

        @Override
        public String getDescripcion() {
            return super.getDescripcion() + ", con Blindaje Reforzado";
        }

        @Override
        public int defensa() {
            return super.defensa() + 30; // Altera el resultado original
        }
    }

    // Radar (+10 Ataque)
    public static class RadarDecorador extends BarcoBaseDecorador {
        public RadarDecorador(Barco barco) {
            super(barco);
        }

        @Override
        public String getDescripcion() {
            return super.getDescripcion() + ", con Radar Avanzado";
        }

        @Override
        public int poderAtaque() {
            return super.poderAtaque() + 10;
        }
    }

    // Misiles (+40 Ataque)
    public static class MisilesDecorador extends BarcoBaseDecorador {
        public MisilesDecorador(Barco barco) {
            super(barco);
        }

        @Override
        public String getDescripcion() {
            return super.getDescripcion() + ", equipado con Batería de Misiles";
        }

        @Override
        public int poderAtaque() {
            return super.poderAtaque() + 40;
        }
    }

    // Anti-Torpedos (+20 Ataque según especificaciones)
    public static class AntiTorpedosDecorador extends BarcoBaseDecorador {
        public AntiTorpedosDecorador(Barco barco) {
            super(barco);
        }

        @Override
        public String getDescripcion() {
            return super.getDescripcion() + ", con Sistema Anti-Torpedos";
        }

        @Override
        public int poderAtaque() {
            return super.poderAtaque() + 20;
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void printShipStats(Barco barco) {
        System.out.println("Descripción : " + barco.getDescripcion());
        System.out.println("Ataque      : " + barco.poderAtaque());
        System.out.println("Defensa     : " + barco.defensa());
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        
        System.out.println(">>> 1. Astillero: Creando barco base sin mejoras...");
        Barco miBarco = new BarcoBase();
        printShipStats(miBarco);

        // Envolvemos dinámicamente el barco base con la mejora de Blindaje
        System.out.println(">>> 2. Taller: Instalando Blindaje Reforzado...");
        miBarco = new BlindajeDecorador(miBarco);
        printShipStats(miBarco);

        // Volvemos a envolver el barco blindado, ahora con Radar
        System.out.println(">>> 3. Taller: Instalando Radar Avanzado...");
        miBarco = new RadarDecorador(miBarco);
        printShipStats(miBarco);

        // Finalmente le añadimos dos mejoras más a la vez
        System.out.println(">>> 4. Taller: Instalando Misiles y Sistema Anti-Torpedos...");
        miBarco = new MisilesDecorador(miBarco);
        miBarco = new AntiTorpedosDecorador(miBarco);
        
        System.out.println(">>> ESTADÍSTICAS FINALES DEL BUQUE INSIGNIA:");
        printShipStats(miBarco);
        
        /*
         * EL PODER DEL DECORATOR:
         * Hemos logrado tener un Barco con Blindaje, Radar, Misiles y Anti-Torpedos 
         * en una sola variable, y todo esto en TIEMPO DE EJECUCIÓN (dinámicamente).
         * No tuvimos que crear una clase precompilada monstruosa llamada:
         * "BarcoBlindajeRadarMisilesAntiTorpedos".
         */
    }
}
