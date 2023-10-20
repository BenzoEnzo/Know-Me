package pl.benzo.enzo.kmserver.area;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.area.dto.AreaUserDto;
import pl.benzo.enzo.kmserver.area.dto.QueueJoinDto;
import pl.benzo.enzo.kmserver.area.mapper.AreaMapper;
import pl.benzo.enzo.kmserver.chat.ChattService;
import pl.benzo.enzo.kmserver.user.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueueService {
    private final AreaRepossitory areaRepository;
    private final ChattService chattService;
    private final AreaService areaService;

    public QueueJoinDto addUserToQueue(AreaUserDto areaUserDto) {
        areaRepository.findByUser_Id(areaUserDto.userId()).ifPresent(a -> {
            a.setInQueue(true);
            areaRepository.save(a);
        });
        return new QueueJoinDto(true, areaUserDto.keyId(),areaUserDto.userId(),false);
    }

    public void getRandomPair() {
        List<Area> usersInQueue = areaRepository.findAllByIsInQueueAndDuringConversation(true,false);

        Map<Long, List<Area>> groupedByRoom = usersInQueue.stream()
                .collect(Collectors.groupingBy(area -> area.getKey().getId()));

        if(groupedByRoom.size() < 2){
            System.out.println("Za malo ludzi!");
        }

        for (Map.Entry<Long, List<Area>> entry : groupedByRoom.entrySet()) {
            List<Area> usersInRoom = entry.getValue()
                    .stream()
                    .filter(area -> !area.isDuringConversation())
                    .toList();

            for (int i = 0; i < usersInRoom.size() - 1; i += 2) {
                Area talkerId1 = usersInRoom.get(i);
                Area talkerId2 = usersInRoom.get(i + 1);
                String sessionId = String.valueOf(talkerId1.getId()*23 + talkerId2.getId()*54);
                    chattService.createChatt(talkerId1.getId(), talkerId2.getId(), sessionId);
                    talkerId1.setDuringConversation(true);
                    talkerId2.setDuringConversation(true);
                    areaService.update(talkerId1);
                    areaService.update(talkerId2);
                    System.out.println("Dodano chatt jego sessID: " + sessionId);
            }
        }

    }
}
