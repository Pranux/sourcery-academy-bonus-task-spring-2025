FROM openjdk:17-slim

WORKDIR /app

RUN apt update && apt install -y findutils

COPY . .

RUN chmod +x ./gradlew

RUN ./gradlew build

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "./build/libs/sourcery-0.0.1-SNAPSHOT.jar"]
