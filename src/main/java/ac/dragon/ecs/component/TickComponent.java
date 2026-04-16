package ac.dragon.ecs.component;

import java.util.ArrayList;
import java.util.List;

public class TickComponent {
    public List<Long> history = new ArrayList<>();
    public int count = 0;
    public double delta;

}
