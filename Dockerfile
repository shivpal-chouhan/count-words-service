# Use a base image with JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy built jar from host to container
COPY target/count-words-service-0.0.1.jar app.jar

# Expose application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
