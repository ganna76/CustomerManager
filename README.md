# CustomerManager

For Customer Manager, I decided to use the features of Spring Boot to enable endpoints to be coded quickly.  I also decided to use a postgres database because it’s open source and popular.

By using various Spring Boot annotations, it’s possible for endpoints to write and read from the database without coding any SQL.  Even the CUSTOMER table itself is created automatically based on the composition of the Customer object in the java code.  This cuts down development time and ensures consistency between java and the database.

Within Spring Boot, I followed the standard layers:  API (Controller), Service, and Data.  Calls flow through each endpoint, reach the database, then go in reverse order back up to the user.

I stored the database connection information in an application.properties file to minimise the need to touch the java code between any environments.

As well as unit testing, I tested the endpoints with Postman.
