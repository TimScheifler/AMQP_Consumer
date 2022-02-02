#AMQP-Consumer using RabbitMQ
RabbitMQ: http://192.168.0.76:15672/

**Filtering command**
```
outbound and ip.DstAddr =192.168.0.76
```

**pom.xml**
```
server.port=0
spring.rabbitmq.host=<ip>           //can be left empty if "localhost"
spring.rabbitmq.port=<port>         //can be left empty if "5672"
spring.rabbitmq.password=<password>
spring.rabbitmq.username=<username>
```