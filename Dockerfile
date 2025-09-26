# Usa un'immagine base con Java 17 e Maven
FROM eclipse-temurin:17-jdk AS build

# Imposta la directory di lavoro
WORKDIR /app

# Copia i file Maven e scarica le dipendenze
COPY pom.xml ./
COPY src ./src

# Compila il progetto e genera il JAR
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Secondo stage: immagine leggera per esecuzione
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copia il JAR dal primo stage
COPY --from=build /app/target/fmbackend-0.0.1-SNAPSHOT.jar app.jar

# Espone la porta (modifica se usi una diversa)
EXPOSE 8080

# Comando di avvio
ENTRYPOINT ["java", "-jar", "app.jar"]
