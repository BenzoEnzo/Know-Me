package pl.benzo.enzo.kmserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class KmServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(KmServerApplication.class, args);
    }

}
