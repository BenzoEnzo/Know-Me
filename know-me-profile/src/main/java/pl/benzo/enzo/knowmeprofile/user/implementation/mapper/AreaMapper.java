package pl.benzo.enzo.knowmeprofile.user.implementation.mapper;

import org.springframework.stereotype.Component;
import pl.benzo.enzo.kmservicedto.profile.AreaUserDto;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.Area;



@Component
public class AreaMapper {

    public AreaUserDto mapToAreaUserDto(Area area){
        return new AreaUserDto(area.getId(),area.getUser().getId(),area.getUser().getName(),area.getKey().getId(),area.isJoined(),area.isDuringConversation(),false);
    }

    public Area mapToArea(AreaUserDto areaUserDto){
        return new Area();
    }
}
