# cellpone-report
Little example to run reports based in csv files

**How to compile the app**

```mvn clean install```

**How to run the app**

```spring-boot:run```

**Technical Notes and Decisions**

- Use of _OpenCsv_ to ease the csv files manipulation 
- Use of _Spring Boot_ to ease the dependency injection of components and services
- Use of _JUnit_ to create unit tests
- Csv files were stored in the _src/main/resources_ to ease the report process execution

**Alternatives to consider in order to improve the current app**
- Due to the time limitation, I would consider extending the app with the following
  - Use, at least, a db in memory to take advantage of using SQL / PL to run complex queries instead of using memory for calculations
  - Extend the amount of tests to make sure the app validates positive and negative scenarios
  - Use of env variables to start reading parameters like: csv files, year and such other parameters needed to create the report
  - Add more validations to the component and services in order to handle errors in a better way.
  - Add documentation to explain services, components