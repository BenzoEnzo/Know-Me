package pl.benzo.enzo.knowmeprofile.user.implementation.util;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaLogService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendLog(String log) {
        kafkaTemplate.send("logs-from-km-profile", log);
    }
}
