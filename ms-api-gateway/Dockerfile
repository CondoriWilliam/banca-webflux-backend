FROM eclipse-temurin:17.0.16_8-jdk

LABEL authors="wcod"

COPY target/gateway-0.0.1-SNAPSHOT.jar app-msvc-gateway.jar

ENTRYPOINT ["java", "-jar", "/app-msvc-gateway.jar"]