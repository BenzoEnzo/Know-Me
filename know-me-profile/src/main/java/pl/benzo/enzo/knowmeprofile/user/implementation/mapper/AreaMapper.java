package pl.benzo.enzo.knowmeprofile.user.implementation.mapper;

import org.springframework.stereotype.Component;
import pl.benzo.enzo.kmservicedto.profile.AreaUserDto;
import pl.benzo.enzo.kmservicedto.profile.CreateAreaRequest;
import pl.benzo.enzo.kmservicedto.profile.KeyDto;
import pl.benzo.enzo.kmservicedto.profile.UserDto;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.Area;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.Key;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;


@Component
public class AreaMapper {

    public AreaUserDto mapToAreaUserDto(Area area){
        return new AreaUserDto(area.getId(),area.getUser().getId(),area.getUser().getName(),area.getKey().getId(),area.isJoined(),area.isDuringConversation(),false);
    }

    public Area mapToArea(AreaUserDto areaUserDto){
        return new Area();
    }

    public Area createAreaRequestMapper(CreateAreaRequest createAreaRequest){
        final UserDto userDto = createAreaRequest.user();
        final KeyDto keyDto = createAreaRequest.key();
        final User user = new User(createAreaRequest.user().id(), createAreaRequest.user().name(),createAreaRequest.user().gender());
        final Key key = new Key(createAreaRequest.key().id(),createAreaRequest.key().name());

        return new Area(user,key);
    }
}
