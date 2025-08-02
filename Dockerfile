# Start from a lightweight JDK base image
FROM eclipse-temurin:17-jdk-alpine

# Add a volume for logs (optional)
VOLUME /tmp

# Set working directory
WORKDIR /app

# Copy the JAR (replace with your jar file name if not target/*.jar)
COPY ./cheque/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
