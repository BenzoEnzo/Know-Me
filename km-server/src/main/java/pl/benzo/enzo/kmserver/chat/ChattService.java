package pl.benzo.enzo.kmserver.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChattService {
    private final ChattRepository chattRepository;
    public void createChatt(Long talkerId1,Long talkerId2){
        final Chatt chatt = Chatt.builder()
                .talkerId1(talkerId1)
                .talkerId2(talkerId2)
                .build();
        chattRepository.save(chatt);
    }

}
