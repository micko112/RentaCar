# Rent-a-Car Frontend (Angular)

This is the Angular frontend for the Rent-a-Car Management System.  
It provides the user interface for authentication, car management, client management and rental operations.  
The frontend communicates with the Spring Boot backend via a REST API.

## Features

### Authentication
- User login
- Client login
- User and client registration
- Basic route protection and access control

### Cars
- List all cars
- Filter cars by category or other criteria
- Add new car
- Edit existing car
- Car card component for displaying car details
- Car model add and update modals

### Clients
- Add new client
- Client list view
- Client details in separate card component
- Update client in modal dialog
- Delete client if needed

### Rentals
- Create a new rental
- Select car and client
- Rental list page (for admins)
- Rental details in modal
- Rental requests handling (pending rentals)
- Cancel or update rental

## Project Structure

Main structure under `src/app`:

```txt
src/app/
│
├── auth/
│   ├── login/
│   │   ├── login.component.ts
│   │   ├── login.component.html
│   │   └── login.component.css
│   ├── login-client/
│   │   ├── login-client.component.ts
│   │   ├── login-client.component.html
│   │   └── login-client.component.css
│   └── register/
│       ├── register.component.ts
│       ├── register.component.html
│       └── register.component.css
│
├── components/
│   ├── cars/
│   │   ├── car-add/
│   │   ├── car-card/
│   │   ├── car-list/
│   │   ├── car-model-add/
│   │   └── car-update-modal/
│   │
│   ├── clients/
│   │   ├── client-add/
│   │   ├── client-card/
│   │   ├── client-list/
│   │   └── client-update-modal/
│   │
│   └── rentals/
│       ├── rental-add/
│       ├── rental-card/
│       ├── rental-list/
│       ├── rental-modal/
│       └── rental-requests/
│
├── connection/
│   └── response.ts
│
├── models/
│   ├── car.model.ts
│   ├── car-model.ts
│   ├── city.model.ts
│   ├── client.model.ts
│   ├── rental.model.ts
│   └── user.model.ts
│
└── services/
    ├── car.service.ts
    ├── car-model.service.ts
    ├── city.service.ts
    ├── client.service.ts
    ├── rental.service.ts
    └── user.service.ts
```
## Technologies
- Angular
- TypeScript
- HTML / CSS
- Angular Router
- HTTPClient
  
## API Integration
The frontend consumes REST endpoints from the backend, for example:
```txt
GET    /api/cars
POST   /api/cars
GET    /api/clients
POST   /api/rentals
POST   /api/auth/login
POST   /api/auth/register
```
The base URL is configured in the Angular services.

### Running the Frontend
From the frontend directory:
```bash
npm install
npm start
```
or
```bash
ng serve
```
The application is available at:
```txt
http://localhost:4200
```
