# PennyWise - Expense Tracker Application

The Expense Tracker is a Spring Boot-based application designed to help users track their income and expenses. This project is intended to improve understanding of Spring Boot, JPA, REST APIs, and full-stack development best practices. The application features a clean architecture with separate layers for entities, DTOs, repositories, services, and controllers.

## Features

- **User Management:** Create, retrieve, and delete user accounts.
- **Expense Management:** Log expenses, assign them to categories, and retrieve expense records.
- **Income Management:** Log income records and track total income.
- **Category Management:** Create and manage expense categories.
- **DTO & Entity Mapping:** Clear separation between database entities and API data transfer objects.
- **Spring Data JPA:** Uses JPA repositories for easy CRUD operations.
- **RESTful API:** Endpoints to interact with the system.
- **CORS Configuration:** Configured to allow cross-origin requests (useful when connecting with a frontend).
- **JMX & Actuator (Optional):** Monitor application metrics and health.

## Architecture

The application follows a layered architecture:
- **Entity Layer:** Contains JPA entity classes (e.g., `UserEntity`, `Expense`, `Income`, `Category`).
- **DTO Layer:** Contains data transfer objects to expose only necessary fields.
- **Repository Layer:** Contains Spring Data JPA repository interfaces for data access.
- **Service Layer:** Encapsulates business logic, including converting between entities and DTOs.
- **Controller Layer:** Exposes RESTful API endpoints for client interaction.
- **Configuration:** Contains application-wide settings (database, CORS, security, etc.).

## Tech Stack

- **Backend:** Java, Spring Boot, Spring Data JPA, Hibernate
- **Database:** MySQL
- **Build Tool:** Maven (or Gradle)
- **Monitoring:** JMX, Spring Boot Actuator
- **Testing:** JUnit, Mockito
- **IDE:** IntelliJ IDEA / Eclipse / VSCode
- **Deployment and Containerization:** AWS, Docker

## Installation and Setup

### Prerequisites

- Java 17 (or higher)
- Maven (if using Maven as the build tool)
- MySQL database
- Git

### Clone the Repository

```bash
git clone https://github.com/yourusername/expense-tracker.git
cd expense-tracker

spring.datasource.url=jdbc:mysql://localhost:3306/expensetracker
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

mvn spring-boot:runmvn clean install
mvn spring-boot:run
