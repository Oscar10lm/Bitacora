package dosw.semana_5.patrones.extra.ejercicio4;

public class Ejercicio4 implements PanelDeControl {
    private ManejadorPeligro manejadorBase;

    public Ejercicio4(ManejadorPeligro manejadorBase) {
        this.manejadorBase = manejadorBase;
    }

    @Override
    public void actualizarTemperatura(int nuevaTemperatura) {
        System.out.println("🖥️ [Panel de Control] Registra cambio a " + nuevaTemperatura + "°C");
        // Delegamos la decisión a la Cadena de Responsabilidad
        manejadorBase.manejarTemperatura(nuevaTemperatura);
    }

    public static void main(String[] args) {
        // 1. Configurar la Cadena de Responsabilidad
        ManejadorPeligro nivel1 = new ManejadorNormal();
        ManejadorPeligro nivel2 = new ManejadorAlerta();
        ManejadorPeligro nivel3 = new ManejadorCritico();

        nivel1.setSiguiente(nivel2);
        nivel2.setSiguiente(nivel3);

        // 2. Crear el Observador (El Panel de Control)
        PanelDeControl panel = new Ejercicio4(nivel1);

        // 3. Crear el Sujeto Observable (El Reactor)
        Reactor reactor = new Reactor();
        reactor.agregarPanel(panel);

        // 4. Simular cambios
        reactor.setTemperatura(60);
        reactor.setTemperatura(300);
        reactor.setTemperatura(600);
    }
}
