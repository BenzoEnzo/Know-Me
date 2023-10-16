package pl.benzo.enzo.kmserver.area;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.user.model.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaService {
    private final HttpSession httpSession;
    private final AreaRepossitory areaRepossitory;
    public void deleteArea(String sessionId){
        areaRepossitory.deleteAreaBySessionId(sessionId);
    }
    public void createArea(CreateAreaRequest createAreaRequest){
        final Area area = new Area(createAreaRequest.user(),createAreaRequest.key(),httpSession.getId());
        areaRepossitory.save(area);
    }

    public List<String> getAll(){
        return areaRepossitory.findAll()
                .stream().map(Area::getSessionId).collect(Collectors.toList());
    }
    public Set<Long> getAllUserIdsFromArea(Long keyId){
        return areaRepossitory.findAllByKey_Id(keyId)
                .stream().map(Area::getUser).map(User::getId)
                .collect(Collectors.toSet());
    }

    public int getAllUserIdsFromArenaSize(Long keyId){
       return getAllUserIdsFromArea(keyId).size();
    }
}
