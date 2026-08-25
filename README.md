# My journey to becoming a Java Developer ☕

## Project 2 - Festival Cordillera API with Spring Boot
REST API created to manage artists, stages and shows for a music festival.

This project represents the next step in my Java learning journey, moving from JDBC to Spring Boot, JPA, REST APIs and Docker.

Course: [Platzi Java Spring](https://platzi.com/cursos/java-spring/)

### Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* MariaDB
* Gradle
* MapStruct
* Spring Security
* Swagger / OpenAPI
* Docker
* Docker Compose

### Installation Guide

1. Clone the repository

```bash
git clone https://github.com/LuisaAcero2004/festival-cordillera-api.git
cd festival-cordillera-api
```

2. Create a `.env` file

```env
APP_ADMIN_USER=<admin_user>
APP_ADMIN_PASSWORD=<admin_password>

DB_URL=jdbc:mariadb://mariadb:3306/<database_name>
DB_USERNAME=<db_user>
DB_PASSWORD=<db_password>
DB_PASSWORD_ROOT=<root_password>

DATABASE_NAME=<database_name>
```

3. Start the application

```bash
docker compose up --build
```

Docker Compose starts:

* Spring Boot API on port `8080`
* MariaDB on port `3306`
* phpMyAdmin on port `8090`

4. Create the database tables using the SQL script included in the project.

### How to run

Base URL:

```text
http://localhost:8080/cordillera/api
```

Swagger:

```text
http://localhost:8080/cordillera/api/swagger-ui.html
```

Main resources:

```text
/artists
/stages
/shows
```

GET requests are public.

Create, update and delete operations require HTTP Basic Authentication using the credentials defined in:

```text
APP_ADMIN_USER
APP_ADMIN_PASSWORD
```

### Key Takeaways
* Development of a REST API using Spring Boot
* Implementation of a layered architecture separating domain, persistence and web responsibilities
* Use of Spring Data JPA and Hibernate instead of direct JDBC database access
* Implementation of Repository and Service patterns
* Mapping between persistence entities and domain DTOs using MapStruct
* Implementation of CRUD operations for related domain entities
* Use of JPA relationships and foreign keys between artists, stages and shows
* Request validation using Jakarta Validation
* API documentation using OpenAPI and Swagger
* Separation of sensitive configuration using environment variables


### AI Usage Note

AI tools were used as support for the Docker configuration and authentication implementation.

These topics are outside the scope of this project, so I have not studied them in depth yet. However, they were necessary to prepare the application for deployment and understand the complete production flow.

The main Spring Boot application, persistence layer, services, controllers and database modeling were developed as part of my Java learning process.
