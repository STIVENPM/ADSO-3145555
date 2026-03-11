Modelo de datos propuesto
Entidades y campos principales

Usuario

id_usuario (PK)
nombre
rol
Producto

id_producto (PK)
nombre
precio
Pedido

id_pedido (PK)
fecha
estado
id_usuario (FK)
Detalle_Pedido

id_detalle (PK)
cantidad
id_pedido (FK)
id_producto (FK)