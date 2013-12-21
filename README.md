# BDD in Action Chapter 9
## Sample web service testing application

A simple application that demonstrates testing a web service API using BDD.

The application uses Jersey to provide a simple REST web service that reports on flight itineraries and statuses.
It runs using an embedded Tomcat server.

-----
mvn clean package tomcat:run
-----

You can check that the web services are available by using the following URL:

-----
http://localhost:8080/rest/flights/status
-----


