# Book Catalog API

Welcome to the Book Catalog API! This project provides a RESTful service to manage a catalog of books. The API is built using Spring Boot and includes features like data persistence, security, and Swagger documentation.

## Table of Contents

- [Book Catalog API](#book-catalog-api)
  - [Table of Contents](#table-of-contents)
  - [Project Description](#project-description)
  - [Installation](#installation)
    - [Prerequisites](#prerequisites)
    - [Dependencies](#dependencies)
    - [Setup](#setup)
  - [Running the Project](#running-the-project)
  - [API Endpoints](#api-endpoints)
  - [Database Initialization](#database-initialization)
  - [Swagger Documentation](#swagger-documentation)

## Project Description

The Book Catalog API allows users to create, retrieve, update, and delete books in a catalog. The API supports various operations such as searching for books by author, title, genre, and publication status.

## Installation

### Prerequisites

- Java 22 or later
- Maven

### Dependencies

The project uses the following dependencies:
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- Spring Boot Starter Web
- H2 Database
- Spring Boot Starter Test
- Spring Security Test
- Springdoc OpenAPI Starter WebMVC UI
- Spring Boot DevTools
- Jackson Databind

The dependencies are managed using Maven. You can find them in the `pom.xml` file.

### Setup

If you don't have JDK and Maven installed:
- Download the JDK from the official website:
  - [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk22-downloads.html)
  - [OpenJDK](https://jdk.java.net/22/)
- Download Apache Maven from the official website:
  - [Apache Maven](https://maven.apache.org/download.cgi)

After the installation for your operating system use the below steps to proceed:
1. Clone the repository: 
    ```sh
    git clone https://github.com/vvasileios/book-catalog.git
    cd bookcatalog
    ```

2. Build the project:
    ```sh
    mvn clean install
    ```

## Running the Project

To run the project, use the following command:
```sh
mvn spring-boot:run
```

The application run at `http://localhost:8080`. When loaded it provides extra information about the project.

## API Endpoints

1. Home Controller
  - GET `/`
    - Welcome message and available endpoints
2. Book Controller
  - GET `/books`
    - Retrieve all books available.
      **Example Request:**
        ```bash
        curl -X GET http://localhost:8080/books
        ```
      **Example Response:**
      "flag": true,
      "code": 200,
      "message": "Books retrieved successfully.",
      "data": [
        {
            "id": 1,
            "title": "To Kill a Mockingbird",
            "author": "Harper Lee"
        },
        {
            "id": 2,
            "title": "Go Set a Watchman",
            "author": "Harper Lee"
        },
        {
            "id": 3,
            "title": "Pride and Prejudice",
            "author": "Jane Austen"
        },
        {
            "id": 4,
            "title": "Sense and Sensibility",
            "author": "Jane Austen"
        },
        {
            "id": 5,
            "title": "The Catcher in the Rye",
            "author": "J.D. Salinger"
        },
      ]
  - GET `/books/{id}`
    - Retrieve a book by its ID.
  - POST `/books/add`
    - Add a new book.
  - POST `/books`
    - Add multiple new books.
  - PUT `/books/{id}`
    - Update an existing book by ID.
  - DELETE `/books/{id}`
    - Delete a book by ID.
  - DELETE `/books`
    - Delete all books.
  - GET `/books/published`
    - Find books by published status.
  - GET `/books/author`
    - Find books by author.
  - GET `/books/title`
    - Find books by title.
  - GET `/books/genre`
    - Find books by genre.

## Database Initialization

The project uses H2 in-memory database for data persistence. Initial data is provided at start-up for testing purposes through DataLoader class in config folder.
This setup ensures that the database is populated with books every time the project reloads or is initialized. Data can be found in resources folder.

## Swagger Documentation

Swagger UI is available to interact with the API and  view documentation. Access point: `http://localhost:8080/swagger-ui/index.html#/`.