package pl.benzo.enzo.knowmeprofile.user.implementation.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.Area;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.AreaRepossitory;
import pl.benzo.enzo.kmservicedto.profile.AreaUserDto;
import pl.benzo.enzo.kmservicedto.profile.QueueJoinDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueueService {
    private final AreaRepossitory areaRepository;
    private final AreaService areaService;

    public QueueJoinDto addUserToQueue(AreaUserDto areaUserDto) {
        areaRepository.findByUser_Id(areaUserDto.userId()).ifPresent(a -> {
            a.setInQueue(true);
            areaRepository.save(a);
        });
        return new QueueJoinDto(true, areaUserDto.keyId(),areaUserDto.userId(),false);
    }

    public List<Pair<Long, Long>> getRandomPair() {
        List<Pair<Long, Long>> gotUsers = new ArrayList<>();
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
                Long talkerIdd1 = talkerId1.getId();
                Long talkerIdd2 = talkerId2.getId();
                gotUsers.add(Pair.of(talkerIdd1,talkerIdd2));
            }
        }
        return gotUsers;
    }
}
