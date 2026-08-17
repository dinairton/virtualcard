# Start with a Maven image to build the application
FROM maven:3.9.4-eclipse-temurin-21 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml ./
# Download dependencies only to cache the layers
RUN mvn dependency:go-offline -B

# Copy the rest of the application source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# ---- Final stage ----
# Start with a lightweight OpenJDK 21 runtime image
FROM eclipse-temurin:21-jre

# Set the working directory for the Java application
WORKDIR /app

# Copy the built jar file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port the Spring Boot app runs on
EXPOSE 8085

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]