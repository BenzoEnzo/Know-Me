package pl.benzo.enzo.kmserver.util;

import org.springframework.context.ApplicationListener;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.events.SessionCreatedEvent;
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
        System.out.println(sessionId);
        areaService.deleteArea(sessionId);
    }
}
