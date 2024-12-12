#!/bin/bash

# Start Minikube
minikube stop && \
minikube delete && \
minikube start && \
minikube addons enable registry && \
minikube addons enable ingress
kubectl delete -A ValidatingWebhookConfiguration ingress-nginx-admission

docker build -t client-service-image client/
docker build -t order-service-image order/

#upload all images
minikube image load client-service-image
minikube image load order-service-image

# Apply all configurations
kubectl apply -f kube/namespace.yaml
kubectl apply -f kube/ingress.yaml
kubectl apply -f kube/config-map.yaml
kubectl apply -f kube

# Optional
#minikube dashboard
#minikube service client-service -n=emall
#minikube service order-service -n=emall
