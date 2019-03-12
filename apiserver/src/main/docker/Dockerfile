FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY maven/apiserver-@project.version@.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]