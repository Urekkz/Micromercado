FROM gradle:7.6.0-jdk17-alpine AS build
COPY . /home/app
WORKDIR /home/app
RUN gradle build

FROM openjdk:17
COPY --from=build /home/app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
