# Task 3

To compile project and run tests with maven `mvn clean test`.

To run the server `mvn spring-boot:run`
(Port `8080` must not be in use or changed in `/src/main/resources/application.properties`)

Not only documents are returned in JSON, but also tags associated with each document.

## Assumptions

Documents should be unique by uri, which is enforced by using HashSet with proper hashCode method.
Tag name is limited to 50 characters.

## Test
`curl "http://localhost:8080/taggedContent?tag=humans"`

### Known bug
More tags can have the same name. In our dataset 'carnivore' is tag both for plants and humans... 

If we than search for humans, documents with sub-tags for humans are searched as well and one of the sub-tag is 'carnivore' - which matches also the plant! 
