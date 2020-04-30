# HTTP and Echo Servers
An HTTP Server and a TCP/IP server echoing sent messages
 
## Run the Echo Server
1. Connect via netcat by typing the following in your terminal:  nc ec2-52-70-154-54.compute-1.amazonaws.com 5000

## Run the HTTP Server
1. [Install Postman](https://learning.postman.com/docs/postman/launching-postman/installation-and-updates/)
2. Submit a request to http://ec2-35-153-12-10.compute-1.amazonaws.com:5000 for the following routes:
    1. GET at /simple_get
    2. GET at /simple_get_with_body
    3. HEAD at /simple_get
    4. HEAD at /head_request
3. Alternatively, enter http://ec2-35-153-12-10.compute-1.amazonaws.com:5000 + route (for example, http://ec2-35-153-12-10.compute-1.amazonaws.com:5000/simple_get) in your browser.

## Run tests
1. [Install Java](https://java.com/en/download/help/download_options.xml)
2. [Install JDK 11.0.6](https://www.techspot.com/downloads/5553-java-jdk.html)
3. Clone repository by typing the following in the terminal:  https://github.com/kudukacar/server.git
4. In your terminal, navigate to the project folder
5. Execute the following command:  gradle wrapper --gradle-version 6.3
6. Type the following in the terminal to run the tests: ./gradlew test