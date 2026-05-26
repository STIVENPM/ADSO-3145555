4. MVC (Model View Controller)
Esta estructura separa:

Modelo → lógica y datos
Vista → interfaz
Controlador → conexión entre ambos
Estructura
PRJ-EDU-HORARIOS
│
├── Model
│   ├── Instructor
│   ├── Ambiente
│   ├── Ficha
│   ├── Horario
│   ├── Observacion
│   └── Disponibilidad
│
├── View
│   ├── InstructorView
│   ├── AmbienteView
│   ├── HorarioView
│   ├── Dashboard
│   └── Reportes
│
├── Controller
│   ├── InstructorController
│   ├── AmbienteController
│   ├── HorarioController
│   ├── ObservacionController
│   └── ReporteController
│
├── Service
│   ├── HorarioService
│   ├── ConflictService
│   └── ReportService
│
├── Repository
│   ├── InstructorRepository
│   ├── AmbienteRepository
│   ├── HorarioRepository
│   └── ObservacionRepository
│
├── Config
├── Utils
├── Exceptions
└── Resources
¿Qué lleva cada componente?
Model
Representa los datos y lógica del sistema.

Model
│
├── atributos
├── relaciones
├── constructores
└── getters/setters
Aquí normalmente va:

entidades
atributos
lógica básica
relaciones
View
Es la interfaz visual del usuario.

View
│
├── formularios
├── tablas
├── dashboard
└── reportes
Aquí normalmente va:

pantallas
vistas
formularios
reportes
Controller
Conecta la vista con el sistema.

Controller
│
├── recibir peticiones
├── llamar services
└── devolver respuestas
Aquí normalmente va:

endpoints
manejo HTTP
request/response
Service
Contiene la lógica del negocio.

Service
│
├── validaciones
├── reglas
└── procesos
Aquí normalmente va:

lógica del sistema
procesos
validaciones
Repository
Acceso a base de datos.

Repository
│
├── save()
├── findAll()
├── findById()
└── delete()
Aquí normalmente va:

CRUD
consultas
acceso BD
Config
Configuraciones generales.

Config
│
├── Security
├── Swagger
└── Database
Utils
Herramientas reutilizables.

Utils
│
├── helpers
├── validators
└── converters
Exceptions
Manejo de errores.

Exceptions
│
├── ValidationException
├── ConflictException
└── GlobalExceptionHandler
Resources
Archivos del sistema.

Resources
│
├── application.yml
└── static
¿Cómo se comporta esta arquitectura?
Flujo
Usuario
   ↓
View
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Base de datos
¿Por qué usar MVC?
Porque:

es fácil de entender
separa responsabilidades
organiza mejor el proyecto
facilita mantenimiento
es muy usado en aplicaciones web
ideal para proyectos medianos