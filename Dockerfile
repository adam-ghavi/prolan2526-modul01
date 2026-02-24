# Use official Java 21 runtime
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy Gradle wrapper and project files
COPY . .

# Make gradlew executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build -x test

# Expose port (Render uses 10000 by default, but Spring must use $PORT)
EXPOSE 8080

# Run the application
CMD ["sh", "-c", "java -jar build/libs/prolan2526-modul01-0.0.1-SNAPSHOT.jar"]