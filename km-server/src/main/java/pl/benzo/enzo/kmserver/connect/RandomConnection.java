package pl.benzo.enzo.kmserver.connect;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;


public class RandomConnection {
    private final Random random = new Random();
    private final Set<Long> availableIds;

    public RandomConnection(Set<Long> availableIds) {
        this.availableIds = new HashSet<>(availableIds);
    }


    public Connect pickTwoUniqueIds() {
        if (availableIds.size() < 2) {
            throw new IllegalArgumentException("Need at least two unique IDs to pick from.");
        }

        Long firstId = pickRandomId();
        availableIds.remove(firstId);

        Long secondId = pickRandomId();

        return new Connect(firstId,secondId);
    }

    private Long pickRandomId() {
        List<Long> idList = availableIds.stream().toList();
        return idList.get(random.nextInt(idList.size()));
    }
}
