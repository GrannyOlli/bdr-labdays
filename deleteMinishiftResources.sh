# delete resources for apiserver and webapp from minishift

oc delete deployment --selector app=apiserver
oc delete deploymentconfig --selector app=apiserver
oc delete service --selector app=apiserver
oc delete deployment --selector app=webapp
oc delete deploymentconfig --selector app=webapp
oc delete service --selector app=webapp


