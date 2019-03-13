# Bdr Labdays Testproject

## Start
mvn clean compile spring-boot:run

## Image bauen und pushen 

### Variante 1:
docker login -u developer -p `oc whoami -t` docker-registry-default.192.168.42.112.nip.io
mvn clean process-resources install -Ddocker -Dcontainer-registry=<<container-registry>>

### Variante 2:
#### Voraussetzungen
Minishift lokal installiert oder
Zugang zu OpenShift Online und installiertes OpenShift CLI (oc).

mvn clean install -Dopenshift

Der Build erzeugt und pusht die Container-Images in der Minishift-Umgebung und erzeugt ein Helm-Chart.

Installation mit Helm:

helm install apiserver/target/apiserver-LocalBuild-helm.tar.gz

## Anwendung testen
API-Endpunkt Swagger: swagger-ui.html
Webapp: http://localhost:8081/app

Management-Endpoints unter /actuator, z.B. http://localhost:8080/actuator/health

## Web-App Konfiguration
Konfiguration API-Server Endpunkt über Umgebungsvariable APISERVER_URL, default ist http://localhost:8080

