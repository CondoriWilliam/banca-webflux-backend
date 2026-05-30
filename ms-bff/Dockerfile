FROM eclipse-temurin:17.0.16_8-jdk

LABEL authors="wcod"

COPY target/microservice-bff-0.0.1-SNAPSHOT.jar app-msvc-bff.jar

ENTRYPOINT ["java", "-jar", "/app-msvc-bff.jar"]