package ac.dragon.ecs.component;

import java.util.ArrayList;
import java.util.List;

import ac.dragon.utils.data.Movement;

public class MovementComponent {
   public List<Movement> movementHistory = new ArrayList<>();
   public Movement current, previous;
   
}

