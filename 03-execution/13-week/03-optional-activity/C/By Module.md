2. By Module (Modular / Feature Based)
Esta es una estructura más moderna.
El proyecto se organiza por módulos funcionales.

Estructura
PRJ-EDU-HORARIOS
│
├── Modules
│
│   ├── Instructor
│   │   ├── Entity
│   │   ├── Repository
│   │   ├── IService
│   │   ├── Service
│   │   ├── Controller
│   │   ├── DTO
│   │   └── Mapper
│   │
│   ├── Ambiente
│   │   ├── Entity
│   │   ├── Repository
│   │   ├── IService
│   │   ├── Service
│   │   ├── Controller
│   │   ├── DTO
│   │   └── Mapper
│   │
│   ├── Ficha
│   │   ├── Entity
│   │   ├── Repository
│   │   ├── IService
│   │   ├── Service
│   │   ├── Controller
│   │   ├── DTO
│   │   └── Mapper
│   │
│   ├── Horario
│   │   ├── Entity
│   │   ├── Repository
│   │   ├── IService
│   │   ├── Service
│   │   ├── Controller
│   │   ├── DTO
│   │   ├── Validator
│   │   └── ConflictEngine
│   │
│   └── Observacion
│       ├── Entity
│       ├── Repository
│       ├── IService
│       ├── Service
│       ├── Controller
│       ├── DTO
│       └── Mapper
│
├── Shared
│   ├── Security
│   ├── Config
│   ├── Exceptions
│   ├── Utils
│   └── Constants
│
└── Resources
    ├── application.yml
    └── static
¿Qué lleva cada componente?
Modules
Cada módulo contiene todo lo necesario para funcionar de manera independiente.

Entity
Representa las entidades del módulo.

Instructor
│
├── atributos
├── constructores
├── getters/setters
├── relaciones
└── validaciones básicas
Aquí normalmente va:

clases
atributos
relaciones
constructores
getters/setters
Repository
Acceso a base de datos del módulo.

Repository
│
├── save()
├── findById()
├── findAll()
├── delete()
└── consultas personalizadas
Aquí normalmente va:

interfaces
CRUD
consultas personalizadas
IService
Define los contratos del módulo.

IService
│
├── crear()
├── actualizar()
├── eliminar()
└── consultar()
Aquí normalmente va:

interfaces
métodos del sistema
contratos
Service
Implementa la lógica del negocio.

Service
│
├── validaciones
├── reglas de negocio
├── uso de repositories
└── transformación DTO ↔ Entity
Aquí normalmente va:

lógica de negocio
validaciones
procesos
implementación de interfaces
Controller
Recibe peticiones HTTP.

Controller
│
├── @GetMapping
├── @PostMapping
├── @PutMapping
└── @DeleteMapping
Aquí normalmente va:

endpoints REST
request
response
manejo HTTP
DTO
Objetos de transferencia de datos.

DTO
│
├── Request
└── Response
Request normalmente lleva:

datos enviados por el cliente
validaciones
Response normalmente lleva:

datos que devuelve el sistema
Mapper
Convierte objetos.

Mapper
│
├── DTO → Entity
└── Entity → DTO
Aquí normalmente va:

conversiones
transformaciones de datos
Validator
Validaciones complejas.

Validator
│
├── validarCruceHorario()
├── validarDisponibilidad()
└── validarCargaHoraria()
Aquí normalmente va:

validaciones del negocio
reglas complejas
ConflictEngine
Motor para detectar conflictos.

ConflictEngine
│
├── detectarCruces()
├── validarHorarios()
└── verificarDisponibilidad()
Aquí normalmente va:

análisis de conflictos
validaciones automáticas
Shared
Elementos compartidos entre módulos.

Shared
│
├── Security
├── Config
├── Exceptions
├── Utils
└── Constants
Aquí normalmente va:

seguridad
configuraciones
utilidades
excepciones
constantes globales
Resources
Archivos del sistema.

Resources
│
├── application.yml
└── static
¿Cómo se comporta esta arquitectura?
Flujo
Cliente
   ↓
Controller del módulo
   ↓
Service del módulo
   ↓
Repository del módulo
   ↓
Base de datos
¿Por qué esta arquitectura es moderna?
Porque:

organiza mejor proyectos grandes
cada módulo es independiente
facilita mantenimiento
mejora escalabilidad
facilita trabajo en equipo
reduce desorden del proyecto