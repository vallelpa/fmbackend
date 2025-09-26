# Stage 1: build con Maven e Java 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copia tutto il progetto
COPY . .

# Compila il progetto e genera il JAR
RUN mvn clean package -DskipTests

# Stage 2: runtime con JRE leggero
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copia il JAR dal build stage
COPY --from=build /app/target/fmbackend-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
