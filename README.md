Library Management System - LMS


Short Description
A production-ready Spring Boot backend for managing books, users, and borrow/return workflows with clean DTOs, mappers, validation, and global exception handling, built for speed, clarity, and extensibility.​

Features
CRUD for books, users, and borrow records with DTO mapping and validation ✅​

Search by title, author, and category with typed enums 📚​

Borrow/return flows with due dates, fines, and renewal count ⏱️​

RESTful API with layered architecture: controller, service, mapper, entity 🧭​

Global exception handling and structured error responses 🚨​

Clear API routes versioning: /api/v1 and /api1/v1 for books 🔀​

Tech Stack
<p> <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=oracle&logoColor=white" /> <img src="https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" /> <img src="https://img.shields.io/badge/Spring%20Data%20JPA-Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white" /> <img src="https://img.shields.io/badge/MySQL-8+-4479A1?style=for-the-badge&logo=mysql&logoColor=white" /> <img src="https://img.shields.io/badge/Validation-Jakarta-2F5D62?style=for-the-badge&logo=opensourceinitiative&logoColor=white" /> <img src="https://img.shields.io/badge/Postman-API-orange?style=for-the-badge&logo=postman&logoColor=white" /> </p>[4][5][2]
API Highlights
text
GET    /api1/v1/books
GET    /api1/v1/books/{id}
GET    /api1/v1/books/search/title?title=Clean%20Code
GET    /api1/v1/books/search/author?author=Robert
GET    /api1/v1/books/search/category?category=PROGRAMMING
POST   /api1/v1/books
PUT    /api1/v1/books/{id}
DELETE /api1/v1/books/{id}

POST   /api/v1/borrow/issue?userId={uid}&bookId={bid}
POST   /api/v1/borrow/return?bookId={bid}
GET    /api/v1/borrow/all
GET    /api/v1/borrow/user/{userId}

GET    /api/v1/users
GET    /api/v1/users/{id}
POST   /api/v1/users
PUT    /api/v1/users/{id}
DELETE /api/v1/users/{id}

Screenshots / Demo
Postman collection demo: CRUD on Books and Users, Borrow/Return flows 🖼️​

GIF: issuing and returning a book with updated availability and fines 🎬​

Optional: Live Swagger/OpenAPI if integrated (e.g., /swagger-ui.html) 🔗​

Tip: Use an animated GIF recorded from Postman or Swagger UI; for inspiration, see LMS tutorials demonstrating similar flows.​

Installation
Prerequisites

Java 17+, Maven 3.9+, MySQL 8+, and a database named lms.​

Clone and configure

git clone https://github.com/YOUR_USERNAME/library-management-system && cd library-management-system.​

Set application.yml or application.properties with datasource URL, username, and password.​

Build and run

mvn clean install && mvn spring-boot:run.​

Verify

Hit GET /api1/v1/books and GET /api/v1/users to confirm the service is up.​

Quick Setup (application.yml)
text
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/lms?createDatabaseIfNotExist=true&serverTimezone=UTC
    username: root
    password: your_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate.format_sql: true
server:
  port: 8080
Usage Examples
Create a Book

bash
curl -X POST http://localhost:8080/api1/v1/books \
 -H "Content-Type: application/json" \
 -d '{
   "isbn":"9780132350884",
   "title":"Clean Code",
   "author":"Robert C. Martin",
   "publisher":"Pearson",
   "publicationYear":2008,
   "edition":"1st",
   "pages":464,
   "category":"PROGRAMMING",
   "totalCopies":5,
   "availableCopies":5,
   "shelfLocation":"A1",
   "price":39.99
 }'
Borrow a Book

bash
curl -X POST "http://localhost:8080/api/v1/borrow/issue?userId=1&bookId=1"
Return a Book

bash
curl -X POST "http://localhost:8080/api/v1/borrow/return?bookId=1"
Project Structure
text
src/main/java/com/libraryManagementSystem/codes
 ├─ controller/  # REST endpoints (Books, Users, Borrow) 
 ├─ service/     # Business logic and transactions
 ├─ mapper/      # DTO <-> Entity converters
 ├─ entity/      # JPA entities and enums
 ├─ dto/         # API-safe transfer models
 └─ exception/   # GlobalExceptionHandler, custom exceptions
Contributing
Fork the repo and create a feature branch from main.​
