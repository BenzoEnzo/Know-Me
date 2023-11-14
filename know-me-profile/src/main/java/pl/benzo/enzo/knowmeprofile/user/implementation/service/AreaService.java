package pl.benzo.enzo.knowmeprofile.user.implementation.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmservicedto.profile.AreaSizeDto;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.Area;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.AreaRepossitory;
import pl.benzo.enzo.kmservicedto.profile.AreaUserDto;
import pl.benzo.enzo.kmservicedto.profile.CreateAreaRequest;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.Key;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;
import pl.benzo.enzo.knowmeprofile.user.implementation.mapper.AreaMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaService {
    private final AreaRepossitory areaRepossitory;
    private final AreaMapper areaMapper;
    public void deleteArea(Long id){
        areaRepossitory.deleteAreaByUser_Id(id);
    }
    public Set<AreaUserDto> createArea(CreateAreaRequest createAreaRequest){
        final Area area = areaMapper.createAreaRequestMapper(createAreaRequest);
        area.setJoined(true);
        areaRepossitory.save(area);
        return areaRepossitory.findAllByKey_Id(area.getKey().getId())
                .stream().map(areaMapper::mapToAreaUserDto).
                collect(Collectors.toSet());
    }

    public List<AreaUserDto> getAllAreas(){
        return areaRepossitory.findAll()
                .stream()
                .map(areaMapper::mapToAreaUserDto)
                .collect(Collectors.toList());
    }

    public void update(Area area){
        areaRepossitory.save(area);
    }
    public Set<AreaUserDto> getAllUserAreasUser(Long keyId){
        return areaRepossitory.findAllByKey_Id(keyId)
                .stream().map(areaMapper::mapToAreaUserDto).
                collect(Collectors.toSet());

    }


    public void refreshAreaState(AreaUserDto areaUserDto){
        final Area area = areaRepossitory.findAreaByUser_Id(areaUserDto.userId());
        area.setDuringConversation(true);
        area.setInQueue(false);
        area.setJoined(true);
        areaRepossitory.save(area);
    }

    public AreaSizeDto getAllUserIdsFromArenaSize(Long keyId){
      return new AreaSizeDto((long) getAllUserAreasUser(keyId).size(), null);
    }
}
