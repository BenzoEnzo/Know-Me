package pl.benzo.enzo.kmserver.area;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.user.model.User;

@Service
@RequiredArgsConstructor
public class AreaService {
    private final HttpSession httpSession;
    private final AreaRepossitory areaRepossitory;
    public void deleteArea(Area area){
        areaRepossitory.delete(area);
    }
    public Area findBySessionId(String sessionId){
        return areaRepossitory.findAreaBySessionId(sessionId);
    }
    public String createArea(CreateAreaRequest createAreaRequest){
        Area area = new Area(createAreaRequest.user(),createAreaRequest.key());
        area.setSessionId(httpSession.getId());
        return httpSession.getId();
    }
}
