package pl.benzo.enzo.kmserver.area;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.area.dto.AreaUserDto;
import pl.benzo.enzo.kmserver.area.dto.QueueJoinDto;
import pl.benzo.enzo.kmserver.area.mapper.AreaMapper;
import pl.benzo.enzo.kmserver.chat.ChattService;

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

    public QueueJoinDto addUserToQueue(AreaUserDto areaUserDto) {
        areaRepository.findByUser_Id(areaUserDto.userId()).ifPresent(a -> {
            a.setInQueue(true);
            areaRepository.save(a);
        });
        return new QueueJoinDto(true, areaUserDto.keyId(),areaUserDto.userId(),false);
    }

    public void getRandomPair() {
        List<Area> usersInQueue = areaRepository.findAllByIsInQueueAndDuringConversation(true,false);
        if(usersInQueue.size() < 2 ){
            System.out.println("Za malo ludzi!");
        }
        Map<Long, List<Area>> groupedByRoom = usersInQueue.stream()
                .collect(Collectors.groupingBy(area -> area.getKey().getId()));

        for (Map.Entry<Long, List<Area>> entry : groupedByRoom.entrySet()) {
            List<Area> usersInRoom = entry.getValue();
            for (int i = 0; i < usersInRoom.size() - 1; i += 2) {
                if(!usersInRoom.get(i).isDuringConversation() && !usersInRoom.get(i+1).isDuringConversation()) {
                    chattService.createChatt(usersInRoom.get(i).getId(), usersInRoom.get(i + 1).getId(), String.valueOf(i + (i + 2) + usersInRoom.get(i).getId()));
                    usersInRoom.get(i).setDuringConversation(true);
                    usersInRoom.get(i + 1).setDuringConversation(true);
                    System.out.println("Dodano chatt");
                } else {
                    System.out.println("Uzytkownik zajety");
                }
            }
        }

    }
}
