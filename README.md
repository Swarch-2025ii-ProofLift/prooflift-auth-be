# **Login Service**

*This service controls register and authentication for the software system users.*

# Architecture

## Components

    Framework used: Springboot
    Language: Java
    Database: PostgreSQL

## Conectors
    Client -> Server (HTTP REST)
    Server -> Database (conector JDBC (with PostgreSQL driver))

## Previous requeriments
    Docker installed
    Environment variables to run database
    Java springboot
    Maven
    WSL

# How to run
### Using docker-compose
    1. Clone the repository
    2. Set the "docker-compose" file out of prooflift-auth-be folder
    3. Inicialize a cmd console on folder
    4. Excecute "docker-compose build"
    5. Then "docker-compose up"

## API documentation


### POST /auth/login

Description: Authenticates a registered user and returns a JWT token.

Request body (JSON):
```
{
  "email": "user1@email.com",
  "password": "1234"
}
```

Response (200 OK):
```
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5..."
}
```

Error responses:
```
401 Unauthorized → Invalid credentials (wrong email or password).
```
### POST /auth/register

Description: Registers a new user and returns a JWT token.

Request body (JSON):
```
{
  "email": "user1@email.com",
  "password": "1234",
  "nombre": "Juan",
  "apellido": "Pérez"
}
```

Response (200 OK):
```
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5..."
}
```

Error responses:
```
400 Bad Request → Missing fields or invalid format.

409 Conflict → Email already registered.
```

## Service Structure
```
prooflift-auth-be/
├── src/
│   └── main/
│       ├── java/com/prooflift/login/
│       │   ├── Auth/                        # Authentication logic
│       │   │   ├── AuthController.java
│       │   │   ├── AuthService.java
│       │   │   ├── AuthResponse.java
│       │   │   ├── LoginRequest.java
│       │   │   └── RegisterRequest.java
│       │   │
│       │   ├── Jwt/                         # JWT service
│       │   │   ├── JwtService.java
│       │   │   └── JwtAuthenticationFilter.java
│       │   │
│       │   ├── User/                        # Repository and entity
│       │   │   ├── User.java
│       │   │   ├── Role.java
│       │   │   └── UserRepository.java
│       │   │
│       │   ├── config/                      # Security and beand config
│       │   │   └── SecurityConfig.java
│       │   │
│       │   └── Demo/                        # Ignore 
│       │       └── DemoController.java
│       │
│       └── resources/
│           └── application.properties       # Service configuration
│
├── Dockerfile                               # Service image
├── docker-compose.yml                       # Orquestación de servicios
├── pom.xml                                  # Maven dependencies
└── README.md                                # Service documentation

```