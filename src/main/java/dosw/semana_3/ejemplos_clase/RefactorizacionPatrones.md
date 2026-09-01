# 🛠️ Guía de Refactorización: El "Olor" y la Solución

Esta guía te muestra exactamente cómo se ve un código "feo" (el olor) y cómo se refactoriza usando el patrón de diseño adecuado. Memoriza estas estructuras, porque son la clave para responder la pregunta: *"Dado este enunciado, refactorice..."*

---

## 1. 🍔 Decorator: "El Olor a if-else Gigante"

**El Problema:** Tienes una clase base (ej. Hamburguesa, Café, Personaje) y necesitas agregarle funcionalidades u opciones extra (queso, leche, armadura). Terminas con un método lleno de `if` o una explosión de subclases (`CafeConLeche`, `CafeConCrema`, `CafeConLecheYCrema`).

### ❌ ANTES (Código Feo)
```java
public class HamburguesaFea {
    private boolean conQueso;
    private boolean conTocineta;
    private boolean conPapas;

    public HamburguesaFea(boolean conQueso, boolean conTocineta, boolean conPapas) {
        this.conQueso = conQueso;
        this.conTocineta = conTocineta;
        this.conPapas = conPapas;
    }

    public double getPrecio() {
        double precio = 15000; // precio base
        if (conQueso) precio += 2000;
        if (conTocineta) precio += 3000;
        if (conPapas) precio += 4000;
        return precio;
    }

    public String getDescripcion() {
        String desc = "Hamburguesa";
        if (conQueso) desc += " + Queso";
        if (conTocineta) desc += " + Tocineta";
        if (conPapas) desc += " + Papas";
        return desc;
    }
}
```

### ✅ DESPUÉS (Patrón Decorator)
**La Solución:** Crear una interfaz común, una clase concreta base, y decoradores que "envuelven" a la interfaz.

```java
// 1. La Interfaz Común
public interface Hamburguesa {
    double getPrecio();
    String getDescripcion();
}

// 2. La Clase Base Concreta
public class HamburguesaBase implements Hamburguesa {
    public double getPrecio() { return 15000; }
    public String getDescripcion() { return "Hamburguesa"; }
}

// 3. El Decorador Abstracto (OPCIONAL pero recomendado)
public abstract class HamburguesaDecorator implements Hamburguesa {
    protected Hamburguesa hamburguesa; // Referencia a la interfaz

    public HamburguesaDecorator(Hamburguesa hamburguesa) {
        this.hamburguesa = hamburguesa;
    }
    // Delega los métodos por defecto
    public double getPrecio() { return hamburguesa.getPrecio(); }
    public String getDescripcion() { return hamburguesa.getDescripcion(); }
}

// 4. Decoradores Concretos
public class QuesoDecorator extends HamburguesaDecorator {
    public QuesoDecorator(Hamburguesa hamburguesa) { super(hamburguesa); }
    
    public double getPrecio() { return hamburguesa.getPrecio() + 2000; }
    public String getDescripcion() { return hamburguesa.getDescripcion() + " + Queso"; }
}

public class TocinetaDecorator extends HamburguesaDecorator {
    public TocinetaDecorator(Hamburguesa hamburguesa) { super(hamburguesa); }
    
    public double getPrecio() { return hamburguesa.getPrecio() + 3000; }
    public String getDescripcion() { return hamburguesa.getDescripcion() + " + Tocineta"; }
}

// 5. Uso
public class Main {
    public static void main(String[] args) {
        Hamburguesa miPedido = new HamburguesaBase();
        miPedido = new QuesoDecorator(miPedido);
        miPedido = new TocinetaDecorator(miPedido);
        
        System.out.println(miPedido.getDescripcion()); // Hamburguesa + Queso + Tocineta
        System.out.println("Total: $" + miPedido.getPrecio());
    }
}
```

---

## 2. 🏗️ Builder: "El Olor a Telescoping Constructor"

**El Problema:** Tienes una clase con demasiados parámetros en su constructor, muchos de los cuales son opcionales. Terminas creando múltiples constructores (telescoping) o pasando muchos `null` o `false` incomprensibles.

### ❌ ANTES (Código Feo)
```java
public class ComputadoraFea {
    private String cpu;
    private int ram;
    private int almacenamiento;
    private String gpu;      // Opcional
    private boolean wifi;    // Opcional
    private boolean bluetooth; // Opcional

    // ¡Qué desastre de constructor!
    public ComputadoraFea(String cpu, int ram, int almacenamiento, String gpu, boolean wifi, boolean bluetooth) {
        this.cpu = cpu;
        this.ram = ram;
        this.almacenamiento = almacenamiento;
        this.gpu = gpu;
        this.wifi = wifi;
        this.bluetooth = bluetooth;
    }
    
    // Uso: ¿Qué son todos esos true/false y nulls?
    // ComputadoraFea pc = new ComputadoraFea("i7", 16, 512, null, true, false);
}
```

### ✅ DESPUÉS (Patrón Builder)
**La Solución:** Ocultar el constructor y usar una clase estática interna `Builder` (o una clase separada) que configura el objeto paso a paso retornando `this`.

```java
public class Computadora {
    private String cpu;
    private int ram;
    private int almacenamiento;
    private String gpu;
    private boolean wifi;
    private boolean bluetooth;

    // 1. Constructor PRIVADO que recibe el Builder
    private Computadora(ComputadoraBuilder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.almacenamiento = builder.almacenamiento;
        this.gpu = builder.gpu;
        this.wifi = builder.wifi;
        this.bluetooth = builder.bluetooth;
    }

    // 2. La clase Builder
    public static class ComputadoraBuilder {
        // Obligatorios
        private String cpu;
        private int ram;
        private int almacenamiento;
        
        // Opcionales (con valores por defecto si se desea)
        private String gpu;
        private boolean wifi;
        private boolean bluetooth;

        // Constructor del Builder solo con los OBLIGATORIOS
        public ComputadoraBuilder(String cpu, int ram, int almacenamiento) {
            this.cpu = cpu;
            this.ram = ram;
            this.almacenamiento = almacenamiento;
        }

        // 3. Métodos "Setter" que retornan el mismo Builder (this)
        public ComputadoraBuilder conGpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public ComputadoraBuilder conWifi(boolean wifi) {
            this.wifi = wifi;
            return this;
        }

        public ComputadoraBuilder conBluetooth(boolean bluetooth) {
            this.bluetooth = bluetooth;
            return this;
        }

        // 4. El método build() que crea el objeto final
        public Computadora build() {
            return new Computadora(this);
        }
    }
}

// 5. Uso
public class Main {
    public static void main(String[] args) {
        // Claro, legible y escalable
        Computadora miPc = new Computadora.ComputadoraBuilder("i7", 16, 512)
                .conWifi(true)
                .conBluetooth(true)
                .build();
    }
}
```

---

## 3. 🔌 Adapter: "El Olor a Interfaces Incompatibles"

**El Problema:** Tu sistema actual espera trabajar con una interfaz específica, pero necesitas integrar una librería externa o un sistema heredado (legacy) que hace lo mismo pero con nombres de métodos y/o parámetros diferentes. **Y no puedes modificar el código externo.**

### ❌ ANTES (Código Incompatible)
```java
// Lo que tu sistema espera (Interfaz Objetivo)
public interface ProcesadorPagos {
    void pagar(double montoPesos);
}

// La API Externa que tienes que usar (NO PUEDES MODIFICAR ESTO)
public class StripeAPIUSA {
    public void chargeInDollars(double dollars) {
        System.out.println("Cobrando USD: " + dollars);
    }
}

// Tu clase actual que falla
public class Tienda {
    private ProcesadorPagos procesador;

    public Tienda(ProcesadorPagos procesador) {
        this.procesador = procesador;
    }

    public void checkout(double montoEnPesos) {
        // ERROR: procesador.pagar() es lo que Tienda necesita,
        // pero si le pasas un StripeAPIUSA, no compila.
        procesador.pagar(montoEnPesos); 
    }
}
```

### ✅ DESPUÉS (Patrón Adapter)
**La Solución:** Crear una clase "Adaptador" que implemente la interfaz que TU sistema espera, y por dentro, traduzca la llamada a la clase externa.

```java
// 1. El Adaptador IMPLEMENTA la interfaz que tu sistema espera
public class StripeAdapter implements ProcesadorPagos {
    
    // 2. COMPONE (tiene una instancia de) la clase externa
    private StripeAPIUSA stripe;
    
    public StripeAdapter(StripeAPIUSA stripe) {
        this.stripe = stripe;
    }

    // 3. Traduce la llamada
    @Override
    public void pagar(double montoPesos) {
        // Traducir parámetros (Ej: 1 USD = 4000 COP)
        double montoDolares = montoPesos / 4000.0;
        
        // Llamar al método de la API externa
        stripe.chargeInDollars(montoDolares);
    }
}

// 4. Uso
public class Main {
    public static void main(String[] args) {
        // Instancias la API externa
        StripeAPIUSA apiExterna = new StripeAPIUSA();
        
        // La "envuelves" en el Adaptador
        ProcesadorPagos adaptador = new StripeAdapter(apiExterna);
        
        // Tu sistema funciona feliz porque le diste la interfaz que quería
        Tienda miTienda = new Tienda(adaptador);
        miTienda.checkout(80000); // Imprimirá: "Cobrando USD: 20.0"
    }
}
```
