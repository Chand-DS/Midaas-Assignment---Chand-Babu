**MoonWalk Restaurant Kitchen Management System**

A backend system for intelligent restaurant order processing, countdown estimation, and kitchen workflow simulation built using Spring Boot.

**Overview**

MoonWalk is a space-themed restaurant that requires a system capable of:

- Accepting customer orders
- Calculating estimated preparation time
- Displaying countdown timers
- Managing kitchen workflow
- Simulating kitchen processing
- Tracking execution logs
- Supporting extensible estimation strategies

**This project demonstrates:**

REST API development
Scheduler-based background processing
Strategy Pattern implementation
Queue/backlog-based ETA calculation
Database persistence
API documentation with Swagger/OpenAPI


**Tech Stack**

Java 21	Programming Language
Spring Boot	Backend Framework
Spring Data JPA	ORM Layer
H2 Database	In-memory Database
Lombok	Boilerplate Reduction
Swagger/OpenAPI	API Documentation
Maven	Dependency Management


**Features**

- Order Management
- Place customer orders
- Retrieve order details
- Track order lifecycle
- Intelligent ETA Calculation

ETA calculation includes:

- Dish preparation time
- Current kitchen backlog
- FIFO queue strategy

**Example:**

Order 1 Pizza -> 20 mins
Order 2 Pizza -> 40 mins
Order 3 Pizza -> 60 mins
Countdown Timer

Each order returns:

- Estimated ready time
- Remaining countdown in seconds
- Kitchen Workflow Simulation

Order lifecycle:

PLACED
→ PREPARING
→ READY

Implemented using:

- Spring Scheduler
- Background kitchen processor
- Execution Logging


Every important action is persisted:

- Order placement
- ETA calculation
- Strategy used
- Status changes
- Scheduler execution
- Swagger/OpenAPI Documentation

Interactive API documentation available at:

**http://localhost:8080/swagger-ui/index.html**


Project Structure

```text
src/main/java/com/example/MoonWalkRestro

├── config
├── controller
├── dto
├── entity
├── enums
├── repository
├── scheduler
├── service
├── strategy
└── exception
```


**API Endpoints**

Order APIs

Method	Endpoint	Description
POST	/orders	Place new order
GET	/orders/{id}	Get order details

Kitchen APIs

Method	Endpoint	Description
GET	/kitchen/pending-orders	Get pending orders
GET	/kitchen/active-orders	Get preparing orders
GET	/kitchen/ready-orders	Get completed orders

Log APIs
Method	Endpoint	Description
GET	/logs	Get execution logs
