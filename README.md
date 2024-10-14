# Employee Management API

## Descripción

La API de gestión de empleados permite crear, obtener y actualizar el estado de los empleados. Esta API se construyó utilizando Spring Boot y ofrece varios endpoints para interactuar con la entidad `Employee`.

## Tecnologías Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Lombok
- JPA
- MySQL

## Endpoints

### 1. Crear Empleado

- **URL:** `/api/employee/create`
- **Método:** `POST`
- **Parámetros:**
  - `name`: Nombre del empleado.
  - `lastName`: Apellido del empleado.
  - `position`: Cargo del empleado.
  - `salary`: Salario del empleado.
  - `birthday`: Fecha de nacimiento (formato: `yyyy-MM-dd`).
  - `startJobDate`: Fecha de inicio laboral (formato: `yyyy-MM-dd`).
  - `typeDocId`: Tipo de documento.
  - `documentId`: ID del documento.

- **Respuesta:**
  - **Código:** 201 CREATED
  - **Cuerpo:** El objeto `Employee` creado.

### 2. Obtener Empleado por ID

- **URL:** `/api/employee/get/{id}`
- **Método:** `GET`
- **Ruta Variable:**
  - `id`: ID del empleado.

- **Respuesta:**
  - **Código:** 302 FOUND
  - **Cuerpo:** Un objeto `EmployeeResponse` que incluye el nombre completo, edad y tiempo en la empresa.

### 3. Obtener Todos los Empleados

- **URL:** `/api/employee/get/all`
- **Método:** `GET`

- **Respuesta:**
  - **Código:** 302 FOUND
  - **Cuerpo:** Una lista de objetos `EmployeeResponse`.

### 4. Cambiar Estado de Empleado

- **URL:** `/api/employee/change-status/{id}`
- **Método:** `PUT`
- **Ruta Variable:**
  - `id`: ID del empleado.

- **Respuesta:**
  - **Código:** 202 ACCEPTED
  - **Cuerpo:** Mensaje confirmando que el estado ha sido cambiado.

## Ejemplo de Solicitud cURL

### Crear Empleado

```bash
curl -X POST "http://localhost:8080/api/employee/create" \
-H "Content-Type: application/json" \
-d '{
  "name": "Juan",
  "lastName": "Pérez",
  "position": "Desarrollador",
  "salary": 50000.0,
  "birthday": "1990-01-0,
"startJobDate": "2023-01-01",
  "typeDocId": "DNI",
  "documentId": "12345678"
}'
