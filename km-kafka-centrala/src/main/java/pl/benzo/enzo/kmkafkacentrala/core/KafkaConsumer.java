package pl.benzo.enzo.kmkafkacentrala.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public final class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = "know-me-profile", groupId = "group_id")
    public void consumeFromProfile(String message) {
        logger.info(String.format("KNOW_ME_PROFILE service msg => %s", message));
    }
}
