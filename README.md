# Spring Boot Application with MySQL and Docker Setup

This guide explains how to set up and run the Spring Boot application with MySQL database using Docker.

## Prerequisites

- Docker installed
- Maven installed
- Java 21

## Setup Steps

### 1. Start MySQL with Docker

In the root directory of the project, run the following command to start MySQL in a Docker container:

```bash
docker-compose up -d
```

This command will download the MySQL image and start the container.

### 2. Configure the Database

The application is configured to use the Sakila database.

a. Download the database:
```bash
wget https://downloads.mysql.com/docs/sakila-db.tar.gz
```

b. Extract the files:
```bash
tar -xvzf sakila-db.tar.gz
```

c. Import the schema and data:
```bash
docker exec -i mysql_db mysql -u root -proot -e "CREATE DATABASE IF NOT EXISTS sakila;"
docker exec -i mysql_db mysql -u root -proot sakila < sakila-db/sakila-schema.sql
docker exec -i mysql_db mysql -u root -proot sakila < sakila-db/sakila-data.sql
```

### 3. Build and Run the Application

Build the application using Maven:
```bash
mvn clean install
```

Run the Spring Boot application:
```bash
mvn spring-boot:run
```

### 4. Running Tests (missing some tests)

The application includes three types of tests:

#### Unit Tests
- Located in `src/test/java/com/andcelsode`
- Tests individual components in isolation
- Run with: `mvn test`

Key test classes:
- `ActorControllerTest`: Tests the REST endpoints 
- `ActorServiceTest`: Tests business logic and service layer

#### Integration Tests
- Located in `src/test/java/integration`
- Tests components working together with the database
- Requires a running MySQL instance
- Run with: `mvn verify`

To run specific test classes:
```bash
mvn test -Dtest=ActorServiceTest
mvn test -Dtest=ActorControllerTest
mvn test -Dtest=ActorIntegrationTest
```

### 5. Verify the Application

Once the application is running, you can test it by accessing:
- The Hello World endpoint: http://localhost:8080/api
- The database connection will be automatically tested when you access any JPA repository

### Troubleshooting

1. If you can't connect to the database, verify:
   - MySQL container is running: `docker ps`
   - Database credentials in `application.properties` match your setup
   - Database port (3306) is not being used by another process

2. If the application fails to start:
   - Check the console for error messages
   - Verify that all dependencies are properly downloaded
   - Ensure Java 17 or later is installed and configured

### Additional Information

- The application runs on port 8080 by default
- Database configuration can be modified in `application.properties`
- JPA/Hibernate is configured to show SQL queries in the console