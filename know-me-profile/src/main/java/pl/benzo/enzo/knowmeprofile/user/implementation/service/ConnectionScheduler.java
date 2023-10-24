package pl.benzo.enzo.knowmeprofile.user.implementation.service;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConnectionScheduler {
    private final QueueService queueService;
    @Scheduled(fixedRate = 5000)
    public void matchTalkers(){
        queueService.getRandomPair();
    }
}
