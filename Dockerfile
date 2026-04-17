FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"

COPY . .

RUN mvn clean package -DskipTests -Dproject.build.sourceEncoding=UTF-8 -Dproject.reporting.outputEncoding=UTF-8

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Xmx300m", "-Xms300m", "-jar", "seu-projeto.jar"]