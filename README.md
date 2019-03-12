# Bdr Labdays Testproject

## Start
mvn clean compile spring-boot:run

## Image bauen und pushen 
docker login -u developer -p `oc whoami -t` docker-registry-default.192.168.42.112.nip.io
mvn clean process-resources install -Ddocker -Dcontainer-registry=<<container-registry>>

## Anwendung testen
API-Endpunkt Swagger: swagger-ui.html
Webapp: http://localhost:8081/app