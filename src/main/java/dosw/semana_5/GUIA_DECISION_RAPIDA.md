<br>
<div align="center">
  <h1>------------- Guía de Decisión Rápida -------------</h1>
</div>
<br>

Si el enunciado dice **esto**... entonces usa **esto otro**. Tu brújula para el parcial.

---

### 🔍 ¿Cuándo usar Optional?

| Si el enunciado dice... | Usa |
|---|---|
| "Buscar un producto/usuario/elemento por ID" | `stream().filter(...).findFirst()` → devuelve `Optional` |
| "Si existe, hacer algo; si no, mostrar mensaje" | `.ifPresent()` o `.orElse()` |
| "Obtener el primero que cumpla X condición" | `.findFirst()` → `Optional` |
| "Si no hay resultado, usar un valor por defecto" | `.orElse(valorDefault)` |
| "Si no hay resultado, lanzar error" | `.orElseThrow(() -> new RuntimeException(...))` |
| "Encontrar el máximo / mínimo" | `.max()` / `.min()` → devuelven `Optional` |
| "Calcular el promedio" | `.average()` → devuelve `OptionalDouble` |
| "Puede ser null" / "puede no existir" | `Optional.ofNullable(valor)` |

---

### 🌊 ¿Cuándo usar cada operación de Stream?

| Si el enunciado dice... | Usa |
|---|---|
| "Filtrar / extraer solo los que cumplan..." | `.filter(condición)` |
| "Filtrar con dos o más condiciones" | `.filter(c1 && c2)` o `.filter(c1).filter(c2)` |
| "Obtener solo los nombres / solo un campo" | `.map(Persona::nombre)` |
| "Convertir a mayúsculas / transformar" | `.map(String::toUpperCase)` |
| "Convertir Strings a números" | `.map(Integer::parseInt)` |
| "Ordenar alfabéticamente" | `.sorted()` o `.sorted(Comparator.comparing(...))` |
| "Ordenar de mayor a menor" | `.sorted(Comparator.comparingX(...).reversed())` |
| "Ordenar por X, y si empatan por Y" | `.sorted(Comparator.comparing(X).thenComparing(Y))` |
| "Eliminar duplicados / valores únicos" | `.distinct()` |
| "¿Cuántos hay?" / "Contar" | `.count()` |
| "Suma total / Sumar salarios" | `.mapToDouble(...).sum()` o `.reduce(0, Double::sum)` |
| "Promedio" | `.mapToDouble(...).average()` |
| "Agrupar por categoría / departamento / ciudad" | `Collectors.groupingBy(...)` |
| "Agrupar y contar cuántos hay en cada grupo" | `Collectors.groupingBy(..., Collectors.counting())` |
| "Agrupar y listar solo los nombres de cada grupo" | `Collectors.groupingBy(..., Collectors.mapping(...))` |
| "Separar en dos grupos (sí/no, aprobados/reprobados)" | `Collectors.partitioningBy(...)` |
| "Los 3 primeros / Top N" | `.sorted(...).limit(N)` |
| "Saltar los primeros N" / "Página 2" | `.skip(N).limit(N)` |
| "Unir nombres con coma" / "como texto" | `Collectors.joining(", ")` |
| "Crear un mapa clave → valor" | `Collectors.toMap(clave, valor)` |
| "¿Hay alguno que cumpla X?" | `.anyMatch(condición)` |
| "¿Todos cumplen X?" | `.allMatch(condición)` |
| "¿Ninguno cumple X?" | `.noneMatch(condición)` |
| "Estadísticas completas (min, max, avg, sum, count)" | `Collectors.summarizingDouble(...)` |
| "Aplanar listas dentro de listas" | `.flatMap(x -> x.getLista().stream())` |
| "Ejecutar algo por cada elemento" | `.forEach(acción)` |

---

### 🏗️ ¿Cuándo usar cada Patrón de Diseño?

#### Patrones Creacionales (Crear objetos)

| Si el enunciado dice... | Patrón |
|---|---|
| "Constructor con muchos parámetros" / "telescoping constructor" | **Builder** |
| "Configuración paso a paso" / "objeto inmutable complejo" | **Builder** |
| "Crear objetos sin especificar la clase concreta" | **Factory Method** |
| "Generar un reporte en PDF, Excel o CSV según configuración" | **Factory Method** |
| "Familias de objetos relacionados" / "depende de la región/país" | **Abstract Factory** |
| "Pasarelas de pago según el país (Colombia vs USA)" | **Abstract Factory** |
| "Solo una instancia en toda la aplicación" / "configuración global" | **Singleton** |
| "Clonar / copiar un objeto existente como plantilla" | **Prototype** |

#### Patrones Estructurales (Organizar clases)

| Si el enunciado dice... | Patrón |
|---|---|
| "Interfaz incompatible" / "API externa con formato diferente" | **Adapter** |
| "Convertir centímetros a pulgadas" / "dólares a pesos" | **Adapter** |
| "No podemos modificar el código del proveedor" | **Adapter** |
| "Agregar funcionalidad dinámicamente" / "extras opcionales" | **Decorator** |
| "If-else para agregar toppings / extras / potenciadores" | **Decorator** |
| "Envolver un objeto con capas adicionales" | **Decorator** |
| "Simplificar un sistema complejo" / "ocultar pasos internos" | **Facade** |
| "Un solo método que haga todo por detrás" | **Facade** |
| "Dos dimensiones que varían independientemente" | **Bridge** |
| "Tipo de mensaje × Algoritmo de compresión" | **Bridge** |
| "Estructura de árbol" / "jerarquía parte-todo" | **Composite** |
| "Control de acceso" / "carga perezosa" / "proxy de seguridad" | **Proxy** |
| "Muchos objetos similares → optimizar memoria" | **Flyweight** |

#### Patrones de Comportamiento (Comunicación entre objetos)

| Si el enunciado dice... | Patrón |
|---|---|
| "Algoritmos intercambiables" / "elegir forma de pago/envío/descuento" | **Strategy** |
| "El usuario elige entre varias formas de hacer lo mismo" | **Strategy** |
| "Notificar a múltiples sistemas cuando algo cambie" | **Observer** |
| "Cuando un pedido cambia de estado, avisar a X, Y, Z" | **Observer** |
| "Suscriptores / listeners / eventos" | **Observer** |
| "Cadena de validaciones" / "pasa por múltiples filtros" | **Chain of Responsibility** |
| "Si este aprueba, pasa al siguiente; si no, se detiene" | **Chain of Responsibility** |
| "El objeto cambia su comportamiento según su estado" | **State** |
| "Borrador → En Revisión → Aprobado → Rechazado" | **State** |
| "Deshacer / rehacer operaciones" / "historial de acciones" | **Command** |
| "Encapsular acciones como objetos" | **Command** |
| "Guardar y restaurar estado anterior" / "checkpoint" | **Memento** |
| "Ctrl+Z en un editor" | **Memento** |
| "Recorrer una colección personalizada sin exponer su estructura" | **Iterator** |
| "Esqueleto de algoritmo con pasos que cambian en subclases" | **Template Method** |
| "Evitar que los objetos se comuniquen directamente entre sí" | **Mediator** |
| "Chat grupal / torre de control" | **Mediator** |

---

### 📐 ¿Cuándo combinar patrones?

| Situación | Combinación |
|---|---|
| "Construir objeto complejo + notificar cuando se confirme" | **Builder + Observer** |
| "Elegir algoritmo según país + crear con fábrica" | **Strategy + Abstract Factory** |
| "Validar en cadena + cambiar estado del documento" | **Chain of Responsibility + State** |
| "Construir personaje + agregarle poderes dinámicamente" | **Builder + Decorator** |
| "Elegir algoritmo + actualizar UI cuando cambie" | **Strategy + Observer** |
| "Integrar API externa + simplificar su uso" | **Adapter + Facade** |

---

### 🎯 ¿Cuándo usar Scanner?

| Si el enunciado dice... | Método de Scanner |
|---|---|
| "Pedir nombre / texto con espacios" | `sc.nextLine()` |
| "Pedir un número entero" | `sc.nextInt()` + `sc.nextLine()` ← limpiar buffer |
| "Pedir un número decimal" | `sc.nextDouble()` + `sc.nextLine()` ← limpiar buffer |
| "Pedir solo una palabra (sin espacios)" | `sc.next()` |
| "Menú interactivo" / "elegir opción" | `while + switch + nextInt()` |
| "Leer datos hasta que escriba 'fin'" | `while(true) + break si equals("fin")` |
| "Validar que lo que ingresó sea un número" | `sc.hasNextInt()` en un `do-while` |

---

### 🔗 ¿Cuándo usar Referencias a Métodos (::)?

| Lambda | Referencia equivalente | Tipo |
|---|---|---|
| `x -> System.out.println(x)` | `System.out::println` | Instancia |
| `p -> p.nombre()` | `Persona::nombre` | Del tipo |
| `s -> s.toUpperCase()` | `String::toUpperCase` | Del tipo |
| `s -> Integer.parseInt(s)` | `Integer::parseInt` | Estático |
| `n -> new Persona(n)` | `Persona::new` | Constructor |
| `(a, b) -> a.compareTo(b)` | `String::compareTo` | Del tipo |
