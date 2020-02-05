# FlightAPI

Another flight integration api with Java and Spring boot.

## Requirements
This project was built using:
- Java JDK 13
- Spring Boot 2.2.4
- Lombok (mandatory, install it on your IDE)
- Maven
- check pom.xml for a complete dependencies list

## Concepts used here
For this project we're using the complete Spring stack:
1. spring-boot-starter-cache for caching 
2. spring-boot-starter-security to protect our admin endpoints
3. spring-boot-starter-jdbc to provide a interface with PostgreSQL

I decided to use JDBC Template to have more performance. Don't use JPA when it's not necessary.

We have swagger to provide an API documentation:
http://localhost:8080/swagger-ui.html

Flyway to generate and mantain our database.

## Run the application
To deploy and run the application follow these steps
1. You'll need an Postgres database that will serve to log the requests and save the currencies
2. Adjust the database connection information on pom.xml inside flyway plugin block
3. Adjust the database connection information on src/main/resources/application.properties
4. Generate the database
```bash
mvn flyway:migrate
```
5. run the application
```bash
mvn spring-boot:run
```

## Using the API
A complete documentation of the API is provided at http://localhost:8080/swagger-ui.html

Some methods are secured in /api/admin/**
We have an in memory user as "admin" and password is "123456".

Enjoy it.


## License
                    DO WHATEVER YOU WANT License
                     Version 0.94, October 2017

Copyright (c) 2020 Felipe Amaral

Permission is hereby granted, without limitation, to use, copy, modify,
sublicense and/or distribute this software for any purpose with or without fee,
subject to only the following conditions:

0. This copyright notice and permission notice must appear in all copies
1. That's it, just do whatever you want

This program is free software. The software is provided "as is" without any
warranty whatsoever, to the extent permitted by applicable law. In no event
shall the authors or copyright holders be liable for any claim, damages or
other liability, wether in an action of contract, tort or otherwise, arising
from, out of or in connection with the software or the use of the software.

   
 