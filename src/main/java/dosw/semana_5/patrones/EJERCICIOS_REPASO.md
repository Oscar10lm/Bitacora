# 🎓 Batería de Ejercicios para el Parcial

Resuelve cada uno de estos ejercicios interactivos (usando `Scanner`) en el paquete correspondiente de tu bitácora.

## Ejercicios Individuales (Básicos)

### 1. Iterator: Reproductor de Música
**Reto:** Crea una lista de reproducción y un iterador. Usa `Scanner` en un bucle `while` para preguntarle al usuario:
`1. Siguiente canción | 2. Ver actual | 3. Salir`.
El sistema debe avanzar el iterador e imprimir la canción correspondiente.

### 2. Composite: Sistema de Archivos
**Reto:** Implementa un `Archivo` y una `Carpeta` (ambos comparten la interfaz `Componente`). Usa `Scanner` para que el usuario cree una estructura en memoria (ej: "Crear carpeta 'Fotos'", "Agregar archivo de 10MB a 'Fotos'"). Al final, pide imprimir el tamaño total de la raíz.

### 3. Builder: Pizza Personalizada
**Reto:** Usa `Scanner` para preguntar al cliente su orden paso a paso: ¿Masa delgada o gruesa?, ¿Salsa BBQ o Tomate?, ¿Desea Pepperoni? (Sí/No). El `PizzaBuilder` debe ir construyendo el objeto paso a paso y al final devolver el objeto `Pizza` armado para imprimir sus detalles.

### 4. Decorator: Cafetería
**Reto:** Tienes un `CafeBase` que cuesta $5000. Usa `Scanner` para preguntarle al cliente: "¿Añadir Leche ($1000)?", "¿Añadir Caramelo ($1500)?". Por cada respuesta positiva, envuelve tu café en un nuevo decorador. Al final, imprime el costo total y la descripción completa.

### 5. Chain of Responsibility: Soporte IT
**Reto:** Crea los manejadores: `Bot`, `Asesor`, `Ingeniero`, `Gerente`. Usa `Scanner` para ingresar la gravedad de un problema (1 al 5). Pasa el problema al primer manejador y deja que la cadena decida automáticamente quién lo resuelve.

### 6. Adapter: Pagos en Criptomonedas
**Reto:** Tienes un procesador que solo acepta una interfaz `PagoTarjeta`. El usuario selecciona pagar con "Bitcoin" en la consola (`Scanner`). Debes usar un `Adapter` que reciba los datos de la tarjeta pero se conecte por debajo a una clase `CryptoAPI` incompatible.

---

## 🔥 Ejercicios Combinados (Nivel Parcial)

### 7. Combo 1: Builder + Decorator (Computadores Gamer)
**Reto:** El cliente configura su PC base con `Scanner` (RAM, CPU) usando **Builder**. Antes de pagar, se le pregunta si desea luces RGB o Refrigeración (Decoraciones extra). Aplica el **Decorator** sobre el objeto construido por el Builder para calcular el precio final.

### 8. Combo 2: Composite + Iterator (Menú Complejo)
**Reto:** Tienes categorías principales (Desayuno, Almuerzo) que dentro tienen Subcategorías o Platos finales (**Composite**). Debes crear un **Iterator** que se encargue de aplanar todo ese árbol (usando recursividad o una pila) y lo imprima en orden de consola cuando el usuario pulse 'Ver Menú completo'.

### 9. Combo 3: Chain of Responsibility + Adapter (Filtro Bancario)
**Reto:** Un cliente ingresa una transacción bancaria. Pasa por una cadena de filtros de seguridad (**CoR**): *VerificarSaldo* -> *VerificarFraude* -> *ProcesarPago*. Si todo está bien, el último eslabón necesita usar la API del Banco Nacional, la cual es vieja y usa XML, por lo que requieres un **Adapter** para procesarlo.

### 10. Combo 4: Iterator + Chain of Responsibility (Lector de Logs)
**Reto:** Crea una colección masiva de 10 alertas de sistema. Usa un **Iterator** para leerlas una a una y lanzarlas a una **Cadena de Responsabilidad** (*LoggerFiltro -> AlertaEmailFiltro -> ApagarServidorFiltro*) según la gravedad de la alerta.

### 11. Combo 5: Builder + Adapter (Importadora de Vehículos)
**Reto:** Construyes un `AutoEuropeo` (Kilómetros, Litros) paso a paso usando **Builder**. Luego, el cliente indica que quiere exportarlo a USA. Debes pasarlo por un **Adapter** que lo envuelva y convierta todos sus métodos a Millas y Galones al imprimirlo.

### 12. Combo 6: Composite + Decorator (Diseño UI)
**Reto:** Crea una interfaz `ElementoUI`. Puedes tener `Boton` y `Panel` (que contiene botones) (**Composite**). A ciertos botones específicos, envuélvelos en un `BordeRojoDecorator` o `SombraDecorator`. Al imprimir el Panel completo, los botones decorados deben mostrar sus mejoras visuales.

### 13. Combo 7: Iterator + Adapter (Migración BD)
**Reto:** Tienes una `BaseDeDatosLegacy` que retorna arreglos de Strings raros. Usa un **Adapter** para que implemente la interfaz `Iterable<UsuarioModerno>`, lo que te permitirá recorrerla transparentemente usando un **Iterator** estándar y mostrarlos bonito en consola.

### 14. 👑 Combo Supremo: Fábrica de Robots (Builder + Composite + Decorator + CoR)
**Reto Final:**
1. Pregunta al usuario por consola qué robot construir y usa un **Builder** para armar su núcleo.
2. El robot es un **Composite** complejo (Cabeza, Torso, Brazos).
3. Añádele armas especiales usando **Decorator** según lo que el usuario pida.
4. Antes de entregarlo, pásalo por una **Cadena** de sensores de control de calidad (Peso, Batería, Armamento). Si pasa todos los filtros de la cadena, ¡el robot está listo!
