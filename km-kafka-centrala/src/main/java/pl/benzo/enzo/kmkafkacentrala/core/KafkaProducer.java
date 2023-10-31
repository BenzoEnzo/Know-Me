package pl.benzo.enzo.kmkafkacentrala.core;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public final class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String TOPIC = "all-service-logs";

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
