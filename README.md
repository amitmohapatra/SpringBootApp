# SpringBootApp
  
  - Application starts by using the url http://localhost:8080
  - Tech stacks
        - Java 1.8
        - Spring Boot
        - Database H2 (In-Memory)
        - Maven
        
# create user
  - Application need few users to be created.
  - A admin user is created by default (username - admin, password - admin, name - admin, Role - ADMIN)
  - username is a unique constraint in DB.
  
  - you can create a user by following curl command
  - curl -X POST http://localhost:8080/users/sign-up -H 'content-type: application/json' -d \
    '{"name":    "user","password":"user","userName": "user"}'
    
  - for valid user creation we will get response like : {
    "userName": "user",
    "name": "user"
    }
    
  - if user exist then you will get an exception like : {
    "timestamp": 1521472925007,
    "status": 400,
    "error": "Bad Request",
    "exception": "com.upday.exception.ConstraintsViolationException",
    "message": "User exist with username : user",
    "path": "/users/sign-up"
    }
  
  
