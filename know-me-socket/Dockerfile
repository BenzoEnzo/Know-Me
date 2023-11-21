FROM openjdk:17

WORKDIR /app


COPY ./target/know-me-socket-*.jar /app/know-me-socket.jar


EXPOSE 8064


CMD ["java", "-jar", "/app/know-me-socket.jar"]