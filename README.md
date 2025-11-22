# 🗡️ Strategic Coordination System of the Demon Slayer Corps

## 📋 Description

REST API developed in Java with Spring Boot for strategic coordination during the Infinite Castle Battle. The system allows triangulating enemy positions, reconstructing scattered tactical messages, and synchronizing Pillar attacks.

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **MySQL 8.0**
- **Maven**
- **Lombok**

## 📦 Prerequisites

- Java JDK 17 or higher
- Maven 3.6+
- MySQL 8.0 or higher
- IDE (IntelliJ IDEA, Eclipse, VS Code)

## 🚀 Setup and Installation

### 1. Clone the repository

```bash
git clone <repository-url>
cd castillo-infinito-api
```

### 2. Configure MySQL

Create a database in MySQL:

```sql
CREATE DATABASE castillo_infinito;
```

Or the database will be created automatically if you have permissions (according to the configuration in `application.properties`).

### 3. Configure credentials

Edit the file `src/main/resources/application.properties` and adjust the MySQL credentials:

```properties
spring.datasource.username=your_user
spring.datasource.password=your_password
```

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

Or run the main class `CastilloInfinitoApplication.java` from your IDE.

The application will be available at: `http://localhost:8081`

## 📚 API Endpoints

### ✅ 1. GET - Get Pillar information

**Endpoint:** `GET /api/pilares/{id}`

**Description:** Gets the complete information of a Pillar by its ID.

**Request Example:**
```
GET http://localhost:8081/api/pilares/1
```

**Response Example (200 OK):**
```json
{
  "id": 1,
  "nombre": "Giyu Tomioka",
  "posX": -500,
  "posY": -200,
  "estado": "Fighting"
}
```

**Response Codes:**
- `200 OK`: Pillar found
- `404 NOT FOUND`: No Pillar exists with that ID

---

### ✅ 2. GET - Get estimated enemy triangulation

**Endpoint:** `GET /api/inteligencia/triangulacion`

**Description:** Calculates an approximate projection of Muzan and Upper Moons location using Pillar coordinates.

**Request Example:**
```
GET http://localhost:8081/api/inteligencia/triangulacion
```

**Response Example (200 OK):**
```json
{
  "posiblePosicionMuzan": {
    "x": 0,
    "y": -50
  },
  "nivelConfianza": 0.78,
  "descripcion": "High probability of demonic presence at the given coordinates."
}
```

---

### 🚀 3. POST - Register or update Pillar position

**Endpoint:** `POST /api/pilares/actualizar-posicion`

**Description:** Updates the position and status of a Pillar when establishing communication.

**Request Example:**
```json
POST http://localhost:8081/api/pilares/actualizar-posicion
Content-Type: application/json

{
  "pilarId": 1,
  "posX": -480,
  "posY": -210,
  "estado": "Injured"
}
```

**Response Example (201 CREATED):**
```json
{
  "mensaje": "Position updated successfully.",
  "pilar": {
    "id": 1,
    "nombre": "Giyu Tomioka",
    "posX": -480,
    "posY": -210,
    "estado": "Injured"
  }
}
```

**Response Codes:**
- `201 CREATED`: Data updated successfully
- `400 BAD REQUEST`: Error in sent data
- `404 NOT FOUND`: Pillar does not exist

---

### 🚀 4. POST - Register fragmented tactical message

**Endpoint:** `POST /api/mensajes`

**Description:** Stores a fragmented tactical message sent by a Pillar.

**Request Example:**
```json
POST http://localhost:8081/api/mensajes
Content-Type: application/json

{
  "pilarId": 3,
  "contenidoFragmentado": "Muz... mov... north... atta..."
}
```

**Response Example (201 CREATED):**
```json
{
  "id": 14,
  "pilarId": 3,
  "contenidoFragmentado": "Muz... mov... north... atta...",
  "contenidoReconstruido": null,
  "timestamp": "2025-11-20T08:15:43"
}
```

**Response Codes:**
- `201 CREATED`: Message created successfully
- `400 BAD REQUEST`: Error in sent data
- `404 NOT FOUND`: Pillar does not exist

---

### 🔧 5. PUT - Reconstruct a tactical message

**Endpoint:** `PUT /api/mensajes/{id}/reconstruir`

**Description:** Allows reconstructing a distorted tactical message.

**Request Example:**
```json
PUT http://localhost:8081/api/mensajes/14/reconstruir
Content-Type: application/json

{
  "contenidoReconstruido": "Muzan is moving north. Prepare attack."
}
```

**Response Example (200 OK):**
```json
{
  "id": 14,
  "pilarId": 3,
  "contenidoFragmentado": "Muz... mov... north... atta...",
  "contenidoReconstruido": "Muzan is moving north. Prepare attack.",
  "timestamp": "2025-11-20T08:15:43"
}
```

**Response Codes:**
- `200 OK`: Reconstruction successful
- `400 BAD REQUEST`: Invalid data
- `404 NOT FOUND`: No message exists with that ID

---

## 🏗️ Project Architecture

The project follows the standard Spring Boot layered architecture:

```
src/main/java/com/cuerpo/cazadores/
├── CastilloInfinitoApplication.java    # Main class
├── config/
│   └── DataInitializer.java            # Data initialization
├── controller/                          # REST controllers layer
│   ├── InteligenciaController.java
│   ├── MensajeController.java
│   └── PilarController.java
├── dto/                                 # Data Transfer Objects
│   ├── ActualizarPosicionRequest.java
│   ├── ActualizarPosicionResponse.java
│   ├── MensajeRequest.java
│   ├── MensajeResponse.java
│   ├── PilarResponse.java
│   ├── ReconstruirMensajeRequest.java
│   └── TriangulacionResponse.java
├── entity/                              # JPA entities
│   ├── Mensaje.java
│   └── Pilar.java
├── exception/                           # Exception handling
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── repository/                          # JPA repositories
│   ├── MensajeRepository.java
│   └── PilarRepository.java
└── service/                             # Business logic
    ├── InteligenciaService.java
    ├── MensajeService.java
    └── PilarService.java
```

## 🗄️ Database Structure

### Table: `pilares`

| Field    | Type        | Description                    |
|----------|-------------|--------------------------------|
| id       | BIGINT      | Unique ID (auto-incremental)   |
| nombre   | VARCHAR     | Pillar name                    |
| pos_x    | DOUBLE      | X position in space           |
| pos_y    | DOUBLE      | Y position in space           |
| estado   | VARCHAR     | Current Pillar status          |

### Table: `mensajes`

| Field                    | Type         | Description                          |
|--------------------------|--------------|--------------------------------------|
| id                       | BIGINT       | Unique ID (auto-incremental)          |
| pilar_id                 | BIGINT       | ID of the Pillar that sent the message |
| contenido_fragmentado    | TEXT         | Distorted message                    |
| contenido_reconstruido   | TEXT         | Reconstructed message (nullable)      |
| timestamp                | DATETIME     | Creation date and time               |

## 🔄 Automatic Initialization

When starting the application, three Pillars are automatically created with their initial positions:

1. **Giyu Tomioka** - Position: [-500, -200]
2. **Sanemi Shinazugawa** - Position: [100, -100]
3. **Mitsuri Kanroji** - Position: [500, 100]

## 🧪 Testing with Postman or cURL

### Example 1: Get a Pillar
```bash
curl -X GET http://localhost:8081/api/pilares/1
```

### Example 2: Update position
```bash
curl -X POST http://localhost:8081/api/pilares/actualizar-posicion \
  -H "Content-Type: application/json" \
  -d '{
    "pilarId": 1,
    "posX": -480,
    "posY": -210,
    "estado": "Injured"
  }'
```

### Example 3: Create message
```bash
curl -X POST http://localhost:8081/api/mensajes \
  -H "Content-Type: application/json" \
  -d '{
    "pilarId": 3,
    "contenidoFragmentado": "Muz... mov... north... atta..."
  }'
```

### Example 4: Reconstruct message
```bash
curl -X PUT http://localhost:8081/api/mensajes/1/reconstruir \
  -H "Content-Type: application/json" \
  -d '{
    "contenidoReconstruido": "Muzan is moving north. Prepare attack."
  }'
```

### Example 5: Get triangulation
```bash
curl -X GET http://localhost:8081/api/inteligencia/triangulacion
```

## ⚙️ Database Configuration

The application is configured to:
- **Automatically create** the database if it doesn't exist
- **Automatically update** the table schema (`spring.jpa.hibernate.ddl-auto=update`)
- **Show SQL queries** in console for debugging

## 🐛 Error Handling

The system includes a global exception handler that returns structured JSON responses:

- **404 NOT FOUND**: Resource not found
- **400 BAD REQUEST**: Invalid data or validation errors
- **500 INTERNAL SERVER ERROR**: Unexpected errors

## 📝 Additional Notes

- All endpoints require data validation using `@Valid` annotations
- Timestamps are automatically generated when creating messages
- The triangulation algorithm uses a weighted average of Pillar positions
- Confidence level is calculated based on coordinate dispersion

## 👨‍💻 Author
Diego Alejandro Vásquez Raigoza

Developed for the Spring Boot course final exam.

---

**May the battle against Muzan be successful! 🗡️**
