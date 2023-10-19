package pl.benzo.enzo.kmserver.area;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.area.dto.AreaUserDto;
import pl.benzo.enzo.kmserver.area.dto.QueueJoinDto;
import pl.benzo.enzo.kmserver.area.mapper.AreaMapper;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class QueueService {
    private final AreaRepossitory areaRepository;
    public QueueJoinDto addUserToQueue(AreaUserDto areaUserDto){
        areaRepository.findById(areaUserDto.id()).ifPresent(a -> {
            a.setInQueue(true);
            areaRepository.save(a);
        });
        return new QueueJoinDto(true, areaUserDto.keyId());
    }
    public List<Area> getRandomPair(QueueJoinDto queueJoinDto){
        List<Area> usersInQueue = areaRepository.findAllByIsInQueueAndKey_Id(queueJoinDto.isInQueue(), queueJoinDto.keyId());
        if(usersInQueue.size() < 2) return Collections.emptyList();
        Collections.shuffle(usersInQueue);
        return usersInQueue.subList(0,2);
    }
}
