FieldConditionStatisticsServices

1. Project Starter - FieldConditionStatisticsStarter.java class
2. ResponseEntity exception handling (controller) - RestResponseEntityExceptionHandler.java
3. Application exception handling - FieldConditionStatisticsStarterException
4. Swagger api handler - SwaggerConfig.java can be connected on http://localhost:8080/swagger-ui.html when application is up and running.

DESIGN -
1. FieldCondition (entity) used to perform db operations.
2. Used SpringDataJpa. FieldConditionRepository.java is a jpa repository.

There are three different domain classes - 
a. FieldStatistics.java - Used as a response object of avg, min and max field-statistics.
b. StoreFieldConditionRequest.java - Used as a request object to receive field-condition.
c. StoreFieldConditionResponse.java - Used as a response object to provide stored db id after successful storage of field-condition.

H2 DATABASE -
1. Database can be connected on http://localhost:8080/h2-console
JDBC URL - jdbc:h2:mem:fieldconditionstatisticsdb
User name -sa
password

DB configuration is available in application.yml inside src/main/resources folder structure.
There is a wrapper json object which is wrapped to FieldStatistics.java to receive desired form of the output.

TESTING
Folder - src/testint/java
Integration testing - Spring runner is used in "Transactional" mode.
1. FieldConditionTestInt.java (entity) - SpringRunner is used.
2. FieldConditionRepositoryTestInt.java (Repository) - SpringRunner is used.
3. FieldConditionControllerTestInt.java (Controller) - RestTemplate is used.

Unit testing -
Folder - src/test/java
For unit testing mockito is used to provide mock.
Unit testing is performed layer by layer (bottom to up) which means, once service layer testing is performed, mock service is used in controller to evaluate desired outcome.

POM.XML
1. H2 DB dependency.
2. Jackson dependency for wrapper object.
3. Google gson library for json parsing of wrapper object.
4. Springforx for swagger api.