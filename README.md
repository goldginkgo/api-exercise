# Java CRUD RESTful services

## Requirements
Write an application that provides CRUD (create, read, update, delete) RESTful services for customer data.

Each customer has a
- name
- address
- telephone number

The code should be production level quality and it should be possible to run the application.

Further, the code should demonstrate your level of proficiency in TDD/BDD.

The technology to be used:
-Java 7 or later
-free choice of Java frameworks

## Running the application
1. Import api-exercise as a Maven project in IDE(e.g. Intellij IDEA).
2. Run install command in Maven Project of IDE. Code coverage reports are located in target/site/jacoco folder.
   Logs are located in logs folder.
3. Run com.example.api.Application to start the application.
4. Use RESTClient to test RESTful services.

## Design notes
- Implemented the following Restful services with Spring. And used Spring Boot to launch the application.

| URL | HTTP  Method | Description |
| :-------------- |:-------| :-------------------------------|
| /custommers     | GET    | Return a list of all customers. |
| /customers/{id} | GET    | Return customer with id {id}.   |
| /customers      | POST   | Create a new customer.          |
| /customers/{id} | PUT    | Update customer with id {id}.   |
| /customers/{id} | DELETE | Delete customer with id {id}.   |
- Used interface-based programming.
- Used log4j 2 rolling files to log the application behavior.
- Used JUnit for unit test and Spring REST Template for integration test.
- Used JaCoCo to show code coverage. My test cases covered almost 100% of the code (except for the main method).
