package dosw.semana_3.ejercicios_diapositivas.patrones.estructurales.adapter;

public class GasStationSystem {

    // ==========================================
    // 1. INTERFAZ DEL CLIENTE (Lo que el sistema central entiende)
    // ==========================================
    public interface FuelService {
        void supply(int amountInLiters);
    }

    // Clase cliente existente (Surtidor de Gasolina)
    public static class GasPump implements FuelService {
        @Override
        public void supply(int amountInLiters) {
            System.out.println("Surtidor Gasolina: Abasteciendo " + amountInLiters + " litros de combustible.");
        }
    }

    // ==========================================
    // 2. SERVICIOS INCOMPATIBLES (Adaptees - Proveedores externos)
    // ==========================================
    
    // Proveedor de cargadores rápidos (Mide en KWh y su método se llama distinto)
    public static class FastElectricCharger {
        public void fastCharge(double kWh) {
            System.out.println("FastCharger [Tercero]: Entregando carga ultra rápida de " + kWh + " KWh.");
        }
    }

    // Proveedor de cargadores lentos (Mide en KWh y su método también es distinto)
    public static class SlowElectricCharger {
        public void slowCharge(double kWh) {
            System.out.println("SlowCharger [Tercero]: Entregando carga nocturna lenta de " + kWh + " KWh.");
        }
    }

    // ==========================================
    // 3. ADAPTADORES (Los Traductores)
    // ==========================================
    
    // Adaptador para el cargador rápido
    public static class FastChargerAdapter implements FuelService {
        private FastElectricCharger charger;

        public FastChargerAdapter(FastElectricCharger charger) {
            this.charger = charger;
        }

        @Override
        public void supply(int amountInLiters) {
            System.out.println("Adaptador Rápido: Interceptando solicitud de " + amountInLiters + " litros...");
            double kwh = convertLitersToKWh(amountInLiters);
            // Llamada traducida al sistema incompatible
            charger.fastCharge(kwh);
        }

        private double convertLitersToKWh(int liters) {
            // Regla de negocio: En el modelo rápido se multiplica por 8.0
            return liters * 8.0;
        }
    }

    // Adaptador para el cargador lento
    public static class SlowChargerAdapter implements FuelService {
        private SlowElectricCharger charger;

        public SlowChargerAdapter(SlowElectricCharger charger) {
            this.charger = charger;
        }

        @Override
        public void supply(int amountInLiters) {
            System.out.println("Adaptador Lento: Interceptando solicitud de " + amountInLiters + " litros...");
            double kwh = convertLitersToKWh(amountInLiters);
            // Llamada traducida al sistema incompatible
            charger.slowCharge(kwh);
        }

        private double convertLitersToKWh(int liters) {
            // Regla de negocio: En el modelo lento se multiplica por 7.0
            return liters * 7.0;
        }
    }

    // ==========================================
    // 4. CLIENTE CENTRAL (Demostración / MainClass)
    // ==========================================
    public static class CentralSystem {
        // El sistema central SOLO sabe hablar con la interfaz FuelService en litros
        public void processVehicle(FuelService station, int requestLiters) {
            station.supply(requestLiters);
            System.out.println("------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        CentralSystem central = new CentralSystem();

        System.out.println("--- LLEGA UN CARRO A COMBUSTIÓN ---");
        FuelService gasPump = new GasPump();
        central.processVehicle(gasPump, 50); // Pide 50 litros

        System.out.println("--- LLEGA UN CARRO ELÉCTRICO (CARGA RÁPIDA) ---");
        // Tenemos el servicio incompatible de terceros
        FastElectricCharger thirdPartyFastCharger = new FastElectricCharger();
        // Lo envolvemos en el adaptador para que el sistema central lo entienda
        FuelService fastAdapter = new FastChargerAdapter(thirdPartyFastCharger);
        
        // El sistema central sigue pidiendo "50 litros", el adaptador hace la magia
        central.processVehicle(fastAdapter, 50); 

        System.out.println("--- LLEGA UN CARRO ELÉCTRICO (CARGA LENTA) ---");
        SlowElectricCharger thirdPartySlowCharger = new SlowElectricCharger();
        FuelService slowAdapter = new SlowChargerAdapter(thirdPartySlowCharger);
        
        central.processVehicle(slowAdapter, 50);
    }
}
