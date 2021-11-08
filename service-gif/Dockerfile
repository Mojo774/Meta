FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine as builder
EXPOSE 8082
VOLUME /tmp

COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]