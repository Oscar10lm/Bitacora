package dosw.semana_3.extra.patrones.comportamiento.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * EJERCICIO PRÁCTICO: SISTEMA DE ACCIONES BURSÁTILES (Observer)
 * ============================================================================
 *
 * Una acción en bolsa (Sujeto) notifica a todos los inversionistas suscritos 
 * (Observadores) cada vez que su precio cambia.
 * 
 * Esto permite que cada inversionista reaccione a su manera:
 * - Uno puede vender o comprar automáticamente (Inversor Agresivo/Bot).
 * - Otro simplemente registra el historial en una libreta (Inversor Conservador).
 * 
 * Todo esto ocurre sin que la acción conozca quiénes son los inversionistas 
 * ni cómo están reaccionando, manteniendo el desacoplamiento total.
 */
public class StockMarketObserverSystem {

    // ==========================================
    // 1. LA INTERFAZ OBSERVADOR (El Inversionista)
    // ==========================================
    // El método callback que recibirá el nuevo precio
    public interface InvestorObserver {
        void updatePrice(String stockSymbol, double newPrice);
    }

    // ==========================================
    // 2. OBSERVADORES CONCRETOS (Tipos de Inversionistas)
    // ==========================================
    
    // Inversionista 1: Bot de Trading Agresivo (Reacciona con lógica de negocio)
    public static class TradingBot implements InvestorObserver {
        private String botName;
        private double sellThreshold;

        public TradingBot(String botName, double sellThreshold) {
            this.botName = botName;
            this.sellThreshold = sellThreshold;
        }

        @Override
        public void updatePrice(String stockSymbol, double newPrice) {
            System.out.print("[BOT " + botName + "]: Evaluando precio de " + stockSymbol + " a $" + newPrice + "... ");
            
            // Lógica interna del Bot de la cual la Acción Bursátil no sabe nada
            if (newPrice >= sellThreshold) {
                System.out.println("¡ALERTA! Precio superó el límite esperado. VENDIENDO TODAS LAS ACCIONES AHORA.");
            } else {
                System.out.println("HOLD (Manteniendo acciones).");
            }
        }
    }

    // Inversionista 2: Inversor Conservador (Analista tradicional)
    public static class ConservativeAnalyst implements InvestorObserver {
        private String analystName;

        public ConservativeAnalyst(String analystName) {
            this.analystName = analystName;
        }

        @Override
        public void updatePrice(String stockSymbol, double newPrice) {
            System.out.println("[ANALISTA " + analystName + "]: Anotando en la libreta que " 
                               + stockSymbol + " ahora vale $" + newPrice + ". Sin acciones de riesgo tomadas.");
        }
    }

    // ==========================================
    // 3. LA INTERFAZ SUJETO (Observable)
    // ==========================================
    public interface StockSubject {
        void subscribe(InvestorObserver investor);
        void unsubscribe(InvestorObserver investor);
        void notifyInvestors();
    }

    // ==========================================
    // 4. SUJETO CONCRETO (La Acción de la Empresa en Bolsa)
    // ==========================================
    public static class Stock implements StockSubject {
        
        private String symbol;
        private double currentPrice;
        
        // El listado de corredores interesados en esta empresa
        private List<InvestorObserver> investors = new ArrayList<>();

        public Stock(String symbol, double initialPrice) {
            this.symbol = symbol;
            this.currentPrice = initialPrice;
        }

        @Override
        public void subscribe(InvestorObserver investor) {
            investors.add(investor);
        }

        @Override
        public void unsubscribe(InvestorObserver investor) {
            investors.remove(investor);
        }

        @Override
        public void notifyInvestors() {
            System.out.println("\n*** WALL STREET: La acción " + symbol + " ha notificado un cambio a sus inversores ***");
            // Iterar y notificar a todos los interesados (multicast)
            for (InvestorObserver investor : investors) {
                investor.updatePrice(this.symbol, this.currentPrice);
            }
            System.out.println("*********************************************************************************\n");
        }

        // Lógica de Negocio: El mercado empuja el precio hacia arriba o hacia abajo
        public void setPrice(double newPrice) {
            if (this.currentPrice != newPrice) { // Solo notifica si realmente hubo un cambio
                System.out.println("\n[SISTEMA BURSÁTIL]: Fluctuación de mercado para " + symbol + " (De $" + this.currentPrice + " a $" + newPrice + ")");
                this.currentPrice = newPrice;
                
                // Dispara el patrón
                notifyInvestors();
            }
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> ABRIENDO MERCADO DE VALORES <<<");

        // 1. Instanciamos el Sujeto (La Acción de una empresa Tecnológica)
        Stock appleStock = new Stock("AAPL", 150.0);

        // 2. Instanciamos los Observadores (Los Inversores)
        // Este bot venderá automáticamente si el precio toca o supera los $170
        InvestorObserver highFrequencyBot = new TradingBot("AlphaGoBot", 170.0);
        
        // Este analista solo mirará la gráfica
        InvestorObserver wallStreetAnalyst = new ConservativeAnalyst("Warren Buffett");

        System.out.println("\n--- Los inversionistas compran y se suscriben a las alertas de AAPL ---");
        // 3. Registramos las dependencias
        appleStock.subscribe(highFrequencyBot);
        appleStock.subscribe(wallStreetAnalyst);

        // 4. Simulamos los movimientos del mercado a lo largo del día
        
        // 10:00 AM - Sube un poco
        appleStock.setPrice(155.5);

        // 12:00 PM - Cae la bolsa por malas noticias
        appleStock.setPrice(148.0);
        
        // 03:00 PM - Apple anuncia un nuevo iPhone revolucionario, las acciones se disparan
        appleStock.setPrice(175.2);
        
        /*
         * NOTA: En el último cambio a $175.2, podrás ver en la consola cómo 
         * el Analista simplemente anota el valor, mientras que el Bot 
         * entra en pánico y ejecuta su orden de VENTA automática.
         * ¡Todo esto sin que la clase Stock sepa nada de lo que está pasando!
         */
    }
}
