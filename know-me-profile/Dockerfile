FROM openjdk:17

WORKDIR /app


COPY ./target/know-me-profile-*.jar /app/know-me-profile.jar


ENV SPRING_DATASOURCE_URL=jdbc:postgresql://0.0.0.0:5432/profile
ENV SPRING_DATASOURCE_USERNAME=profile1
ENV SPRING_DATASOURCE_PASSWORD=profile2
ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
ENV SPRING_JPA_SHOW_SQL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update


EXPOSE 8082


CMD ["java", "-jar", "/app/know-me-profile.jar"]