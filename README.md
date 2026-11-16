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
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

NYS DCSS – Legacy Application Project

Child & Family Services Case Management System



Legacy Application Modernization – Child & Family Services Case Management System

1. Project Overview

This project demonstrates the modernization of a traditional monolithic enterprise application into a modern, scalable, and maintainable application using Java, Spring Boot, Angular, and cloud-native technologies.

The application is inspired by child and family services case-management business processes. It represents an educational modernization project and is not an official State of New York application or a reproduction of any government production system.

The original application is assumed to be built using a traditional monolithic architecture with Java, Spring MVC, JSP, Hibernate, and Oracle Database.

The modernization effort focuses on migrating legacy functionality into modular Spring Boot components, exposing REST APIs, modernizing the frontend, improving security, and gradually transitioning selected business capabilities into independent microservices.

Primary Objectives

* Modernize legacy Java applications using Java 17 and Spring Boot.
* Transform tightly coupled business functionality into modular components.
* Replace JSP-based interfaces with Angular.
* Develop reusable RESTful APIs.
* Modernize database access using Spring Data JPA and Hibernate.
* Implement authentication and role-based authorization.
* Introduce event-driven communication using Apache Kafka.
* Containerize applications using Docker.
* Deploy and orchestrate applications using Kubernetes.
* Implement automated CI/CD pipelines using Jenkins.
* Improve application maintainability, scalability, security, and reliability.

⸻

2. Legacy Architecture

The original application follows a traditional monolithic architecture.

Legacy Technology Stack

Layer	Technology
Programming Language	Java 8
Backend Framework	Spring MVC
Frontend	JSP, HTML, CSS, JavaScript
Persistence	Hibernate
Database	Oracle
Application Server	Traditional Java application server
Integration	Point-to-point integrations
Deployment	Traditional application deployment

Legacy Architecture Diagram

                  Legacy Web Application
                           |
          -------------------------------------
          |                 |                 |
          v                 v                 v
    Person Module      Case Module      Investigation
                                            Module
          |                 |                 |
          -------------------------------------
                           |
                     Business Logic
                           |
                     Data Access
                           |
                       Hibernate
                           |
                       Oracle DB

Legacy Architecture Challenges

* Tightly coupled business modules.
* Difficult application maintenance.
* Changes to one module can affect other modules.
* Entire application deployment required for individual functionality changes.
* Limited independent scalability.
* Complex regression testing.
* Difficult integration with modern enterprise systems.

⸻

3. Modernized Technology Stack

The application is being modernized using a modern Java full-stack technology ecosystem.

Layer	Legacy Technology	Modern Technology
Programming Language	Java 8	Java 17
Backend Framework	Spring MVC	Spring Boot
Frontend	JSP	Angular
API Communication	Traditional server-side calls	REST APIs
Persistence	Hibernate	Spring Data JPA / Hibernate
Database	Oracle	PostgreSQL
Security	Traditional authentication	Spring Security and JWT
Integration	Point-to-point	REST APIs and Apache Kafka
Containerization	Traditional deployment	Docker
Orchestration	Traditional application server	Kubernetes
CI/CD	Manual deployment	Jenkins
Cloud Infrastructure	Traditional infrastructure	AWS

⸻

4. Target Architecture

The modernization strategy begins with a modular Spring Boot application and gradually evolves toward a microservices-based architecture.

High-Level Architecture

                     Angular Frontend
                            |
                            v
                       API Gateway
                            |
                            v
                    Spring Security / JWT
                            |
          -----------------------------------------
          |                  |                    |
          v                  v                    v
     Person Module      Case Module         Intake Module
          |                  |                    |
          -----------------------------------------
                            |
                            v
                      PostgreSQL DB
                            |
                            v
                       Apache Kafka
                            |
                            v
                  Notification Service
          -----------------------------------------
          |                                       |
          v                                       v
    Document Service                       Reporting Service
          |                                       |
          v                                       v
        AWS S3                              Reporting Data

Note: This is the intended target architecture. The initial implementation will use a modular monolith before extracting selected capabilities into independently deployable microservices.

Architecture Principles

* Separation of concerns.
* Layered application architecture.
* Modular business capabilities.
* RESTful API development.
* Centralized authentication and authorization.
* Event-driven communication.
* Independent deployment of extracted services.
* Containerized application deployment.
* Automated build, test, and deployment pipelines.

⸻

5. Business Modules and Functionalities

The application will contain the following business capabilities.

5.1 User Management

* Create and manage users.
* Maintain user profiles.
* Assign application roles.
* Activate and deactivate users.
* Manage user access.

5.2 Person and Family Management

* Register individuals.
* Maintain personal information.
* Search individuals.
* Manage contact information.
* Maintain family relationships.
* Identify potential duplicate records.
* View person history.

5.3 Case Management

* Create and update cases.
* Search and retrieve cases.
* Assign cases to caseworkers.
* Transfer case ownership.
* Maintain case status.
* Record case notes.
* Track case history.
* Close and reopen cases.

5.4 Intake and Investigation

* Create intake records.
* Capture incoming reports.
* Search existing family information.
* Perform preliminary screening.
* Assign investigations.
* Record investigation findings.
* Maintain investigation outcomes.

5.5 Foster Care and Placement

* Maintain foster home information.
* Manage foster parent details.
* Track placement availability.
* Record placement information.
* Track placement history.
* Manage placement changes.

5.6 Health and Well-Being

* Maintain health records.
* Record appointments.
* Track assessments.
* Maintain progress notes.
* Record referrals and follow-ups.

5.7 Document Management

* Upload documents.
* Download documents.
* Maintain document metadata.
* Categorize documents.
* Track document versions.
* Manage document access.

5.8 Notification Management

* Generate application notifications.
* Notify users about case assignments.
* Notify users about case updates.
* Process asynchronous events using Kafka.
* Implement retry and dead-letter handling.

5.9 Reporting and Dashboard

* Display application statistics.
* Generate case reports.
* Generate assignment reports.
* Filter reports by date and status.
* Provide dashboard summaries.

5.10 Audit and Logging

* Track user activities.
* Maintain case history.
* Record important application events.
* Track security-related activities.
* Support application troubleshooting.

⸻

6. Backend Architecture

The backend will follow a layered architecture.

                    Client Request
                          |
                          v
                    REST Controller
                          |
                          v
                     Service Layer
                          |
                          v
                    Repository Layer
                          |
                          v
                     JPA / Hibernate
                          |
                          v
                     PostgreSQL

Package Structure

com.nys.dcss.casemanagement
│
├── config
│
├── controller
│
├── service
│   └── impl
│
├── repository
│
├── entity
│
├── dto
│
├── mapper
│
├── exception
│
├── security
│
├── validation
│
└── DcssCaseManagementApplication.java

Package Responsibilities

Package	Responsibility
controller	REST API endpoints
service	Business service interfaces
service.impl	Business logic implementation
repository	Database operations
entity	JPA entities
dto	Request and response objects
mapper	Entity and DTO conversion
exception	Global exception handling
config	Application configuration
security	Authentication and authorization
validation	Request validation

⸻

7. Database Design

Database

PostgreSQL

Initial Database Tables

Table	Purpose
users	Application users
roles	Application roles
user_roles	User-role mapping
persons	Individual information
family_relationships	Family relationships
cases	Case information
case_assignments	Caseworker assignments
case_notes	Case notes
case_history	Case status and activity history
intakes	Intake records
investigations	Investigation records
foster_homes	Foster home information
placements	Placement information
health_records	Health-related records
documents	Document metadata
notifications	Application notifications
audit_logs	User activity records

Database schema changes will eventually be managed using Flyway migrations.

⸻

8. REST API Design

All REST endpoints will follow a versioned API convention.

Base URL:

/api/v1

Person Management APIs

HTTP Method	Endpoint	Description
POST	/api/v1/persons	Create person
GET	/api/v1/persons/{id}	Retrieve person
GET	/api/v1/persons	Retrieve all persons
PUT	/api/v1/persons/{id}	Update person
DELETE	/api/v1/persons/{id}	Deactivate person

Case Management APIs

HTTP Method	Endpoint	Description
POST	/api/v1/cases	Create case
GET	/api/v1/cases/{id}	Retrieve case
GET	/api/v1/cases	Retrieve cases
PUT	/api/v1/cases/{id}	Update case
PATCH	/api/v1/cases/{id}/status	Update case status
POST	/api/v1/cases/{id}/assign	Assign case
GET	/api/v1/cases/{id}/history	Retrieve case history

Additional APIs will be implemented as the project evolves.

⸻

9. Security Architecture

Security will be implemented using Spring Security and JWT.

Planned Features

* User authentication.
* JWT token generation and validation.
* Role-based access control.
* Password encryption using BCrypt.
* Endpoint-level authorization.
* Method-level authorization.
* Secure API access.
* Centralized exception handling.

Application Roles

Role	Access
ADMIN	Application administration
SUPERVISOR	Manage teams and cases
CASEWORKER	Manage assigned cases
INVESTIGATOR	Manage investigation activities
READ_ONLY	View authorized information

⸻

10. Event-Driven Architecture

Apache Kafka will support asynchronous communication between business capabilities.

Example: Case Update Notification

                Case Service
                     |
                     v
              Update Case Status
                     |
                     v
               Kafka Producer
                     |
                     v
              Case Updated Event
                     |
                     v
                Kafka Topic
                     |
                     v
             Notification Service
                     |
                     v
              Process Notification

Planned Kafka concepts:

* Producers and consumers.
* Topics and partitions.
* Consumer groups.
* Event serialization.
* Retry mechanisms.
* Dead-letter topics.
* Idempotent event processing.

⸻

11. Frontend Architecture

The frontend will be developed using Angular and TypeScript.

Planned Angular Modules

src/app/
│
├── core/
├── shared/
│
├── features/
│   ├── authentication/
│   ├── dashboard/
│   ├── persons/
│   ├── cases/
│   ├── intake/
│   ├── investigations/
│   ├── placements/
│   ├── reports/
│   └── administration/
│
├── services/
│
└── app.routes.ts

Frontend Features

* Login and authentication.
* Dashboard.
* Person search and registration.
* Case creation and management.
* Case assignment.
* Intake and investigation screens.
* Reporting.
* User administration.

Angular will communicate with backend services through REST APIs.

⸻

12. Testing Strategy

Testing will be implemented at multiple levels.

Testing Type	Technology
Unit Testing	JUnit 5
Mocking	Mockito
Controller Testing	MockMvc
Integration Testing	Spring Boot Test
Database Testing	Testcontainers
API Testing	REST Assured / Postman
Frontend Testing	Angular testing tools
Code Quality	SonarQube

⸻

13. DevOps and Deployment

Containerization

Docker will be used to package Spring Boot applications.

Orchestration

Kubernetes will manage:

* Application deployments.
* Replicas.
* Services.
* ConfigMaps and Secrets.
* Health checks.
* Scaling.

CI/CD Pipeline

Jenkins will automate the build and deployment process.

       Developer
           |
           v
       Git Commit
           |
           v
        Jenkins
           |
           v
       Maven Build
           |
           v
      Unit Testing
           |
           v
      SonarQube
           |
           v
      Docker Build
           |
           v
     Container Registry
           |
           v
      Kubernetes
           |
           v
       Deployment

AWS Services

* EC2
* EKS
* S3
* RDS
* IAM
* CloudWatch

⸻

14. Development Roadmap

The project will be developed incrementally.

Phase	Implementation
1	Spring Boot project setup
2	PostgreSQL database configuration
3	Person Management
4	Case Management
5	Case Assignment and Workflow
6	Intake Management
7	Investigation Management
8	Foster Care and Placement
9	Health and Well-Being
10	Spring Security and JWT
11	Audit Logging
12	Kafka Integration
13	Document Management
14	Reporting and Dashboard
15	Angular Frontend
16	Unit and Integration Testing
17	Microservices Extraction
18	Docker and Kubernetes
19	Jenkins CI/CD and AWS Deployment

⸻

15. Local Development Setup

Prerequisites

Install the following:

* Java 17
* IntelliJ IDEA
* Maven
* PostgreSQL
* Git
* Docker Desktop
* Node.js
* Angular CLI
* Postman

Step 1: Create the Database

CREATE DATABASE dcss_db;

Step 2: Configure application.properties

spring.application.name=dcss-case-management
server.port=8080
spring.datasource.url=jdbc:postgresql://localhost:5432/dcss_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

Step 3: Build the Application

mvn clean install

Step 4: Run the Application

mvn spring-boot:run

Backend application:

http://localhost:8080

⸻

16. Git Branching Strategy

The project will follow a feature-based branching approach.

main
  |
  └── develop
        |
        ├── feature/person-management
        ├── feature/case-management
        ├── feature/intake-management
        ├── feature/security
        └── feature/kafka-integration

Changes will be developed in feature branches and merged into the development branch after review and testing.

⸻

17. Development Standards

* Follow Java coding conventions.
* Use meaningful class and method names.
* Follow SOLID principles.
* Maintain separation of concerns.
* Use DTOs for API communication.
* Implement centralized exception handling.
* Validate incoming requests.
* Write unit and integration tests.
* Avoid hardcoded credentials.
* Document REST APIs.
* Use Git for source control.
* Follow secure coding practices.

⸻

18. Learning and Project Objectives

This project provides hands-on experience with:

* Java 17 and modern Java features.
* Spring Framework and Spring Boot.
* Spring MVC and REST APIs.
* Dependency Injection and Inversion of Control.
* Spring Data JPA and Hibernate.
* PostgreSQL and SQL.
* Spring Security and JWT.
* Microservices architecture.
* Apache Kafka.
* Angular and TypeScript.
* JUnit and Mockito.
* Docker and Kubernetes.
* Jenkins CI/CD.
* AWS cloud services.
* Enterprise application modernization.

⸻

19. Final Project Goal

The final goal is to demonstrate an end-to-end enterprise application modernization journey.

Starting from a traditional monolithic application, the project will progressively introduce modern Java technologies, modular architecture, RESTful communication, event-driven integration, frontend modernization, automated testing, containerization, and cloud deployment.

The result will be a practical full-stack project demonstrating the skills required to design, develop, modernize, test, and deploy enterprise Java applications.
