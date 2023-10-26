package pl.benzo.enzo.kmserver.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import pl.benzo.enzo.kmserver.resource.ProfileRestTemplate;

@Configuration
@RequiredArgsConstructor
public class SchedulerConfig {
    private final ProfileRestTemplate profileRestTemplate;
    @Scheduled(fixedRate = 10000)
    public void fetchPairs(){
        profileRestTemplate.getPairsFromQueue();
    }
}
