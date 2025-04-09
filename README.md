
---

## 🚀 Tech Stack

- Java 17+
- Spring Boot
- Spring Cloud Gateway
- Eureka Discovery
- Spring Data JPA
- H2 Database (for development)
- MySQL (for production)
- Spring Security (optional with Keycloak)

---



## ⚙️ Configuration

### `application.properties` (Gateway)

```properties
spring.application.name=Gateway
server.port=8093

# Eureka configuration
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
eureka.client.register-with-eureka=true

# Route definition (optional if using Java config)
spring.cloud.gateway.routes[0].id=loan-service
spring.cloud.gateway.routes[0].uri=http://localhost:8080
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/**
```

### `application.properties` (Loan Service)

```properties
spring.application.name=loan-service
spring.profiles.active=dev

# Eureka
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone=http://localhost:8761/eureka

# H2 Database (dev only)
spring.datasource.url=jdbc:h2:mem:librarydb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

---


## 🌐 API Gateway

You can access the loan service endpoints through the Gateway at:

```
http://localhost:8093/api/loans
```

Make sure the Gateway, Eureka Server, and Loan Service are all running.

---

## 🧪 Testing Endpoints (via Postman)

| Method | Endpoint              | Description           |
|--------|-----------------------|-----------------------|
| GET    | `/api/loans`          | Get all loans         |
| GET    | `/api/loans/{id}`     | Get loan by ID        |
| POST   | `/api/loans`          | Create a new loan     |
| PUT    | `/api/loans/{id}`     | Update a loan         |
| DELETE | `/api/loans/{id}`     | Delete a loan         |

---

## 🛠️ How to Run

1. **Start Eureka Server**
2. **Run Loan Service:**

```bash
./mvnw spring-boot:run
```

3. **Run Gateway:**

```bash
./mvnw spring-boot:run -pl gateway
```

4. Visit: [http://localhost:8093/api/loans](http://localhost:8093/api/loans)

---

## 👩‍💻 Author

Created by Manel Fatnassi  
📚 Student @ ESPRIT — Software Engineering

---


