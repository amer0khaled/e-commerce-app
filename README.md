
# E-Commerce Microservices Platform

**Spring Boot 3 | Spring Cloud | Kafka | Keycloak | Docker**

A scalable, event-driven **E-Commerce backend system** built using **Spring Boot 3** and **Spring Cloud**, following **microservices architecture best practices**.

The system is secured using **Keycloak**, communicates asynchronously using **Apache Kafka**, and supports **distributed tracing with Zipkin**.

---

## Architecture Overview

![Architecture Diagram](docs/architecture.png)

### High-Level Flow

1. Client (Web / Mobile) → **API Gateway**
2. Gateway authenticates requests using **Keycloak**
3. Requests are routed to microservices via **Eureka Service Discovery**
4. Services communicate:

   * **Synchronously** via REST
   * **Asynchronously** via Kafka events
5. Distributed tracing handled by **Zipkin**
6. Each service owns its **own database**

---

## Microservices

| Service                       | Description                           | Database   |
| ----------------------------- | ------------------------------------- | ---------- |
| **API Gateway**               | Single entry point, routing, security | —          |
| **Customer Service**          | Manage customers & addresses          | MongoDB    |
| **Product Service**           | Manage products & inventory           | PostgreSQL |
| **Order Service**             | Create & manage orders                | PostgreSQL |
| **Payment Service**           | Handle payments                       | PostgreSQL |
| **Notification Service**      | Send notifications                    | MongoDB    |
| **Config Server**             | Centralized configuration             | Git        |
| **Discovery Server (Eureka)** | Service registration & discovery      | —          |

---

## Security (Keycloak)

* OAuth2 / OpenID Connect
* JWT-based authentication
* Centralized security at **API Gateway**
* Services act as **Resource Servers**
* Role-based access support

```text
Client → Gateway → Keycloak → JWT → Microservices
```

---

## Event-Driven Communication (Kafka)

Kafka is used to decouple services and ensure reliability.

### Topics

* `order-confirmation`
* `payment-confirmation`

### Flow

1. **Order Service**

   * Publishes `order-confirmation` event
2. **Payment Service**

   * Processes payment
   * Publishes `payment-confirmation` event
3. **Notification Service**

   * Consumes both events
   * Sends email / notification

- Ensures **eventual consistency**
- Improves **scalability and fault tolerance**

---

## Distributed Tracing (Zipkin)

* Integrated using **Micrometer + Brave**
* Traces requests across:

  * Gateway
  * Microservices
  * Kafka producers/consumers

Access Zipkin UI:

```
http://localhost:9411
```

---

## Databases (Database per Service)

Each microservice owns its data to ensure loose coupling:

* **MongoDB**

  * Customer
  * Notification
* **PostgreSQL**

  * Product
  * Order
  * Payment

- No shared databases
- Enables independent scaling & evolution

---

## Docker & Containerization

All services are containerized using **Docker** and orchestrated via **Docker Compose**.

Services include:

* Kafka & Zookeeper
* Zipkin
* Keycloak
* Databases
* All microservices

---

## 🚀 How to Run

```bash
# Start infrastructure and services
docker-compose up -d
```

### Service URLs

| Component     | URL                                            |
| ------------- | ---------------------------------------------- |
| API Gateway   | [http://localhost:8222](http://localhost:8222) |
| Eureka        | [http://localhost:8761](http://localhost:8761) |
| Config Server | [http://localhost:8888](http://localhost:8888) |
| Keycloak      | [http://localhost:9098](http://localhost:9098) |
| Zipkin        | [http://localhost:9411](http://localhost:9411) |

---

## API Testing

* Postman collection available in:

```text
api-collections/ms.postman_collection.json
```

* All requests go through **API Gateway**
* Authorization via **Bearer Token (Keycloak)**

---

## 🛠 Tech Stack

* **Java 17**
* **Spring Boot 3**
* **Spring Cloud (Gateway, Eureka, Config)**
* **Keycloak**
* **Apache Kafka**
* **MongoDB & PostgreSQL**
* **Zipkin**
* **Docker & Docker Compose**

---

## Key Design Principles

* Microservices architecture
* Database per service
* Event-driven communication
* Centralized security
* Observability & tracing
* Clean layered architecture
* SOLID & DDD-inspired structure

---

## Author

**Amer Khaled**
Backend / Java Engineer
Focused on **Backend & Distributed Systems**

---

