# Proyecto: Sistema de Gestión de Clientes y Productos Financieros


## Decripcion General
Este proyecto implementa un sistema basado en microservicios para gestionar información de clientes y sus productos financieros. Incluye tres servicios:

1. **Microservicio de Clientes:** Gestiona la información personal de los clientes.
2. **Microservicio de Productos Financieros:** Proporciona información sobre los productos financieros de los clientes.
3. **BFF (Backend For Frontend):** Orquesta y combina los datos de los microservicios para exponer una API consolidada.

## Tecnologias Utilizadas
- **Lenguaje:** Java 17
- **Frameworks:**
  - Spring Boot (WebFlux, Security, Data, AOP)
  - Springdoc OpenAPI (Swagger)
- **Base de Datos:** SQL Server
- **Contenerización:** Docker
- **Mapeo de Objetos:** MapStruct
- **Gestión de Dependencias:** Maven
- **Autenticación:** OAuth 2.0
- **Pruebas Unitarias:** JUnit 5
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

4. Construye los proyectos
   ```bash
   mvn clean install
   
5. Levanta los servicios con Docker Compose:
   ```bash
   docker-compose up --build
   
7. Accede a los servicios:
- Microservicio de Clientes: http://localhost:8081/swagger-ui/index.html
- Microservicio de Productos Financieros: http://localhost:8082/swagger-ui/index.html
- BFF: http://localhost:8080/swagger-ui/index.html
