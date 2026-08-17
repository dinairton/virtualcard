# Virtual Card

**Virtual Card** is a Spring Boot-based application designed to handle and process virtual card data. It leverages modern Java technologies to provide a robust, scalable, and efficient system.

## Features

- **Spring Boot** for rapid application development.
- **Spring Data JPA** with Hibernate for database interactions.
- **MariaDB** as the primary runtime database.
- **Liquibase** for database migrations and versioning.
- **Redis** to implement global request throttling.
- **Mockito, JUnit, TestContainer** for unit testing and integration testing.
- **Lombok** to reduce boilerplate code.
- Full support for **Docker** to containerize the application.

---

## Requirements

To build and run this project, you need the following tools installed on your machine:

- **Java 21** (JDK 21 or newer)
- **Maven 3.9+**
- **Docker** (optional for containerized deployment)

---

## Getting Started

Follow these steps to run the application locally or in a Docker container.

### Clone the Repository

```bash
git clone https://github.com/dinairton/virtualcard.git
cd virtualcard
```

### Build the Project

Use Maven to build and package the application:

```bash
mvn clean install
```

This will download all required dependencies and create a JAR file in the `target` directory.

---

## Running the Application Locally

1. Configure your MariaDB database settings in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mariadb://localhost:3306/virtualcard......
   spring.datasource.username=root
   spring.datasource.password=root
   ```

2. Start the application using Maven:

   ```bash
   mvn spring-boot:run
   ```

3. Access the application at `http://localhost:8085`.

---

## Running the Application with Docker

You can use Docker to build and run the application easily. Follow these steps:

### Docker Image MARIADB

```bash
docker network create app-network
```

```bash
docker run -d --name mariadb --network app-network -e MARIADB_ROOT_PASSWORD=root -p 3306:3306 mariadb:latest
```
### Docker Image REDIS

```bash
docker run -d --name redis -p 6379:6379 redis:8
```

### Build the Docker Image

```bash
docker build -t virtualcard .
```

### Run the Docker Container

```bash
docker run -d --network app-network -p 8085:8085 --name virtualcard-container virtualcard
```

The application will now be running on `http://localhost:8085`.

---

### Swagger Configuration
By default, SpringDoc OpenAPI auto-configures Swagger for most Spring Boot applications. You can customize Swagger settings if needed, but no manual steps are required for basic setup.

Endpoints enabled by Swagger:
- **Swagger UI**: `http://localhost:8085/swagger-ui.html`
- **OpenAPI Specification**: `http://localhost:8085/v3/api-docs`

---


## Running Tests

To run the tests, use the following command:

```bash
mvn test
```

Tests use the H2 in-memory database.

---

## Directory Structure

```plaintext
virtualcard
├── src
│   ├── main
│   │   ├── java         # Main application source code
│   │   ├── resources    # Configuration files like application.properties
│   ├── test             # Unit and integration tests
├── pom.xml              # Maven configuration file
├── Dockerfile           # For containerization
└── README.md            # Documentation
```

---

## Technologies Used

- **Java 21** (or newer)
- **Spring Boot** 4.1.0
- **MariaDB** (runtime database)
- **Mockito, JUnit, TestContainer** (for testing)
- **Liquibase** (database migration management)
- **Spring Data JPA** for ORM
- **Redis** to implement global request throttling.
- **Lombok** for reducing boilerplate code
- **Docker** for containerized deployment

---

## Roadmap

1. Add more endpoints and features.
2. Implement authentication and security using **Spring Security**.
3. Increase test coverage.
4. Asynchronous processing
5. Support for scheduled expiration of cards

---

## Contact

If you have any questions or issues, feel free to reach out:

- **Author**: Dinairton Rodrigues
- **Email**: dinairton@gmail.com
