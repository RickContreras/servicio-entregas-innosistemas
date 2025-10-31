# 📦 Servicio de Entregas - Innosistemas

API REST para la gestión de entregas desarrollada con Spring Boot 3.5.7 y PostgreSQL.

## 🚀 Características

- ✅ CRUD completo de entregas
- ✅ Validación de datos con excepciones personalizadas
- ✅ Documentación interactiva con Swagger/OpenAPI
- ✅ Migraciones de base de datos con Flyway
- ✅ Base de datos PostgreSQL en la nube (Neon)
- ✅ Manejo automático de fechas con `LocalDateTime`

## 📋 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **Java 21** o superior
- **Maven 3.8+** (o usar el wrapper incluido `./mvnw`)
- **PostgreSQL** (o acceso a la base de datos en la nube)
- **Git** para clonar el repositorio

## 🔧 Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/luisasoto12/servicio-entregas-innosistemas.git
cd servicio-entregas-innosistemas
```

### 2. Configurar las variables de entorno

Configura las siguientes variables de entorno con tus credenciales de base de datos:

**En Linux/Mac:**
```bash
export DB_URL="jdbc:postgresql://tu-host:5432/tu-base-de-datos?sslmode=require&channel_binding=require"
export DB_USERNAME="tu-usuario"
export DB_PASSWORD="tu-contraseña"
```

**En Windows (CMD):**
```cmd
set DB_URL=jdbc:postgresql://tu-host:5432/tu-base-de-datos?sslmode=require&channel_binding=require
set DB_USERNAME=tu-usuario
set DB_PASSWORD=tu-contraseña
```

**En Windows (PowerShell):**
```powershell
$env:DB_URL="jdbc:postgresql://tu-host:5432/tu-base-de-datos?sslmode=require&channel_binding=require"
$env:DB_USERNAME="tu-usuario"
$env:DB_PASSWORD="tu-contraseña"
```

### 3. Compilar el proyecto

```bash
./mvnw clean install -DskipTests
```

### 4. Ejecutar las migraciones de base de datos

Las migraciones se ejecutan automáticamente al iniciar la aplicación gracias a Flyway. Los scripts se encuentran en:

- `src/main/resources/db/migration/V1__create_deliveries_table.sql` - Crea la tabla de entregas
- `src/main/resources/db/migration/V2__insert_sample_data.sql` - Inserta datos de ejemplo

### 5. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

La aplicación se iniciará en `http://localhost:8080`

## 📚 Estructura de la Base de Datos

### Tabla: `deliveries`

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id` | SERIAL (PK) | Identificador único de la entrega |
| `title` | VARCHAR(255) | Título de la entrega (obligatorio) |
| `description` | TEXT | Descripción detallada de la entrega |
| `file_url` | VARCHAR(512) | URL del archivo asociado |
| `created_at` | TIMESTAMP | Fecha de creación (auto-generada) |
| `team_id` | INTEGER | ID del equipo responsable (obligatorio) |

### Índices:
- `idx_deliveries_team_id` - Índice en `team_id`
- `idx_deliveries_created_at` - Índice en `created_at`

## 🌐 Endpoints de la API

### Base URL: `http://localhost:8080/api/deliveries`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/deliveries` | Obtener todas las entregas |
| `GET` | `/api/deliveries/{id}` | Obtener una entrega por ID |
| `POST` | `/api/deliveries` | Crear una nueva entrega |
| `PUT` | `/api/deliveries/{id}` | Actualizar una entrega existente |
| `DELETE` | `/api/deliveries/{id}` | Eliminar una entrega |

### Ejemplo de JSON para crear/actualizar:

```json
{
  "title": "Entrega de módulo de autenticación",
  "description": "Implementación completa del sistema de login",
  "file_url": "https://ejemplo.com/archivo.pdf",
  "team_id": 301
}
```

## 📖 Documentación con Swagger

Una vez que la aplicación esté ejecutándose, puedes acceder a la documentación interactiva de Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

Desde allí podrás:
- Ver todos los endpoints disponibles
- Probar las peticiones directamente desde el navegador
- Ver los modelos de datos
- Consultar los códigos de respuesta

## 🧪 Pruebas con Postman

### GET - Obtener todas las entregas
```
GET http://localhost:8080/api/deliveries
```

### GET - Obtener una entrega específica
```
GET http://localhost:8080/api/deliveries/1
```

### POST - Crear una nueva entrega
```
POST http://localhost:8080/api/deliveries
Content-Type: application/json

{
  "title": "Nueva entrega",
  "description": "Descripción de la entrega",
  "file_url": "https://ejemplo.com/archivo.pdf",
  "team_id": 301
}
```

### PUT - Actualizar una entrega
```
PUT http://localhost:8080/api/deliveries/1
Content-Type: application/json

{
  "id": 1,
  "title": "Entrega actualizada",
  "description": "Descripción actualizada",
  "file_url": "https://ejemplo.com/nuevo-archivo.pdf",
  "team_id": 301
}
```

### DELETE - Eliminar una entrega
```
DELETE http://localhost:8080/api/deliveries/1
```

## ⚠️ Manejo de Excepciones

La API devuelve mensajes de error en español:

- **400 Bad Request**: Cuando los datos enviados son inválidos
  - "La entrega no puede ser nula"
  - "El título no puede estar vacío"
  - "El team_id es obligatorio"
  
- **404 Not Found**: Cuando no se encuentra un recurso
  - "Entrega no encontrada con ID: {id}"
  
- **500 Internal Server Error**: Para errores del servidor

## 🗄️ Migraciones de Base de Datos (Flyway)

### Ubicación de los scripts:
```
src/main/resources/db/migration/
├── V1__create_deliveries_table.sql
└── V2__insert_sample_data.sql
```

### Configuración de Flyway en `application.properties`:
```properties
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
spring.flyway.validate-on-migrate=false
```

### Crear una nueva migración:

1. Crea un nuevo archivo en `src/main/resources/db/migration/`
2. Nombra el archivo siguiendo el patrón: `V{version}__{descripcion}.sql`
   - Ejemplo: `V3__add_status_column.sql`
3. Escribe el script SQL
4. Reinicia la aplicación para aplicar la migración

## 🛠️ Tecnologías Utilizadas

- **Spring Boot 3.5.7** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **Hibernate** - ORM
- **PostgreSQL** - Base de datos
- **Flyway** - Migraciones de base de datos
- **SpringDoc OpenAPI (Swagger)** - Documentación de API
- **Maven** - Gestión de dependencias
- **HikariCP** - Pool de conexiones

## 📁 Estructura del Proyecto

```
crud-entregas/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/udea/crudentregas/
│   │   │   │   ├── CrudEntregasApplication.java
│   │   │   │   └── config/
│   │   │   │       └── OpenApiConfig.java
│   │   │   ├── controller/
│   │   │   │   └── DeliveryController.java
│   │   │   ├── domain/
│   │   │   │   └── Delivery.java
│   │   │   ├── exception/
│   │   │   │   ├── BadRequestException.java
│   │   │   │   ├── ResourceNotFoundException.java
│   │   │   │   ├── ErrorResponse.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── repository/
│   │   │   │   └── DeliveryRepository.java
│   │   │   └── service/
│   │   │       ├── DeliveryService.java
│   │   │       └── DeliveryServiceImpl.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── db/
│   │           └── migration/
│   │               ├── V1__create_deliveries_table.sql
│   │               └── V2__insert_sample_data.sql
│   └── test/
├── pom.xml
├── .gitignore
└── README.md
```

## 🐛 Solución de Problemas

### Puerto 8080 ya está en uso
```bash
# En Linux/Mac
lsof -ti:8080 | xargs kill -9

# En Windows
netstat -ano | findstr :8080
taskkill /PID [PID_DEL_PROCESO] /F
```

### Error de conexión a la base de datos
- Verifica que las variables de entorno estén configuradas correctamente
- Asegúrate de que la base de datos esté accesible
- Verifica las credenciales de acceso

### Flyway checksum mismatch
Si modificas un script de migración ya aplicado, puedes desactivar temporalmente la validación:
```properties
spring.flyway.validate-on-migrate=false
```

## 👥 Autor

**Luisa Soto**
- GitHub: [@luisasoto12](https://github.com/luisasoto12)

## 📝 Licencia

Este proyecto es parte de Innosistemas - Universidad de Antioquia.
