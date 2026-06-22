🛒 Secure Online MarketPlace

A production-grade, secure RESTful e-commerce backend built with Spring Boot. Designed with clean architecture, JWT-based authentication, and role-based access control — enabling a scalable marketplace where buyers and sellers can interact safely.
---
🚀 Features
🔐 JWT Authentication & Authorization — Stateless security using Spring Security + JSON Web Tokens
👥 Role-Based Access Control (RBAC) — Separate roles for Buyers, Sellers, and Admins
🛍️ Product Management — Full CRUD operations for listings with category support
🧾 Order Management — End-to-end order lifecycle handling
🔍 Search & Filter — Query products by category, price range, and keywords
🧩 Clean Architecture — Layered separation: Controller → Service → Repository → Model
⚠️ Global Exception Handling — Centralized error responses with meaningful HTTP codes
📦 DTO Pattern — Clean request/response payloads using dedicated payload classes
🗄️ JPA + Hibernate ORM — Database-agnostic persistence layer
---
🏗️ Architecture & Project Structure
```
src/main/java/com/ecommerce/project/
├── config/          # Security config, CORS, Bean definitions
├── controller/      # REST API endpoints (Auth, Product, Order, User)
├── exceptions/      # Custom exceptions & global exception handler
├── model/           # JPA entity classes (User, Product, Order, etc.)
├── payload/         # Request/Response DTOs
├── repositories/    # Spring Data JPA repository interfaces
├── security/        # JWT filter, UserDetailsService, token utility
├── service/         # Business logic layer (interfaces + implementations)
├── util/            # Helper/utility classes
└── SbEcomApplication.java  # Main application entry point
```
> Follows **MVC + Service Layer pattern** — ensuring separation of concerns and testability.
---
🔧 Tech Stack
Layer	Technology
Language	Java 17
Framework	Spring Boot 3.x
Security	Spring Security + JWT
ORM	Spring Data JPA / Hibernate
Database	MySQL / PostgreSQL
Build Tool	Maven
API Style	RESTful JSON API
---
📡 API Overview
Auth Endpoints
Method	Endpoint	Description
POST	`/api/auth/signup`	Register a new user
POST	`/api/auth/signin`	Login and receive JWT token
Product Endpoints
Method	Endpoint	Description
GET	`/api/products`	List all products
GET	`/api/products/{id}`	Get product by ID
POST	`/api/products`	Add product (Seller only)
PUT	`/api/products/{id}`	Update product (Seller only)
DELETE	`/api/products/{id}`	Delete product (Seller/Admin)
Order Endpoints
Method	Endpoint	Description
POST	`/api/orders`	Place a new order
GET	`/api/orders/user`	Get orders for logged-in user
GET	`/api/admin/orders`	Get all orders (Admin only)
> All protected routes require `Authorization: Bearer <token>` header.
---
⚙️ Getting Started
Prerequisites
Java 17+
Maven 3.8+
MySQL or PostgreSQL running locally
Installation
```bash
# Clone the repository
git clone https://github.com/snehalb3001/Secure_Online_MarketPlace.git
cd Secure_Online_MarketPlace

# Configure your database in application.properties
src/main/resources/application.properties

# Build and run
mvn clean install
mvn spring-boot:run
```
Environment Configuration
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecom_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

app.jwtSecret=your_jwt_secret_key
app.jwtExpirationMs=86400000
```
---
🔒 Security Design
Passwords are hashed using BCrypt before storage — plain-text passwords never persisted
JWTs are signed and validated on every protected request via a custom JWT filter in the Spring Security filter chain
Role-based method-level security ensures users can only access their permitted resources
Custom exception handlers return structured error responses (no stack traces exposed to clients)
---
🧠 Key Engineering Decisions
Stateless Authentication: No sessions stored server-side — every request is self-contained with a JWT, making the service horizontally scalable
Repository Pattern: All DB access goes through JPA repositories, making it easy to swap databases
DTO Separation: Request/Response objects are decoupled from entity classes — prevents over-posting and accidental data exposure
Global Exception Handler: A single `@ControllerAdvice` class catches all exceptions and returns consistent error responses across the API
---
🤝 Contributing
Pull requests are welcome! For major changes, please open an issue first to discuss what you'd like to change.
---

👤 Author
Snehal B — GitHub
