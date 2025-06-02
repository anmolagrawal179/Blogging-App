
# Blogging App

## Overview

The Blogging App is a robust platform built using **Spring Boot**, **Hibernate**, and **MySQL** to allow users to create, manage, and interact with blog posts. The project demonstrates a modern approach to building RESTful web applications, incorporating industry-standard tools and practices.

## Features

1. **User Management**:

   - User registration.
   - Profile updates.

2. **Category Management**:

   - Create, update, delete, and view categories.

3. **Post Management**:

   - CRUD (Create, Read, Update, Delete) operations for posts.
   - Assign posts to a specific category and user.
   - Filter posts by category and user.

4. **Comment Management**:

   - Add comments on posts.
   - Delete comments.


5. **API Documentation**:

   - Integrated with Spring Docs (OpenAPI) for easy exploration and testing of APIs.

## Technology Stack

- **Backend Framework**: Spring Boot
- **ORM**: Hibernate
- **Database**: MySQL
- **Documentation**: Spring Docs (OpenAPI)
- **Programming Language**: Java (Version 21)

## Entities

### 1. User

- **Fields**: `id`, `name`, `email`, `password`, `about`
- **Relationships**:
  - One-to-Many with `Post`

### 2. Category

- **Fields**: `id`, `title`
- **Relationships**:
  - One-to-Many with `Post`

### 3. Post

- **Fields**: `id`, `title`, `description`
- **Relationships**:
  - Many-to-One with `User`
  - Many-to-One with `Category`
  - One-to-Many with `Comment`

### 4. Comment

- **Fields**: `id`, `content`
- **Relationships**:
  - Many-to-One with `Post`

## Setup Instructions

### Prerequisites

1. **Java Development Kit (JDK)**: Version 21.
2. **Maven**: For dependency management.
3. **MySQL**: Database setup.
4. **IDE**: IntelliJ IDEA, Eclipse, or any preferred Java IDE.

### Installation Steps

1. **Clone the Repository**:

   ```
   git clone https://github.com/anmolagrawal179/Blogging-App.git
   ```

2. **Configure the Database**:

   - Update `application.yml` in the `src/main/resources` directory:
     ```yaml
     spring:
       datasource:
         url: jdbc:mysql://localhost:3306/blog_db
         username: yourUsername
         password: yourPassword
       jpa:
         hibernate:
           ddl-auto: update
         show-sql: true
     ```

3. **Run the Application**:

   - Navigate to the project directory and execute:
     ```
     mvn spring-boot:run
     ```

4. **Access the Application**:

   - Open your browser and navigate to: `http://localhost:8080`.
   - Access the API documentation at: `http://localhost:8080/swagger-ui.html`.

## API Documentation

The application uses **Spring Docs** to provide an interactive and comprehensive API documentation interface. You can explore all available endpoints, their request/response structures, and test them directly from the documentation.

## Future Enhancements

- Add user-friendly front-end using Angular or React.
- Implement advanced search and filter options.
- Enable file uploads for posts (e.g., images, documents).
- Add social media sharing functionality.

## Contribution

Contributions are welcome! Please fork the repository, make your changes, and submit a pull request.


