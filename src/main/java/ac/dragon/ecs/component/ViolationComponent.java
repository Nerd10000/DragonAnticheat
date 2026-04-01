package ac.dragon.ecs.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ac.dragon.checks.Check;
import ac.dragon.checks.autoclicker.AutoClickerA;
import ac.dragon.checks.autoclicker.AutoClickerB;
import ac.dragon.checks.autoclicker.AutoClickerC;

public class ViolationComponent {
    public List<Check> checkClasses = List.of(new AutoClickerA("AutoClicker(A)", 10),
            new AutoClickerB("AutoClicker(B)", 10),
        new AutoClickerC("Autoclicker(C)", 10));
}
