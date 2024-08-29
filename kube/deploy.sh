#!/bin/bash

# Start Minikube
minikube start

#upload all images
minikube image load e-mall-db
minikube image load mongo:latest
minikube image load client-service-image
minikube image load order-service-image
minikube image load eureka-server-image
minikube image load confluentinc/cp-zookeeper:latest
minikube image load confluentinc/cp-kafka:latest

# Apply all configurations
kubectl apply -f e-mall-db-service.yaml,mongo-service.yaml,zookeeper-service.yaml,kafka-service.yaml,eureka-server-service.yaml,order-service-service.yaml,client-service-service.yaml,e-mall-db-deployment.yaml,mongo-deployment.yaml,zookeeper-deployment.yaml,kafka-deployment.yaml,order-service-deployment.yaml,client-service-deployment.yaml,eureka-server-deployment.yaml

# Optional: Expose services to Minikube IP (if needed)
#minikube service eureka-server --url
#minikube service client-service --url
#minikube service order-service --url

minikube dashboard
minikube service client-service
minikube service order-service
