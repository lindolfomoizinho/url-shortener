***

## URL Shortener
[![Tools](https://skillicons.dev/icons?i=java,spring,gradle,postgres,docker&theme=light)](https://skillicons.dev)

This project is a URL shortener with authentication and authorization, developed in Java using the Spring Framework. The system allows authenticated users to create short URLs, view their shortened URLs, and track usage statistics.

### Technologies Used
- Java 17
- Spring Boot - Core of the project
- Spring Security - Authentication and authorization
- PostgreSQL - Relational database
- Lombok - Reduces boilerplate code
- Gradle - Dependency management and build tool
- JWT (JSON Web Token) - Authentication token for securing APIs

### Features
- User Authentication: User registration and secure login.
- URL Management: Create, view, and delete shortened URLs.
- Usage Statistics: View click count on shortened URLs.
- Security: All URL operations are protected and require user authentication.

### Prerequisites

- Java 17
- Docker (to run the PostgreSQL database via Docker)
- Gradle - To build and run the project

### Project Setup

1. Clone the repository:
~~~bash
git clone https://github.com/lindolfomoizinho/url_shortener.git
cd url_shortener
~~~

2. Database Setup: This project uses PostgreSQL in a Docker container. The docker-compose.yml file in the docker folder is already configured to start the database
~~~bash
cd docker
docker-compose up -d
~~~
This will start the PostgreSQL container with the configured connection details. Check the credentials in the application.yml file.

3. Project Properties Setup: In the file src/main/resources/application.properties, configure the database and JWT details:
~~~yml
# Database configuration
spring:
  datasource:
  url: jdbc:postgresql://localhost:5432/url_shortener_db
  username: your_username
  password: your_password

# JWT configuration
jwt:
  public:
    key: your-public-key
  private:
    key: your-private-key
  issuer: task-manager
  expires-in: 300
  
# Admin configuration
admin:
  credentials:
    username: admin
    password: admin
~~~

### Running the Project
1. Compile and run the project:
~~~bash
./gradlew bootRun
~~~
2. Access the API: The API will be available at http://localhost:8080

### Main Endpoints

- Authentication:
  - POST /api/auth/signup - Register a new user 
  - POST /api/auth/login - User login, returns a JWT token

- Shortened URLs:
  - POST /api/urls - Create a new short URL (requires authentication)
  - GET /api/urls/{id} - View a specific shortened URL (requires authentication)
  - DELETE /api/urls/{id} - Delete a shortened URL (requires authentication)

- Redirection:
  - GET /{shortUrl} - Redirects the user to the original URL
***
