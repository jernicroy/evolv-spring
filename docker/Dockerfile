# --------------------
# Build stage
# --------------------
FROM ubuntu:latest AS build

# Install Java 17 and unzip for Gradle wrapper
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk unzip && \
    apt-get clean

# Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$JAVA_HOME/bin:$PATH

# Copy project files
COPY . .

# Grant execute permission for Gradle wrapper
RUN chmod +x ./gradlew

# Build the project and generate Javadoc
RUN ./gradlew clean build # javadoc -x test

# --------------------
# Runtime stage
# --------------------
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Expose the port your Spring Boot app will run on
EXPOSE 8080

# Copy the JAR and static Javadocs from the build stage
COPY --from=build /build/libs/EvolveHomeSync-0.0.1-SNAPSHOT.jar /app/EvolveHomeSync-0.0.1.jar
#COPY --from=build /build/docs/javadoc /app/static

# Entry point to run the application
ENTRYPOINT ["java","-jar","/app/EvolveHomeSync-0.0.1.jar"]
