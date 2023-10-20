package pl.benzo.enzo.kmserver.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattService {
    private final ChattRepository chattRepository;
    public void createChatt(Long talkerId1,Long talkerId2){
        final Chatt chatt = Chatt.builder()
                .talkerId1(talkerId1)
                .talkerId2(talkerId2)
                .talker1Accepted(true)
                .talker2Accepted(true)
                .build();
        chattRepository.save(chatt);
    }

    public List<Chatt> getAll(){
        return chattRepository.findAll();
    }

}
