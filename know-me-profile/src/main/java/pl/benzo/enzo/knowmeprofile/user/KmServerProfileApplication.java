package pl.benzo.enzo.knowmeprofile.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KmServerProfileApplication {

    public static void main(String[] args) {
        SpringApplication.run(KmServerProfileApplication.class, args);
    }

}
