package pl.benzo.enzo.knowmesocket.chat;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaLogService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendLog(String message) {
        kafkaTemplate.send("know-me-socket", message);
    }
}