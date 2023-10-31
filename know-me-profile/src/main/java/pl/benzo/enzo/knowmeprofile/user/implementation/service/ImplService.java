package pl.benzo.enzo.knowmeprofile.user.implementation.service;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmservicedto.socket.ChatSession;
import pl.benzo.enzo.knowmeprofile.user.implementation.ProfileFacadeApi;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.Key;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;
import pl.benzo.enzo.kmservicedto.profile.*;

import java.util.List;
import java.util.Set;


@Service
public class ImplService implements ProfileFacadeApi {
   private final SignService signService;
   private final UserService userService;
   private final KeyService keyService;

   private final QueueService queueService;
   private final AreaService areaService;

    public ImplService(SignService signService, UserService userService, KeyService keyService, QueueService queueService, AreaService areaService) {
        this.signService = signService;
        this.userService = userService;
        this.keyService = keyService;
        this.queueService = queueService;
        this.areaService = areaService;
    }


    @Override
    public SendCrypto generateCrypto() {
        return signService.generateCrypto();
    }

    @Override
    public ReadUserResponse readUser(ReadUserRequest readUserRequest) {
        return userService.readUser(readUserRequest);
    }
    @Override
    public ValidateCrypto validateAccount(SendCrypto sendCrypto) {
        return signService.validateCrypto(sendCrypto);
    }

    @Override
    public List<User> findAllUsers() {
        return signService.findAllUsers();
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }

    @Override
    public List<Key> findAllKeys() {
        return keyService.findAllKeys();
    }

    @Override
    public boolean saveKey(String name) {
        return keyService.saveKey(name);
    }

    @Override
    public Set<AreaUserDto> createArea(CreateAreaRequest createAreaRequest) {
        return areaService.createArea(createAreaRequest);
    }

    @Override
    public QueueJoinDto addUserToQueue(AreaUserDto areaUserDto) {
        return queueService.addUserToQueue(areaUserDto);
    }

    @Override
    public ChatSession getRandomPairs() {
        return queueService.getRandomPairs();
    }

    @Override
    public void refreshAreaState(AreaUserDto areaUserDto) {
        areaService.refreshAreaState(areaUserDto);
    }

    public List<AreaUserDto> getAllAreas(){
        return areaService.getAllAreas();
    }


}
