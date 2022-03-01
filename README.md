# lab03-kafka-spring-cloud-stream

This lab is based on tutorials from [here](https://tanzu.vmware.com/developer/guides/spring-cloud-stream-kafka-p1/) and [here](https://github.com/amrutprabhu/kafka-workouts/tree/master/spring-cloud-stream-kafka-communication/src/main/java/com/amrut/prabhu).

The example project in this lab shows how to use Kafka together with the [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream) Framework. We focus on using these technologies
in combination to prepare for the upcoming lecture contents, especially for the [flowing retail](https://github.com/berndruecker/flowing-retail) project.

If you are interested in the plain combination of Kafka and Spring you find example tutorials [here](https://medium.com/@aliarslan10/apache-kafka-configuration-in-spring-boot-with-producer-and-consumer-example-621adf2fd78b) and [here](https://github.com/aliarslan10/spring-for-kafka).

The project has a [Producer](/src/main/java/com/example/KafkaProducer.java) that shows how to produce messages at a regular pace in a scheduled manner (as specified in the CRON expression) and additionally via a [REST Controller](/src/main/java/com/example/KafkaRestController.java) using a [StreamBridge](https://www.tutorialspoint.com/spring_cloud/spring_cloud_streams_with_apache_kafka.htm).

Moreover, there are two consumers that listen to the different topics and a producer/supplier (sending a message once per second) implemented via Java functional programming in the main [Application](/src/main/java/com/example/SpringCloudStreamKafkaApplication.java) class. 
You can find additional material on using Spring Cloud Stream via the functional programming model with Suppliers and Consumers [here](https://piotrminkowski.com/2021/11/11/kafka-streams-with-spring-cloud-stream/) and [here](https://refactorfirst.com/spring-cloud-stream-with-kafka-communication.html).

The messages follow the [Cloud Events](https://cloudevents.io/) specification.

The example uses custom [Serializer](/src/main/java/com/example/dto/converters/MessageSerializer.java) and [Deserializer](/src/main/java/com/example/dto/converters/MessageDeSerializer.java) classes.

### Running the Project

We have provided a [Docker file](/docker-compose.yml) again that allows you to start the Kafka infrastructure by running:
```bash
$ docker-compose up
```

You can then start the Spring Boot application by running the [Application](/src/main/java/com/example/SpringCloudStreamKafkaApplication.java) via your IDE. 
This starts the scheduled message producers and a REST endpoint. By sending *POST* requests (e.g., via Postman) to the ''/produce'' resource
on your machine (by default: 'http://localhost:8081/produce') you can trigger the sending of additional messages via the [REST Controller](/src/main/java/com/example/KafkaRestController.java) to Kafka.
The number of messages is thereby specified as raw payload (i.e., an Integer) to be sent via the POST request.

Please also have a look at the [application.yml](/src/main/resources/application.yml) file to see the binding configurations of
producers and consumers in Spring Cloud Stream. The [pomx.xml](pom.xml) file shows the necessary dependencies for the project.
