# 🚀 Preparcial DOSW — Nivel Avanzado (Modo Hard)

**Nombre:** ___________________________
**Tiempo estimado:** 120 minutos
**Herramientas permitidas:** Bitácora DOSW, IDE, Guía de Decisión

Este examen está diseñado para poner a prueba tu capacidad de **combinar conceptos**. En el mundo real, los patrones y herramientas (Streams, Optionals) rara vez se usan aislados; funcionan juntos como un engranaje.

---

## 💻 Ejercicio 1 — Análisis Forense Bancario (Streams + Optional + Referencias)

**Enunciado:**
Eres analista de seguridad en un banco. Tienes una lista masiva de `Transaccion` financieras sospechosas. Debes procesarlas usando **exclusivamente Streams, Optional y Referencias a Métodos (::)**. No se permiten bucles `for` ni `if` tradicionales.

Dada la lista de transacciones, debes:
1. Filtrar las transacciones que fueron marcadas como `esInternacional == true` **Y** cuyo monto sea **mayor a $5,000,000**.
2. De esas transacciones, obtener **una lista de los correos únicos** de los clientes (para no enviar spam repetido).
3. Encontrar la transacción con el **monto más alto**. Si existe, imprimir "Alerta Máxima: [email del cliente] movió [monto]". Si no existe, imprimir "Sistema seguro".
4. Agrupar la suma total de dinero movido por cada país de destino (`Map<String, Double>`).

```java
import java.util.*;
import java.util.stream.Collectors;

public class Ejercicio1Avanzado {
    record Transaccion(String emailCliente, double monto, String paisDestino, boolean esInternacional) {}

    public static void main(String[] args) {
        List<Transaccion> txs = List.of(
            new Transaccion("hacker@dark.net", 8_500_000, "Rusia", true),
            new Transaccion("abuela@gmail.com", 200_000, "Colombia", false),
            new Transaccion("ceo@empresa.com", 12_000_000, "Suiza", true),
            new Transaccion("hacker@dark.net", 6_000_000, "Rusia", true), // reincidente
            new Transaccion("oscar@dosw.com", 4_500_000, "USA", true)
        );

        // TODO: 1. Filtrar internacionales mayores a 5M
        
        // TODO: 2. Lista de correos únicos (sin duplicados)
        
        // TODO: 3. Optional: Transacción más alta y mensaje dinámico (usa ifPresentOrElse o isPresent)
        
        // TODO: 4. Map<País, SumaTotalMonto> (Pista: Collectors.groupingBy y Collectors.summingDouble)
    }
}
```

---

## ✈️ Ejercicio 2 — Sistema de Reservas Interactivo (Scanner + Builder + Strategy + Enum)

**Enunciado:**
Debes crear un sistema de reservas de vuelos interactivo por consola. 

1. **Scanner:** Pide al usuario su nombre, edad, peso de equipaje (en kg) y que elija su clase de vuelo (ECONOMICA, EJECUTIVA, PRIMERA_CLASE). ¡Cuidado con el buffer `\n`!
2. **Strategy:** El cálculo del precio base ($1,000,000) depende de la clase elegida (ECONOMICA = x1, EJECUTIVA = x2, PRIMERA_CLASE = x3). 
3. **Builder:** Usa un constructor encadenable para armar el objeto `Tiquete`.

**Regla estricta:** Si el usuario tiene menos de 18 años, el sistema lanza un mensaje de error y termina. Si pesa más de 23kg de equipaje, se le cobra una penalidad del 10% adicional al final.

```java
public class Ejercicio2Avanzado {
    // TODO: Crear Enum ClaseVuelo { ECONOMICA, EJECUTIVA, PRIMERA_CLASE }
    
    // TODO: Crear Interfaz CalculadorPrecio (Strategy) con 3 implementaciones
    
    // TODO: Crear clase Tiquete con patrón Builder (nombre, edad, pesoEquipaje, claseVuelo, precioFinal)
    
    // TODO: Crear el main con el Scanner interactivo que pida los datos, aplique la estrategia, construya el tiquete e imprima el resumen.
}
```

---

## 🍝 Ejercicio 3 — Refactorización Nivel Dios (Decorator + Adapter)

**Enunciado:**
Llegas a una startup y te encuentras este código espagueti inmanejable para su sistema de notificaciones. Además, compraron un nuevo proveedor de notificaciones por WhatsApp externo (`WhatsAppUltraAPI`), pero no es compatible con el código actual.

**Refactoriza este desastre usando:**
1. **Decorator:** Para poder enviar cualquier combinación (Ej: `Email + SMS`, o `Email + WhatsApp + SMS`) dinámicamente sin subclases ni ifs.
2. **Adapter:** Para integrar la clase externa `WhatsAppUltraAPI` a tu interfaz de notificaciones.

**Código Feo a destruir:**
```java
// Lo que usan actualmente
class NotificadorSpaguetti {
    public void notificar(String msg, boolean usarEmail, boolean usarSms, boolean usarWhatsapp) {
        if (usarEmail) System.out.println("Enviando Email: " + msg);
        if (usarSms) System.out.println("Enviando SMS: " + msg);
        if (usarWhatsapp) {
            // PROBLEMA: Aquí metieron la API externa a la fuerza
            WhatsAppUltraAPI wp = new WhatsAppUltraAPI();
            wp.sendSecureMessage("+573000000", msg);
        }
    }
}

// API EXTERNA COMPRADA (No puedes modificar este código)
class WhatsAppUltraAPI {
    public void sendSecureMessage(String phone, String text) {
        System.out.println("📱 [API WhatsApp] Enviando cifrado a " + phone + ": " + text);
    }
}
```

**Tu misión:**
Diseña una interfaz `Notificador` (con método `enviar(msg)`). Crea `NotificadorBase` (Email). Usa **Decorator** para agregar SMS dinámicamente. Usa **Adapter** que implemente `Notificador` pero llame por dentro a `WhatsAppUltraAPI`.

---

## ☢️ Ejercicio 4 — El Reactor Nuclear (Observer + Chain of Responsibility)

**Enunciado:**
Vas a simular el sistema de seguridad de un reactor nuclear.
1. Tienes un objeto `Reactor` que tiene un estado de temperatura (int). 
2. Cuando la temperatura del `Reactor` cambia, debe **notificar (Observer)** a un `PanelDeControl`.
3. El `PanelDeControl` recibe la nueva temperatura y la pasa por una **Cadena de Responsabilidad (Chain of Responsibility)** para evaluar el peligro:
   - **Nivel 1 (<= 100°C):** `ManejadorNormal` imprime "Todo en orden".
   - **Nivel 2 (101°C - 500°C):** `ManejadorAlerta` imprime "Activando bombas de enfriamiento secundarias".
   - **Nivel 3 (> 500°C):** `ManejadorCritico` imprime "¡PELIGRO! Apagando reactor e iniciando evacuación".

**Simulación:**
En tu `main`, configura la cadena, adjunta el observador al reactor, y simula el cambio de temperatura: `60°C` -> `300°C` -> `600°C`. Todo el sistema debe reaccionar automáticamente en cascada.

---

## 🏰 Ejercicio 5 — Masterclass Arquitectónica (Singleton + Factory Method + Facade)

**Enunciado:**
Se requiere un sistema para que los desarrolladores juniors de la empresa interactúen con la base de datos sin romper nada. Debes integrar **3 patrones creacionales/estructurales**:

1. **Singleton:** Crea una clase `GestorConexion` estricta que garantice que solo exista UNA conexión abierta a la base de datos en toda la aplicación.
2. **Factory Method:** Crea una interfaz `Repositorio` y una fábrica que genere repósitos según el tipo de dato que se va a guardar (ej. `FabricaRepositorios.crear("USUARIOS")` retorna un `UsuarioRepository`, `FabricaRepositorios.crear("FACTURAS")` retorna un `FacturaRepository`).
3. **Facade:** Crea una clase `JuniorDatabaseFacade`. Esta fachada debe tener un único método muy simple: `guardarDato(String tipo, String json)`. Por debajo, esta fachada debe:
   - Obtener la instancia del `GestorConexion` (Singleton).
   - Pedirle a la fábrica el repositorio adecuado según el `tipo`.
   - Llamar al método guardar del repositorio.
   - Ocultar toda esta complejidad al desarrollador junior.

En el `main`, un junior solo debería hacer:
`JuniorDatabaseFacade fachada = new JuniorDatabaseFacade();`
`fachada.guardarDato("USUARIOS", "{nombre: 'Oscar'}");`
