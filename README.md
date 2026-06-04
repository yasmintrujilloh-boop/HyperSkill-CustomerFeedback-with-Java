# Customer Feedback Service

Una aplicación Spring Boot para gestionar feedback de clientes usando MongoDB.

## Requisitos

- Java 17 o superior
- MongoDB en ejecución localmente (puerto 27017) o configurable en `src/resources/application.properties`
- Gradle (incluido: `./gradlew` para Linux/Mac o `gradlew.bat` para Windows)

## Configuración

### MongoDB

Por defecto, la aplicación intenta conectarse a MongoDB en:
- Host: `localhost`
- Puerto: `27017`
- Base de datos: `feedback_db`

Para cambiar la configuración, edita `src/resources/application.properties`:

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=feedback_db
```

### Con Docker

Si tienes Docker instalado, puedes ejecutar MongoDB con:

```bash
docker run -d -p 27017:27017 --name mongodb mongo:5
```

## Compilación y Ejecución

### Compilar

```bash
./gradlew build
```

### Ejecutar tests

```bash
./gradlew test
```

### Ejecutar la aplicación

```bash
./gradlew bootRun
```

O después de compilar:

```bash
java -jar build/libs/customer-feedback-service-1.0.0.jar
```

## API Endpoints

- `GET /actuator/health` - Estado de la aplicación
- Otros endpoints dependen de la implementación específica

## Estructura del Proyecto

```
src/
├── feedbackservice/
│   ├── FeedbackApplication.java      # Clase principal
│   ├── config/                       # Configuración
│   ├── controller/                   # Controladores REST
│   ├── dto/                          # Data Transfer Objects
│   ├── model/                        # Modelos de datos
│   ├── repository/                   # Repositorio MongoDB
│   └── service/                      # Lógica de negocio
└── resources/
    └── application.properties         # Configuración de la app

test/
├── FeedbackItem.java
└── FeedbackServiceTests.java
```

## Tecnologías

- Spring Boot 3.0.0
- Spring Data MongoDB
- Spring Validation
- JUnit 5
- TestContainers

## Licencia

MIT

