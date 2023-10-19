package pl.benzo.enzo.kmserver.area.mapper;

import org.springframework.stereotype.Component;
import pl.benzo.enzo.kmserver.area.Area;
import pl.benzo.enzo.kmserver.area.dto.AreaUserDto;

@Component
public class AreaMapper {

    public AreaUserDto mapToAreaUserDto(Area area){
        return new AreaUserDto(area.getId(),area.getUser().getName(),area.getKey().getId(),area.isJoined(),area.isDuringConversation(),false);
    }

    public Area mapToArea(AreaUserDto areaUserDto){
        return new Area();
    }
}
