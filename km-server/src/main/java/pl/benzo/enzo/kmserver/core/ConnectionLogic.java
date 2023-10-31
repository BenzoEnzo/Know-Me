package pl.benzo.enzo.kmserver.core;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.resource.ChatRestTemplate;
import pl.benzo.enzo.kmserver.resource.ProfileRestTemplate;
import pl.benzo.enzo.kmserver.web.dto.MainSession;
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


    private void coreFetching() {
        final List<MainSession> response = profileRestTemplate.getPairsFromQueue();
        for (MainSession e : response) {
            loggerConnectionLogic.info("Para" + e.talkerId1() + e.talkerId2() + "rozpoczyna sesje");
            ChatSession chatSessionSender = ChatSession.builder()
                    .talkerId1(e.talkerId1())
                    .talkerId2(e.talkerId2())
                    .build();
            chatRestTemplate.createSession(chatSessionSender);
        }
    }

    public void getFromQue(){
        boolean init = extensionForScheduler();
        if(init){
            coreFetching();
        } else throw new IllegalArgumentException("Scheduler couldnt start");
    }
}
