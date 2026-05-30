FROM eclipse-temurin:17.0.16_8-jdk

LABEL authors="wcod"

COPY target/discovery-server-0.0.1-SNAPSHOT.jar app-msvc-discovery-server.jar

ENTRYPOINT ["java", "-jar", "/app-msvc-discovery-server.jar"]