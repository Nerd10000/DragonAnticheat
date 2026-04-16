package ac.dragon.ecs.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ac.dragon.checks.Check;
import ac.dragon.checks.autoclicker.AutoClickerA;
import ac.dragon.checks.autoclicker.AutoClickerB;
import ac.dragon.checks.autoclicker.AutoClickerC;
import ac.dragon.checks.packets.PacketA;
import ac.dragon.checks.speed.SpeedA;

public class ViolationComponent {
    public List<Check> checkClasses = List.of(new AutoClickerA("AutoClicker(A)"),
            new AutoClickerB("AutoClicker(B)"),
            new AutoClickerC("Autoclicker(C)"),
            new SpeedA("Speed(A)"),
        new PacketA("..."));
}
