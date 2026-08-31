# Bitácora DOSW - Oscar Lasso

Este documento sirve como guía rápida para encontrar los patrones de diseño y operaciones de streams aplicados en los ejercicios.

---


<br>
<div align="center">
  <h1>------------- Semana 1: Streams -------------</h1>
</div>
<br>



### Streams

#### Extra

- **Ejercicio1: [Ejercicio1.java](src/main/java/dosw/semana_1/streams/extra/Ejercicio1.java)**
  - **Patrón:** FILTRADO MÚLTIPLE O COMPUESTO
  - **Uso / Definición:** "Dada una lista, extraer los elementos que deban cumplir con DOS O MÁS condiciones simultáneamente".
  - **Estructura / Ventajas:**
    - Puedes aplicarlo de dos formas:
    - 1. Encadenado: .filter(condición1).filter(condición2) -> Ideal si las reglas son largas o complejas.
    - 2. Lógico (&&): .filter(condición1 && condición2) -> Ideal para reglas cortas y matemáticas.
    - 3. .toList() -> Cierra el proceso empacando en una lista (atajo moderno para collect(Collectors.toList())).

- **Ejercicio2: [Ejercicio2.java](src/main/java/dosw/semana_1/streams/extra/Ejercicio2.java)**
  - **Patrón:** PIPELINE COMPLETO Y CONTEO DE ELEMENTOS (COUNT)
  - **Uso / Definición:** "Dada una lista, aplica múltiples operaciones en cadena (filtrar, transformar, ordenar) y al final cuenta cuántos elementos sobrevivieron".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .filter() -> Filtra (ej. longitud > 4).
    - 3. .map() -> Transforma (ej. a mayúsculas).
    - 4. .sorted() -> Ordena (alfabéticamente por defecto).
    - 5. .count() -> Operación terminal. Cierra el stream y devuelve un 'long' con la cantidad.
    - * Extra: .peek() -> Sirve para espiar/imprimir los datos justo antes de contarlos.

- **Ejercicio3: [Ejercicio3.java](src/main/java/dosw/semana_1/streams/extra/Ejercicio3.java)**
  - **Patrón:** EXTRACCIÓN Y TRANSFORMACIÓN DE ATRIBUTOS (PROYECCIÓN)
  - **Uso / Definición:** "Dada una lista de objetos (ej. Usuarios), filtrar por una condición, extraer un atributo específico de ese objeto (ej. el nombre), transformarlo y ordenar los resultados".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .filter() -> Selecciona los objetos que cumplen la regla. Se puede usar 'Referencia de Método' (Clase::metodoBooleano).
    - 3. .map() -> Convierte el objeto completo en solo una parte de él (ej. pasar de User a String).
    - - Opción A: Encadenar múltiples .map() separando pasos (uno para extraer, otro para mayúsculas).
    - - Opción B: Un solo .map() con una función lambda que hace todo el trabajo a la vez.
    - 4. .sorted() -> Ordena alfabéticamente (ya que ahora el flujo contiene Strings).
    - 5. .toList() -> Empaca los Strings resultantes en una nueva lista.

- **Ejercicio4: [Ejercicio4.java](src/main/java/dosw/semana_1/streams/extra/Ejercicio4.java)**
  - **Patrón:** FILTRADO POR CONDICIÓN NUMÉRICA Y EXTRACCIÓN
  - **Uso / Definición:** "Dada una lista de objetos complejos, descarta los que no cumplan una regla matemática (ej. mayor a X, menor a Y) en uno de sus atributos, y luego quédate solo con el nombre o dato que te interesa de los que pasaron".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista de objetos.
    - 2. .filter( u -> u.age() >= 18 ) -> Aplica la condición numérica usando el getter del atributo.
    - 3. .map() -> Extrae el atributo final que necesitas.
    - - Opción A: Referencia de método (Clase::metodo).
    - - Opción B: Expresión Lambda (u -> u.metodo()).
    - 4. .toList() -> Empaca los resultados extraídos en una lista.

- **Ejercicio5: [Ejercicio5.java](src/main/java/dosw/semana_1/streams/extra/Ejercicio5.java)**
  - **Patrón:** DEPURACIÓN Y COMPROBACIÓN (PEEK + CORTOCIRCUITO)
  - **Uso / Definición:** "Dada una lista, procesa los elementos imprimiéndolos para ver qué pasa (debug) y verifica si AL MENOS UNO cumple una condición. Útil para validar lotes de datos enteros".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .peek(System.out::println) -> Imprime el elemento tal cual va pasando por la cinta.
    - 3. .anyMatch( t -> condición ) -> Revisa la regla. OJO: Tiene "cortocircuito", lo que
    - significa que apenas encuentre un 'true' (ej. la primera transacción denegada),
    - detiene el Stream completo y no procesa los elementos restantes.

#### Taller

- **Ejercicio1: [Ejercicio1.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio1.java)**
  - **Patrón:** FILTRADO Y CREACIÓN DE NUEVA LISTA
  - **Uso / Definición:** "Dada una lista, saca los elementos que cumplan X condición y guárdalos".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista para empezar a procesar elemento por elemento.
    - 2. .filter( e -> condición ) -> Tu condición 'if'. Solo pasan los que den true.
    - 3. .collect(Collectors.toList()) -> Cierra el proceso empacando los que pasaron en una nueva List.

- **Ejercicio2: [Ejercicio2.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio2.java)**
  - **Patrón:** RECORRIDO Y EJECUCIÓN DE ACCIÓN (ITERACIÓN)
  - **Uso / Definición:** "Dada una lista, recorre todos los elementos y ejecuta una acción (como imprimir en consola) por cada uno de ellos, sin crear una nueva lista".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista para empezar a procesar elemento por elemento.
    - 2. .forEach( e -> acción ) -> Tu ciclo 'for'. Ejecuta el bloque de código indicado para cada elemento. Cierra el proceso y no devuelve nada.

- **Ejercicio3: [Ejercicio3.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio3.java)**
  - **Patrón:** TRANSFORMACIÓN DE ELEMENTOS (MAPEO)
  - **Uso / Definición:** "Dada una lista, aplica un cambio, cálculo o conversión a cada elemento y guarda los resultados transformados en una lista nueva".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista para empezar a procesar.
    - 2. .map( transformacion ) -> Reemplaza cada elemento por su nueva versión.
    - 3. .collect(Collectors.toList()) -> Empaca los elementos ya transformados.

- **Ejercicio4: [Ejercicio4.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio4.java)**
  - **Patrón:** ACUMULACIÓN / REDUCCIÓN A UN ÚNICO VALOR
  - **Uso / Definición:** "Dada una lista de números o valores, combínalos todos (sumando, multiplicando, etc.) para obtener un único resultado final".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .reduce( valorInicial, operación ) -> Acumula los valores empezando desde
    - el 'valorInicial' (ej. 0 para sumas) aplicando la operación dada. Cierra el proceso.

- **Ejercicio5: [Ejercicio5.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio5.java)**
  - **Patrón:** ELIMINACIÓN DE DUPLICADOS EN UNA COLECCIÓN ESPECÍFICA (SET)
  - **Uso / Definición:** "Dada una lista con elementos repetidos, guárdalos en una colección que no permita duplicados, pero que mantenga el orden de inserción".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .collect(Collectors.toCollection(Coleccion::new)) -> Empaca el resultado
    - forzando que el contenedor sea del tipo exacto que necesitas (ej. LinkedHashSet).

- **Ejercicio6: [Ejercicio6.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio6.java)**
  - **Patrón:** DEPURACIÓN / ESPIAR EL FLUJO SIN ALTERARLO
  - **Uso / Definición:** "Dada una lista, transfórmala o fíltrala, pero imprime o ejecuta algo en medio del proceso para ver qué está pasando paso a paso".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .map() / .filter() -> Tus operaciones normales.
    - 3. .peek( e -> acción ) -> Espía el elemento en ese punto e imprime,
    - pero lo deja seguir su camino sin modificarlo.
    - 4. .collect(...) -> Empaca el resultado.

- **Ejercicio7: [Ejercicio7.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio7.java)**
  - **Patrón:** ORDENAMIENTO DE LISTAS
  - **Uso / Definición:** "Dada una lista, organizar sus elementos de menor a mayor (ascendente) o de mayor a menor (descendente) y guardarlos en una lista nueva".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .sorted() -> Ordena de menor a mayor por defecto.
    - .sorted(Comparator.reverseOrder()) -> Ordena de mayor a menor.
    - 3. .collect(Collectors.toList()) -> Empaca la lista ya ordenada.

- **Ejercicio8: [Ejercicio8.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio8.java)**
  - **Patrón:** ELIMINACIÓN RÁPIDA DE DUPLICADOS (DISTINCT)
  - **Uso / Definición:** "Dada una lista, filtrar de manera directa para que no pase ningún elemento repetido a la nueva lista".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .distinct() -> Bloquea automáticamente cualquier elemento que ya haya pasado.
    - 3. .collect(Collectors.toList()) -> Empaca los únicos en una List.

- **Ejercicio9: [Ejercicio9.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio9.java)**
  - **Patrón:** RECORTAR / LIMITAR LA LISTA (TOP N)
  - **Uso / Definición:** "Dada una lista, quédate únicamente con los primeros N elementos y descarta absolutamente todo lo demás".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .limit(N) -> Corta el flujo, solo deja pasar los primeros N elementos.
    - 3. .collect(Collectors.toList()) -> Empaca ese top en una nueva lista.

- **Ejercicio10: [Ejercicio10.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio10.java)**
  - **Patrón:** OMITIR / SALTAR ELEMENTOS (PAGINACIÓN)
  - **Uso / Definición:** "Dada una lista, ignora los primeros N elementos y empieza a procesar o guardar únicamente del resto en adelante".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .skip(N) -> Descarta los primeros N elementos ciegamente.
    - 3. .collect(Collectors.toList()) -> Empaca lo que sobró.

- **Ejercicio11: [Ejercicio11.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio11.java)**
  - **Patrón:** BÚSQUEDA DEL VALOR MÍNIMO
  - **Uso / Definición:** "Dada una lista, encontrar el elemento más pequeño (el menor número, la fecha más antigua, etc.)".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .min(Comparator.naturalOrder()) -> Encuentra el mínimo devolviendo un 'Optional'
    - (una caja que puede contener el valor o estar vacía si la lista original lo estaba). Cierra el proceso.

- **Ejercicio12: [Ejercicio12.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio12.java)**
  - **Patrón:** BÚSQUEDA DEL VALOR MÁXIMO
  - **Uso / Definición:** "Dada una lista, encontrar el elemento más grande (el mayor número, el sueldo más alto, etc.)".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .max(Comparator.naturalOrder()) -> Encuentra el máximo devolviendo un 'Optional'.
    - Cierra el proceso.

- **Ejercicio13: [Ejercicio13.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio13.java)**
  - **Patrón:** COMPROBACIÓN DE EXISTENCIA (¿HAY ALGUNO?)
  - **Uso / Definición:** "Dada una lista, saber si AL MENOS UN elemento cumple con una condición. Te responde con un booleano (true/false) inmediato".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .anyMatch( e -> condición ) -> Devuelve true en el instante que encuentra el primero.
    - Cierra el proceso.

- **Ejercicio14: [Ejercicio14.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio14.java)**
  - **Patrón:** COMPROBACIÓN TOTAL (¿TODOS CUMPLEN?)
  - **Uso / Definición:** "Dada una lista, confirmar obligatoriamente que TODOS y cada uno de los elementos cumplen con una regla. Si uno solo falla, devuelve false".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .allMatch( e -> condición ) -> Revisa a todos. Si todos dan true, devuelve true.
    - Cierra el proceso.

- **Ejercicio15: [Ejercicio15.java](src/main/java/dosw/semana_1/streams/taller/Ejercicio15.java)**
  - **Patrón:** COMPROBACIÓN NEGATIVA (¿NINGUNO CUMPLE?)
  - **Uso / Definición:** "Dada una lista, garantizar que NINGÚN elemento cumpla con una condición (asegurarse de que la lista esté 'limpia' de algo)".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .noneMatch( e -> condición ) -> Devuelve true SÓLO si ningún elemento coincide.
    - Cierra el proceso.


<br>
<div align="center">
  <h1>------------- Semana 2: Pokémon (Streams Avanzados) -------------</h1>
</div>
<br>



### Pokemon

#### Alto Mando N4

- **Ejercicio15: [Ejercicio15.java](src/main/java/dosw/semana_2/pokemon/alto_mando_N4/Ejercicio15.java)**
  - **Patrón:** BÚSQUEDA DEL VALOR MÁXIMO EN OBJETOS (MAX)
  - **Uso / Definición:** "Dada una lista de objetos complejos, buscar y extraer el objeto completo que posea el valor más alto en un atributo numérico específico (en este caso, la cantidad de medallas)".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista de objetos.
    - 2. .max(Comparator.comparingInt( Entrenador::getMedallas )) -> Busca el máximo
    - evaluando el atributo entero. Devuelve un 'Optional' (caja de seguridad). Cierra el proceso.
    - 3. .get() -> Extrae el objeto ganador de la caja para acceder a sus datos.

- **Entrenador: [Entrenador.java](src/main/java/dosw/semana_2/pokemon/alto_mando_N4/Entrenador.java)**

- **Pokemon: [Pokemon.java](src/main/java/dosw/semana_2/pokemon/alto_mando_N4/Pokemon.java)**

#### Entrenador Intermedio N2

- **Ejercicio6: [Ejercicio6.java](src/main/java/dosw/semana_2/pokemon/entrenador_intermedio_N2/Ejercicio6.java)**
  - **Patrón:** ELIMINACIÓN RÁPIDA DE DUPLICADOS (DISTINCT)
  - **Uso / Definición:** "Dada una lista con elementos repetidos, limpiar la colección filtrando automáticamente cualquier elemento que ya exista, para que quede solo una copia".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .distinct() -> Identifica y bloquea los elementos duplicados (usa el método equals() por debajo).
    - 3. .toList() -> Empaca los elementos únicos resultantes en una nueva lista.

- **Ejercicio7: [Ejercicio7.java](src/main/java/dosw/semana_2/pokemon/entrenador_intermedio_N2/Ejercicio7.java)**
  - **Patrón:** ORDENAMIENTO BÁSICO (SORTED)
  - **Uso / Definición:** "Dada una lista desordenada, organiza sus elementos según su orden natural (alfabéticamente para textos, de menor a mayor para números)".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .sorted() -> Ordena los elementos por defecto. Si necesitas un orden
    - inverso o por un atributo, aquí dentro usarías un 'Comparator'.
    - 3. .toList() -> Empaca los elementos ya ordenados en una nueva lista.

- **Ejercicio8: [Ejercicio8.java](src/main/java/dosw/semana_2/pokemon/entrenador_intermedio_N2/Ejercicio8.java)**
  - **Patrón:** FILTRADO POR CONDICIÓN BOOLEANA Y EXTRACCIÓN
  - **Uso / Definición:** "Dada una lista de objetos, descarta los que tengan un atributo booleano en falso, y luego extrae un dato específico (como el nombre) de los que sí cumplen (true)".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .filter( Pokemon::puedeEvolucionar ) -> Evalúa la condición. Al ser un booleano, no necesitas poner '== true'. Solo pasan los verdaderos.
    - 3. .map(Pokemon::nombre) -> Extrae el nombre de los que sobrevivieron al filtro.
    - 4. .toList() -> Empaca los nombres en una nueva lista.

#### Entrenador Novato N1

- **Ejercicio1: [Ejercicio1.java](src/main/java/dosw/semana_2/pokemon/entrenador_novato_N1/Ejercicio1.java)**
  - **Patrón:** FILTRADO Y EXTRACCIÓN (PROYECCIÓN)
  - **Uso / Definición:** "Dada una lista de objetos, quédate únicamente con los que cumplan una condición en particular (ej. un tipo específico) y luego extrae solo el atributo que te interesa (el nombre) para la respuesta final".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista de objetos.
    - 2. .filter( p -> p.tipo().equals("Fuego") ) -> Filtra evaluando el atributo.
    - 3. .map(Pokemon::nombre) -> Extrae el nombre de los que pasaron el filtro.
    - 4. .toList() -> Empaca los nombres en una lista.

- **Ejercicio2: [Ejercicio2.java](src/main/java/dosw/semana_2/pokemon/entrenador_novato_N1/Ejercicio2.java)**
  - **Patrón:** TRANSFORMACIÓN BÁSICA (MAPEO)
  - **Uso / Definición:** "Dada una lista de elementos (ej. textos), aplica un cambio uniforme a todos y cada uno de ellos (como convertirlos a mayúsculas) y guarda el resultado".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista para empezar a procesar.
    - 2. .map() -> Ejecuta la transformación. Puedes usar la referencia de
    - método (String::toUpperCase) para que el código quede más limpio.
    - 3. .toList() -> Empaca los textos ya transformados en una nueva lista.

- **Ejercicio3: [Ejercicio3.java](src/main/java/dosw/semana_2/pokemon/entrenador_novato_N1/Ejercicio3.java)**
  - **Patrón:** ACUMULACIÓN / REDUCCIÓN A UN ÚNICO VALOR
  - **Uso / Definición:** "Dada una lista de números, combinarlos todos consecutivamente para obtener un único resultado final (como sumar el nivel de todo un equipo)".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista de números.
    - 2. .reduce( valorInicial, operación ) -> Toma un valor inicial (0 para sumas)
    - y una operación (Integer::sum) para ir acumulando cada número de la lista
    - hasta devolver un único entero. Cierra el proceso.

- **Ejercicio4: [Ejercicio4.java](src/main/java/dosw/semana_2/pokemon/entrenador_novato_N1/Ejercicio4.java)**
  - **Patrón:** BÚSQUEDA DEL ELEMENTO MÁXIMO (EN OBJETOS)
  - **Uso / Definición:** "Dada una lista de objetos, encontrar aquel que tenga el valor más alto o grande basándose en uno de sus atributos (ej. el nivel)".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista de objetos.
    - 2. .max(Comparator.comparingInt( Clase::metodo )) -> Busca el máximo usando
    - un comparador enfocado en el atributo numérico que te interesa.
    - Devuelve un 'Optional' (una caja). Cierra el proceso.
    - 3. .get() -> Extrae el objeto ganador de la caja Optional.

- **Ejercicio5: [Ejercicio5.java](src/main/java/dosw/semana_2/pokemon/entrenador_novato_N1/Ejercicio5.java)**
  - **Patrón:** CONTEO CONDICIONAL (FILTER + COUNT)
  - **Uso / Definición:** "Dada una lista, saber CUÁNTOS elementos cumplen con una condición específica, obteniendo directamente el número total en lugar de una lista".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista de objetos.
    - 2. .filter( p -> p.nivel() > 80 ) -> Deja pasar solo los elementos que cumplen la regla.
    - 3. .count() -> Cuenta cuántos elementos sobrevivieron al filtro.
    - Devuelve un dato de tipo 'long' y cierra el proceso.

#### Lider De Gimnasio N3

- **Ejercicio9: [Ejercicio9.java](src/main/java/dosw/semana_2/pokemon/lider_de_gimnasio_N3/Ejercicio9.java)**
  - **Patrón:** FILTRADO DE OBJETOS COMPLEJOS (FILTER)
  - **Uso / Definición:** "Dada una lista de objetos estructurados, evaluar un umbral en uno de sus atributos numéricos y extraer los elementos que lo superen, dándoles un formato visual específico".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista.
    - 2. .filter( p -> p.getPoderCombate() > 500 ) -> Filtra evaluando el atributo mediante su getter público.
    - 3. .map() -> Transforma el objeto en un texto con el formato "Nombre(PC)".
    - 4. .toList() -> Empaca los resultados en una nueva lista.

- **Ejercicio10: [Ejercicio10.java](src/main/java/dosw/semana_2/pokemon/lider_de_gimnasio_N3/Ejercicio10.java)**
  - **Patrón:** EXTRACCIÓN DE ATRIBUTO (MAP + COLLECT)
  - **Uso / Definición:** "Dada una lista de objetos complejos, extraer un solo dato o atributo de cada uno (como el nombre) para crear una lista más simple y manejable".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista de objetos.
    - 2. .map( Pokemon::getNombre ) -> Transforma el flujo, cambiando el objeto completo por únicamente su nombre en formato String.
    - 3. .collect(Collectors.toList()) -> Empaca los textos resultantes en una nueva lista (tal como sugiere el hint del ejercicio).

- **Ejercicio11: [Ejercicio11.java](src/main/java/dosw/semana_2/pokemon/lider_de_gimnasio_N3/Ejercicio11.java)**
  - **Patrón:** CÁLCULO DE PROMEDIOS CON STREAMS PRIMITIVOS
  - **Uso / Definición:** "Dada una lista de objetos, extraer un atributo numérico específico, transformar el flujo en un 'Stream Primitivo' (como DoubleStream) y calcular estadísticamente el promedio de todos los valores".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista de objetos.
    - 2. .mapToDouble( Pokemon::getPoderCombate ) -> Extrae el número y convierte el flujo general en uno especializado para matemáticas con decimales.
    - 3. .average() -> Calcula el promedio de todos los valores que pasaron. Devuelve un 'OptionalDouble' (una caja que previene errores si la lista estaba vacía).
    - 4. .getAsDouble() -> Extrae el número final de la caja.

- **Ejercicio12: [Ejercicio12.java](src/main/java/dosw/semana_2/pokemon/lider_de_gimnasio_N3/Ejercicio12.java)**
  - **Patrón:** BÚSQUEDA DEL VALOR MÁXIMO EN OBJETOS COMPLEJOS
  - **Uso / Definición:** "Dada una lista de objetos estructurados, buscar y extraer el objeto completo que posea el valor más alto en un atributo numérico específico".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista de objetos.
    - 2. .max(Comparator.comparingDouble( Pokemon::getPoderCombate )) -> Busca el máximo
    - evaluando el atributo decimal. Devuelve un 'Optional' (caja de seguridad). Cierra el proceso.
    - 3. .get() -> Extrae el objeto ganador de la caja para poder usar sus datos.

- **Ejercicio13: [Ejercicio13.java](src/main/java/dosw/semana_2/pokemon/lider_de_gimnasio_N3/Ejercicio13.java)**
  - **Patrón:** AGRUPACIÓN DE ELEMENTOS (GROUPINGBY)
  - **Uso / Definición:** "Dada una lista de objetos, organizarlos en subgrupos o categorías basándose en un atributo en común (como el tipo), creando un Diccionario o Mapa".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista de objetos.
    - 2. .collect(Collectors.groupingBy( llave, valor_opcional )) -> Cierra el proceso agrupando.
    - - 'llave' (Pokemon::getTipo): Es el atributo por el cual vas a agrupar.
    - - 'valor' (Collectors.mapping(...)): Se anida para extraer solo el nombre de los objetos y guardar textos en lugar de objetos completos.

- **Ejercicio14: [Ejercicio14.java](src/main/java/dosw/semana_2/pokemon/lider_de_gimnasio_N3/Ejercicio14.java)**
  - **Patrón:** AGRUPACIÓN DE ELEMENTOS (GROUPINGBY)
  - **Uso / Definición:** "Dada una lista de objetos, organizarlos en subgrupos o categorías basándose en un atributo en común (en este caso, la región), creando un Diccionario o Mapa".
  - **Estructura / Ventajas:**
    - 1. .stream() -> Abre la lista de objetos.
    - 2. .collect(Collectors.groupingBy( llave, valor_opcional )) -> Cierra el proceso agrupando.
    - - 'llave' (Pokemon::getRegion): Es el atributo por el cual vas a agrupar.
    - - 'valor' (Collectors.mapping(...)): Se anida para extraer solo el nombre y evitar guardar el objeto completo.

- **Entrenador: [Entrenador.java](src/main/java/dosw/semana_2/pokemon/lider_de_gimnasio_N3/Entrenador.java)**

- **Pokemon: [Pokemon.java](src/main/java/dosw/semana_2/pokemon/lider_de_gimnasio_N3/Pokemon.java)**


<br>
<div align="center">
  <h1>------------- Semana 3: Patrones de Diseño -------------</h1>
</div>
<br>



### Extra

#### Patrones

##### Comportamiento

###### Chain Of Responsibility

> **Chain Of Responsibility:** Permite pasar solicitudes a lo largo de una cadena de manejadores. Cada manejador decide si procesa la solicitud o la pasa al siguiente.


- **CreditApprovalSystem: [CreditApprovalSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/chain_of_responsibility/CreditApprovalSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: APROBACIÓN DE CRÉDITOS (Chain of Responsibility) Un banco procesa solicitudes de préstamo mediante una cadena de validaciones: 1. Verificación de identidad (documento válido) 2. Revisión de historial crediticio (score mínimo) 3. Verificación de capacidad de pago (ingresos suficientes) 4. Aprobación final del oficial de crédito Cada validación aprueba y pasa a la siguiente, o rechaza la solicitud y detiene el proceso de inmediato. El solicitante no conoce cuál validación específica lo rechazó internamente ni cuántas validaciones existen en total en el banco.

- **HelpDeskSupportSystem: [HelpDeskSupportSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/chain_of_responsibility/HelpDeskSupportSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: SISTEMA DE SOPORTE TÉCNICO (Chain of Responsibility) Una empresa de tecnología atiende tickets de soporte mediante una cadena de niveles de atención: 1. Soporte Nivel 1 (problemas básicos, ej. reinicios, contraseñas) 2. Soporte Nivel 2 (problemas de configuración) 3. Soporte Nivel 3 (problemas técnicos avanzados) 4. Escalamiento a Ingeniería (bugs del producto) Cada nivel: - Puede resolver el ticket y detener el proceso. - O escalarlo al siguiente nivel si no tiene la capacidad de resolverlo. El cliente que reportó el ticket no conoce en qué nivel fue resuelto ni cuántos niveles existen.

- **MigrationControlSystem: [MigrationControlSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/chain_of_responsibility/MigrationControlSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: CONTROLES MIGRATORIOS (ESTADOS UNIDOS) Una persona intenta ingresar al país. Debe pasar por una cadena de controles: 1. Revisión de pasaporte y visa 2. Control de antecedentes 3. Revisión de motivos del viaje 4. Aprobación final por migración Cada control aprueba y pasa al siguiente, o rechaza deteniendo el proceso. El pasajero no sabe cuántos controles hay internamente.
  - **Uso / Definición:** Es un patrón de diseño de comportamiento que te permite pasar solicitudes a lo largo de una cadena de manejadores. Al recibir una solicitud, cada manejador decide si la procesa o si la pasa al siguiente manejador.
  - **Estructura / Ventajas:**
    - - Desacopla al emisor de una petición de sus receptores.
    - - Puedes controlar y cambiar dinámicamente el orden de la cadena.
    - - Sigue el principio SRP al separar cada paso/validación en su propia clase.
    - - Sigue el principio OCP al permitir añadir nuevos eslabones a la cadena
    - sin romper el código cliente.

###### Command

> **Command:** Encapsula una petición como un objeto, permitiendo parametrizar a los clientes con diferentes peticiones, hacer cola o registrar las peticiones, y soportar operaciones que se pueden deshacer.


- **GameCharacterCommandSystem: [GameCharacterCommandSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/command/GameCharacterCommandSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: PERSONAJE DE VIDEOJUEGO Un personaje de videojuego puede ejecutar diferentes acciones (Caminar, Saltar, Atacar, Defenderse). Cada acción se encapsula como un comando, de modo que el control del juego (GameController) pueda ejecutar cualquier acción sin conocer su implementación interna en el personaje.
  - **Uso / Definición:** Es un patrón de diseño de comportamiento que convierte una solicitud en un objeto independiente que contiene toda la información sobre la solicitud.
  - **Estructura / Ventajas:**
    - - Esta transformación te permite parametrizar los métodos con diferentes
    - solicitudes (asignar comandos a botones arbitrarios).
    - - Retrasar o poner en cola la ejecución de una solicitud (Macro de comandos).
    - - Soportar operaciones que se pueden deshacer (Undo/Redo).
    - - Desacopla al objeto que invoca la operación del objeto que la sabe ejecutar.

- **TextEditorCommandSystem: [TextEditorCommandSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/command/TextEditorCommandSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: EDITOR DE TEXTO (Command) Un editor de texto permite ejecutar acciones sobre un documento mediante una barra de herramientas: - Escribir texto - Eliminar texto - Cambiar a mayúsculas Cada acción se encapsula como un comando, de modo que el editor pueda ejecutar cualquier botón de la barra de herramientas sin conocer los detalles internos de cómo se modifica el documento. El editor debe mantener un historial de comandos ejecutados, para poder deshacer (Undo) la última acción en cualquier momento.

- **UniversalRemoteCommandSystem: [UniversalRemoteCommandSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/command/UniversalRemoteCommandSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: CONTROL REMOTO UNIVERSAL (Command) Un control remoto universal debe poder operar distintos dispositivos del hogar mediante botones configurables: - Encender luz - Apagar luz - Subir volumen del televisor - Bajar volumen del televisor Cada acción se encapsula como un comando, de modo que el control remoto pueda ejecutar cualquier botón sin conocer los detalles internos de cómo la luz o el televisor realizan la acción. Además, el sistema debe permitir DESHACER la última acción ejecutada (ej. si se encendió la luz, deshacer la apaga).

###### Iterator

> **Iterator:** Proporciona una forma de acceder secuencialmente a los elementos de un objeto agregado sin exponer su representación subyacente.


- **LibraryCatalogIteratorSystem: [LibraryCatalogIteratorSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/iterator/LibraryCatalogIteratorSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: CATÁLOGO DE BIBLIOTECA (Iterator) Una biblioteca tiene un catálogo de libros disponibles: - Libro A, Libro B, Libro C, Libro D, Libro E El sistema debe poder recorrer el catálogo libro por libro sin exponer la estructura interna, permitiendo además que dos bibliotecarios recorran el catálogo al mismo tiempo de forma independiente (cada uno en su propia posición).

- **MusicPlaylistIteratorSystem: [MusicPlaylistIteratorSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/iterator/MusicPlaylistIteratorSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: LISTA DE REPRODUCCIÓN (Iterator) Una aplicación de música tiene una lista de reproducción con canciones: - Canción 1 - Canción 2 - Canción 3 - Canción 4 El usuario debe poder recorrer la lista de reproducción canción por canción (obtener la siguiente) sin conocer si internamente las canciones están guardadas en un arreglo (Array), una lista enlazada, o cualquier otra estructura.

- **TouristGuideIteratorSystem: [TouristGuideIteratorSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/iterator/TouristGuideIteratorSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: GUÍA TURÍSTICO EN ROMA Un turista quiere explorar distintos lugares emblemáticos de Roma. El recorrido debe poder iterarse sin exponer la estructura interna que almacena los lugares.
  - **Uso / Definición:** Es un patrón de diseño de comportamiento que te permite recorrer elementos de una colección sin exponer su representación subyacente (lista, pila, árbol, etc.).
  - **Estructura / Ventajas:**
    - - Extrae el comportamiento de recorrido de una colección y lo coloca en un
    - objeto independiente llamado iterador.
    - - Varios iteradores pueden recorrer la misma colección al mismo tiempo.
    - - Promueve el Single Responsibility Principle al limpiar el código de la
    - colección principal.
    - - Promueve el Open/Closed Principle: puedes implementar nuevos tipos de
    - recorridos sin modificar la colección original.

###### Mediator

> **Mediator:** Define un objeto que encapsula cómo interactúan un conjunto de objetos. Promueve el bajo acoplamiento al evitar que los objetos se refieran unos a otros explícitamente.


- **AirportTowerMediatorSystem: [AirportTowerMediatorSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/mediator/AirportTowerMediatorSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO M3: TORRE DE CONTROL DE AEROPUERTO (Mediator) Varios aviones necesitan coordinarse para aterrizar y despegar sin chocar entre sí. En vez de que los aviones se comuniquen directamente entre ellos (lo cual sería un caos en el aire), todos se comunican con una torre de control central (Mediador) que coordina el orden de las operaciones.

- **FormValidationMediatorSystem: [FormValidationMediatorSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/mediator/FormValidationMediatorSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO M2: FORMULARIO CON VALIDACIÓN CRUZADA (Mediator) Un formulario tiene varios campos (Checkbox "Suscribirse", TextBox "Email", Button "Enviar") donde la habilitación de unos depende del estado de otros. En vez de que cada campo conozca directamente a los demás (Código espagueti de UI), un mediador central coordina esas interacciones.

- **GroupChatMediatorSystem: [GroupChatMediatorSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/mediator/GroupChatMediatorSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO M1: CHAT GRUPAL En un chat grupal, los usuarios no se envían mensajes directamente entre sí. Todos envían sus mensajes a una sala de chat central (el mediador), que se encarga de distribuirlos a los demás participantes.
  - **Uso / Definición:** Reduce las dependencias directas entre muchos objetos (acoplamiento caótico de tela de araña), forzando que se comuniquen únicamente a través de un objeto central (el mediador).
  - **Estructura / Ventajas:**
    - - Centraliza la lógica de comunicación y control entre componentes.
    - - Reduce el acoplamiento (los componentes no necesitan conocer las clases de
    - los demás componentes, solo conocen al Mediador).
    - - Sigue el principio de Responsabilidad Única.

###### Memento

> **Memento:** Sin violar la encapsulación, captura y externaliza el estado interno de un objeto para que el objeto pueda ser restaurado a este estado más tarde.


- **GraphicEditorMementoSystem: [GraphicEditorMementoSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/memento/GraphicEditorMementoSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO Me3: EDITOR GRÁFICO / CANVAS (Memento) Un editor de diseño gráfico permite deshacer/rehacer cambios en un lienzo (mover, cambiar color, redimensionar figuras), guardando el estado del lienzo antes de cada operación, sin que el historial conozca la estructura interna de las figuras.

- **TextEditorMementoSystem: [TextEditorMementoSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/memento/TextEditorMementoSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO Me1: EDITOR DE TEXTO (Deshacer) Un editor de texto permite deshacer cambios, guardando snapshots del estado del documento antes de cada edición, sin que el historial de cambios necesite conocer la estructura interna del documento.
  - **Uso / Definición:** Permite capturar y guardar el estado interno de un objeto en un momento dado, para poder restaurarlo después (ej. deshacer), sin exponer ni romper el encapsulamiento de esa clase.
  - **Estructura / Ventajas:**
    - - Puedes producir capturas de estado del objeto sin violar su encapsulación.
    - - Simplifica el código de la clase originadora permitiendo que la clase
    - cuidadora (Caretaker) mantenga el historial.

- **VideoGameMementoSystem: [VideoGameMementoSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/memento/VideoGameMementoSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO Me2: VIDEOJUEGO CON PUNTOS DE GUARDADO (Memento) Un videojuego permite guardar el progreso del jugador (vida, posición, inventario) en un punto de control (Checkpoint), y restaurarlo más adelante si el jugador muere, sin exponer los detalles internos del estado del jugador al sistema de guardado.

###### Observer

> **Observer:** Define una dependencia de uno-a-muchos entre objetos para que cuando un objeto cambie de estado, todos sus dependientes sean notificados y actualizados automáticamente.


- **StockMarketObserverSystem: [StockMarketObserverSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/observer/StockMarketObserverSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: SISTEMA DE ACCIONES BURSÁTILES (Observer) Una acción en bolsa (Sujeto) notifica a todos los inversionistas suscritos (Observadores) cada vez que su precio cambia. Esto permite que cada inversionista reaccione a su manera: - Uno puede vender o comprar automáticamente (Inversor Agresivo/Bot). - Otro simplemente registra el historial en una libreta (Inversor Conservador). Todo esto ocurre sin que la acción conozca quiénes son los inversionistas ni cómo están reaccionando, manteniendo el desacoplamiento total.

- **WeatherStationObserverSystem: [WeatherStationObserverSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/observer/WeatherStationObserverSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: ESTACIÓN METEOROLÓGICA (Observer) Una estación meteorológica (Sujeto) mide la temperatura local y notifica automáticamente a varios paneles de visualización o pantallas (Observadores) cada vez que la temperatura cambia. Paneles de visualización: - Pantalla Web - App Móvil - Pantalla LED (Cartel en la calle) La estación no debe conocer los detalles de cómo cada panel procesa o muestra el dato; solo sabe que puede enviarles la actualización de la temperatura cuando ocurra una medición nueva.

- **YouTubeChannelObserverSystem: [YouTubeChannelObserverSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/observer/YouTubeChannelObserverSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: CANAL DE YOUTUBE Un canal de YouTube notifica automáticamente a todos sus suscriptores cuando sube un video nuevo. Los suscriptores pueden unirse o salir de la lista en cualquier momento. El canal no conoce los detalles de qué hace cada suscriptor con la notificación (uno puede mostrar un popup, otro enviar un email, etc.).
  - **Uso / Definición:** Define una relación de dependencia de uno-a-muchos entre objetos, de forma que cuando un objeto (el "sujeto" o "publicador") cambia de estado, se notifica y actualiza automáticamente a todos los objetos dependientes (los "observadores" o "suscriptores").
  - **Estructura / Ventajas:**
    - - Principio Abierto/Cerrado: Puedes introducir nuevas clases de suscriptores
    - sin tener que cambiar el código del publicador.
    - - Relaciones dinámicas: Los objetos pueden suscribirse o desuscribirse en
    - tiempo de ejecución.
    - - Desacoplamiento total: El Sujeto no necesita conocer los detalles concretos
    - ni las clases de sus Observadores, solo que implementan una interfaz común.

###### State

> **State:** Permite que un objeto altere su comportamiento cuando su estado interno cambia. El objeto parecerá cambiar su clase.


- **EcommerceOrderStateSystem: [EcommerceOrderStateSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/state/EcommerceOrderStateSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO S2: PEDIDO DE E-COMMERCE (State) Un pedido cambia de comportamiento según su estado: - Pendiente - Pagado - Enviado - Entregado Reglas de negocio (dependientes del estado): - Solo se puede cancelar si está "Pendiente" o "Pagado". - Solo se puede confirmar recepción si está "Enviado". - Intentar pagar algo ya pagado o cancelar algo enviado debe devolver un error.

- **MediaPlayerStateSystem: [MediaPlayerStateSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/state/MediaPlayerStateSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO S3: REPRODUCTOR MULTIMEDIA (State) Un reproductor de video cambia su comportamiento según su estado: - Reproduciendo (Playing) - Pausado (Paused) - Detenido (Stopped) Presionar el botón "Play/Pause" hace algo distinto según en qué estado se encuentre actualmente el reproductor.

- **TrafficLightStateSystem: [TrafficLightStateSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/state/TrafficLightStateSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO S1: SEMÁFORO Un semáforo cambia su comportamiento según su estado (Rojo, Amarillo, Verde). Cada estado determina cuánto tiempo dura y a qué estado pasa después, sin usar un gran if/else para controlar la transición en el semáforo principal.
  - **Uso / Definición:** Permite que un objeto cambie su comportamiento cuando cambia su estado interno, como si cambiara de clase.
  - **Estructura / Ventajas:**
    - - Evita condicionales gigantes (if/else/switch enormes) agrupando los
    - comportamientos asociados a un estado en particular dentro de una clase
    - aislada.
    - - Sigue el principio SRP y OCP: Puedes añadir nuevos estados fácilmente sin
    - tocar los demás ni el Contexto.

###### Strategy

> **Strategy:** Define una familia de algoritmos, encapsula cada uno y los hace intercambiables. Permite que el algoritmo varíe independientemente de los clientes que lo utilicen.


- **FileCompressionStrategySystem: [FileCompressionStrategySystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/strategy/FileCompressionStrategySystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: COMPRESIÓN DE ARCHIVOS (Strategy) Una aplicación de almacenamiento permite comprimir archivos antes de subirlos al servidor, pero el algoritmo de compresión puede variar: - Compresión ZIP (rápida, compresión moderada) - Compresión RAR (más lenta, mayor compresión) - Sin compresión (subir el archivo tal cual) El algoritmo de compresión no debe estar acoplado al proceso de subida de archivos, permitiendo que el usuario cambie el método de compresión sin modificar el resto de la aplicación (Inversión de Control).

- **NavigationStrategySystem: [NavigationStrategySystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/strategy/NavigationStrategySystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: APLICACIÓN DE NAVEGACIÓN (GPS) Una aplicación de navegación puede calcular rutas de distintas maneras. El algoritmo de cálculo de ruta no debe estar acoplado a la aplicación, ya que puede cambiar según la preferencia del usuario en tiempo real: - Ruta más Rápida (Prioriza autopistas) - Ruta Panorámica/Escénica (Prioriza paisajes y carreteras secundarias) - Ruta más Barata (Evita peajes)
  - **Uso / Definición:** Es un patrón de diseño de comportamiento que te permite definir una familia de algoritmos, colocar cada uno de ellos en una clase separada y hacer sus objetos intercambiables.
  - **Estructura / Ventajas:**
    - - Puedes cambiar de un algoritmo a otro durante el tiempo de ejecución.
    - - Aísla los detalles de implementación de un algoritmo del código que lo usa.
    - - Reemplaza la herencia (o los horribles bloques gigantes de if/switch)
    - por composición.
    - - Principio de Abierto/Cerrado: puedes introducir nuevas estrategias sin
    - cambiar el contexto.

- **ShoppingCartStrategySystem: [ShoppingCartStrategySystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/strategy/ShoppingCartStrategySystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: CÁLCULO DE DESCUENTOS (Strategy) Una tienda en línea aplica descuentos al total de la compra, pero la forma de calcular el descuento puede variar según la promoción activa: - Descuento por porcentaje fijo (ej. 10% del total) - Descuento por monto fijo (ej. $5000 menos, sin importar el total) - Sin descuento (precio normal) El algoritmo de cálculo de descuento no debe estar acoplado al carrito de compras, ya que la promoción activa puede cambiar en cualquier momento (incluso durante la sesión del usuario).

###### Template Method

> **Template Method:** Define el esqueleto de un algoritmo en una operación, difiriendo algunos pasos a las subclases. Permite a las subclases redefinir ciertos pasos del algoritmo sin cambiar su estructura.


- **AutomatedTestTemplateSystem: [AutomatedTestTemplateSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/template_method/AutomatedTestTemplateSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO T3: PRUEBAS AUTOMATIZADAS (Template Method) Ejecutar una prueba automatizada (Testing) sigue el mismo esqueleto: 1. Preparar el entorno (Setup) 2. Ejecutar prueba (Run/Assert - El paso que varía fuertemente) 3. Verificar resultado (Comprobación de métricas base) 4. Limpiar entorno (Teardown)

- **HotBeverageTemplateSystem: [HotBeverageTemplateSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/template_method/HotBeverageTemplateSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO T1: PREPARACIÓN DE BEBIDAS CALIENTES Preparar café y té sigue el mismo esqueleto general (hervir agua, preparar el ingrediente, verter en taza, agregar condimentos). Los pasos "preparar ingrediente" y "agregar condimentos" varían, pero el orden general es inmutable.
  - **Uso / Definición:** Define el esqueleto de un algoritmo en una operación, delegando algunos pasos a las subclases. Template Method permite a las subclases redefinir ciertos pasos de un algoritmo sin cambiar la estructura o el esqueleto del mismo.
  - **Estructura / Ventajas:**
    - - Evita la duplicación de código en procesos similares.
    - - Inversión de Control ("Principio de Hollywood"): "No nos llames, nosotros
    - te llamaremos". La clase base llama a los métodos de las subclases y no al revés.
    - - Asegura que el proceso principal siempre siga el mismo orden de ejecución.

- **ReportGeneratorTemplateSystem: [ReportGeneratorTemplateSystem.java](src/main/java/dosw/semana_3/extra/patrones/comportamiento/template_method/ReportGeneratorTemplateSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO T2: PROCESAMIENTO DE REPORTES (Template Method) Generar un reporte (PDF o Excel) sigue el mismo esqueleto: 1. Obtener datos (Base de datos) 2. Aplicar formato (El paso que varía) 3. Guardar archivo (Disco) 4. Notificar al usuario (Email)

##### Creacionales

###### Abstract Factory

> **Abstract Factory:** Proporciona una interfaz para crear familias de objetos relacionados o dependientes sin especificar sus clases concretas.


- **CrossPlatformUISystem: [CrossPlatformUISystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/abstract_factory/CrossPlatformUISystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: INTERFAZ MULTIPLATAFORMA (Abstract Factory) Una aplicación debe verse "nativa" en distintos sistemas operativos. Cada sistema operativo ofrece una familia de componentes visuales compatibles entre sí: - Botón - Checkbox - Barra de desplazamiento (Scrollbar) SO soportados inicialmente: - Windows - MacOS La aplicación no debe conocer las implementaciones concretas de estos componentes — solo debe poder renderizar el botón, checkbox y scrollbar, sin importar el sistema operativo subyacente.

- **GameEngineSystem: [GameEngineSystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/abstract_factory/GameEngineSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: MOTOR DE VIDEOJUEGOS MULTICONSOLA Una empresa desarrolla videojuegos que deben ejecutarse en distintas consolas. Cada consola ofrece una familia de componentes compatibles entre sí: - Control (se conecta) - Juego (se inicia) - Interfaz gráfica (se renderiza) Consolas soportadas: PlayStation y Xbox. El motor del juego (cliente) no conoce las implementaciones concretas.
  - **Uso / Definición:** Es un patrón de diseño creacional que nos permite producir familias de objetos relacionados sin especificar sus clases concretas.
  - **Estructura / Ventajas:**
    - - Asegura que los productos creados por una fábrica sean compatibles entre sí
    - (ej. no mezclarás un control de Xbox con la interfaz de PlayStation).
    - - Evita un acoplamiento fuerte entre el cliente (GameEngine) y los productos
    - concretos.
    - - Sigue el principio SRP (Single Responsibility Principle) al extraer la
    - lógica de creación a fábricas concretas.
    - - Sigue el principio OCP (Open/Closed Principle) al permitir agregar nuevas
    - familias (ej. NintendoFactory) sin romper el código cliente existente.

- **OfficeFurnitureSystem: [OfficeFurnitureSystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/abstract_factory/OfficeFurnitureSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: MOBILIARIO DE OFICINA (Abstract Factory) Una empresa de decoración de interiores ofrece paquetes completos de mobiliario en distintos estilos. Cada estilo ofrece una familia de muebles compatibles entre sí: - Silla - Mesa - Sofá Estilos soportados inicialmente: - Moderno - Victoriano El sistema de decoración no debe conocer las implementaciones concretas de cada mueble — solo debe poder pedir una silla, una mesa y un sofá, y confiar en que combinan entre sí según el estilo elegido por la fábrica.

###### Builder

> **Builder:** Separa la construcción de un objeto complejo de su representación para que el mismo proceso de construcción pueda crear diferentes representaciones.


- **ComputerBuilderSystem: [ComputerBuilderSystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/builder/ComputerBuilderSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: CONFIGURADOR DE COMPUTADORAS (Builder) Una tienda de tecnología arma computadoras a pedido. El proceso de ensamblaje es el mismo, pero el resultado final varía según el tipo de equipo solicitado. Ejemplos: PC Gamer, PC de Oficina. La tienda quiere separar el proceso de construcción del objeto final. Cada computadora está conformada por: - Procesador - Memoria RAM - Almacenamiento - Fuente de poder Componentes adicionales (Opcionales): - Tarjeta gráfica dedicada - Sistema de refrigeración líquida - Luces RGB

- **PizzaBuilderSystem: [PizzaBuilderSystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/builder/PizzaBuilderSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: ARMADO DE PIZZAS (Builder) Una pizzería arma pizzas a pedido. El proceso de preparación es el mismo (masa, salsa, ingredientes extra), pero el resultado final varía según el tipo de pizza solicitada. Ejemplos: Pizza Margarita, Pizza Especial de la casa. La pizzería quiere separar el proceso de construcción del objeto final. Cada pizza está conformada por: - Tamaño - Tipo de masa - Salsa - Ingredientes adicionales (lista: queso extra, pepperoni, champiñones, etc.)

- **ToyFactorySystem: [ToyFactorySystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/builder/ToyFactorySystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: FÁBRICA DE JUGUETES Una fábrica de juguetes produce muñecos con distintas configuraciones. El proceso de ensamblaje es el mismo, pero el resultado final varía según el tipo de muñeco solicitado. Ejemplos: Muñeco de acción, Muñeca clásica. Cada muñeco está conformado por: cabeza, cuerpo, brazos, piernas y accesorios.
  - **Uso / Definición:** Es un patrón de diseño creacional que nos permite construir objetos complejos paso a paso. El patrón nos permite producir distintos tipos y representaciones de un objeto empleando el mismo código de construcción.
  - **Estructura / Ventajas:**
    - - Puedes construir objetos paso a paso, aplazar pasos de la construcción o
    - ejecutar pasos de forma recursiva.
    - - Puedes reutilizar el mismo código de construcción al construir varias
    - representaciones de productos.
    - - Utiliza SINGLE RESPONSIBILITY (SRP) al aislar el código de construcción
    - complejo de la lógica de negocio del producto.
    - - No es necesario crear constructores tan grandes o especializados (adiós al
    - Telescoping Constructor) dependiendo de la combinación de los datos de entrada.
    - - EL BUILDER NO ELIMINA LOS PARÁMETROS, LOS CONVIERTE EN PASOS EXPLÍCITOS Y CON NOMBRE.

###### Factory Method

> **Factory Method:** Define una interfaz para crear un objeto, pero deja que las subclases decidan qué clase instanciar.


- **DocumentGeneratorSystem: [DocumentGeneratorSystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/factory_method/DocumentGeneratorSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: GENERADOR DE DOCUMENTOS (Factory Method) Una aplicación de oficina permite exportar un reporte en distintos formatos: - PDF - Word - Excel El código que genera el reporte no debe conocer las clases concretas de cada exportador — debe poder pedir "genera el documento" sin importar el formato final.

- **NotificationSystem: [NotificationSystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/factory_method/NotificationSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: SISTEMA DE NOTIFICACIONES (Factory Method) Una aplicación debe enviar notificaciones a los usuarios por distintos canales: - Notificación por Email - Notificación por SMS - Notificación push (móvil) El sistema no debe acoplarse a las clases concretas de cada tipo de notificación. El código que dispara el envío no debe saber si es un email, un SMS o un push.

- **PaymentSystem: [PaymentSystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/factory_method/PaymentSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: PROCESADOR DE PAGOS Una plataforma de comercio electrónico procesa pagos usando distintos métodos: Tarjeta de crédito, PayPal, Transferencia bancaria. El sistema no se acopla a las clases concretas de cada pago.
  - **Uso / Definición:** Es un patrón de diseño creacional que proporciona una interfaz para crear objetos en una superclase, mientras permite a las subclases alterar el tipo de objetos que se crearán.
  - **Estructura / Ventajas:**
    - - Evita un acoplamiento fuerte entre el creador (Processor) y los productos
    - concretos (Payments).
    - - SINGLE RESPONSIBILITY PRINCIPLE (SRP): Se extrae el código de creación del
    - producto hacia un lugar específico, haciendo el programa más fácil de mantener.
    - - OPEN/CLOSED PRINCIPLE (OCP): Permite incorporar nuevos tipos de productos
    - en el programa sin descomponer o alterar el código cliente existente.

###### Prototype

> **Prototype:** Especifica los tipos de objetos a crear utilizando una instancia prototípica, y crea nuevos objetos copiando este prototipo.


- **DocumentTemplateSystem: [DocumentTemplateSystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/prototype/DocumentTemplateSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: EDITOR DE DOCUMENTOS CON PLANTILLAS Un editor permite crear nuevos documentos a partir de plantillas ya diseñadas. Crear la plantilla desde cero es costoso (carga de estilos, imágenes, etc.). El sistema permite clonar una plantilla existente para generar un documento nuevo, modificando solo el contenido sin rehacer el diseño pesado.
  - **Uso / Definición:** Crea objetos clonando una instancia existente en vez de construirla desde cero. ÚTIL CUANDO: a) Crear el objeto es costoso (requiere consultar una base de datos, hacer cálculos pesados, cargar recursos gráficos pesados, etc.). b) Necesitas muchas variantes de un objeto base con pequeños cambios, sin repetir todo el proceso de construcción.

- **ReportConfigurationSystem: [ReportConfigurationSystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/prototype/ReportConfigurationSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: CONFIGURACIONES DE REPORTES (Prototype) Un sistema de reportes financieros tiene una configuración base "Reporte Mensual Estándar" con muchos parámetros ya definidos (formato, columnas, filtros, permisos). Los analistas necesitan crear variantes de ese reporte (ej. "Reporte Mensual - Región Norte") cambiando solo uno o dos filtros, sin tener que configurar todo desde cero cada vez. El sistema debe permitir clonar la configuración base.

- **VideoGameEnemySystem: [VideoGameEnemySystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/prototype/VideoGameEnemySystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: ENEMIGOS EN UN VIDEOJUEGO (Prototype) Un videojuego tiene un enemigo base ("Orco") con estadísticas configuradas (vida, ataque, defensa) que tardan en calcularse al inicio del nivel (basado en la dificultad del jugador). Cuando aparecen oleadas de 10 orcos idénticos, el juego no debe recalcular las estadísticas cada vez — debe clonar el orco base para generar cada nuevo enemigo de la oleada, con la posibilidad de ajustar pequeños detalles después (como la posición X, Y en el mapa).

###### Singleton

> **Singleton:** Garantiza que una clase sólo tenga una instancia, y proporciona un punto de acceso global a ella.


- **DatabaseConnectionSystem: [DatabaseConnectionSystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/singleton/DatabaseConnectionSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: GESTOR DE CONEXIÓN A BASE DE DATOS (Singleton) Una aplicación necesita conectarse a una base de datos. Crear una conexión es costoso (tiempo de red, autenticación, recursos en el motor de BD). Abrir múltiples conexiones innecesarias agotaría el "pool" de conexiones del servidor de base de datos. El sistema debe garantizar que exista una única conexión compartida (el canal físico) que sea reutilizada por todas las partes del programa (Servicio de Usuarios, Servicio de Productos, etc.) que necesiten ejecutar consultas.

- **GlobalConfigurationSystem: [GlobalConfigurationSystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/singleton/GlobalConfigurationSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: CONFIGURACIÓN GLOBAL DE LA APLICACIÓN (Singleton) Un sistema carga su configuración (idioma, tema, credenciales de conexión) una sola vez al iniciar, desde un archivo. Distintos módulos del programa necesitan leer esa misma configuración, pero no debe existir más de una instancia cargando o representando esa configuración — todos deben acceder a los mismos valores.

- **LoggerSystem: [LoggerSystem.java](src/main/java/dosw/semana_3/extra/patrones/creacionales/singleton/LoggerSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: REGISTRO DE LOGS (LOGGER) Una aplicación necesita registrar mensajes de log (errores, información) desde distintas partes del código. Todos los mensajes deben escribirse en el mismo lugar usando la misma configuración. No debe ser posible crear múltiples instancias del logger para evitar desincronización.
  - **Uso / Definición:** Garantiza que una clase tenga una única instancia en toda la aplicación, con un punto de acceso global a ella. Se implementa típicamente con: 1. Un constructor privado. 2. Una variable estática que guarda la única instancia. 3. Un método estático getInstancia() que crea la instancia solo la primera vez y la reutiliza después.

##### Estructurales

###### Adapter

> **Adapter:** Convierte la interfaz de una clase en otra interfaz que los clientes esperan. Permite que las clases trabajen juntas que de otra manera no podrían debido a interfaces incompatibles.


- **GasStationSystem: [GasStationSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/adapter/GasStationSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: GASOLINERÍA INTELIGENTE Una gasolinería inteligente atiende vehículos a combustión usando un sistema estándar interno que mide en "litros". Con la llegada de vehículos eléctricos, incorporan cargadores de distintos proveedores (Rápido, Lento), los cuales miden en "KWh" y tienen interfaces incompatibles. El sistema central no debe cambiar. Se usan adaptadores para unificar el proceso.
  - **Uso / Definición:** Es un patrón de diseño estructural que permite la colaboración entre objetos con interfaces incompatibles. El patrón Adapter te permite crear una clase intermedia que sirva como traductora entre tu código y una clase heredada, una clase de un tercero o cualquier otra clase con una interfaz extraña.
  - **Estructura / Ventajas:**
    - - SINGLE RESPONSIBILITY PRINCIPLE (SRP): Puedes separar la interfaz o el código
    - de conversión de datos de la lógica de negocio primaria del programa.
    - - OPEN/CLOSED PRINCIPLE (OCP): Puedes introducir nuevos tipos de adaptadores
    - al programa sin descomponer el código cliente existente, siempre y cuando
    - trabajen con los adaptadores a través de la interfaz con el cliente.

- **MediaAdapterSystem: [MediaAdapterSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/adapter/MediaAdapterSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: REPRODUCTOR DE MEDIOS MULTIFORMATO (Adapter) Una aplicación de reproducción de audio tiene un reproductor estándar que espera un método: reproducir(archivo). La aplicación necesita soportar dos librerías externas de terceros para formatos avanzados, con interfaces incompatibles que no se pueden modificar: - VlcEngineLegacy: su método es playMedia(path, format). - Mp4NativeDecoder: su método es startDecoding(filePath). El reproductor central de la aplicación no debe cambiar. Se debe unificar la reproducción de todos los formatos mediante el patrón Adapter.

- **PaymentGatewayAdapterSystem: [PaymentGatewayAdapterSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/adapter/PaymentGatewayAdapterSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: SISTEMA DE PAGOS CON PASARELAS EXTERNAS (Adapter) Una tienda en línea tiene un sistema de checkout que espera un método estándar: procesarPago(montoEnPesos). Se necesitan integrar dos pasarelas de pago internacionales de terceros (cuyas interfaces no se pueden modificar): - PayGlobalAPI: su método es charge(amountInCents) y espera el monto en centavos de dólar. - EuroPay: su método es debitar(montoEnEuros) y espera el monto en euros. El sistema central no debe cambiar. Se debe unificar el cobro para ambas pasarelas mediante el patrón Adapter.

###### Bridge

> **Bridge:** Desacopla una abstracción de su implementación para que las dos puedan variar independientemente.


- **NotificationBridgeSystem: [NotificationBridgeSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/bridge/NotificationBridgeSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: NOTIFICACIONES (Bridge) Una aplicación envía notificaciones de distinto tipo de contenido (Mensaje de Texto, Alerta de Sistema) a través de distintos canales de envío (Email, SMS). Con herencia pura, la combinación crece con cada nuevo tipo de contenido o cada nuevo canal (MensajeTextoEmail, AlertaSistemaSMS, etc.). Se aplica Bridge separando la jerarquía de Notificación (abstracción: qué se envía) de la jerarquía de Canal de Envío (implementación: cómo se envía), de modo que cualquier tipo de notificación pueda enviarse por cualquier canal.

- **RemoteDeviceBridgeSystem: [RemoteDeviceBridgeSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/bridge/RemoteDeviceBridgeSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: DISPOSITIVOS Y CONTROLES REMOTOS (Bridge) Una empresa de electrónica vende distintos tipos de dispositivos (Televisor, Radio) que pueden ser controlados por distintos tipos de controles remotos (Básico, Avanzado). Si se usa herencia pura, la combinación crece con cada nuevo dispositivo o cada nuevo tipo de control (TelevisorControlBasico, RadioControlAvanzado, etc.). Se aplica Bridge separando la jerarquía de Control Remoto (abstracción) de la jerarquía de Dispositivo (implementación). Un control remoto puede operar sobre cualquier dispositivo sin necesitar una subclase por cada combinación.

- **ShapeColorBridgeSystem: [ShapeColorBridgeSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/bridge/ShapeColorBridgeSystem.java)**
  - **Uso / Definición:** Es un patrón de diseño estructural que te permite dividir una clase grande, o un grupo de clases estrechamente relacionadas, en dos jerarquías separadas (abstracción e implementación) que pueden desarrollarse independientemente. EL PROBLEMA DE LA EXPLOSIÓN DE SUBCLASES: Si tenemos la clase Forma (con hijos Círculo y Cuadrado) y queremos agregar Color, la herencia tradicional nos obliga a crear: CirculoRojo, CirculoAzul, CuadradoRojo, CuadradoAzul. Al agregar un triángulo, son 3 clases más. ¡Crece exponencialmente! LA SOLUCIÓN DEL PUENTE: El patrón Bridge resuelve esto pasando de la herencia a la composición. Se extrae la dimensión "Color" a una jerarquía de clases separada. La clase "Forma" ahora referencia un objeto de la nueva jerarquía "Color". COMPONENTES: - Abstracción: Capa de control de alto nivel (Forma). Delega el trabajo real. - Implementación: Interfaz común para las plataformas (Color).

###### Composite

> **Composite:** Compone objetos en estructuras de árbol para representar jerarquías de parte-todo. Permite a los clientes tratar a los objetos individuales y a las composiciones de objetos de manera uniforme.


- **CompanyOrganizationSystem: [CompanyOrganizationSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/composite/CompanyOrganizationSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: ESTRUCTURA ORGANIZACIONAL (Composite) Una empresa modela su organigrama con empleados individuales y equipos que pueden contener: - Empleados - Otros equipos (equipos grandes divididos en sub-equipos) El sistema debe poder calcular el costo total en salarios de cualquier elemento, sin importar si es un empleado individual o un departamento completo con sub-equipos anidados. Para obtener el costo total de un equipo, es necesario recorrer recursivamente todos sus miembros (empleados y sub-equipos) y sumar sus salarios.

- **FileSystemCompositeSystem: [FileSystemCompositeSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/composite/FileSystemCompositeSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: SISTEMA DE ARCHIVOS (Composite) Un explorador de archivos maneja archivos individuales y carpetas que pueden contener: - Archivos - Otras carpetas (carpetas dentro de carpetas) El sistema debe poder calcular el tamaño total en KB de cualquier elemento, sin importar si es un archivo simple o una carpeta con muchos elementos anidados. Para obtener el tamaño total de una carpeta es necesario recorrer todos sus elementos (archivos y subcarpetas) y sumar sus tamaños usando recursión.

- **WarehouseCompositeSystem: [WarehouseCompositeSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/composite/WarehouseCompositeSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: BODEGA DE PRODUCTOS Y CAJAS Una bodega maneja productos individuales y cajas que pueden contener: - Productos - Otras cajas (cajas dentro de cajas) El sistema debe poder calcular el precio total de cualquier elemento, sin importar si es un producto simple o una caja compuesta, utilizando recursión.
  - **Uso / Definición:** Es un patrón de diseño estructural que te permite componer objetos en estructuras de árbol y trabajar con esas estructuras como si fueran objetos individuales.
  - **Estructura / Ventajas:**
    - - Proporciona dos tipos de elementos básicos: Hojas (simples) y Contenedores
    - (complejos).
    - - Permite construir una estructura de objetos recursivos anidados parecida
    - a un árbol.
    - - Todos los elementos comparten una interfaz común, así que el cliente no
    - tiene que preocuparse de si está lidiando con un elemento simple o con
    - un grupo complejo de elementos (Polimorfismo).

###### Decorator

> **Decorator:** Añade responsabilidades adicionales a un objeto de forma dinámica. Proporcionan una alternativa flexible a las subclases para extender la funcionalidad.


- **CoffeeShopDecoratorSystem: [CoffeeShopDecoratorSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/decorator/CoffeeShopDecoratorSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: BEBIDAS DE UNA CAFETERÍA (Decorator) Una cafetería vende bebidas base con un costo definido, que pueden personalizarse con extras sin modificar la clase original de la bebida. Bebidas base: - Café - Té Extras disponibles (añaden costo y modifican la descripción): - Leche → +$1500 - Shot de espresso extra → +$2500 - Sirope de vainilla → +$1000 - Crema batida → +$1800 Un mismo pedido puede tener múltiples extras combinados dinámicamente, agregados en el momento de pedir. No se permite crear subclases como CafeConLecheYVainilla.

- **NavalSimulatorSystem: [NavalSimulatorSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/decorator/NavalSimulatorSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO: SIMULADOR NAVAL Cada barco tiene capacidades básicas como ataque, defensa y una descripción. Puede ser equipado con módulos adicionales sin modificar su clase original: - Blindaje reforzado →  + 30 en defensa - Radar avanzado → +10 en ataque - Misiles → +40 en ataque - Sistema antitorpedos → +20 de ataque Un mismo barco puede tener múltiples mejoras combinadas dinámicamente.
  - **Uso / Definición:** Es un patrón de diseño estructural que te permite añadir funcionalidades a objetos colocando estos objetos dentro de objetos encapsuladores especiales (wrappers) que contienen estas funcionalidades.
  - **Estructura / Ventajas:**
    - - Evita la herencia múltiple y la explosión de subclases (ej. no necesitas
    - crear clases como BarcoConRadarYMisiles, BarcoConBlindajeYRadar, etc.).
    - - Puedes añadir o eliminar responsabilidades de un objeto en tiempo de ejecución.
    - - Puedes combinar varios comportamientos envolviendo un objeto con múltiples
    - decoradores.
    - - Sigue el principio Single Responsibility Principle (SRP).

###### Facade

> **Facade:** Proporciona una interfaz unificada para un conjunto de interfaces en un subsistema. Define una interfaz de más alto nivel que hace que el subsistema sea más fácil de usar.


- **ComputerBootFacadeSystem: [ComputerBootFacadeSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/facade/ComputerBootFacadeSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO F3: ARRANQUE DE UN COMPUTADOR (Facade) Encender un computador implica inicializar la CPU, cargar la memoria, verificar el disco duro y cargar el sistema operativo — varios componentes con su propia lógica de arranque. Se debe ofrecer un método simple encenderComputador() (El botón físico de Power) que coordine todo el proceso ocultando la enorme complejidad.

- **EcommerceFacadeSystem: [EcommerceFacadeSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/facade/EcommerceFacadeSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO F2: COMPRA EN LÍNEA (Facade) Realizar un pedido en una tienda en línea implica verificar inventario, procesar el pago, generar la factura y notificar al almacén para el envío (Varios subsistemas independientes). Se debe ofrecer un método simple realizarPedido() que coordine todo esto sin que el cliente (el código frontend o móvil) conozca cada subsistema.

- **HomeTheaterFacadeSystem: [HomeTheaterFacadeSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/facade/HomeTheaterFacadeSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO F1: CINE EN CASA (Home Theater) Encender un sistema de "home theater" implica coordinar el proyector, las luces, el sistema de sonido y el reproductor de streaming. La Fachada ofrece un método simple verPelicula() que internamente coordina todo este subsistema.
  - **Uso / Definición:** Proporciona una interfaz simple y unificada para un subsistema complejo (con muchas clases e interacciones internas), ocultando esa complejidad al cliente.
  - **Estructura / Ventajas:**
    - - Aísla a los clientes de los componentes del subsistema complejo.
    - - Reduce el número de objetos que los clientes manejan.
    - - Promueve el bajo acoplamiento entre los subsistemas y los clientes.

###### Flyweight

> **Flyweight:** Usa la compartición para soportar un gran número de objetos de grano fino de forma eficiente.


- **ForestFlyweightSystem: [ForestFlyweightSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/flyweight/ForestFlyweightSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO Fl1: RENDERIZADO DE UN BOSQUE Un videojuego debe dibujar miles de árboles en un bosque. Cada árbol comparte el mismo modelo 3D y textura (datos pesados, estado intrínseco), pero cada uno tiene su propia posición (x, y) en el mapa (estado extrínseco).
  - **Uso / Definición:** Permite mantener muchos objetos en memoria compartiendo eficientemente la parte de su estado que es común entre todos ellos (el estado "intrínseco"), en vez de duplicarla en cada instancia. Solo se guarda por separado lo que varía (el estado "extrínseco").
  - **Estructura / Ventajas:**
    - - Ahorro masivo de memoria RAM.
    - - Mejora de rendimiento al tener menos objetos pesados que instanciar.

- **ParticleSystemFlyweightSystem: [ParticleSystemFlyweightSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/flyweight/ParticleSystemFlyweightSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO Fl3: SISTEMA DE PARTÍCULAS - EXPLOSIONES (Flyweight) Un videojuego genera cientos de partículas para simular una explosión. Todas las partículas del mismo tipo de explosión comparten la misma textura y color base (estado intrínseco), pero cada una tiene su propia posición y velocidad (estado extrínseco).

- **TextEditorFlyweightSystem: [TextEditorFlyweightSystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/flyweight/TextEditorFlyweightSystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO Fl2: EDITOR DE TEXTO CON CARACTERES (Flyweight) Un editor de texto debe representar miles de caracteres en un documento. Cada carácter comparte la misma fuente y tamaño (estado intrínseco), pero cada uno tiene su propia posición/letra en el documento (estado extrínseco).

###### Proxy

> **Proxy:** Proporciona un sustituto o representante para otro objeto para controlar el acceso al mismo.


- **DatabaseCacheProxySystem: [DatabaseCacheProxySystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/proxy/DatabaseCacheProxySystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO Px3: CACHÉ DE CONSULTAS DB (Cache Proxy) Un sistema que consulta datos costosos a una base de datos (por ejemplo, un reporte pesado) no debe repetir la misma consulta si ya se hizo recientemente. Un intermediario (Proxy) debe devolver el resultado guardado en caché si existe, o consultar la base de datos real solo si es necesario.

- **LazyImageProxySystem: [LazyImageProxySystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/proxy/LazyImageProxySystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO Px1: GALERÍA DE IMÁGENES (Virtual Proxy - Carga Diferida) Una galería de imágenes en alta resolución no debe cargar todas las imágenes de disco al iniciar (es muy costoso). Solo debe cargar una imagen real la primera vez que se solicita mostrarla, y reutilizarla después.
  - **Uso / Definición:** Proporciona un objeto sustituto (proxy) que controla el acceso a otro objeto real, permitiendo agregar lógica adicional (carga diferida, seguridad, caché) sin que el cliente note la diferencia.
  - **Estructura / Ventajas:**
    - - El proxy implementa la misma interfaz que el objeto real, por lo que
    - es transparente para el cliente.
    - - Permite retrasar la inicialización de objetos muy pesados hasta que
    - realmente se necesiten (Virtual Proxy).

- **ProtectedDocumentProxySystem: [ProtectedDocumentProxySystem.java](src/main/java/dosw/semana_3/extra/patrones/estructurales/proxy/ProtectedDocumentProxySystem.java)**
  - **Ejercicio:** EJERCICIO PRÁCTICO Px2: CONTROL DE ACCESO (Protection Proxy) Un sistema de documentos corporativos debe verificar si el usuario tiene permisos antes de permitirle abrir un documento sensible. Sin modificar la clase original del documento, un intermediario (Proxy) debe validar el acceso antes de delegar la apertura real.

### Taller

#### Patrones

- **Ejercicio11: [Ejercicio11.java](src/main/java/dosw/semana_3/taller/patrones/Ejercicio11.java)**
  - **Uso / Definición:** La aplicación necesita gestionar la configuración global del sistema con un único objeto compartido por toda la aplicación. Múltiples instancias crearían inconsistencias.
  - **Estructura / Ventajas:**
    - Patrón Creacional (Creational Pattern).
    - (3) PATRÓN SELECCIONADO:
    - Singleton.
    - (4) ¿POR QUÉ?:
    - El propósito fundamental del patrón Singleton es garantizar que una clase
    - tenga una, y solo una, instancia en todo el ciclo de vida de la aplicación,
    - y proporcionar un punto de acceso global a ella. Esto encaja perfectamente
    - con la necesidad de un "único objeto compartido" para configuraciones globales.
    - Sí. En el desarrollo moderno (con frameworks como Spring o Jakarta EE),
    - rara vez se escribe un Singleton clásico a mano. En su lugar, se utiliza:
    - - **Inyección de Dependencias (DI)** configurada con alcance "Singleton"
    - (Singleton Scope).
    - ¿Por qué es mejor usar Inyección de Dependencias en lugar del Singleton clásico?
    - El Singleton clásico (implementado con `static`) introduce un acoplamiento
    - global muy fuerte en el código, lo que dificulta enormemente las pruebas
    - unitarias (mocking) y viola principios SOLID (como DIP). Al usar DI, delegamos
    - la responsabilidad de crear y mantener esa única instancia al contenedor
    - (framework), manteniendo nuestras clases limpias y fáciles de testear.

- **Ejercicio12: [Ejercicio12.java](src/main/java/dosw/semana_3/taller/patrones/Ejercicio12.java)**
  - **Uso / Definición:** El flujo de compra es idéntico, pero el algoritmo final de cobro varía dinámicamente según si el cliente elige Tarjeta, PSE, PayPal o Nequi.
  - **Estructura / Ventajas:**
    - Patrón de Comportamiento (Behavioral Pattern).
    - (3) PATRÓN SELECCIONADO:
    - Strategy (Estrategia).
    - (4) ¿POR QUÉ?:
    - El patrón Strategy permite definir una familia de algoritmos (las diferentes
    - pasarelas de pago), encapsular cada uno de ellos en una clase separada y
    - hacerlos intercambiables en tiempo de ejecución. La clase de contexto
    - (el carrito de compras) delega el trabajo al objeto Strategy en lugar de
    - implementar múltiples `if-else` o un gran bloque `switch`.
    - Podría pensarse en el patrón **Template Method** (Método Plantilla).
    - ¿Por qué Strategy es MEJOR en este caso?
    - - Template Method se basa en **herencia**: Define un esqueleto de algoritmo
    - en una clase base y deja que las subclases reescriban ciertos pasos. Esto
    - significaría tener un `CompraConTarjeta`, `CompraConNequi`, etc.
    - - Strategy se basa en **composición**: El algoritmo entero se abstrae en
    - otra clase que se inyecta. Esto es mucho más flexible, permite cambiar
    - la estrategia en caliente (en tiempo de ejecución) y favorece el
    - principio OCP (visto en el Ejercicio 6, donde usamos precisamente Strategy).

- **Ejercicio13: [Ejercicio13.java](src/main/java/dosw/semana_3/taller/patrones/Ejercicio13.java)**
  - **Uso / Definición:** El sistema debe generar un reporte en PDF, Excel o CSV según una configuración dada. El cliente que pide el reporte no quiere lidiar con los detalles de cómo se construye (instancia) cada uno de ellos.
  - **Estructura / Ventajas:**
    - Patrón Creacional (Creational Pattern).
    - (3) PATRÓN SELECCIONADO:
    - Factory Method (o Simple Factory).
    - (4) ¿POR QUÉ?:
    - El requerimiento central habla sobre ocultar los detalles de **construcción**
    - (instanciación). El patrón Factory delega la responsabilidad de utilizar el
    - operador `new` a una clase especial (la Fábrica). El cliente simplemente
    - le pasa a la fábrica un parámetro (ej. "PDF") y la fábrica le devuelve un
    - objeto genérico (una interfaz `ReportGenerator`) listo para usar.
    - - Podría confundirse con **Abstract Factory**, pero Abstract Factory se
    - usa para crear *familias* enteras de objetos relacionados (ej. crear
    - Botón Windows, Scroll Windows, Panel Windows vs Botón Mac, etc.). Aquí
    - solo creamos un único producto (el reporte), por lo que Factory Method
    - o Simple Factory es suficiente y más directo.
    - - Podría pensarse en **Strategy**, pero Strategy se enfoca en intercambiar
    - comportamientos que ya fueron construidos e inyectados. Aquí el dolor está
    - en el momento mismo de **crear** el objeto según una configuración, terreno
    - exclusivo de los patrones creacionales.

- **Ejercicio14: [Ejercicio14.java](src/main/java/dosw/semana_3/taller/patrones/Ejercicio14.java)**
  - **Uso / Definición:** Cuando un pedido cambia de estado, hay que avisar a 4 sistemas distintos. Si mañana queremos que facturación también se entere, no queremos abrir ni tocar el código de la clase `Pedido`.
  - **Estructura / Ventajas:**
    - Patrón de Comportamiento (Behavioral Pattern).
    - (3) PATRÓN SELECCIONADO:
    - Observer (Observador) / Publisher-Subscriber.
    - (4) ¿POR QUÉ?:
    - El patrón Observer define una dependencia de uno a muchos entre objetos.
    - El "Sujeto" (Pedido) mantiene una lista de "Observadores" (Inventario,
    - Correo, etc.) que cumplen una interfaz común. Cuando el estado del Pedido
    - cambia, este simplemente recorre la lista avisándoles a todos.
    - Agregar un nuevo observador (como Facturación) solo implica crear la
    - nueva clase y añadirla a la lista del Pedido; la clase Pedido en sí nunca
    - se modifica. Esto cumple perfectamente con el principio OCP.
    - - Se podría pensar en el patrón **Mediator** (Mediador), que también
    - desacopla objetos. Sin embargo, Mediator se usa cuando hay una red
    - caótica de objetos que se comunican *entre sí* (todos contra todos).
    - Aquí tenemos un caso puro de "Uno hacia Muchos" de forma reactiva, lo
    - cual es el dominio absoluto del patrón Observer.

- **Ejercicio15: [Ejercicio15.java](src/main/java/dosw/semana_3/taller/patrones/Ejercicio15.java)**
  - **Uso / Definición:** Tenemos un sistema moderno que espera trabajar con una interfaz `PaymentProcessor` (pasando montos normales). Sin embargo, necesitamos integrarnos con un banco antiguo (`LegacyBankService`) que tiene métodos distintos, espera el dinero en centavos y no podemos modificar su código.
  - **Estructura / Ventajas:**
    - Patrón Estructural (Structural Pattern).
    - (3) PATRÓN SELECCIONADO:
    - Adapter (Adaptador / Wrapper).
    - (4) ¿POR QUÉ?:
    - El patrón Adapter se usa específicamente cuando tienes dos interfaces
    - incompatibles que necesitan trabajar juntas y no puedes (o no quieres)
    - modificar el código fuente de alguna de ellas (en este caso, el sistema legado).
    - Creamos una clase "Adaptador" que implementa la interfaz moderna que
    - nuestro sistema espera, pero por dentro traduce y redirige esas llamadas
    - a los métodos extraños del sistema antiguo (ej. multiplicando el monto por
    - 100 para pasarlo a centavos).
    - - Se podría confundir con **Facade** (Fachada). Ambos envuelven objetos
    - antiguos o complejos. Sin embargo, Facade busca *simplificar* un
    - subsistema muy grande (ocultar docenas de clases detrás de una sola).
    - El **Adapter** tiene un propósito más preciso: hacer que una interfaz
    - encaje con otra distinta que ya está predefinida, actuando como un
    - traductor entre dos piezas que no encajan.

- **Ejercicio16: [Ejercicio16.java](src/main/java/dosw/semana_3/taller/patrones/Ejercicio16.java)**
  - **Uso / Definición:** Tenemos dos dimensiones que pueden variar independientemente: el Tipo de Mensaje (Texto, Voz, Video) y el Algoritmo de Compresión (MP3, AAC, H.264, etc.). Queremos combinarlos libremente.
  - **Estructura / Ventajas:**
    - Patrón Estructural (Structural Pattern).
    - (3) PATRÓN SELECCIONADO:
    - Bridge (Puente).
    - (4) ¿POR QUÉ?:
    - El patrón Bridge es la solución de libro de texto cuando tienes dos o más
    - dimensiones ortogonales (independientes) que necesitas combinar. Si
    - intentáramos resolver esto con herencia tradicional, sufriríamos una
    - "explosión de subclases" creando clases como `VideoMessageH264`,
    - `VideoMessageHEVC`, `VoiceMessageMP3`, etc.
    - Bridge soluciona esto dividiendo la lógica en dos jerarquías de clases
    - separadas: la Abstracción (El Mensaje) y la Implementación (El Compresor).
    - Luego, se conectan mediante composición (El mensaje "tiene un" compresor).
    - - Podría parecerse a **Strategy**, y de hecho usan un mecanismo similar
    - (composición e inyección). Sin embargo, Strategy se enfoca en intercambiar
    - el comportamiento de *una* clase. Bridge está diseñado estructuralmente
    - para hacer crecer *dos jerarquías de clases independientes* a la vez,
    - permitiendo que crees nuevos tipos de mensajes y nuevos compresores
    - sin que una jerarquía afecte a la otra.

- **Ejercicio17: [Ejercicio17.java](src/main/java/dosw/semana_3/taller/patrones/Ejercicio17.java)**
  - **Uso / Definición:** Tenemos vehículos con múltiples parámetros de configuración (motor, transmisión, color, GPS, sonido, etc.), donde muchos son opcionales. El constructor clásico requeriría 15 argumentos, lo cual es inmanejable y propenso a errores (anti-patrón "Telescoping Constructor").
  - **Estructura / Ventajas:**
    - Patrón Creacional (Creational Pattern).
    - (3) PATRÓN SELECCIONADO:
    - Builder (Constructor).
    - (4) ¿POR QUÉ?:
    - El patrón Builder está diseñado específicamente para aislar la construcción
    - de un objeto complejo de su representación. En lugar de pasar decenas de
    - parámetros a un constructor, usamos un objeto intermedio (el Builder)
    - que nos permite configurar el vehículo paso a paso usando métodos claros
    - y legibles (ej. `.setEngine()`, `.addGps()`). Solo invocamos el método
    - final `.build()` cuando hemos terminado de configurarlo.
    - - Podría evaluarse el uso de **Factory Method**. Sin embargo, la fábrica
    - es ideal cuando la creación del objeto es de "un solo paso" (entregas el
    - parámetro y te devuelve el objeto listo). Cuando el objeto requiere
    - mucha configuración opcional paso a paso, la fábrica sufriría del mismo
    - problema de los 15 parámetros, por lo que **Builder** es abrumadoramente
    - superior en este caso.

- **Ejercicio18: [Ejercicio18.java](src/main/java/dosw/semana_3/taller/patrones/Ejercicio18.java)**
  - **Uso / Definición:** Una solicitud pasa por múltiples validaciones (autenticación, roles, geografía, etc.). La cantidad y tipo de validaciones puede variar según el entorno (ej. agregar MFA). Cada validación evalúa la solicitud y decide si la rechaza inmediatamente o la pasa al siguiente filtro.
  - **Estructura / Ventajas:**
    - Patrón de Comportamiento (Behavioral Pattern).
    - (3) PATRÓN SELECCIONADO:
    - Chain of Responsibility (Cadena de Responsabilidad).
    - (4) ¿POR QUÉ?:
    - El patrón Cadena de Responsabilidad permite pasar solicitudes a lo largo
    - de una cadena de manejadores (handlers). Al recibir una solicitud, cada
    - manejador decide si la procesa (ej. rechazando el acceso) o si la pasa al
    - siguiente eslabón de la cadena. Es perfecto aquí porque:
    - 1. Evita anidar un montón de bloques `if` en el código cliente.
    - 2. Permite ensamblar o modificar dinámicamente el orden o cantidad de
    - los filtros (ej. añadir MFA en producción pero no en desarrollo) en
    - tiempo de ejecución.
    - - Se podría confundir con **Decorator** (Decorador), que también envuelve
    - objetos de forma recursiva. Sin embargo, la diferencia conceptual clave es:
    - * Decorator busca *añadir comportamiento* a una petición sin detener la ejecución.
    - * Chain of Responsibility está diseñado específicamente para tener la
    - autoridad de *detener el flujo* (corto-circuito). Si el filtro de
    - autenticación falla, la cadena se rompe y jamás llega a evaluar el rol.

- **Ejercicio19: [Ejercicio19.java](src/main/java/dosw/semana_3/taller/patrones/Ejercicio19.java)**
  - **Uso / Definición:** Una plataforma de streaming compleja que involucra recomendaciones, tipos de usuario, algoritmos de búsqueda, notificaciones multicanal e integraciones de terceros. (2) ANÁLISIS DE PRINCIPIOS SOLID A CONSIDERAR: - SRP (Responsabilidad Única): El motor de recomendaciones, el buscador, el reproductor y el facturador deben ser módulos completamente separados. Ninguna "Clase Dios" debe orquestar todo esto. - OCP (Abierto/Cerrado): El sistema debe estar preparado para que mañana se agregue el algoritmo de búsqueda "Por Tendencias en Redes" sin tener que modificar la lógica del buscador principal. - LSP e ISP (Sustitución de Liskov y Segregación de Interfaces): Vital para los Tipos de Usuarios. Un usuario gratuito no debe verse obligado a implementar métodos de `descargarVideo()` (ISP), pero todos deben poder pasarse al reproductor de video sin que este falle (LSP). - DIP (Inversión de Dependencias): El núcleo de la aplicación de streaming jamás debe depender directamente de "PayU" o "Stripe". Debe depender de una interfaz `PaymentGateway`. (3) ANÁLISIS DE PATRONES DE DISEÑO A CONSIDERAR: 1. Algoritmos de Búsqueda -> **Strategy**: Encapsular las búsquedas (popularidad, relevancia) en distintas clases (Estrategias) y permitir que el usuario las seleccione en tiempo de ejecución. 2. Notificaciones Multicanal -> **Observer**: Cuando sale un nuevo episodio, el sistema notifica. Los canales (Push, Email) son "Observadores" suscritos a ese evento. 3. Integraciones Externas -> **Adapter / Facade**: Utilizar Adaptadores para conectar las APIs raras de subtítulos externos con nuestra interfaz local, y Fachadas para simplificar procesos complejos de pasarelas de pago. 4. Tipos de Usuario Complejos -> **Factory Method**: Para crear el perfil y las configuraciones de un nuevo usuario en el registro según el plan que haya pagado.

#### Solid

- **Ejercicio1: [Ejercicio1.java](src/main/java/dosw/semana_3/taller/solid/Ejercicio1.java)**

- **Ejercicio2: [Ejercicio2.java](src/main/java/dosw/semana_3/taller/solid/Ejercicio2.java)**

- **Ejercicio3: [Ejercicio3.java](src/main/java/dosw/semana_3/taller/solid/Ejercicio3.java)**

- **Ejercicio4: [Ejercicio4.java](src/main/java/dosw/semana_3/taller/solid/Ejercicio4.java)**

- **Ejercicio5: [Ejercicio5.java](src/main/java/dosw/semana_3/taller/solid/Ejercicio5.java)**

- **Ejercicio6: [Ejercicio6.java](src/main/java/dosw/semana_3/taller/solid/Ejercicio6.java)**
  - **Uso / Definición:** Un E-Commerce debe integrar constantemente nuevos medios de pago (cripto, PSE, Nequi) y el equipo teme que agregar estas opciones rompa el flujo de compra principal. (2) PRINCIPIO SOLID A APLICAR: OCP - Open/Closed Principle (Principio de Abierto/Cerrado). (3) JUSTIFICACIÓN TÉCNICA: El principal problema es el miedo a romper código existente al agregar funcionalidades nuevas. OCP dicta que el sistema (el flujo de compra) debe estar ABIERTO a la extensión (agregar PSE, Nequi), pero CERRADO a la modificación (no alterar la clase central `CheckoutService` con nuevos `if-else`). Al aplicar OCP, se elimina el riesgo de romper el flujo actual porque no se toca el código central que ya funciona, simplemente se agregan nuevas clases que el sistema central es capaz de usar a través de polimorfismo. (4) SOLUCIÓN PROPUESTA (Estructura): Se crea una interfaz `PaymentMethod`. Cada nuevo método de pago será una clase separada que implemente esta interfaz. El `CheckoutService` solo dependerá de la interfaz. (Muy similar a la solución del Ejercicio 3, y se implementa típicamente usando el patrón de diseño Strategy).

- **Ejercicio7: [Ejercicio7.java](src/main/java/dosw/semana_3/taller/solid/Ejercicio7.java)**

- **Ejercicio8: [Ejercicio8.java](src/main/java/dosw/semana_3/taller/solid/Ejercicio8.java)**

- **Ejercicio9: [Ejercicio9.java](src/main/java/dosw/semana_3/taller/solid/Ejercicio9.java)**

- **Ejercicio10: [Ejercicio10.java](src/main/java/dosw/semana_3/taller/solid/Ejercicio10.java)**


<br>
<div align="center">
  <h1>------------- Semana 4 -------------</h1>
</div>
<br>



### Taller

#### Ejercicio1

**Tema:** Plataforma de Pagos Inteligentes
**Patrones:** Abstract Factory + Strategy
**Descripción:** Combina una fábrica abstracta para proveer estrategias de pago según el país (Colombia vs USA) y usa Strategy para procesar el pago con la pasarela elegida (Nequi, PayPal, Stripe, etc.).


- **Checkout: [Checkout.java](src/main/java/dosw/semana_4/taller/ejercicio1/Checkout.java)**

- **ColombiaPaymentFactory: [ColombiaPaymentFactory.java](src/main/java/dosw/semana_4/taller/ejercicio1/ColombiaPaymentFactory.java)**

- **Main: [Main.java](src/main/java/dosw/semana_4/taller/ejercicio1/Main.java)**

- **NequiStrategy: [NequiStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio1/NequiStrategy.java)**

- **PayPalStrategy: [PayPalStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio1/PayPalStrategy.java)**

- **PaymentFactory: [PaymentFactory.java](src/main/java/dosw/semana_4/taller/ejercicio1/PaymentFactory.java)**

- **PaymentStrategy: [PaymentStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio1/PaymentStrategy.java)**

- **PseStrategy: [PseStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio1/PseStrategy.java)**

- **StripeStrategy: [StripeStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio1/StripeStrategy.java)**

- **TarjetaStrategy: [TarjetaStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio1/TarjetaStrategy.java)**

- **UsaPaymentFactory: [UsaPaymentFactory.java](src/main/java/dosw/semana_4/taller/ejercicio1/UsaPaymentFactory.java)**

#### Ejercicio10

**Tema:** Aplicación de Edición de Imágenes
**Patrones:** Command
**Descripción:** Encapsula las operaciones de edición (como aplicar un filtro Sepia) en objetos Command (`ApplyFilterCommand`). Esto permite aplicar, encolar o deshacer acciones sobre la imagen sin acoplar la interfaz gráfica a la lógica de edición.


- **ApplyFilterCommand: [ApplyFilterCommand.java](src/main/java/dosw/semana_4/taller/ejercicio10/ApplyFilterCommand.java)**

- **BaseImage: [BaseImage.java](src/main/java/dosw/semana_4/taller/ejercicio10/BaseImage.java)**

- **GrayscaleDecorator: [GrayscaleDecorator.java](src/main/java/dosw/semana_4/taller/ejercicio10/GrayscaleDecorator.java)**

- **Image: [Image.java](src/main/java/dosw/semana_4/taller/ejercicio10/Image.java)**

- **ImageCommand: [ImageCommand.java](src/main/java/dosw/semana_4/taller/ejercicio10/ImageCommand.java)**

- **ImageDecorator: [ImageDecorator.java](src/main/java/dosw/semana_4/taller/ejercicio10/ImageDecorator.java)**

- **ImageEditor: [ImageEditor.java](src/main/java/dosw/semana_4/taller/ejercicio10/ImageEditor.java)**

- **Main: [Main.java](src/main/java/dosw/semana_4/taller/ejercicio10/Main.java)**

- **SepiaDecorator: [SepiaDecorator.java](src/main/java/dosw/semana_4/taller/ejercicio10/SepiaDecorator.java)**

#### Ejercicio2

**Tema:** Sistema de Notificaciones Multicanal
**Patrones:** Observer
**Descripción:** Utiliza Observer para notificar cambios de estado en un pedido (Order) a múltiples canales de notificación (Email, SMS, Push) de manera desacoplada.


- **EmailMessageFactory: [EmailMessageFactory.java](src/main/java/dosw/semana_4/taller/ejercicio2/EmailMessageFactory.java)**

- **EmailNotifier: [EmailNotifier.java](src/main/java/dosw/semana_4/taller/ejercicio2/EmailNotifier.java)**

- **Main: [Main.java](src/main/java/dosw/semana_4/taller/ejercicio2/Main.java)**

- **Message: [Message.java](src/main/java/dosw/semana_4/taller/ejercicio2/Message.java)**

- **MessageFactory: [MessageFactory.java](src/main/java/dosw/semana_4/taller/ejercicio2/MessageFactory.java)**

- **NotificationObserver: [NotificationObserver.java](src/main/java/dosw/semana_4/taller/ejercicio2/NotificationObserver.java)**

- **Order: [Order.java](src/main/java/dosw/semana_4/taller/ejercicio2/Order.java)**

- **OrderEvent: [OrderEvent.java](src/main/java/dosw/semana_4/taller/ejercicio2/OrderEvent.java)**

- **PushMessageFactory: [PushMessageFactory.java](src/main/java/dosw/semana_4/taller/ejercicio2/PushMessageFactory.java)**

- **PushNotifier: [PushNotifier.java](src/main/java/dosw/semana_4/taller/ejercicio2/PushNotifier.java)**

- **SmsMessageFactory: [SmsMessageFactory.java](src/main/java/dosw/semana_4/taller/ejercicio2/SmsMessageFactory.java)**

- **SmsNotifier: [SmsNotifier.java](src/main/java/dosw/semana_4/taller/ejercicio2/SmsNotifier.java)**

#### Ejercicio3

**Tema:** Sistema de Reportes Empresariales
**Patrones:** Factory Method
**Descripción:** Usa un Factory Method (`ReportFactory`) para instanciar dinámicamente diferentes tipos de generadores de reportes (PDF, Excel, CSV) sin acoplar al cliente a las clases concretas.


- **CsvReport: [CsvReport.java](src/main/java/dosw/semana_4/taller/ejercicio3/CsvReport.java)**

- **ExcelReport: [ExcelReport.java](src/main/java/dosw/semana_4/taller/ejercicio3/ExcelReport.java)**

- **Main: [Main.java](src/main/java/dosw/semana_4/taller/ejercicio3/Main.java)**

- **PdfReport: [PdfReport.java](src/main/java/dosw/semana_4/taller/ejercicio3/PdfReport.java)**

- **ReportFactory: [ReportFactory.java](src/main/java/dosw/semana_4/taller/ejercicio3/ReportFactory.java)**

- **ReportGenerator: [ReportGenerator.java](src/main/java/dosw/semana_4/taller/ejercicio3/ReportGenerator.java)**

#### Ejercicio4

**Tema:** Plataforma de Videojuegos - Personajes
**Patrones:** Builder + Decorator
**Descripción:** Aplica Builder para construir paso a paso la estructura de un personaje complejo (guerrero, atributos) y luego usa Decorator para agregarle modificaciones dinámicas en tiempo de ejecución (escudos, pociones de velocidad).


- **BaseCharacter: [BaseCharacter.java](src/main/java/dosw/semana_4/taller/ejercicio4/BaseCharacter.java)**

- **Character: [Character.java](src/main/java/dosw/semana_4/taller/ejercicio4/Character.java)**

- **CharacterBuilder: [CharacterBuilder.java](src/main/java/dosw/semana_4/taller/ejercicio4/CharacterBuilder.java)**

- **CharacterDecorator: [CharacterDecorator.java](src/main/java/dosw/semana_4/taller/ejercicio4/CharacterDecorator.java)**

- **InvisibilityDecorator: [InvisibilityDecorator.java](src/main/java/dosw/semana_4/taller/ejercicio4/InvisibilityDecorator.java)**

- **Main: [Main.java](src/main/java/dosw/semana_4/taller/ejercicio4/Main.java)**

- **ShieldDecorator: [ShieldDecorator.java](src/main/java/dosw/semana_4/taller/ejercicio4/ShieldDecorator.java)**

- **SpeedDecorator: [SpeedDecorator.java](src/main/java/dosw/semana_4/taller/ejercicio4/SpeedDecorator.java)**

- **WarriorBuilder: [WarriorBuilder.java](src/main/java/dosw/semana_4/taller/ejercicio4/WarriorBuilder.java)**

#### Ejercicio5

**Tema:** Integración con Sistema Bancario Antiguo
**Patrones:** Facade
**Descripción:** Proporciona una fachada (`BankFacade`) que oculta la complejidad y múltiples pasos de un sistema bancario heredado, exponiendo una interfaz sencilla para procesar un pago.


- **BankFacade: [BankFacade.java](src/main/java/dosw/semana_4/taller/ejercicio5/BankFacade.java)**

- **LegacyBankAdapter: [LegacyBankAdapter.java](src/main/java/dosw/semana_4/taller/ejercicio5/LegacyBankAdapter.java)**

- **LegacyBankService: [LegacyBankService.java](src/main/java/dosw/semana_4/taller/ejercicio5/LegacyBankService.java)**

- **Main: [Main.java](src/main/java/dosw/semana_4/taller/ejercicio5/Main.java)**

- **PaymentProcessor: [PaymentProcessor.java](src/main/java/dosw/semana_4/taller/ejercicio5/PaymentProcessor.java)**

#### Ejercicio6

**Tema:** Motor de Recomendaciones
**Patrones:** Strategy + Observer
**Descripción:** Usa Strategy para intercambiar dinámicamente el algoritmo de recomendación de un usuario (Popularidad, Género, Historial) y Observer para notificar a los componentes de la UI (HomePage, SuggestedList) cuando las preferencias cambian.


- **Content: [Content.java](src/main/java/dosw/semana_4/taller/ejercicio6/Content.java)**

- **GenreStrategy: [GenreStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio6/GenreStrategy.java)**

- **HistoryStrategy: [HistoryStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio6/HistoryStrategy.java)**

- **HomePageComponent: [HomePageComponent.java](src/main/java/dosw/semana_4/taller/ejercicio6/HomePageComponent.java)**

- **Main: [Main.java](src/main/java/dosw/semana_4/taller/ejercicio6/Main.java)**

- **PopularityStrategy: [PopularityStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio6/PopularityStrategy.java)**

- **PreferenceObserver: [PreferenceObserver.java](src/main/java/dosw/semana_4/taller/ejercicio6/PreferenceObserver.java)**

- **RecommendationAlgorithm: [RecommendationAlgorithm.java](src/main/java/dosw/semana_4/taller/ejercicio6/RecommendationAlgorithm.java)**

- **SuggestedListComponent: [SuggestedListComponent.java](src/main/java/dosw/semana_4/taller/ejercicio6/SuggestedListComponent.java)**

- **User: [User.java](src/main/java/dosw/semana_4/taller/ejercicio6/User.java)**

#### Ejercicio7

**Tema:** Flujo de Aprobación de Documentos
**Patrones:** Chain of Responsibility + State
**Descripción:** Combina una cadena de responsabilidad (Líder -> Jurídico -> Financiero) para procesar un documento. Cada eslabón puede cambiar el estado interno del documento (Borrador -> En Revisión -> Aprobado/Rechazado) usando el patrón State.


- **ApprovedState: [ApprovedState.java](src/main/java/dosw/semana_4/taller/ejercicio7/ApprovedState.java)**

- **Document: [Document.java](src/main/java/dosw/semana_4/taller/ejercicio7/Document.java)**

- **DocumentHandler: [DocumentHandler.java](src/main/java/dosw/semana_4/taller/ejercicio7/DocumentHandler.java)**

- **DocumentState: [DocumentState.java](src/main/java/dosw/semana_4/taller/ejercicio7/DocumentState.java)**

- **DraftState: [DraftState.java](src/main/java/dosw/semana_4/taller/ejercicio7/DraftState.java)**

- **FinancieroHandler: [FinancieroHandler.java](src/main/java/dosw/semana_4/taller/ejercicio7/FinancieroHandler.java)**

- **InReviewState: [InReviewState.java](src/main/java/dosw/semana_4/taller/ejercicio7/InReviewState.java)**

- **JuridicoHandler: [JuridicoHandler.java](src/main/java/dosw/semana_4/taller/ejercicio7/JuridicoHandler.java)**

- **LiderHandler: [LiderHandler.java](src/main/java/dosw/semana_4/taller/ejercicio7/LiderHandler.java)**

- **Main: [Main.java](src/main/java/dosw/semana_4/taller/ejercicio7/Main.java)**

- **RejectedState: [RejectedState.java](src/main/java/dosw/semana_4/taller/ejercicio7/RejectedState.java)**

#### Ejercicio8

**Tema:** Sistema de Pedidos en Restaurante
**Patrones:** Builder + Observer
**Descripción:** Utiliza Builder para garantizar que la hamburguesa/pedido se construya de forma inmutable y válida, y luego Observer para disparar notificaciones a Cocina, Facturación y Entrega una vez que el pedido es confirmado.


- **BillingService: [BillingService.java](src/main/java/dosw/semana_4/taller/ejercicio8/BillingService.java)**

- **DeliveryService: [DeliveryService.java](src/main/java/dosw/semana_4/taller/ejercicio8/DeliveryService.java)**

- **KitchenService: [KitchenService.java](src/main/java/dosw/semana_4/taller/ejercicio8/KitchenService.java)**

- **Main: [Main.java](src/main/java/dosw/semana_4/taller/ejercicio8/Main.java)**

- **Meat: [Meat.java](src/main/java/dosw/semana_4/taller/ejercicio8/Meat.java)**

- **Order: [Order.java](src/main/java/dosw/semana_4/taller/ejercicio8/Order.java)**

- **OrderBuilder: [OrderBuilder.java](src/main/java/dosw/semana_4/taller/ejercicio8/OrderBuilder.java)**

- **OrderObserver: [OrderObserver.java](src/main/java/dosw/semana_4/taller/ejercicio8/OrderObserver.java)**

- **Size: [Size.java](src/main/java/dosw/semana_4/taller/ejercicio8/Size.java)**

#### Ejercicio9

**Tema:** Sistema de Autenticación Empresarial
**Patrones:** Chain of Responsibility + Strategy
**Descripción:** La autenticación real de credenciales (Huella, Password, OAuth) se resuelve usando Strategy. Una vez superada, una Cadena de Responsabilidad evalúa filtros adicionales de seguridad (Permisos, Ubicación geográfica).


- **AccessDeniedException: [AccessDeniedException.java](src/main/java/dosw/semana_4/taller/ejercicio9/AccessDeniedException.java)**

- **AuthResult: [AuthResult.java](src/main/java/dosw/semana_4/taller/ejercicio9/AuthResult.java)**

- **AuthService: [AuthService.java](src/main/java/dosw/semana_4/taller/ejercicio9/AuthService.java)**

- **AuthStrategy: [AuthStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio9/AuthStrategy.java)**

- **BiometricStrategy: [BiometricStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio9/BiometricStrategy.java)**

- **CredentialValidator: [CredentialValidator.java](src/main/java/dosw/semana_4/taller/ejercicio9/CredentialValidator.java)**

- **Credentials: [Credentials.java](src/main/java/dosw/semana_4/taller/ejercicio9/Credentials.java)**

- **GoogleStrategy: [GoogleStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio9/GoogleStrategy.java)**

- **LocationValidator: [LocationValidator.java](src/main/java/dosw/semana_4/taller/ejercicio9/LocationValidator.java)**

- **Main: [Main.java](src/main/java/dosw/semana_4/taller/ejercicio9/Main.java)**

- **PasswordStrategy: [PasswordStrategy.java](src/main/java/dosw/semana_4/taller/ejercicio9/PasswordStrategy.java)**

- **PermissionValidator: [PermissionValidator.java](src/main/java/dosw/semana_4/taller/ejercicio9/PermissionValidator.java)**

- **SecurityValidator: [SecurityValidator.java](src/main/java/dosw/semana_4/taller/ejercicio9/SecurityValidator.java)**
<br>
<div align="center">
  <h1>------------- Comandos Git -------------</h1>
</div>
<br>

Esta sección es una guía rápida de referencia con los comandos más útiles y prácticos de Git para el día a día.

### ⚙️ Configuración Inicial
- `git config --global user.name "Tu Nombre"`: Configura el nombre que aparecerá en tus commits.
- `git config --global user.email "tu@email.com"`: Configura el correo asociado a tus commits.
- `git init`: Inicializa un nuevo repositorio de Git en la carpeta actual.
- `git clone [url]`: Descarga un repositorio existente desde internet (ej. GitHub) a tu máquina local.

### 📝 Trabajando con Cambios (El flujo básico)
- `git status`: Muestra el estado de tus archivos (qué archivos han sido modificados, cuáles están listos para el commit, etc.). **¡Úsalo todo el tiempo!**
- `git add [archivo]`: Añade un archivo específico al _staging area_ (área de preparación), preparándolo para el commit.
- `git add .`: Añade **todos** los archivos modificados y nuevos al _staging area_.
- `git commit -m "Mensaje descriptivo"`: Guarda los cambios preparados permanentemente en el historial local con un mensaje que explica qué hiciste.

### 🌍 Sincronizando con GitHub (Remoto)
- `git remote add origin [url]`: Conecta tu repositorio local con un repositorio remoto en internet.
- `git push -u origin main`: Sube tus commits locales a la rama `main` del repositorio remoto por primera vez.
- `git push`: Sube tus nuevos commits al servidor (una vez ya está configurado el `-u`).
- `git pull`: Descarga los últimos cambios del servidor remoto y los fusiona con tu código local automáticamente.
- `git fetch`: Descarga los cambios del servidor remoto, pero **no** los fusiona con tu código (ideal para revisar antes de integrar).

### 🌿 Ramas (Branches)
- `git branch`: Lista todas las ramas locales. La rama actual tendrá un `*`.
- `git branch [nombre-rama]`: Crea una nueva rama sin moverte a ella.
- `git checkout [nombre-rama]` o `git switch [nombre-rama]`: Cambia a la rama especificada.
- `git checkout -b [nombre-rama]` o `git switch -c [nombre-rama]`: Crea una rama nueva y se cambia a ella inmediatamente.
- `git merge [nombre-rama]`: Fusiona la rama especificada hacia la rama en la que estás actualmente.

### ⏪ Deshaciendo Cambios
- `git restore [archivo]`: Descarta los cambios locales en un archivo que **no** has hecho `add`, regresándolo a como estaba en el último commit.
- `git restore --staged [archivo]`: Saca un archivo del _staging area_ (deshace el `git add`), pero conserva tus modificaciones en el código.
- `git reset --soft HEAD~1`: Deshace el último `commit` pero mantiene tus archivos modificados listos en el _staging area_.
- `git reset --hard HEAD~1`: **⚠️ PELIGROSO:** Deshace el último commit y **elimina completamente** todos los cambios de código.
- `git revert [hash-del-commit]`: Crea un *nuevo commit* que deshace los cambios de un commit anterior (la forma segura de deshacer algo que ya subiste a GitHub).

### 🔍 Revisando el Historial
- `git log`: Muestra el historial completo de commits (presiona `q` para salir).
- `git log --oneline`: Muestra el historial resumido (un commit por línea).
- `git diff`: Muestra exactamente qué líneas de código cambiaste en los archivos que aún no has hecho `add`.
- `git diff --staged`: Muestra los cambios de los archivos que ya hiciste `add` y están listos para el commit.

### 📦 Guardado Temporal (Stash)
- `git stash`: Guarda tus cambios actuales temporalmente en un cajón sin hacer commit (muy útil si necesitas cambiar de rama de urgencia).
- `git stash pop`: Saca los cambios del cajón y los vuelve a aplicar a tu código.
- `git stash list`: Muestra todos los guardados temporales que tienes.

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
