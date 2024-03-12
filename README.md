# Vehicle Management API

This is a RESTful API for managing vehicle details implemented in Java using the Spring Boot framework. It provides endpoints for customers to add, update, and search for vehicles. The source code is organized into three main components:

- Controller: Handles REST API calls and responds with OpenAPI definitions.
- Service: Implements the business logic for managing vehicle details.
- Repository: Provides functions to interact with the database for data access.

### Assumptions
- The application assumes using H2 in memory database in the application.
- It assumes the users has knowledge to use OpenAPI 3 (swagger) 

## Running Environment
- Java (17+)
- Spring Boot 3
- Spring Data JPA
- H2 Database
- OpenAPI
- Maven

## Running the Application

### Prerequisites
- Java Development Kit (JDK) installed
- Maven installed

### Compilation and Running using Maven
1. Clone this repository.
2. Navigate to the project directory.
3. Open a terminal and run the following command to compile the application:
    ```bash
    mvn clean install
    ```
4. To run the Spring Boot application using Maven, execute:
    ```bash
    mvn spring-boot:run
    ```

### Running the Packaged Application
1. After compiling the application, you can run the packaged JAR file using the following command:
    ```bash
    java -jar target/vehicle-management-api.jar
    ```

## API Documentation
The application sources code is already included a api.yaml for client generation.

When the application is running, api-docs is also accessible on the following URL:

http://localhost:8080/v3/api-docs

This documentation provides detailed information about the available endpoints and their usage.
