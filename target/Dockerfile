# Start from Ubuntu as the base image
FROM ubuntu:22.04

# Update packages and install a tool
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Set working directory inside the container
WORKDIR /app

# Copy your jar file into the container and rename to app.jar
COPY ContactManager-1.0-SNAPSHOT.jar app.jar

# Default command when container runs
CMD ["java", "-jar", "app.jar"]