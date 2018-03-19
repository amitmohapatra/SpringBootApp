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
  - create one more user with user name user1.
    
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
    
# create an access token
   - once you create a user, you need to create access token to access other urls for that user.
   - curl -X POST  --user my-trusted-client:secret      \  
   'http://localhost:8080/oauth/token?grant_type=password&username=user&password=user'
   - on success we will get a response 
     {"access_token":"ba12ed81-313b-4c7d-b9ba-4ad551da5f5d","token_type":"bearer","expires_in":4999,"scope":"
     read write trust"}
  
# create an article
   - curl -X POST --user my-trusted-client:secret \
     'http://localhost:8080/v1/article?access_token=ba12ed81-313b-4c7d-b9ba-4ad551da5f5d' \
     -H 'content-type: application/json' \
     -d '{"authors": ["user", "user1"],"description": "test test","header": "tset","tags":["test", "test2", "compac"],
     "text":"test test test","createdByUsername": "user"}'
   - on success you will get a response like :
     {
      "id": 5,
      "createdByUsername": "user",
      "header": "tset",
      "description": "test test",
      "text": "test test",
      "authors": [
        "user",
        "user1"
      ],
      "tags": [
        "test2",
        "test",
        "compac"
      ],
      "createdDate": "2018-03-19 21:50:09.971000",
      "updatedDate": "2018-03-19 21:50:09.971000"
     }
