# nys-child-support-portal
NYS DCSS – Application Modernization Project

Child & Family Services Case Management System

A full-stack enterprise application designed to modernize legacy child and family services management systems using Java 17, Spring Boot, Angular, PostgreSQL, Apache Kafka, Docker, Kubernetes, and AWS.

This project demonstrates the end-to-end development lifecycle of an enterprise government application, including requirements analysis, system design, backend development, frontend integration, database management, security, testing, CI/CD, cloud deployment, and production support.

Note: This is an independent educational project inspired by government application modernization workflows. It is not an official State of New York application and does not contain actual government data or confidential production functionality.

⸻

1. Project Overview

Business Objective

The primary objective is to modernize legacy child and family services applications by transitioning from traditional monolithic architectures to a scalable, secure, maintainable application architecture.

The application provides centralized functionality for managing child and family service cases, investigations, foster care placements, health records, notifications, and reporting.

Business Areas

* Child Support Services
* Child Protective Services
* Intake and Investigations
* Case Management
* Foster Care Placement
* Child Health and Well-being
* Caseworker Management
* Reporting and Analytics

Example Business Scenario

A caseworker receives a report concerning a child’s safety.

The application allows the caseworker to:

1. Register the incoming report.
2. Search existing family and case history.
3. Create an intake record.
4. Initiate an investigation.
5. Assign an investigator.
6. Document safety assessments.
7. Add investigation progress notes.
8. Record investigation outcomes.
9. Manage follow-up activities.
10. Generate reports.

⸻

2. Technology Stack

Category	Technology
Programming Language	Java 17
Backend Framework	Spring Boot 3.x
Application Architecture	Modular Monolith / Microservices
REST APIs	Spring MVC
ORM	Hibernate
Data Access	Spring Data JPA
Database	PostgreSQL
Additional Database	Oracle (integration learning)
Security	Spring Security
Authentication	JWT
Authorization	Role-Based Access Control (RBAC)
Messaging	Apache Kafka
Frontend	Angular
Frontend Language	TypeScript
Build Tool	Maven
Unit Testing	JUnit 5
Mocking Framework	Mockito
API Testing	REST Assured / Postman
API Documentation	Swagger / OpenAPI
Version Control	Git / GitHub
CI/CD	Jenkins
Containerization	Docker
Orchestration	Kubernetes
Cloud Platform	AWS
Cloud Services	EC2, EKS, S3, RDS, IAM
Logging	SLF4J / Logback
Monitoring	Spring Boot Actuator

⸻

3. Application Architecture

The initial implementation will follow a layered architecture using Spring Boot.

As the application grows, selected business modules will be extracted into independent microservices.

High-Level Architecture

                  ANGULAR FRONTEND
                         |
                         |
                    API GATEWAY
                         |
                         |
                 SPRING SECURITY
                    JWT / RBAC
                         |
          --------------------------------
          |              |               |
          v              v               v
     CASE SERVICE   PERSON SERVICE   INTAKE SERVICE
          |              |               |
          v              v               v
      DATABASE        DATABASE        DATABASE
          |
          |
     APACHE KAFKA
          |
          v
  NOTIFICATION SERVICE
          |
          v
    NOTIFICATION DB
       DOCUMENT SERVICE
              |
              v
          AWS S3
       REPORTING SERVICE
              |
              v
        REPORT DATABASE
         AUDIT SERVICE

Architecture Principles

* Separation of concerns
* Layered application architecture
* RESTful API design
* Database persistence using JPA/Hibernate
* Secure API communication
* Centralized exception handling
* Event-driven communication
* Independent service responsibilities
* Containerized deployment
* CI/CD automation

⸻

4. Functional Modules

The application will be developed using the following business modules.

Module 1: User Management

Responsible for managing application users.

Features:

* User registration
* User login
* User profile management
* User search
* User updates
* User deactivation
* Role assignment
* Password management

Module 2: Authentication and Authorization

Responsible for securing application resources.

Features:

* User authentication
* JWT token generation
* JWT token validation
* Password encryption using BCrypt
* Role-based authorization
* Method-level security
* Access denied handling
* Secure REST endpoints

Application roles:

* ADMIN
* SUPERVISOR
* CASEWORKER
* INVESTIGATOR
* READ_ONLY

Module 3: Person and Family Management

Maintains information about individuals and family relationships.

Features:

* Create person
* Retrieve person
* Update person
* Search person
* Manage contact information
* Maintain family relationships
* Person history
* Duplicate detection

Module 4: Case Management

Manages the complete lifecycle of child and family service cases.

Features:

* Create case
* Retrieve case
* Update case
* Search cases
* Assign caseworker
* Transfer case
* Update case status
* Add case notes
* Maintain case history
* Close case
* Reopen case
* Case document management

Example case lifecycle:

NEW
 |
 v
OPEN
 |
 v
UNDER_REVIEW
 |
 v
ACTIVE
 |
 v
CLOSED

Module 5: Intake and Investigation Management

Manages incoming reports and investigation workflows.

Features:

* Create intake record
* Capture reporter information
* Search family history
* Screen incoming reports
* Create investigation
* Assign investigator
* Update investigation
* Record safety assessments
* Add investigation notes
* Document investigation outcomes
* Close investigation

Module 6: Foster Care Management

Manages foster homes and child placement information.

Features:

* Foster home registration
* Foster parent management
* Foster home availability
* Child placement
* Placement matching
* Placement history
* Placement transfer
* Placement closure

Module 7: Health and Well-being Management

Maintains relevant health and well-being information.

Features:

* Health record management
* Medical history
* Medical appointment tracking
* Well-being assessments
* Progress notes
* Service referrals
* Health information updates

Module 8: Document Management

Manages documents associated with cases and investigations.

Features:

* Document upload
* Document download
* Document metadata
* Document categorization
* Document search
* Document versioning
* Document access control

Storage integration: AWS S3.

Module 9: Notification Management

Provides asynchronous notifications using Apache Kafka.

Features:

* Case creation notifications
* Case update notifications
* Case assignment notifications
* Investigation notifications
* Placement notifications
* Notification history
* Retry mechanism
* Dead Letter Queue (DLQ)

Module 10: Reporting and Dashboard

Provides operational insights and reporting capabilities.

Features:

* Total case count
* Active case count
* Closed case count
* Cases by county
* Cases by caseworker
* Investigation reports
* Foster care reports
* Case status reports
* Date-based filtering
* CSV/Excel export

Module 11: Audit and Logging

Maintains application activity and accountability.

Features:

* User activity tracking
* Case modification history
* Login activity
* Audit records
* Application exception logging
* API activity logging
* Security event logging

Module 12: Administration

Provides administrative functionality.

Features:

* County management
* Case type management
* Reference data management
* Status configuration
* Application configuration
* User access administration
* Application health monitoring

⸻

5. Backend Architecture

The initial Spring Boot application will follow a layered architecture.

Package Structure

dcss-case-management
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.nys.dcss.casemanagement
│   │   │
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── service
│   │   │       ├── service/impl
│   │   │       ├── repository
│   │   │       ├── entity
│   │   │       ├── dto
│   │   │       ├── mapper
│   │   │       ├── exception
│   │   │       ├── security
│   │   │       ├── validation
│   │   │       └── DcssCaseManagementApplication.java
│   │   │
│   │   └── resources
│   │       ├── application.properties
│   │       └── db/migration
│   │
│   └── test
│       └── java
│           └── com.nys.dcss.casemanagement
│
├── pom.xml
└── README.md

Layer Responsibilities

Layer	Responsibility
Controller	Handles incoming HTTP requests
Service	Implements business logic
Repository	Communicates with database
Entity	Represents database tables
DTO	Transfers data between application layers
Mapper	Converts entities into DTOs and vice versa
Exception	Handles application errors
Config	Contains application configurations
Security	Handles authentication and authorization
Validation	Implements request and business validations

⸻

6. Database Design

The application will initially use PostgreSQL.

Initial Database Tables

Table	Purpose
users	Application users
roles	Application roles
user_roles	User-role relationships
persons	Individual information
family_relationships	Family relationships
cases	Case information
case_assignments	Caseworker assignments
case_notes	Case-related notes
case_history	Case modification history
intakes	Incoming reports
investigations	Investigation information
foster_homes	Foster home information
placements	Foster care placements
health_records	Health information
documents	Document metadata
notifications	Notification records
audit_logs	Application audit records

Database Design Principles

* Primary key and foreign key relationships
* Appropriate indexes
* Database constraints
* Normalization
* Transaction management
* Optimized SQL queries
* Pagination and sorting
* Auditing and timestamps

Database schema changes will be managed using Flyway migrations.

⸻

7. REST API Design

All APIs will follow REST conventions and use versioned endpoints.

Base URL:

http://localhost:8080/api/v1

Person Management APIs

Method	Endpoint	Description
POST	/persons	Create person
GET	/persons/{id}	Retrieve person
GET	/persons	Retrieve persons
PUT	/persons/{id}	Update person
DELETE	/persons/{id}	Deactivate person
GET	/persons/search	Search persons

Case Management APIs

Method	Endpoint	Description
POST	/cases	Create case
GET	/cases/{id}	Retrieve case
GET	/cases	Retrieve cases
PUT	/cases/{id}	Update case
PATCH	/cases/{id}/status	Update case status
PATCH	/cases/{id}/assign	Assign caseworker
POST	/cases/{id}/notes	Add case note
GET	/cases/{id}/history	Retrieve case history
GET	/cases/search	Search cases
DELETE	/cases/{id}	Deactivate case

API Standards

* Appropriate HTTP status codes
* Request and response DTOs
* Input validation
* Centralized exception handling
* Pagination and sorting
* Consistent response structures
* Swagger/OpenAPI documentation

⸻

8. Security Implementation

Security will be implemented using Spring Security and JWT.

Authentication Flow

        User Login
             |
             v
      Authentication API
             |
             v
      Validate Credentials
             |
             v
       Generate JWT
             |
             v
       Return JWT Token
             |
             v
      Client Sends Token
             |
             v
       JWT Validation
             |
             v
      Check User Roles
             |
             v
       Access Resource

Security Requirements

* Password hashing using BCrypt
* JWT authentication
* Role-based access control
* Endpoint-level authorization
* Method-level authorization
* Secure error handling
* Sensitive data protection
* Audit logging

⸻

9. Event-Driven Architecture

Apache Kafka will be used for asynchronous communication between services.

Example: Case Updated Event

          CASE SERVICE
               |
               v
        Update Case Data
               |
               v
       Publish Kafka Event
               |
               v
        case-updated-topic
               |
         -------------
         |           |
         v           v
   Notification   Audit
     Service      Service

Kafka Implementation

* Kafka producers
* Kafka consumers
* Event serialization
* Consumer groups
* Retry mechanisms
* Dead Letter Topics
* Idempotent processing
* Error handling

⸻

10. Frontend Development

Angular and TypeScript will be used to build the application interface.

Frontend Modules

* Login page
* Dashboard
* User management
* Person management
* Case management
* Intake management
* Investigation management
* Foster care management
* Health records
* Document management
* Notifications
* Reports
* Administration

Angular Concepts

* Components
* Services
* Dependency Injection
* Routing
* Route Guards
* Reactive Forms
* HTTP Client
* RxJS Observables
* Interceptors
* Authentication integration
* Error handling

⸻

11. Testing Strategy

Testing will be performed at different levels.

Testing Type	Technology
Unit Testing	JUnit 5
Mocking	Mockito
Controller Testing	MockMvc
Integration Testing	Spring Boot Test
Database Testing	Testcontainers
API Testing	REST Assured
Manual API Testing	Postman
Frontend Testing	Angular Testing Tools

Testing Coverage

* Controller tests
* Service tests
* Repository tests
* Validation tests
* Exception handling tests
* Security tests
* Integration tests
* Kafka integration tests
* End-to-end API tests

⸻

12. DevOps and Cloud Deployment

Docker

Containerize Spring Boot applications and supporting infrastructure.

Kubernetes

Deploy and manage application containers.

Implementation:

* Deployments
* Services
* ConfigMaps
* Secrets
* Ingress
* Replicas
* Health probes
* Resource limits

Jenkins CI/CD

Build an automated pipeline.

       Developer
           |
           v
        Git Push
           |
           v
        Jenkins
           |
           v
      Maven Build
           |
           v
       Unit Tests
           |
           v
      SonarQube
           |
           v
     Docker Build
           |
           v
      Image Registry
           |
           v
   Kubernetes Deployment
           |
           v
       Application

AWS Services

Service	Purpose
EC2	Compute infrastructure
EKS	Kubernetes management
S3	Document storage
RDS	Managed relational database
IAM	Identity and access management
CloudWatch	Logs and monitoring

⸻

13. Development Roadmap

The project will be implemented incrementally.

Phase	Implementation	Status
1	Project setup and configuration	Not Started
2	PostgreSQL integration	Not Started
3	Person Management APIs	Not Started
4	Case Management APIs	Not Started
5	Case assignment and workflow	Not Started
6	Intake and Investigation	Not Started
7	Foster Care Management	Not Started
8	Health and Well-being	Not Started
9	Spring Security and JWT	Not Started
10	Kafka integration	Not Started
11	Document Management	Not Started
12	Reporting and Audit	Not Started
13	Angular frontend	Not Started
14	Unit and Integration Testing	Not Started
15	Docker containerization	Not Started
16	Kubernetes deployment	Not Started
17	Jenkins CI/CD	Not Started
18	AWS deployment	Not Started
19	Monitoring and optimization	Not Started

⸻

14. Local Development Setup

Prerequisites

Install the following:

* Java 17
* IntelliJ IDEA
* Maven
* PostgreSQL
* Git
* Docker Desktop
* Node.js and Angular CLI (for frontend development)
* Postman

Step 1: Clone the Repository

git clone <your-github-repository-url>

Step 2: Navigate to the Project

cd dcss-case-management

Step 3: Configure PostgreSQL

Create the development database:

CREATE DATABASE dcss_db;

Configure the database connection in application.properties:

spring.application.name=dcss-case-management
spring.datasource.url=jdbc:postgresql://localhost:5432/dcss_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
server.port=8080

Database credentials should be provided through environment variables, not committed to Git.

Step 4: Build the Application

mvn clean install

Step 5: Run the Application

mvn spring-boot:run

Application URL:

http://localhost:8080

⸻

15. Development Standards

The project will follow enterprise development practices.

* Follow Java coding conventions.
* Use meaningful class and method names.
* Apply SOLID principles.
* Follow separation of concerns.
* Use DTOs for API communication.
* Implement centralized exception handling.
* Use dependency injection.
* Write unit tests for business logic.
* Document REST APIs.
* Use Git feature branches.
* Perform code reviews.
* Avoid committing credentials.
* Maintain database migration scripts.
* Implement appropriate logging.
* Validate all incoming requests.

⸻

16. Learning and Implementation Objectives

By completing this project, the following practical skills will be developed:

1. Java 17 enterprise application development.
2. Spring Boot REST API development.
3. Spring Security and JWT authentication.
4. Hibernate and Spring Data JPA.
5. PostgreSQL database design and SQL optimization.
6. Microservices architecture.
7. Apache Kafka event-driven communication.
8. Angular and TypeScript frontend development.
9. JUnit and Mockito testing.
10. Docker and Kubernetes deployment.
11. Jenkins CI/CD implementation.
12. AWS cloud infrastructure.
13. Application troubleshooting and production support.
14. End-to-end Agile SDLC implementation.

⸻

17. Project Goal

Build a realistic, enterprise-level child and family services case management application using modern Java technologies.

The project will demonstrate the complete software development lifecycle, from business requirements and application architecture through implementation, testing, deployment, and production readiness.

Primary Development Focus: Java 17, Spring Boot, REST APIs, Spring Security, JPA/Hibernate, PostgreSQL, and Angular.

Advanced Implementation: Kafka, Microservices, Docker, Kubernetes, Jenkins, and AWS.
