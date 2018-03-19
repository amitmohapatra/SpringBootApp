# SpringBootApp
  
  - Application starts by using the url http://localhost:8080 by executing com.upday.Application
  - H2 db access http://localhost:8080/console and put jdbc:h2:mem:upday_db in JDBC URL
  
# Tech Stacks
  - Java 1.8
  - Spring Boot
  - Database H2 (In-Memory)
  - Maven
        
# create an user
  - Application need few users to be created.
  - A admin user is created by default (username - admin, password - admin, name - admin, Role - ADMIN)
  - username is a unique constraint in DB.
  
  - you can create a user by following curl command
  - curl -X POST http://localhost:8080/users/sign-up -H 'content-type: application/json' -d '{"name":    "user","password":"user","userName": "user"}'
  - create one more user with user name user1.
    curl -X POST http://localhost:8080/users/sign-up -H 'content-type: application/json' -d '{"name":    "user1","password":"user1","userName": "user1"}'
    
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
   - curl -X POST  --user my-trusted-client:secret  'http://localhost:8080/oauth/token?grant_type=password&username=user&password=user'
   - on success we will get a response 
     {"access_token":"ba12ed81-313b-4c7d-b9ba-4ad551da5f5d","token_type":"bearer","expires_in":4999,"scope":"
     read write trust"}
   - apis can be accessed through access token but role is not checked for the time being.
  
# create an article
   - curl -X POST --user my-trusted-client:secret 'http://localhost:8080/v1/article?access_token=ba12ed81-313b-4c7d-b9ba-4ad551da5f5d' -H 'content-type: application/json' -d '{"authors": ["user", "user1"],"description": "test test","header": "tset","tags":["test", "test2", "compac"],"text":"test test test","createdByUsername": "user"}'
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
     
  - application accept only valid user(created) as author. else it will throw an error.
    {
    "timestamp": 1521477097648,
    "status": 400,
    "error": "Bad Request",
    "exception": "com.upday.exception.ConstraintsViolationException",
    "message": "user does not exist with username : user12",
    "path": "/v1/article"
   }
  - editor can be an author by assumption.
  - you can create an article without author or tags by providing empty list.
 
# update an article
  - curl -X PUT 'http://localhost:8080/v1/article?access_token=a2df5070-b14d-4990-977e-8e52c2bba762' -H 'content-type: application/json' -d '{"id":4,"authors": ["user", "user1"],"description": "test test","header": "tset","tags": ["test","test2222", "compac123"],"text": "test test test","createdByUsername": "user"}' 
  
# delete an article
  - curl -X DELETE 'http://localhost:8080/v1/article/4?access_token=d6b52a53-3b24-49e9-9924-e146117b5048'
  - if article not present then it will throw an error : 
    {
    "timestamp": 1521485902980,
    "status": 400,
    "error": "Bad Request",
    "exception": "com.upday.exception.EntityNotFoundException",
    "message": "Could not find article with id: 4",
    "path": "/v1/article/4"
    }
   - delete implementation is a soft delete mechanism. which do not delete the article from db but make field to false.
   
# select one article
  - curl -X GET 'http://localhost:8080/v1/article/2?access_token=d6b52a53-3b24-49e9-9924-e146117b5048'
  - if article not present or deleted then
    {
    "timestamp": 1521486256641,
    "status": 400,
    "error": "Bad Request",
    "exception": "com.upday.exception.EntityNotFoundException",
    "message": "Could not find article with id: 4",
    "path": "/v1/article/4"
    }

# select all article
  - curl -X GET 'http://localhost:8080/v1/article?access_token=6eda71ee-7bc5-4857-80fc-75023ea16b56'
  - select all except deleted
  
# select all article by author attribute (by  author username)
  - curl -X POST 'http://localhost:8080/v1/article/author?access_token=35641e89-3430-44d7-ba98-cac082ca6ca8' -H 'content-type: application/json' -d '{"username": "user"}'
  - if success you will get result like :
    [
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
        "createdDate": "2018-03-20 01:41:05.887000",
        "updatedDate": "2018-03-20 01:41:05.887000"
    }
   ]
 - for no match it will return [] (empty list)
 
 # select all article by keyword
  - http://localhost:8080/v1/article/keyword?search=compac&access_token=425bb807-cb33-492b-abcd-a135be4901a4
  
 # select all article by dates
  - curl -X POST 'http://localhost:8080/v1/article/date?access_token=425bb807-cb33-492b-abcd-a135be4901a4' -H 'content-type: application/json' -d '{"begindate": "2018-03-20 01:57:38.198","enddate": "2018-03-24 01:57:38.198"}'
  - on success
    [
    {
        "id": 3,
        "createdByUsername": "admin",
        "header": "apple",
        "description": "apple phone",
        "text": "apple phone",
        "createdDate": "2018-03-20 01:57:38.198000",
        "updatedDate": "2018-03-20 01:57:38.198000"
    },
    {
        "id": 2,
        "createdByUsername": "admin",
        "header": "nokia",
        "description": "nokia phone",
        "text": "nokia phone",
        "createdDate": "2018-03-20 01:57:38.198000",
        "updatedDate": "2018-03-20 01:57:38.198000"
    },
    {
        "id": 1,
        "createdByUsername": "admin",
        "header": "mobile",
        "description": "samsung phone",
        "text": "samsung phone",
        "createdDate": "2018-03-20 01:57:38.198000",
        "updatedDate": "2018-03-20 01:57:38.198000"
    },
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
        "createdDate": "2018-03-20 01:59:53.080000",
        "updatedDate": "2018-03-20 01:59:53.080000"
    }
    ]     
 - date format should be in yyyy-MM-dd HH:mm:ss.SSS format
 - if your begindate is greter than enddate then you will get an error
    {
    "timestamp": 1521492933253,
    "status": 400,
    "error": "Bad Request",
    "exception": "com.upday.exception.EntityNotFoundException",
    "message": "Begin date should not be greater than start date",
    "path": "/v1/article/date"
    }
