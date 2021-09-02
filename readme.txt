Backed-end Restful Web Services: 

To Search Australian PostCode/Suburb

Brief info:

This project using Spring Boot Version 2.5.4
And Securing a microservice With JWT token


Build:

>mvn clean package

Run:

>java -jar target/postcode-api-0.0.1-SNAPSHOT.jar

Note:

This microservices will run on the port: 9000

Health check URL:http://localhost:9000/actuator/health
Welcome Page URL: http://localhost:9000

API Documentation (Using Swagger 2.0) URL: 
http://localhost:9000/v2/api-docs
http://localhost:9000/swagger-ui/

http://localhost:9000/h2-console/

User:
test/test


Provoded API specification Documentation
  - resources/APISpecification.yaml

We need the access token to access the API

Step to run:
 1. Retrieve Access Token by calling /api/v1/authenticate
 2. Pass Token in Authorization header  for other APIs
 
 
 
 
 