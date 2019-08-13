#FieldConditionStatisticsServices

1. Project Starter - FieldConditionStatisticsStarter.java class
2. ResponseEntity exception handling (controller) - RestResponseEntityExceptionHandler.java
3. Swagger api handler - SwaggerConfig.java can be connected on http://localhost:8080/swagger-ui.html when application is up and running.

# DESIGN -
## Decisions - 
1. As field conditions(field-conditions) are required to store (POST) and hence FieldCondition (entity) used to perform db operations.
There are two domain classes associated- 
b. StoreFieldConditionRequest.java - Used as a request object to receive field-condition.
c. StoreFieldConditionResponse.java - Used as a response object to provide stored db id after successful storage of field-condition.

2. Used SpringDataJpa, FieldConditionRepository.java is a jpa repository. AVG, MIN and MAX database aggregate functions are used to find out the min, max and avg vegetation.

Hence rest service fulfills the objective of constant time O(1) one operation performed to locate.

3. Field statistics(field-statistics) are required to find (GET) and hence FieldStatistics.java used as a response object of avg, min and max field-statistics.
Used jackson to provide a simple wrapper over FieldStatistics.java to receive desired form of the output. It is possible to use a nested static class indeed but for current project point of view jackson wrapper seems sufficient. 

## H2 DATABASE -
1. Database can be connected on http://localhost:8080/h2-console
JDBC URL - jdbc:h2:mem:fieldconditionstatisticsdb
User name -sa
password
No password required.

DB configuration is available in application.yml inside src/main/resources folder structure.

#TESTING
Folder - src/testint/java
## Integration testing - 
###(Project prepared in Eclipse due to that the folder structure src/testint/java may or may not be available as source folder. In case if not, then just find them in project directory and configure them as select java folder (from src/testint/java) and "Build Path -> Use as a source folder).

Spring runner is used in "Transactional" mode.
1. FieldConditionTestInt.java (entity) - SpringRunner is used.
2. FieldConditionRepositoryTestInt.java (Repository) - SpringRunner is used.
3. FieldConditionControllerTestInt.java (Controller) - RestTemplate is used.

## Unit testing -
Folder - src/test/java

For unit testing mockito is used to provide mock.
Unit testing is performed layer by layer (bottom to up) which means, once service layer testing is performed, mock service is used in controller to evaluate desired outcome.

1. FieldConditionControllerTest.java
2. FieldConditionTest.java
3. FieldStatisticsTest.java
4. StoreFieldConditionRequestTest.java
5. StoreFieldConditionResponseTest.java
6. FieldConditionServiceTest.java

#POM.XML
1. H2 DB dependency.
2. Jackson dependency for wrapper object.
3. Google gson library for json parsing of wrapper object.
4. Springforx for swagger api.

#Project build -
## a) mvn package OR mvn clean install
It will create FieldConditionStatisticsServices.jar in target folder.

## b) java -jar FieldConditionStatisticsServices.jar  
Execute it using command prompt.
OR
a)It is a SpringBoot project and can be simply executed as a java project from FieldConditionStatisticsStarter.java class