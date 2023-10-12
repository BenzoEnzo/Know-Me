package pl.benzo.enzo.kmserver.area;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.connect.Connect;
import pl.benzo.enzo.kmserver.connect.RandomConnection;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ConnectionAreaService {
    private final AreaService areaService;

    public Connect rouletteArea(Long keyId){
        final Set<Long> usersOnArea = areaService.getAllUserIdsFromArea(keyId);
        final RandomConnection randomConnection = new RandomConnection(usersOnArea);
        return new Connect(randomConnection.pickTwoUniqueIds().getPerson1(), randomConnection.pickTwoUniqueIds().getPerson2());
    }
}
