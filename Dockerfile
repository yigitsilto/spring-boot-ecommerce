# Use the openjdk 11 image as the base image
FROM openjdk:11

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file from the host to the container
COPY target/ecommerce-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8081
EXPOSE 8081

# Set the entrypoint command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
