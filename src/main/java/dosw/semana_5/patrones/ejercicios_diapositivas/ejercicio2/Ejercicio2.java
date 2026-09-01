package dosw.semana_5.patrones.extra.ejercicio2;

public class Ejercicio2 {
    // TODO: Crear Enum ClaseVuelo { ECONOMICA, EJECUTIVA, PRIMERA_CLASE }

    public enum ClaseVuelo {
        ECONOMICA {
            @Override
            public CalculadorPrecio getCalculador() {
                return new CalculadorEconomica();
            }},
        EJECUTIVA{
            @Override
            public CalculadorPrecio getCalculador() {
                return new CalculadorEjecutiva();
            }},
        PRIMERA_CLASE{
            @Override
            public CalculadorPrecio getCalculador() {
                return new CalculadorPrimeraClase();
            }
        };
        public abstract CalculadorPrecio getCalculador();
    }

    // TODO: Crear Interfaz CalculadorPrecio (Strategy) con 3 implementaciones

    // TODO: Crear clase Tiquete con patrón Builder (nombre, edad, pesoEquipaje, claseVuelo, precioFinal)

    // TODO: Crear el main con el Scanner interactivo que pida los datos, aplique la estrategia, construya el tiquete e imprima el resumen.
}
