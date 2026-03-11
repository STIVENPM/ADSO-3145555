4. Requerimientos del sistema
Requerimientos funcionales (RF)

RF-01. Visualización del menú:
El sistema debe permitir al usuario visualizar el catálogo de productos disponibles en la cafetería.

RF-02. Consulta de información de productos:
El sistema debe mostrar información básica de cada producto (nombre, precio y disponibilidad).

RF-03. Agregar productos al carrito:
El sistema debe permitir al usuario agregar productos al carrito de compras.

RF-04. Modificar carrito de compras:
El sistema debe permitir editar el carrito, aumentando o disminuyendo cantidades de productos seleccionados.

RF-05. Eliminar productos del carrito:
El sistema debe permitir eliminar productos del carrito antes de confirmar el pedido.

RF-06. Confirmación del pedido:
El sistema debe permitir al usuario confirmar el pedido para enviarlo a la cafetería.


Requerimientos no funcionales (RNF)

RNF-01. Usabilidad:
El sistema debe contar con una interfaz sencilla, intuitiva y fácil de utilizar para cualquier usuario.

RNF-02. Rendimiento:
El sistema debe responder en tiempos cortos al cargar el menú y al registrar pedidos, evitando demoras.

RNF-03. Mantenibilidad:
El sistema debe permitir futuras mejoras o actualizaciones sin afectar su funcionamiento principal.


Reglas de negocio (RN)

RN-01. Disponibilidad de productos:
Solo se podrán solicitar productos que estén disponibles en el menú.

RN-02. Pedido válido:
El pedido solo podrá confirmarse si el carrito contiene al menos un producto.

RN-03.  Pedido confirmado:
Una vez confirmado el pedido, no podrá modificarse; si el usuario requiere cambios deberá generar un nuevo pedido.
























5. Priorización MoSCoW

La metodología MoSCoW permite clasificar los requerimientos del sistema según su nivel de prioridad para el desarrollo del MVP.

✅ Must Have (Debe tener)

Son funcionalidades obligatorias e indispensables para que el sistema funcione.

RF-01. Visualización del menú

RF-02. Consulta de información de productos

RF-03. Agregar productos al carrito

RF-04. Modificar carrito de compras

RF-05. Eliminar productos del carrito

RF-06. Confirmación del pedido

RN-01. Disponibilidad de productos

RN-02. Pedido válido

RN-03. Pedido confirmado

RNF-01. Usabilidad

RNF-02. Rendimiento

⭐ Should Have (Debería tener)

Son importantes para mejorar el sistema, pero no son estrictamente obligatorios en la primera versión.

RNF-03. Mantenibilidad

👍 Could Have (Podría tener)

Son características adicionales que mejoran la experiencia, pero se pueden dejar para una versión futura.

Notificaciones del estado del pedido (en preparación, listo, entregado).

Historial de pedidos del usuario.

Filtros o búsqueda de productos dentro del menú.

Confirmación visual del pedido con número de orden.

🚫 Won’t Have (No incluirá por ahora)

Son funciones que no se implementarán en el MVP inicial.

Servicio de domicilios o entregas fuera del SENA.

Pagos con tarjeta débito/crédito o pasarelas como Nequi, Daviplata, PayPal o PSE.

Reservas anticipadas de pedidos para fechas futuras.