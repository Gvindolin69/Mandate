# Build
FROM maven:3.6-openjdk-8 AS build

COPY . /tmp/app/
WORKDIR /tmp/app/
RUN mvn clean package

# Package
FROM openjdk:8

WORKDIR /opt
COPY --from=build /tmp/app/target/mandate-jar-with-dependencies.jar .

ENTRYPOINT ["java", "-jar", "mandate-jar-with-dependencies.jar"]