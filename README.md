# Echo Server
A TCP/IP server echoing sent messages
 
## Run the app
1. Connect via netcat by typing the following in your terminal:  nc ec2-52-70-154-54.compute-1.amazonaws.com 5000

## Run tests
1. [Install Java](https://java.com/en/download/help/download_options.xml)
2. [Install JDK 11.0.6](https://www.techspot.com/downloads/5553-java-jdk.html)
3. Clone repository by typing the following in the terminal:  https://github.com/kudukacar/server.git
4. In your terminal, navigate to the project folder
5. Execute the following command:  gradle wrapper --gradle-version 6.3
6. Type the following in the terminal to run the tests: ./gradlew test