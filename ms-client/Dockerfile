FROM eclipse-temurin:17.0.16_8-jdk

LABEL author=wcod

COPY target/microservice-client-0.0.1-SNAPSHOT.jar app-msvc-client.jar

ENTRYPOINT ["java", "-jar" , "/app-msvc-client.jar"]