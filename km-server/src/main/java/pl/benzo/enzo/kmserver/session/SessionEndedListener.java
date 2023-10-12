package pl.benzo.enzo.kmserver.session;

import org.springframework.context.ApplicationListener;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.stereotype.Component;
import pl.benzo.enzo.kmserver.area.Area;
import pl.benzo.enzo.kmserver.area.AreaService;

@Component
public class SessionEndedListener implements ApplicationListener<SessionDestroyedEvent> {

    private final AreaService areaService;

    public SessionEndedListener(AreaService areaService) {
        this.areaService = areaService;
    }

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        String sessionId = event.getSessionId();
        Area area = areaService.findBySessionId(sessionId);
        if (area != null) {
           areaService.deleteArea(area);
        }
    }
}
