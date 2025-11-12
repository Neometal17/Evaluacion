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


.

## Autor

[Emmanuel Gonzalez]
📧 [neometal17@gmail.com]
🔗 https://github.com/Neometal17