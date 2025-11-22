# 🧪 Guía para Probar la API de Castillo Infinito

Esta guía te ayudará a probar tu API REST de diferentes maneras.

## 📋 Requisitos Previos

1. **Asegúrate de que la aplicación esté corriendo:**

   ```bash
   mvn spring-boot:run
   ```

   O ejecuta la clase `CastilloInfinitoApplication` desde tu IDE.

2. **Verifica que MySQL esté corriendo** y que la base de datos `castillo_infinito` esté disponible.

3. **La API estará disponible en:** `http://localhost:8081`

---

## 🛠️ Métodos para Probar la API

### 1. **Usando REST Client**

El archivo `api-tests.http` contiene todos los ejemplos listos para usar.

**En VS Code:**

- Instala la extensión "REST Client"
- Abre el archivo `api-tests.http`
- Haz clic en "Send Request" sobre cada petición

### 2. **Usando Postman**

1. **Importar la colección:**

   - Abre Postman
   - Haz clic en "Import"
   - Selecciona el archivo `Castillo-Infinito-API.postman_collection.json`
   - La colección se importará con todas las peticiones configuradas

2. **Configurar la variable de entorno:**

   - La variable `baseUrl` ya está configurada como `http://localhost:8081`
   - Si necesitas cambiarla, edita la variable en la colección

3. **Ejecutar peticiones:**
   - Selecciona cualquier petición de la colección
   - Haz clic en "Send"

**Ventajas:**

- ✅ Interfaz gráfica intuitiva
- ✅ Permite guardar respuestas
- ✅ Facilita compartir colecciones con el equipo
- ✅ Permite crear tests automatizados

---

### 3. **Usando curl (Línea de comandos)**

#### En Windows:

Ejecuta el archivo `ejemplos-curl.bat`:

```bash
ejemplos-curl.bat
```

#### En Linux/Mac:

Ejecuta el archivo `ejemplos-curl.sh`:

```bash
chmod +x ejemplos-curl.sh
./ejemplos-curl.sh
```

#### Ejemplos individuales de curl:

**1. Obtener información de un Pilar:**

```bash
curl -X GET "http://localhost:8081/api/pilares/1" \
  -H "Accept: application/json"
```

**2. Obtener triangulación:**

```bash
curl -X GET "http://localhost:8081/api/inteligencia/triangulacion" \
  -H "Accept: application/json"
```

**3. Actualizar posición de un Pilar:**

```bash
curl -X POST "http://localhost:8081/api/pilares/actualizar-posicion" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d "{\"pilarId\": 1, \"posX\": -480, \"posY\": -210, \"estado\": \"Herido\"}"
```

**4. Crear mensaje fragmentado:**

```bash
curl -X POST "http://localhost:8081/api/mensajes" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d "{\"pilarId\": 3, \"contenidoFragmentado\": \"Muz... mov... norte... ata...\"}"
```

**5. Reconstruir mensaje:**

```bash
curl -X PUT "http://localhost:8081/api/mensajes/1/reconstruir" \
  -H "Content-Type: application/json" \
  -H "Accept: application/json" \
  -d "{\"contenidoReconstruido\": \"Muzan se mueve hacia el norte. Preparar ataque.\"}"
```

**Ventajas:**

- ✅ Disponible en cualquier sistema operativo
- ✅ No requiere instalación de herramientas adicionales
- ✅ Ideal para scripts y automatización

---

### 4. **Usando httpie (Alternativa moderna a curl)**

Si tienes `httpie` instalado:

```bash
# Obtener Pilar
http GET http://localhost:8081/api/pilares/1

# Obtener triangulación
http GET http://localhost:8081/api/inteligencia/triangulacion

# Actualizar posición
http POST http://localhost:8081/api/pilares/actualizar-posicion \
  pilarId:=1 posX:=-480 posY:=-210 estado="Herido"

# Crear mensaje
http POST http://localhost:8081/api/mensajes \
  pilarId:=3 contenidoFragmentado="Muz... mov... norte... ata..."

# Reconstruir mensaje
http PUT http://localhost:8081/api/mensajes/1/reconstruir \
  contenidoReconstruido="Muzan se mueve hacia el norte. Preparar ataque."
```

---

## 📝 Endpoints Disponibles

### 1. GET `/api/pilares/{id}`

Obtiene la información de un Pilar por su ID.

**Ejemplo de respuesta:**

```json
{
  "id": 1,
  "nombre": "Giyu Tomioka",
  "posX": -500,
  "posY": -200,
  "estado": "Combatiendo"
}
```

### 2. GET `/api/inteligencia/triangulacion`

Calcula la triangulación estimada del enemigo.

**Ejemplo de respuesta:**

```json
{
  "posiblePosicionMuzan": {
    "x": 0,
    "y": -50
  },
  "nivelConfianza": 0.78,
  "descripcion": "Probabilidad alta de presencia demoníaca en las coordenadas dadas."
}
```

### 3. POST `/api/pilares/actualizar-posicion`

Actualiza la posición y estado de un Pilar.

**Body:**

```json
{
  "pilarId": 1,
  "posX": -480,
  "posY": -210,
  "estado": "Herido"
}
```

### 4. POST `/api/mensajes`

Crea un mensaje táctico fragmentado.

**Body:**

```json
{
  "pilarId": 3,
  "contenidoFragmentado": "Muz... mov... norte... ata..."
}
```

### 5. PUT `/api/mensajes/{id}/reconstruir`

Reconstruye un mensaje táctico.

**Body:**

```json
{
  "contenidoReconstruido": "Muzan se mueve hacia el norte. Preparar ataque."
}
```

---

## 🔍 Verificar que la API está funcionando

1. **Verifica el estado del servidor:**

   ```bash
   curl http://localhost:8081/api/pilares/1
   ```

2. **Revisa los logs de la aplicación** para ver las peticiones recibidas.

3. **Verifica la base de datos** para confirmar que los datos se están guardando correctamente.

---

## 🐛 Solución de Problemas

### Error: "Connection refused"

- Verifica que la aplicación esté corriendo en el puerto 8081
- Revisa los logs de la aplicación

### Error: "404 Not Found"

- Verifica que la URL sea correcta
- Asegúrate de que el endpoint existe en el controlador

### Error: "400 Bad Request"

- Verifica que el JSON del body sea válido
- Revisa que todos los campos requeridos estén presentes
- Consulta los logs para ver el mensaje de error específico

### Error: "500 Internal Server Error"

- Revisa los logs de la aplicación para ver el error completo
- Verifica que MySQL esté corriendo y accesible
- Asegúrate de que la base de datos exista

---

## 📚 Recursos Adicionales

- **Documentación de Spring Boot:** https://spring.io/projects/spring-boot
- **Documentación de Postman:** https://learning.postman.com/
- **Documentación de curl:** https://curl.se/docs/

---

¡Buena suerte probando tu API! 🗡️

