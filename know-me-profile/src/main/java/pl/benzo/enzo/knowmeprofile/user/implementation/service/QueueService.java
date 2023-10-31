package pl.benzo.enzo.knowmeprofile.user.implementation.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmservicedto.socket.ChatSession;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.Area;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.AreaRepossitory;
import pl.benzo.enzo.kmservicedto.profile.AreaUserDto;
import pl.benzo.enzo.kmservicedto.profile.QueueJoinDto;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;
import pl.benzo.enzo.knowmeprofile.user.implementation.util.GenerateID;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueService {
    private static final Logger loggerQueueService = LoggerFactory.getLogger(QueueService.class);
    private final AreaRepossitory areaRepository;
    private final AreaService areaService;

    public QueueJoinDto addUserToQueue(AreaUserDto areaUserDto) {
        areaRepository.findByUser_Id(areaUserDto.userId()).ifPresent(a -> {
            a.setInQueue(true);
            areaRepository.save(a);
        });
        return new QueueJoinDto(true, areaUserDto.keyId(),areaUserDto.userId(),false);
    }

    public List<ChatSession> getRandomPairs() {
        List<ChatSession> gotUsers = new ArrayList<>();

        List<Area> usersInQueue = areaRepository.findAllByIsInQueueAndDuringConversation(true,false);

        for(Area a: usersInQueue){
            loggerQueueService.info("Aktywni Uczestnicy:" + a.getId());
        }

        Map<Long, List<Area>> groupedByRoom = usersInQueue.stream()
                .collect(Collectors.groupingBy(area -> area.getKey().getId()));

        for(Long l: groupedByRoom.keySet()){
            loggerQueueService.info("Aktywne pokoje:" + l);
        }


        for (Map.Entry<Long, List<Area>> entry : groupedByRoom.entrySet()) {
            List<Long> usersInRoom = entry.getValue()
                    .stream()
                    .map(Area::getUser)
                    .map(User::getId)
                    .toList();

            for (int i = 0; i < usersInRoom.size() - 1; i += 2) {
                Long talkerIdd1 = usersInRoom.get(i);
                Long talkerIdd2 = usersInRoom.get(i + 1);

                ChatSession chatSession = ChatSession.builder()
                        .talkerId1(talkerIdd1)
                        .talkerId2(talkerIdd2).build();

                gotUsers.add(chatSession);

                loggerQueueService.info("Połączono:" + talkerIdd1 + " " + talkerIdd2);
            }
        }

        return gotUsers;
    }
}
