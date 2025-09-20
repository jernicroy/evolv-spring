# ---- Build Stage ----
FROM gradle:8.14.3-jdk17-alpine AS builder
WORKDIR /app

# Copy Gradle wrapper and build files first (for caching)
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# Download dependencies
RUN ./gradlew --no-daemon build -x test || return 0

# Copy source code
COPY src ./src

# Build the app (skip tests for faster builds, remove -x test if you want tests)
RUN ./gradlew clean bootJar --no-daemon -x test

# ---- Run Stage ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy only the built jar from builder
COPY --from=builder /app/build/libs/*.jar evolv-app.jar

# Expose the default Spring Boot port
EXPOSE 8080


# Run the application
ENTRYPOINT ["java","-jar","evolv-app.jar"]
