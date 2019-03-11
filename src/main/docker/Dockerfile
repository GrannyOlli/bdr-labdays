FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY maven/apiserver-0.1.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]