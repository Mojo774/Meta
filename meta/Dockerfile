FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine as builder

EXPOSE 8080
VOLUME /tmp

COPY target/meta-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]