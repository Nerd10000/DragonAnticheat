package ac.dragon.ecs;

import java.util.HashMap;
import java.util.Map;

import ac.dragon.ecs.component.*;

public class EcsManager {
    
    public static Map<Integer, ClickComponent> clicks = new HashMap<>();
    public static Map<Integer, ViolationComponent> violations = new HashMap<>();
    public static Map<Integer, MovementComponent> movements = new HashMap<>();
    public static Map<Integer, CompensationComponent> compensations = new HashMap<>();
    public static Map<Integer, TickComponent> ticks = new HashMap<>();
    public static Map<Integer, TransactionComponent> transactions = new HashMap<>();
}
