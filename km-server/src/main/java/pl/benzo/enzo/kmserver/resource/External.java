package pl.benzo.enzo.kmserver.resource;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource(value = "classpath:integration.properties")
@ConfigurationProperties(prefix = "service")
public class External {
    private String restProfile;
    private String restChat;
}
