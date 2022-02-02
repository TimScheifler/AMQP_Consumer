#AMQP-Consumer using RabbitMQ
RabbitMQ: http://192.168.0.76:15672/

**Running RabbitMQ on Docker**
```
docker run -it --name myrabbitmq -p 5672:5672 -p 15672:15672 -p 1883:1883 -p 15675:15675 rabbitmq:3
```
We are also opening ports for MQTT opening -p 1883:1883 -p 15675:15675. 

You can skip these commands and also the following one if you are not using MQTT:
```
rabbitmq-plugins enable rabbitmq_management
rabbitmq-plugins enable rabbitmq_mqtt
rabbitmq-plugins enable rabbitmq_web_mqtt
rabbitmq-plugins enable rabbitmq_amqp1_0
```
Enabling MQTT-Plugin for RabbitMQ.

**Filtering command**

We are using "Clumsy" to simulate different kinds of networks with the following command:
```
outbound and ip.DstAddr =<ip>
```
Enough Pre-Configuration! Let's start with the real one...

**application.properties**
```
server.port=0
spring.rabbitmq.host=<ip>           //can be left empty if "localhost"
spring.rabbitmq.port=<port>         //can be left empty if "5672"
spring.rabbitmq.password=<password>
spring.rabbitmq.username=<username>
```