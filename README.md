# nasa-rover-api
API to retrieve select photos from NASA's Mars Rover

Building and Running the Project
	- This is a Maven based project, so run `mvn install` to execute unit tests and build the Jar.
	- Next run `java -jar target/rover-0.0.1-SNAPSHOT.jar` to start the project

Running Sample Calls
	- A Sample Postman collection has been included with in the repository. This collection can be imported to your Postman.
	- Otherwise, this is a GET request with one parameter `date`. The date parameter must be in YYY-MM-dd format to be parsed.