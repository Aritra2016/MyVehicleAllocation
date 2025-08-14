<h1> User Service – Vehicle Allocation Microservice </h1>
📌 Overview
The User Service is a Spring Boot microservice in the Vehicle Allocation System designed to handle user authentication, authorization, and profile management.
It uses JWT (JSON Web Tokens) for stateless authentication, Spring Security for role-based access control, and integrates with user-related data (roles, countries, and eligibility).

This service will:

Register and authenticate users.

Issue JWT tokens to be used for subsequent requests.

Secure REST API endpoints via JWT-based Spring Security.

Validate user eligibility, roles, and access permissions for vehicle allocation.

<br> </br>

🛠️ <h1> Tech Stack</h1>
Backend Framework: Spring Boot
Security: Spring Security, JWT (io.jsonwebtoken - JJWT)
Database: Oracle (can adapt to any RDBMS)
Build Tool: Maven
Java Version: 17+
REST API: Spring Web
Other: Lombok, JPA/Hibernate
