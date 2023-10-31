package pl.benzo.enzo.kmserver.core;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.resource.ChatRestTemplate;
import pl.benzo.enzo.kmserver.resource.ProfileRestTemplate;
import pl.benzo.enzo.kmservicedto.profile.AreaUserDto;
import pl.benzo.enzo.kmservicedto.socket.ChatSession;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionLogic {
    private static final Logger loggerConnectionLogic = LoggerFactory.getLogger(ConnectionLogic.class);
    private final ProfileRestTemplate profileRestTemplate;
    private final ChatRestTemplate chatRestTemplate;
    private boolean extensionForScheduler(){
        final List<AreaUserDto> areas = profileRestTemplate.queryAreas();
        return areas.size() > 2;
    }

    public void coreFetching(){
        final boolean init = extensionForScheduler();
        if(init) {
            final List<Pair<Long, Long>> response = profileRestTemplate.getPairsFromQueue();
            for (Pair<Long, Long> e : response) {
                loggerConnectionLogic.info("Para" + e.getLeft() + e.getRight() + "rozpoczyna sesje");
                    final ChatSession chatSession = ChatSession.builder()
                            .talkerId1(e.getLeft())
                            .talkerId2(e.getRight())
                            .build();
                    chatRestTemplate.createSession(chatSession);
            }
        } else throw new IllegalArgumentException("Error during fetching");
    }
}
