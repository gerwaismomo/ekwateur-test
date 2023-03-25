

## Info

The following project is in Hexagonal Architecture.

It has 3 main layers:
- **application**:
  - has 3 REST APIs to create clients and compute their bills
- **domain**: 
  - all the business rules reside here
  - there are no infrastructure (annotations, classes), except for Logger and LogFactory classes
- **infrastructure**: 
  - comprises database access

## API docs
 
- Swagger UI : http://localhost:8080/swagger-ui
- Open API : http://localhost:8080/api-docs

## Tests
### Unit Tests
To run tests, just execute the command
```
mvn test
```


### Integration Tests
- start the project
- import the Postman collection `src/test/resources/ekwateur.postman_collection.json`
- run the the collection 
