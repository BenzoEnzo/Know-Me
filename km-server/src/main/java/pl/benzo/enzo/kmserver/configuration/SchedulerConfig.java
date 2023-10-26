package pl.benzo.enzo.kmserver.configuration;


import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import pl.benzo.enzo.kmserver.resource.ChatRestTemplate;
import pl.benzo.enzo.kmserver.resource.ProfileRestTemplate;
import pl.benzo.enzo.kmservicedto.socket.ChatSession;

import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class SchedulerConfig {
    private final ProfileRestTemplate profileRestTemplate;
    private final ChatRestTemplate chatRestTemplate;
    @Scheduled(fixedRate = 10000)
    public void fetchPairs(){
        coreFetching();
    }

    private void coreFetching(){
        final ResponseEntity<Pair<Long, Long>> response = profileRestTemplate.getPairsFromQueue();
        final ChatSession chatSession = ChatSession.builder()
                .talkerId1(Objects.requireNonNull(response.getBody()).getLeft())
                .talkerId2(Objects.requireNonNull(response.getBody()).getLeft())
                .build();
                chatRestTemplate.createSession(chatSession);
    }
}
