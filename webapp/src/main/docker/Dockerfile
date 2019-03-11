FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY maven/webapp-LocalBuild.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]