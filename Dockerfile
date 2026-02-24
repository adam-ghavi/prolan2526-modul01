# ---------- Build Stage ----------
FROM gradle:8.5-jdk21 AS builder

WORKDIR /app
COPY . .
RUN gradle build -x test

# ---------- Runtime Stage ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copy built jar and rename to app.jar
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar app.jar"]