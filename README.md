# Evaluacion
Proyecto de validacion de Usuario y Correo

## Requisitos Previos

Antes de ejecutar el proyecto asegúrate de tener instalado lo siguiente en tu máquina local:

| Herramienta | Versión recomendada | Descripción |
|--------------|--------------------|--------------|
| [Java](https://adoptium.net/) | 21 o superior | Entorno de ejecución de la aplicación |
| [Maven](https://maven.apache.org/download.cgi) | 3.9+ | Para compilar y ejecutar el proyecto |
| [Git](https://git-scm.com/) | Opcional | Para clonar el repositorio |

---

## Configuración del Proyecto

1. **Clonar el repositorio**

   ```bash
   git clone https://github.com/Neometal17/Evaluacion.git
   cd Evalucion

2. **Clonar el repositorio**

En el archivo src/main/resources/application.properties, asegúrate de definir los valores necesarios.

### Configuracion de Base de Datos
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

### Puerto del servidor
server.port=8080

## Pasa Empaquetar y Ejecutar 
    ```bash
    mvn clean install
    mvn spring-boot:run

## Ejecutar el compilado 
    ```bash
    java -jar target/nombre-del-archivo.jar

## Acceso a Consola H2
http://localhost:8080/h2-console

## Endpoint de Ejemplos

### Registrar usuario 
curl --location 'http://localhost:8080/user/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Emmanuel",
    "email": "egonzalez2@gmail.com",
    "password": "Adam21$",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}'

### Login
curl --location 'http://localhost:8080/login/' \
--header 'Content-Type: application/json' \
--data '{
    "userName":"Emmanuel",
    "password":"Adam21$"
}'

#Login

## Caso 1: Exitoso

sequenceDiagram
    participant Client as Postman
    participant AuthService as Microservicio Auth
    participant DB as Base de Datos

    Client->>AuthService: POST /login {username, password}
    AuthService->>DB: Buscar usuario por username
    DB-->>AuthService: Datos de usuario

    AuthService->>AuthService: Usaurio contraseña invalida
    AuthService->>AuthService: Validar criterios de la contraseña
    AuthService->>AuthService: Generar JWT

    AuthService-->>Client: 200 OK {username: text, token: JWT}

## Caso 2: Usuario y/o contraseña invalida

sequenceDiagram
    participant Client as Postman
    participant AuthService as Microservicio Auth
    participant DB as Base de Datos

    Client->>AuthService: POST /login {username, password}
    AuthService->>DB: Buscar usuario por username
    DB-->>AuthService: Datos de usuario

    AuthService->>AuthService: Validar usuario contraseña invalida

    AuthService-->>Client: 401 Unauthorized {mensaje: Usuario y/o Contraseña Invalida}


## Caso 3: Usuario Inactivo

sequenceDiagram
    participant Client as Postman
    participant AuthService as Microservicio Auth
    participant DB as Base de Datos

    Client->>AuthService: POST /login {username, password}
    AuthService->>DB: Buscar usuario por username
    DB-->>AuthService: Datos de usuario

    AuthService->>AuthService: Validar usuario contraseña invalida
    AuthService->>AuthService: Usuario inactivo

    AuthService-->>Client: 401 Unauthorized {mensaje: El usuario se encuenta inactivo}

## Caso 4: Criterio en Password

sequenceDiagram
    participant Client as Postman
    participant AuthService as Microservicio Auth
    participant DB as Base de Datos

    Client->>AuthService: POST /login {username, password}
    AuthService->>DB: Buscar usuario por username
    DB-->>AuthService: Datos de usuario

    AuthService->>AuthService: Validar usuario contraseña invalida
    AuthService->>AuthService: Usuario inactivo
    AuthService->>AuthService: Validacion Criterio Contraseña

    AuthService-->>Client: 401 Unauthorized {mensaje: El password no cumple con los criterios}

# Crear Usuario

## Caso 1: Exitoso

sequenceDiagram
    participant Client as Postman
    participant AuthService as Microservicio Auth
    participant DB as Base de Datos

    Client->>AuthService: POST /user {name, email, password, phone[{number, citycode, contrycode}]}
    AuthService->>DB: Buscar Email de usuario
    DB-->>AuthService: Datos de usuario

    AuthService->>AuthService: Validacion: Correo Existe 
    
    AuthService->>DB: Guardar Usuario
    DB-->>AuthService: Modelo Usuario Guardo

    AuthService->>DB: Guardar de Telefonos
    DB-->>AuthService: Modelo Telefonos Guardo

    AuthService-->>Client: 201 CREATE: {name, email, password, phone[{number, citycode, contrycode}]}

## Caso 2: 

sequenceDiagram
    participant Client as Postman
    participant AuthService as Microservicio Auth
    participant DB as Base de Datos

    Client->>AuthService: POST /user {name, email, password, phone[{number, citycode, contrycode}]}
    AuthService->>DB: Buscar Email de usuario
    DB-->>AuthService: Datos de usuario

    AuthService->>AuthService: Validacion: Correo Existe 

    AuthService-->>Client: 409 Conflict: {mensaje: El correo ya registrado}

## Autor

[Emmanuel Gonzalez]
📧 [neometal17@gmail.com]
🔗 https://github.com/Neometal17