# 🛒 Smart-Ecommerce-Application

A scalable, event-driven **E-commerce Backend System** designed to handle high throughput, user activity, and intelligent processing using **Kafka**, **microservices**, and **MongoDB**. Built for performance, fault-tolerance, and real-world application needs.

## 🚀 Features

- ✅ User Registration & Authentication (JWT, Refresh Tokens)
- 🛍️ Product Management (Add, Update, Delete, View)
- 🧾 Order & Payment Processing
- 📦 Inventory & Cart Management
- 📊 Event-driven architecture using Apache Kafka
- 📬 Notification Microservice for order events
- 💬 Email/SMS Integration
- 🔐 API Gateway + Service Discovery with Spring Cloud
- 🛠️ CI/CD, Docker & Kubernetes-ready

---

## 🏗️ Architecture

[ Client App ]
|
[API Gateway]
|
| | | | | | |
User Product Order Cart Kafka Messaging Token
MS MS MS MS Broker Service MS

yaml
Copy
Edit

> Microservices communicate through Kafka for event-driven actions.

---

## ⚙️ Tech Stack

- **Java + Spring Boot** (Microservices)
- **Spring Cloud Gateway**, **Eureka** (API Gateway, Service Discovery)
- **MongoDB**, **PostgreSQL**
- **Apache Kafka** (Async Communication)
- **JWT + Refresh Token**
- **Docker**, **Docker Compose**
- **CI/CD:** GitHub Actions or Jenkins *(optional setup)*
- **Frontend:** *(Planned for future / under development)*

---

## 📂 Modules Breakdown

| Module         | Responsibility                          |
|----------------|------------------------------------------|
| User Service   | Handles registration, login, tokens      |
| Product Service| Manages product listings and updates     |
| Order Service  | Handles order placement and payments     |
| Cart Service   | Shopping cart management                 |
| Kafka Broker   | Manages event stream between services    |
| Message Service| Sends email/SMS via Kafka events         |
| Token Service  | Manages JWT & Refresh tokens             |

---

## 🔄 Kafka Events Example

| Event Producer    | Kafka Topic          | Event Consumer         |
|-------------------|----------------------|------------------------|
| Order Service     | `order_placed`       | Messaging Service      |
| User Service      | `user_registered`    | Messaging Service      |
| Product Service   | `product_updated`    | Inventory Service      |

---

## 🧪 How to Run Locally

### Prerequisites

- Java 17+
- Maven
- Docker & Docker Compose
- MongoDB & PostgreSQL installed (or use Docker)

### Run Kafka with Docker

```bash
docker-compose -f kafka-docker-compose.yml up -d
Run Microservices
Each service is a Spring Boot app. You can run them individually:

bash
Copy
Edit
cd user-service
mvn spring-boot:run
Or run all with Docker Compose (if configured).

🔐 API Endpoints
Method	Endpoint	Description
POST	/api/user/signup	Register new user
POST	/api/user/login	Authenticate & get tokens
GET	/api/products	List all products
POST	/api/orders	Place new order
POST	/api/refresh-token	Generate new access token

Full API documentation available soon.

📧 Kafka + Email Flow (Example)
User places an order.

Order-Service sends message to order_placed Kafka topic.

Messaging-Service consumes the message.

Sends email/SMS to user with order details.

📦 Future Enhancements
 AI-driven product recommendation

 Fraud detection using ML

 Admin dashboard

 GraphQL API support

 Payment gateway integration (Stripe, Razorpay, etc.)

🧑‍💻 Author
Aditya Bedi
Backend Engineer | Kafka | Spring Boot | Microservices
LinkedIn • GitHub
