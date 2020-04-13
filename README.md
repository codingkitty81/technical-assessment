# Software Technical Assessment for EXL

## The Employee Search Application

## Business Requirements
* The application accepts the following search input and then displays in a pleasing style a list of employees who meet these criteria
(displaying at least name, job title, age, start and end date of employment).
    * A text box where any part of their first or last name matches what was typed in the search box
    * A date range where the results would include any employees that were employed during this period
* Solution should either seed data or provide a way to enter new users or both

## SQL Setup
1. Update the following information in Spring/src/main/resources/application.properties
    * spring.datasource.url => update schema name to match where employee table will be created
    * spring.datasource.username => update as needed
    * spring.datasource.password => update as needed
2. Use employee_table.sql to build the Employee table
3. If desired, use employee_data.sql to populate the Employee table with sample data. Data can be added to the table through the app itself.

## BackEnd Execution
Within the Spring folder the backend can be executed from the terminal with the following command
```
./mwn spring-boot:run
```

## FrontEnd Execution
Within the Angular folder the frontend can be executed from the terminal with the following command
```
ng serve --open
```
Once compilation is complete a browser window will automatically open to localhost:4200/view-employee

## Test Exectuion
Within the Spring folder tests can be executed from the terminal with the following command
```
./mwn test
```