package pl.benzo.enzo.kmserver.configuration;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import pl.benzo.enzo.kmserver.core.ConnectionLogic;
import pl.benzo.enzo.kmserver.resource.ChatRestTemplate;
import pl.benzo.enzo.kmserver.resource.ProfileRestTemplate;
import pl.benzo.enzo.kmservicedto.socket.ChatSession;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class SchedulerConfig {
private final ConnectionLogic connectionLogic;

    @Scheduled(fixedRate = 10000)
    public void fetchPairs(){
        connectionLogic.coreFetching();
    }


}
