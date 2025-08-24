# Spring Boot + Keycloak OAuth2.0 Starter Project
This project, springboot-keyclok-auth02, serves as a foundational example for building a Spring Boot 3 application secured with Keycloak. It demonstrates how to configure a Spring Boot application to act as an OAuth 2.0 Resource Server, validating JSON Web Tokens (JWTs) issued by a Keycloak server. This approach is ideal for securing REST APIs in a microservice architecture.
### Key Features
OAuth 2.0 & OIDC: Secure REST endpoints using the industry-standard OAuth 2.0 protocol with OpenID Connect.
Keycloak Integration: Seamlessly integrates with a Keycloak realm to handle all authentication and token validation.
Stateless Security: Utilizes JWTs for stateless security, making it scalable and well-suited for microservices.
Role-Based Access Control (RBAC): Easily protect endpoints based on user roles and permissions configured in Keycloak.
H2 Database: Includes an in-memory H2 database for a simple and quick-start development environment.

### Prerequisites
To build and run this project, you will need:
* Java 17+
* Maven
* A running Keycloak instance (Docker is a great option for local development)
### Getting Started
Follow these steps to set up and run the application.
1. Configure Keycloak
First, you need to set up a Keycloak realm and a client to issue tokens.
* Start Keycloak: If you're using Docker, you can run a Keycloak server with a command like:
docker run -p 8180:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.0 start-dev

This will start a development server on http://localhost:8180.
* Create a Realm: Log in to the Keycloak admin console and create a new realm named dexterv.
* Create a Client: Inside the dexterv realm, create a client.
* Client ID: springboot-client
* Access type: Public (or Confidential, if you prefer a client secret)
* For testing purposes, you may also want to set the Valid Redirect URIs to include http://localhost:8282/*.
* Create a User and Role: Create a new user (e.g., user1) and assign them a role (e.g., ROLE_USER). This role can be used later to protect specific endpoints.
2. Project Configuration
* The project is pre-configured with the provided application.yml and pom.xml.

### src/main/resources/application.yml:
```
server:
 port: 8282

spring:
  datasource:
    url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
    driverClassName: org.h2.Driver
    username: sa
    password:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://localhost:8180/realms/dexterv

```
### pom.xml:
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-parent</artifactId>
       <version>3.5.5</version>
       <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.dexterv</groupId>
    <artifactId>keycloak</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>keycloak</name>
    <description>Demo project for Spring Boot and Keycloak authentication</description>
    <url/>
    <licenses>
       <license/>
    </licenses>
    <developers>
       <developer/>
    </developers>
    <scm>
       <connection/>
       <developerConnection/>
       <tag/>
       <url/>
    </scm>
    <properties>
       <java.version>17</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
        </dependency>
       <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-security</artifactId>
       </dependency>
       <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
       </dependency>
       <dependency>
          <groupId>org.projectlombok</groupId>
          <artifactId>lombok</artifactId>
          <optional>true</optional>
       </dependency>
       <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-test</artifactId>
          <scope>test</scope>
       </dependency>
       <dependency>
          <groupId>org.springframework.security</groupId>
          <artifactId>spring-security-test</artifactId>
          <scope>test</scope>
       </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
    </dependencies>

    <build>
       <plugins>
          <plugin>
             <groupId>org.apache.maven.plugins</groupId>
             <artifactId>maven-compiler-plugin</artifactId>
             <configuration>
                <annotationProcessorPaths>
                   <path>
                      <groupId>org.projectlombok</groupId>
                      <artifactId>lombok</artifactId>
                   </path>
                </annotationProcessorPaths>
             </configuration>
          </plugin>
          <plugin>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-maven-plugin</artifactId>
             <configuration>
                <excludes>
                   <exclude>
                      <groupId>org.projectlombok</groupId>
                      <artifactId>lombok</artifactId>
                   </exclude>
                </excludes>
             </configuration>
          </plugin>
       </plugins>
    </build>
</project>

```
3. Build and Run
* With Keycloak running and configured, you can start the Spring Boot application.
mvn spring-boot:run

### The application will start on port 8282.

### Usage
The application will expose secured endpoints that require a valid JWT.
1. Get an Access Token
To test, you first need to get an access token from your Keycloak server. You can use a tool like curl or Postman.

This will return a JSON object containing your access token.
2. Access the Secured Endpoint
Once you have the access_token from the previous step, you can use it in the Authorization header to access a secured endpoint

This request should succeed. If you try the same request without the Authorization header, it should be rejected with a 401 Unauthorized status.

https://www.keycloak.org/

https://start.spring.io/

https://www.jwt.io/
