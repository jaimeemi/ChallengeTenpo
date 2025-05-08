
# Proyecto Tenpo

Este proyecto es un microservicio que utiliza Kafka para realizar operaciones de manera eficiente y escalable. A continuación, se detallan las características y configuraciones del proyecto.

## Características
- Microservicio basado en Kafka.
- API REST en Spring Boot (Java 21).
- Cálculo con porcentaje dinámico.
- Caché del porcentaje durante 30 minutos.
- Historial de llamadas asíncrono.
- Configuración de Feign para llamadas a APIs externas.
- Integración con H2 como base de datos en memoria para pruebas.
- Base de datos PostgreSQL para almacenar el historial de llamadas.

## Funcionalidades
1. **Cálculo con porcentaje dinámico**: Un endpoint que recibe `num1` y `num2`, los suma y aplica un porcentaje adicional obtenido de un servicio externo.
2. **Caché del porcentaje**: Almacena el porcentaje obtenido en memoria durante 30 minutos. Si el servicio externo falla, utiliza el último valor almacenado.
3. **Historial de llamadas**: Un endpoint que devuelve el historial de llamadas (fecha, endpoint, parámetros, respuesta o error).

## Inversión de Control (IoC) y Dependencia de Inyección (DI)
El proyecto utiliza IoC y DI para gestionar las dependencias entre los componentes. Esto permite una mayor flexibilidad y facilita las pruebas unitarias.

## Principios SOLID
Se han aplicado los principios SOLID en el diseño del código para asegurar que sea mantenible y escalable:

## Conexión con la Base de Datos
El proyecto está configurado para conectarse a una base de datos PostgreSQL utilizando JPA (Java Persistence API) para almacenar el historial de llamadas.

## Docker Compose
Para crear la imagen del proyecto utilizando Docker Compose, se debe ejecutar el siguiente comando:
```bash
docker-compose down  # Limpia cualquier contenedor previo
docker-compose build --no-cache  # Reconstruye sin usar caché
docker-compose up  # Inicia los servicios
```
Esto construirá la imagen y levantará los servicios definidos en el archivo `docker-compose.yml`.


## Dependencias
El proyecto incluye las siguientes dependencias:
- Redis para almacenamiento en caché.
- JPA para la gestión de la base de datos PostgreSQL.

## Dependencias
El proyecto incluye las siguientes dependencias:
- **Spring Boot Starters**:
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
- **PostgreSQL**:
  - `postgresql` (runtime)
- **H2 Database** (para pruebas locales):
  - `h2` (runtime)
- **Kafka**:
  - `spring-kafka`
- **Redis**:
  - `spring-boot-starter-data-redis`
  - `jedis` (versión 5.1.0)
- **Cache**:
  - `spring-boot-starter-cache`
- **Feign Client**:
  - `spring-cloud-starter-openfeign` (versión 4.1.1)
- **Lombok**:
  - `lombok` (provided)
- **SpringDoc OpenAPI (Swagger)**:
  - `springdoc-openapi-starter-webmvc-ui` (versión 2.3.0)
- **Dependencias de Prueba**:
  - `spring-boot-starter-test` (test)
  - `spring-kafka-test` (test)


## Documentación
- Se utiliza Swagger para la documentación de la API.
- Instrucciones claras para ejecutar el servicio están incluidas en este README.

## Tests
- Se implementan pruebas unitarias para las clase implementadas
