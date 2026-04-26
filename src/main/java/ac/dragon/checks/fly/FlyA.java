package ac.dragon.checks.fly;

import ac.dragon.checks.Check;
import ac.dragon.ecs.component.MovementComponent;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.entity.Player;

public class FlyA extends Check {
    public FlyA(String name) {
        super(name);
    }


    @Override
    public void handleFlying(PacketReceiveEvent e, MovementComponent movementComponent, Player player) {
        if (shouldBeExempt(movementComponent, player)) {
            return;
        }


        double actual = movementComponent.current.deltaY;
        double prediction = 0.42;

        double delta = Math.abs(prediction - actual);

        //print("§3Fly(A) §f| §aA:" + actual + " §bP: " + prediction + " §cE: " + delta);

        if (delta > 1e-7) {
            increaseBuffer();

            if (buffer > (int) getSetting("max_buffer")) {
                setDescription(String.format("p=%.3f e=%.3f", prediction, delta));
                //alert(player);
                buffer = 0;
            }
        } else {
            decreaseBuffer();
        }
    }

    @Override
    public boolean shouldBeExempt(MovementComponent component, Player p) {
        if (p.getFallDistance() > 0) {
            return true;
        }
        return component.current.deltaY == 0;
    }
}
