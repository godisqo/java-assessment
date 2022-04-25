## Clone the project

- Clone
- Open the project with the IDE of your choice

## Build Instructions
### Take one of the two approaches below :

1. Run note-service with mysqldb (Requires Docker as a prerequisite)
    - Install [Docker](https://www.docker.com/products/docker-desktop), if you haven't already
    - Navigate to note-service app in your local dir
    - Run `````$ ./gradlew build -x test`````
    - Run `````$ docker-compose up --build -d`````
    - Run `````$ docker ps````` to check the containers are running
    - If the notes-service-0.0.1 docker container fails to start for any reason then simply start the application from command line with  `````$ ./gradlew bootRun````` .
 
####
2. Run note-service with in-memory db
    - Uncomment properties values for in-memory db from ```/NotesService/src/main/resources ```
    - Comment out properties values for mysql db
    - Run `````$ ./gradlew build -x test`````
    - Start a Spring Boot Application using the IDE of your choice
    
### Test

To add a Note hit below cURL or use Postman collection ```/NotesService/Postman/note-service.postman_collection.json``` 
```shell
curl --location --request POST 'http://localhost:8080/api/v1.0/notes' \
--header 'Authorization: Basic dXNlcjpwYXNzd29yZA==' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=7EC61E480804EE04D2BB468C931CEF22' \
--data-raw '{
    "title": "My Note",
    "note": "Buy Headphone"
}'
  ```
   - In order to test further, keep the mysql docker container running, just restart the application from your IDE as you make code changes.
### Database

Login to MySql using client of your choice or use CLI

```shell
mysql -h 127.0.0.1 --port 33306 -u MYSQL_USER -pMYSQL_PASSWORD MYSQL_DATABASE
  ```
Use the values from ```docker-compose.yml``` file

### Caveats
For some reason if note-service docker container fail to start, hit ```docker kill note-service-0.0.1``` and start spring boot application from your IDE instead.
