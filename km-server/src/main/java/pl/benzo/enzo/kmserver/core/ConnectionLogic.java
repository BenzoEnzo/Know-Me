package pl.benzo.enzo.kmserver.core;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.resource.ChatRestTemplate;
import pl.benzo.enzo.kmserver.resource.KafkaLogService;
import pl.benzo.enzo.kmserver.resource.ProfileRestTemplate;
import pl.benzo.enzo.kmserver.web.dto.MainSession;
import pl.benzo.enzo.kmservicedto.profile.AreaUserDto;
import pl.benzo.enzo.kmservicedto.socket.ChatSession;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionLogic {
    private final KafkaLogService kafkaLogService;
    private final ProfileRestTemplate profileRestTemplate;
    private final ChatRestTemplate chatRestTemplate;
    private boolean extensionForScheduler(){
        final List<AreaUserDto> areas = profileRestTemplate.queryAreas();
        return areas.size() > 2;
    }


    private void coreFetching() {
        final MainSession response = profileRestTemplate.getPairsFromQueue();
            ChatSession chatSessionSender = ChatSession.builder()
                    .talkerId1(response.talkerId1())
                    .talkerId2(response.talkerId2())
                    .build();
            chatRestTemplate.createSession(chatSessionSender);
            kafkaLogService.sendLog("Queue zakończone sukcesem");
        }


    public void getFromQue(){
        boolean init = extensionForScheduler();
        if(init){
            coreFetching();
        } else kafkaLogService.sendLog("Brak ludzi w kolejce");
    }
}
