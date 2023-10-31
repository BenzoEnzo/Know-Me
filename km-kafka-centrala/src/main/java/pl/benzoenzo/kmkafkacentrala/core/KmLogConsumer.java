package pl.benzoenzo.kmkafkacentrala.core;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class KmLogConsumer {
    private final KmLogRepository kmLogRepository;
    @KafkaListener(topics = "logs-from-km-profile")
    public void consumeLogs(String msg){
        KmLogEntity kmLogEntity = new KmLogEntity();
        kmLogEntity.setMessage(msg);
        kmLogEntity.setTimestamp(LocalDateTime.now());
        kmLogRepository.save(kmLogEntity);
    }

}
