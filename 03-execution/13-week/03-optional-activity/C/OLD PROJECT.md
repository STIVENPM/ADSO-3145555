1. OLD PROJECT
Estructura
PRJ-EDU-HORARIOS
│
├── Entity
│
├── Repository
│
├── IService
│
├── Service
│
├── Controller
│
├── DTO
│
├── Config
│
├── Utils
│
├── Exceptions
│
└── Resources
¿Qué lleva cada componente?
Entity
Representa las entidades del sistema.

Entity
│
├── Instructor
│   ├── atributos
│   ├── constructores
│   ├── getters/setters
│   ├── relaciones
│   └── validaciones básicas
│
├── Ambiente
├── Horario
├── Ficha
└── Observacion
Aquí normalmente va:

clases
atributos
constructores
getters/setters
relaciones entre entidades
Repository
Encargado de acceder a la base de datos.

Repository
│
├── InstructorRepository
│   ├── save()
│   ├── findById()
│   ├── delete()
│   ├── findAll()
│   └── consultas personalizadas
│
├── HorarioRepository
└── AmbienteRepository
Aquí normalmente va:

interfaces
métodos CRUD
consultas personalizadas
IService
Define qué debe hacer el servicio.

IService
│
├── IHorarioService
│   ├── crearHorario()
│   ├── validarCruce()
│   ├── buscarDisponibilidad()
│   └── obtenerCargaHoraria()
Aquí normalmente va:

interfaces
definición de métodos
contratos del sistema
Service
Implementa la lógica de negocio.

Service
│
├── HorarioService
│   ├── implementa IHorarioService
│   ├── validaciones
│   ├── reglas de negocio
│   ├── uso de repositories
│   └── transformación DTO ↔ Entity
Aquí normalmente va:

clases service
implementación de interfaces
lógica del negocio
validaciones complejas
comunicación con repositories
Controller
Recibe peticiones HTTP.

Controller
│
├── HorarioController
│   ├── @GetMapping
│   ├── @PostMapping
│   ├── @PutMapping
│   ├── @DeleteMapping
│   └── respuestas HTTP
Aquí normalmente va:

endpoints REST
request
response
validaciones básicas
manejo HTTP
DTO
Objetos para transferencia de datos.

DTO
│
├── Request
│   ├── CrearHorarioRequest
│   └── CrearInstructorRequest
│
└── Response
    ├── HorarioResponse
    └── InstructorResponse
Request normalmente lleva:

atributos enviados por el cliente
validaciones
Response normalmente lleva:

datos que devuelve el sistema
estructuras simplificadas
Config
Configuraciones globales.

Config
│
├── SecurityConfig
├── SwaggerConfig
├── CorsConfig
├── DatabaseConfig
└── JWTConfig
Aquí normalmente va:

seguridad
swagger
CORS
JWT
configuración de base de datos
Utils
Herramientas reutilizables.

Utils
│
├── DateUtils
├── TimeUtils
├── ConflictValidator
├── JWTUtils
└── MapperUtils
Aquí normalmente va:

funciones reutilizables
validadores auxiliares
conversores
helpers
Exceptions
Manejo de errores.

Exceptions
│
├── ResourceNotFoundException
├── ConflictException
├── ValidationException
└── GlobalExceptionHandler
Aquí normalmente va:

excepciones personalizadas
manejo global de errores
respuestas de error
Resources
Archivos del sistema.

Resources
│
├── application.yml
├── application-dev.yml
├── data.sql
└── static
¿Cómo se comporta esta arquitectura?
Flujo
Cliente
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Base de datos