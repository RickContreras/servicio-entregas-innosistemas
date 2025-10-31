## Instalación y Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/luisasoto12/servicio-entregas-innosistemas.git
cd servicio-entregas-innosistemas
```

### 2. Configurar las variables de entorno

Configura las siguientes variables de entorno con las credenciales de base de datos:

**En Linux/Mac:**
```bash
export DB_URL="jdbc:postgresql://ep-muddy-mouse-af2imat3-pooler.c-2.us-west-2.aws.neon.tech:5432/neondb?sslmode=require&channel_binding=require"
export DB_USERNAME="neondb_owner"
export DB_PASSWORD="npg_GA2k9waJYIBn"
```

**En Windows (CMD):**
```cmd
set DB_URL=jdbc:postgresql://ep-muddy-mouse-af2imat3-pooler.c-2.us-west-2.aws.neon.tech:5432/neondb?sslmode=require&channel_binding=require
set DB_USERNAME=neondb_owner
set DB_PASSWORD=npg_GA2k9waJYIBn
```

**En Windows (PowerShell):**
```powershell
$env:DB_URL="jdbc:postgresql://ep-muddy-mouse-af2imat3-pooler.c-2.us-west-2.aws.neon.tech:5432/neondb?sslmode=require&channel_binding=require"
$env:DB_USERNAME="neondb_owner"
$env:DB_PASSWORD="npg_GA2k9waJYIBn"
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
