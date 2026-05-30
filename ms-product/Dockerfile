FROM eclipse-temurin:17.0.16_8-jdk

LABEL authors="wcod"

COPY target/microservice-product-0.0.1-SNAPSHOT.jar app-msvc-product.jar

ENTRYPOINT ["java", "-jar", "/app-msvc-product.jar"]