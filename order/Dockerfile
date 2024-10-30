FROM openjdk:21

WORKDIR /app

COPY target/order-1.0.0.jar .

CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "order-1.0.0.jar"]