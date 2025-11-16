# BACKEND 


# Rent-a-Car Backend (Spring Boot)

This is the Spring Boot backend for the Rent-a-Car Management System.  
It exposes a REST API used by the Angular frontend for managing cars, car models, cities, clients, rentals and users.

## Features

- RESTful API for all core entities
- DTO-based request and response objects
- Service layer with business logic
- JPA repositories for database access
- Separation between domain model and transport objects
- Basic authentication and user handling

## Project Structure

Main package: `com.rentaCar.rentaCarBackend`

```txt
src/main/java/com/rentaCar/rentaCarBackend/
│
├── connection/
│   ├── HttpResponse.java
│   └── Response.java
│
├── controller/
│   ├── CarController.java
│   ├── CarModelController.java
│   ├── CityController.java
│   ├── ClientController.java
│   ├── RentalController.java
│   └── UserController.java
│
├── dto/
│   ├── CarDTO.java
│   ├── CarModelDTO.java
│   ├── CityDTO.java
│   ├── ClientDTO.java
│   ├── DomainDTO.java
│   ├── RentalDTO.java
│   ├── RequestSearch.java
│   └── UserDTO.java
│
├── JPARepo/
│   └── (JPA repository interfaces for all entities)
│
├── mapper/
│   └── (mapping logic between entities and DTOs)
│
├── model/
│   ├── Car.java
│   ├── CarModel.java
│   ├── City.java
│   ├── Client.java
│   ├── Rental.java
│   └── User.java
│
├── service/
│   ├── CarModelService.java
│   ├── CarService.java
│   ├── CityService.java
│   ├── ClientService.java
│   ├── RentalService.java
│   └── UserService.java
│
└── RentaCarBackendApplication.java
```
## Entities

The backend manages the following main entities:

- **Car** – car information and status  
- **CarModel** – car model and category  
- **City** – city / rental location  
- **Client** – client data  
- **Rental** – rental period and relations with car and client  
- **User** – application users (admins, employees, clients)

---

## Controllers and Endpoints

### **CarController**
- GET /api/cars
- GET /api/cars/{id}
- POST /api/cars
- PUT /api/cars/{id}
- DELETE /api/cars/{id}
### **ClientController**
- GET /api/clients
- GET /api/clients/{id}
- POST /api/clients
- PUT /api/clients/{id}
- DELETE /api/clients/{id}
### **RentalController**
- GET /api/rentals
- GET /api/rentals/{id}
- POST /api/rentals
- PUT /api/rentals/{id}
- DELETE /api/rentals/{id}
### **UserController**
- POST /api/auth/login
- POST /api/auth/register
## Technologies

- **Java**
- **Spring Boot**
- **Spring Web (REST)**
- **Spring Data JPA / Hibernate**
- **MySQL / MariaDB**
- **Maven**
## Configuration

Database configuration is located in:
```txt
src/main/resources/application.properties
```
## Running the Backend

From the backend directory:

```bash
mvn clean install
mvn spring-boot:run
```
The backend will run on:
```arduino
http://localhost:8080
```
