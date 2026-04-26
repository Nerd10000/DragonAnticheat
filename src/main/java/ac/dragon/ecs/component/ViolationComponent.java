package ac.dragon.ecs.component;

import ac.dragon.checks.Check;
import ac.dragon.checks.aim.AimA;
import ac.dragon.checks.autoclicker.AutoClickerA;
import ac.dragon.checks.autoclicker.AutoClickerB;
import ac.dragon.checks.autoclicker.AutoClickerC;
import ac.dragon.checks.fly.FlyA;
import ac.dragon.checks.speed.SpeedA;
import ac.dragon.checks.speed.SpeedB;

import java.util.List;

public class ViolationComponent {
    public List<Check> checkClasses = List.of(new AutoClickerA("AutoClicker(A)"),
            new AutoClickerB("AutoClicker(B)"),
            new AutoClickerC("Autoclicker(C)"),
            new SpeedA("Speed(A)"),
            //new PacketA("..."), -- This was a debug check
            new AimA("Aim(A)"),
            new FlyA("Fly(A)"),
            new SpeedB("Speed(B)"));

}
