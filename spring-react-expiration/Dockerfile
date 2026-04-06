#FROM eclipse-temurin:25-jdk
#WORKDIR /app
#COPY target/*.jar myapp.jar
#ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]


# Stage 1: Build
FROM maven:3.9-eclipse-temurin-25 AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY --from=builder /build/target/*.jar myapp.jar
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]