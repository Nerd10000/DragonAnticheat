package ac.dragon.ecs.component;

import java.util.ArrayList;
import java.util.List;

public class ClickComponent {
    public List<Long> historyList = new ArrayList<>();
    public List<Long> deltaList = new ArrayList<>();
    public int cps;

}
