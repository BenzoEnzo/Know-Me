FROM openjdk:17

WORKDIR /app


COPY ./target/km-kafka-centrala-*.jar /app/km-kafka-centrala.jar

EXPOSE 8065


CMD ["java", "-jar", "/app/km-kafka-centrala.jar"]