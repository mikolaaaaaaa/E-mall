#!/bin/bash

# Start Minikube
#minikube start && \
#minikube addons enable registry && \
#minikube addons enable ingress
#kubectl delete -A ValidatingWebhookConfiguration ingress-nginx-admission

docker build -t client-service-image client/
docker build -t order-service-image order/
docker build -t eureka-server-image eureka-server/


#upload all images
minikube image load client-service-image
minikube image load order-service-image
minikube image load eureka-server-image

# Apply all configurations
kubectl apply -f kube

# Optional
#minikube dashboard
#minikube service client-service -n=emall
#minikube service order-service -n=emall
