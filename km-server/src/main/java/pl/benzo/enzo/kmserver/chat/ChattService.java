package pl.benzo.enzo.kmserver.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.area.dto.StartChattingDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattService {
    private final ChattRepository chattRepository;
    public void createChatt(Long talkerId1,Long talkerId2, String sessionId){
        final Chatt chatt = Chatt.builder()
                .talkerId1(talkerId1)
                .talkerId2(talkerId2)
                .talker1Accepted(true)
                .talker2Accepted(true)
                .sessionId(sessionId)
                .build();
        chattRepository.save(chatt);
    }

    public StartChattingDto getSession(Long id){
        final Chatt chatt = chattRepository.findByTalkerId1OrTalkerId2(id,id).orElse(null);
        if(chatt != null){
            return new StartChattingDto(chatt.getSessionId());
        } else return new StartChattingDto("0");
    }

}
