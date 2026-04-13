ADR 001 – Eliminación de FK compuesta innecesaria
Escritura

Título: Eliminación de FK compuesta en seat_assignment

Decisión:
Se elimina la clave foránea compuesta (ticket_segment_id, flight_segment_id) en la tabla seat_assignment.

Motivo:

ticket_segment_id ya es clave primaria en ticket_segment
La combinación era redundante
Complicaba el modelo sin aportar integridad adicional

Alternativas:

Mantener la FK compuesta
Crear una clave compuesta real en ticket_segment

Consecuencias:

Modelo más simple
Mejor rendimiento en consultas
Menor complejidad en mantenimiento

ADR 002 – Agregar FK faltante en seat_assignment
Escritura

Título: Adición de clave foránea para flight_segment_id

Decisión:
Se agrega una FK directa desde seat_assignment hacia flight_segment.

Motivo:

Se detectó que flight_segment_id no tenía restricción de integridad
Podía permitir datos inválidos

Alternativas:

Validar desde backend
Mantener sin FK

Consecuencias:

Se garantiza integridad referencial
Se evitan registros inconsistentes

ADR 003 – Validación de consistencia asiento-avión
Escritura

Título: Validación de coherencia entre asiento y avión del vuelo

Decisión:
Se implementará validación (trigger o lógica backend) para asegurar que el asiento pertenece al avión del vuelo.

Motivo:

Se podían asignar asientos de un avión a vuelos de otro
Generaba inconsistencias críticas

Alternativas:

No validar
Validar solo en backend

Consecuencias:

Mayor integridad de datos
Ligero aumento en complejidad
Necesidad de lógica adicional (trigger o servicio)

ADR 004 – Manejo de UNIQUE con valores NULL
Escritura

Título: Uso de índices únicos condicionales para campos opcionales

Decisión:
Se reemplazan constraints UNIQUE por índices únicos con condición WHERE IS NOT NULL en campos opcionales.

Motivo:

PostgreSQL permite múltiples NULL en UNIQUE
Puede generar duplicados no deseados

Alternativas:

Mantener UNIQUE actual
Hacer campos NOT NULL

Consecuencias:

Mayor control de unicidad
Mejora en calidad de datos

ADR 005 – Mejora en almacenamiento de contraseñas
Escritura

Título: Mejora en la columna password_hash

Decisión:
Se cambia el tipo de dato a TEXT y se define validación de longitud mínima.

Motivo:

varchar(255) es limitado para algunos algoritmos
No había validación de contenido

Alternativas:

Mantener varchar(255)
Validar solo en backend

Consecuencias:

Mayor seguridad
Flexibilidad para distintos algoritmos de hash

ADR 006 – Normalización de valores tipo ENUM
Escritura

Título: Normalización de valores de tipo mediante tablas catálogo

Decisión:
Se crean tablas para valores como transaction_type en lugar de usar CHECK.

Motivo:

Difícil de escalar
No reutilizable entre tablas

Alternativas:

Mantener CHECK
Usar ENUM nativo de PostgreSQL

Consecuencias:

Mayor flexibilidad
Mejor mantenibilidad
Más tablas en el modelo

ADR 007 – Automatización de updated_at
Escritura

Título: Automatización del campo updated_at

Decisión:
Se implementan triggers para actualizar automáticamente updated_at en cada UPDATE.

Motivo:

El campo no se actualizaba automáticamente
Se perdía trazabilidad

Alternativas:

Actualizar manualmente desde backend
No usar updated_at

Consecuencias:

Mejor auditoría de datos
Ligero costo en rendimiento por triggers