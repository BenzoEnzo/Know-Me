package pl.benzo.enzo.knowmeprofile.user.implementation.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.Area;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.AreaRepossitory;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.AreaUserDto;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.CreateAreaRequest;
import pl.benzo.enzo.knowmeprofile.user.implementation.mapper.AreaMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaService {
    private final HttpSession httpSession;
    private final AreaRepossitory areaRepossitory;
    private final AreaMapper areaMapper;
    public void deleteArea(String sessionId){
        areaRepossitory.deleteAreaBySessionId(sessionId);
    }
    public Set<AreaUserDto> createArea(CreateAreaRequest createAreaRequest){
        final Area area = new Area(createAreaRequest.user(),createAreaRequest.key(),httpSession.getId());
        area.setJoined(true);
        areaRepossitory.save(area);
        return areaRepossitory.findAllByKey_Id(area.getKey().getId())
                .stream().map(areaMapper::mapToAreaUserDto).
                collect(Collectors.toSet());
    }

    public List<String> getAll(){
        return areaRepossitory.findAll()
                .stream().map(Area::getSessionId).collect(Collectors.toList());
    }

    public void update(Area area){
        areaRepossitory.save(area);
    }
    public Set<AreaUserDto> getAllUserAreasUser(Long keyId){
        return areaRepossitory.findAllByKey_Id(keyId)
                .stream().map(areaMapper::mapToAreaUserDto).
                collect(Collectors.toSet());

    }

    public int getAllUserIdsFromArenaSize(Long keyId){
       return getAllUserAreasUser(keyId).size();
    }
}
