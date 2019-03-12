FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY maven/webapp-@project.version@.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]