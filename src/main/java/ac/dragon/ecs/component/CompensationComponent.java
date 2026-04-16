package ac.dragon.ecs.component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ac.dragon.utils.data.WorldSnapshot;

public class CompensationComponent {
    
    public Map<UUID,List<WorldSnapshot>> history = new HashMap<>();

    public WorldSnapshot current;
    public WorldSnapshot previous;

}
