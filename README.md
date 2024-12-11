# Proyecto: Sistema de Gestión de Clientes y Productos Financieros


## Decripcion General
Este proyecto implementa un sistema basado en microservicios para gestionar información de clientes y sus productos financieros. Incluye tres servicios:

1. **Microservicio de Clientes:** Gestiona la información personal de los clientes.
2. **Microservicio de Productos Financieros:** Proporciona información sobre los productos financieros de los clientes.
3. **BFF (Backend For Frontend):** Orquesta y combina los datos de los microservicios para exponer una API consolidada.

## Tecnologias Utilizadas
- **Lenguaje:** Java 17
- **Frameworks:**
  - Spring Boot (WebFlux, Data, AOP)
  - Springdoc OpenAPI (Swagger)
- **Base de Datos:** SQL Server
- **Contenerización:** Docker
- **Mapeo de Objetos:** MapStruct
- **Gestión de Dependencias:** Maven
- **Pruebas Unitarias:** JUnit 5 y Mockito
- **Logging:** Logback

## Arquitectura
  El sistema sigue una arquitectura basada en microservicios:


## Despliegue
### Requisitos
- Java 17 instalado
- Docker y Docker Compose instalados
- SQL Server configurado y accesible

## Pasos
1. Clona este repositorio:
   ```bash
   git clone https://github.com/tuusuario/tu-repo.git
   cd tu-repo
2. Configura las variables de entorno para conectar a SQL Server:
  Archivo application.properties.

4. Construye los proyectos (Esto recordar hacerlo dentro de la carpeta de cada proyecto)
   ```bash
   mvn clean install
   
5. Levanta los servicios con Docker Compose:
   ```bash
   docker-compose up --build
   
## Uso del BFF
El BFF expone una API para obtener información del cliente y productos asociados.

- Obtener Información del Cliente
- Endpoint: /bff/informacion-cliente/{codigoUnico}
- Método: GET

### Ejemplo Solicitud
  ```http
  GET /bff/informacion-cliente/{codigoUnico}
  ```
### Respuesta
```Json
{
    "nombres": "John",
    "apellidos": "Doe",
    "tipoDocumento": "DNI",
    "numeroDocumento": "12345678",
    "productosFinancieros": [
        {
            "id": 1,
            "nombre": "Producto A",
            "tipo": "Financiero"
        }
    ]
}
````

## Microservicio de Clientes
### Controlador de Clientes
  - Clase: ClienteController.java
  - Expone la API para acceder a los datos de clientes.

### Servicio de Clientes
- Clase: ClienteService.java
- Lógica de negocio para manejar la información de los clientes.

## Microservicio de Productos
### Controlador de Productos
  - Clase: ProductosController.java
  - Expone la API para acceder a los productos.

### Servicio de Productos
 - Clase: ProductosService.java
 - Lógica de negocio para manejar la información de los productos.

## Pruebas Unitarias
Las pruebas unitarias están implementadas usando JUnit 5. Puedes ejecutar las pruebas usando el siguiente comando:

```bash
mvn test
```
