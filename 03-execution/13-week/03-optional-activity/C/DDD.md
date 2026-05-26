3. DDD (Domain Driven Design)
DDD separa el sistema según el dominio del negocio.
Es ideal para proyectos grandes y escalables.

Estructura
PRJ-EDU-HORARIOS
│
├── Domain
│   │
│   ├── Instructor
│   │   ├── Entity
│   │   ├── ValueObject
│   │   ├── Repository
│   │   └── Rules
│   │
│   ├── Ambiente
│   │   ├── Entity
│   │   ├── ValueObject
│   │   ├── Repository
│   │   └── Rules
│   │
│   ├── Horario
│   │   ├── Entity
│   │   ├── ValueObject
│   │   ├── Repository
│   │   ├── Services
│   │   └── Policies
│   │
│   └── Observacion
│       ├── Entity
│       ├── Repository
│       └── Rules
│
├── Application
│   │
│   ├── UseCases
│   │   ├── CrearHorario
│   │   ├── ValidarCruceHorario
│   │   ├── ConsultarDisponibilidad
│   │   ├── RegistrarObservacion
│   │   └── GenerarCargaHoraria
│   │
│   ├── DTO
│   ├── Mappers
│   └── Interfaces
│
├── Infrastructure
│   │
│   ├── Persistence
│   │   ├── JpaRepositories
│   │   └── Database
│   │
│   ├── Security
│   ├── Config
│   ├── ExternalServices
│   └── Notifications
│
├── Presentation
│   ├── Controllers
│   ├── REST
│   ├── GraphQL
│   └── Swagger
│
├── Shared
│   ├── Exceptions
│   ├── Utils
│   ├── Constants
│   └── Validators
│
└── Resources
    └── application.yml
¿Qué lleva cada componente?
Domain
Es el corazón del sistema.
Aquí vive la lógica del negocio.

Entity
Representa entidades principales.

Entity
│
├── atributos
├── relaciones
├── constructores
└── comportamiento
Aquí normalmente va:

clases principales
atributos
reglas básicas
relaciones
ValueObject
Objetos pequeños sin identidad propia.

ValueObject
│
├── Email
├── Password
├── Fecha
└── Direccion
Aquí normalmente va:

validaciones encapsuladas
objetos reutilizables
Repository
Define contratos para acceder a datos.

Repository
│
├── save()
├── findById()
├── findAll()
└── delete()
Aquí normalmente va:

interfaces
contratos de persistencia
Rules
Reglas del negocio.

Rules
│
├── validarCruce()
├── validarDisponibilidad()
└── validarEstado()
Aquí normalmente va:

reglas empresariales
validaciones complejas
Services
Lógica avanzada del dominio.

Services
│
├── generarHorario()
└── calcularCarga()
Aquí normalmente va:

procesos complejos
lógica compartida
Policies
Políticas del negocio.

Policies
│
├── politicaHorarios
└── politicaDisponibilidad
Aquí normalmente va:

comportamientos
restricciones del sistema
Application
Coordina casos de uso del sistema.

UseCases
Acciones que realiza el usuario.

UseCases
│
├── CrearHorario
├── ConsultarDisponibilidad
└── GenerarCargaHoraria
Aquí normalmente va:

procesos del sistema
coordinación de acciones
DTO
Objetos para transferencia de datos.

Mappers
Transformación de datos.

Mapper
│
├── DTO → Entity
└── Entity → DTO
Interfaces
Contratos entre capas.

Infrastructure
Parte técnica del sistema.

Persistence
Acceso real a base de datos.

Persistence
│
├── JpaRepositories
└── Database
Aquí normalmente va:

JPA
Hibernate
conexión BD
Security
Seguridad del sistema.

Config
Configuraciones globales.

ExternalServices
Servicios externos.

ExternalServices
│
├── APIs
├── Firebase
└── Cloudinary
Notifications
Sistema de notificaciones.

Presentation
Entrada del usuario al sistema.

Controllers
Endpoints REST.

REST
Configuraciones REST.

GraphQL
Endpoints GraphQL.

Swagger
Documentación automática.

Shared
Elementos reutilizables.

Shared
│
├── Exceptions
├── Utils
├── Constants
└── Validators
Aquí normalmente va:

excepciones
utilidades
validaciones
constantes
Resources
Archivos del sistema.

Resources
│
└── application.yml
¿Cómo se comporta esta arquitectura?
Flujo
Cliente
   ↓
Presentation
   ↓
Application
   ↓
Domain
   ↓
Infrastructure
   ↓
Base de datos
¿Por qué esta arquitectura es poderosa?
Porque:

separa negocio de tecnología
mejora escalabilidad
facilita mantenimiento
reduce acoplamiento
permite proyectos grandes
facilita microservicios
mejora testing