FROM openjdk:latest
COPY ./target/AMQP_Consumer-0.0.1-SNAPSHOT.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "AMQP_Consumer-0.0.1-SNAPSHOT.jar"]