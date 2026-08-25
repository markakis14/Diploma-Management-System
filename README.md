# Diplomas Management App

A Spring Boot web app for managing university thesis assignments, built for the MYY803 course.

Professors publish thesis topics, students browse and apply, and professors assign each thesis to an applicant using one of three configurable strategies — highest average grade, fewest remaining courses, or random — implemented with the **Strategy** and **Template Method** design patterns. Once assigned, professors grade the finished thesis across implementation, report, and presentation scores.

Built with Spring Boot, Spring Security (role-based access for `STUDENT` and `PROFESSOR`), Spring Data JPA / Hibernate, Thymeleaf, and MySQL.

## Features

- User registration and login with role-based access (Student / Professor)
- Professors: publish, edit, and delete thesis topics
- Students: browse open topics, view details, and apply
- Automatic thesis assignment by grade, remaining courses, or at random
- Grading of assigned theses (implementation, report, presentation) with a computed total
- Profile editing for both roles

## Tech Stack

- Java 8, Spring Boot 2.7
- Spring Security, Spring Data JPA (Hibernate)
- Thymeleaf
- MySQL

## Getting Started

### Prerequisites

- JDK 8 or newer
- Maven (or use the included `mvnw` / `mvnw.cmd` wrapper)
- A running MySQL server

### Setup

1. **Clone the repository**
   ```
   git clone https://github.com/YOUR_USERNAME/diplomas_mgt_app.git
   ```

2. **Create the database**

   In MySQL, create an empty database:
   ```sql
   CREATE DATABASE diplomas_app_users;
   ```

3. **Add your own database credentials**

   Open `src/main/resources/application.properties` and replace the placeholder values with your own MySQL username and password:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/diplomas_app_users
   spring.datasource.username=YOUR_DB_USERNAME
   spring.datasource.password=YOUR_DB_PASSWORD
   ```
   > This file is intentionally left with placeholders in the repository. Do not commit your real credentials — keep them local only.

   Tables are created automatically on first run (`spring.jpa.hibernate.ddl-auto=update`), so no manual schema setup is needed.

4. **Run the app**

   From an IDE (e.g. IntelliJ): open the project, let Maven sync, then run `DiplomasMgtAppApplication`.

   Or from the command line:
   ```
   ./mvnw spring-boot:run
   ```

5. **Open it in your browser**

   Visit [http://localhost:8080](http://localhost:8080), register an account as a Student or Professor, and log in.

## Project Structure

```
src/main/java/.../
├── config/       Security and MVC configuration
├── controller/   Auth, student, and professor endpoints
├── dao/          Spring Data JPA repositories
├── model/        Entities, roles, and assignment strategies
└── service/      Business logic
src/main/resources/
├── templates/    Thymeleaf views (auth, student, professor)
└── application.properties
```
