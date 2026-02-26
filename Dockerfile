FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy full project
COPY . .

# Give gradle permission
RUN chmod +x gradlew

# Build project inside container
RUN ./gradlew build -x test

# Run jar
CMD ["java", "-jar", "build/libs/com.internshipmanagementsystem-0.0.1-SNAPSHOT.jar"]

EXPOSE 8082