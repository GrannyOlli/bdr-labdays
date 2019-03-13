# Bdr Labdays Testproject

## Voraussetzungen
JDK < 10. Mit JDK10 gibt es ein Build-Problem aufgrund einer nicht kompatiblen Dependency zu Lombok.

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


## Installation von Helm

minishift addon helm (https://github.com/minishift/minishift-addons/tree/master/add-ons/helm) did not work for us

successful path:

follow https://helm.sh/docs/using_helm/#installing-helm

run:

helm init

oc create serviceaccount helm -n kube-system

oc patch deployment/tiller-deploy -p '{"spec":{"template":{"spec":{"serviceAccountName":"helm"}}}}' -n kube-system

oc adm policy add-cluster-role-to-user cluster-admin -z helm -n kube-system

oc expose deployment/tiller-deploy --target-port=tiller --type=NodePort --name=tiller -n kube-system

helm init -c

Add into your ~/.bashrc this lines:

eval "$(minishift oc-env)"

export HELM_HOST="$(minishift ip):$(oc get svc/tiller -o jsonpath='{.spec.ports[0].nodePort}' -n kube-system --as=system:admin)"

export MINISHIFT_ADMIN_CONTEXT="default/$(oc config view -o jsonpath='{.contexts[?(@.name=="minishift")].context.cluster}')/system:admin"

run helm init -c

you should see Happy Helming!

## Installation von Jaeger

see https://github.com/jaegertracing/jaeger-operator

Jaeger-resources are located under /resource

oc login -u system:admin

oc create -f jaegertracing_v1_jaeger_crd.yaml

oc create -f service_account.yaml

oc create -f role.yaml

oc create -f role_binding.yaml

oc create -f operator.yaml

oc create clusterrolebinding developer-jaeger-operator --clusterrole=jaeger-operator --user=developer

oc create -f jaeger-instance.yml

For jaeger to work, applications must be created with deployments instead of deploymentconfigs. Otherwise no jaeger sidecar-containers will be created. For this the created helmcharts can be used:

helm install apiserver-LocalBuild-helm.tar.gz

