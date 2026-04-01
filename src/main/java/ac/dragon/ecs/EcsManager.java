package ac.dragon.ecs;

import java.util.HashMap;
import java.util.Map;

import ac.dragon.ecs.component.ClickComponent;
import ac.dragon.ecs.component.MovementComponent;
import ac.dragon.ecs.component.ViolationComponent;

public class EcsManager {
    
    public static Map<Integer, ClickComponent> clicks = new HashMap<>();
    public static Map<Integer, ViolationComponent> violations = new HashMap<>();
    public static Map<Integer, MovementComponent> movements = new HashMap<>();

}
