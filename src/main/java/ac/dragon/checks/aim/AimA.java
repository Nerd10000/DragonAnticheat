package ac.dragon.checks.aim;

import ac.dragon.checks.Check;
import ac.dragon.ecs.component.MovementComponent;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.entity.Player;


public class AimA extends Check {
    public AimA(String name) {
        super(name);
    }
    /*
    This check is never going to be flagged seriously.
    The goal of the check is to kick the player for repetitive yaw angles.
     */

    @Override
    public void handleFlying(PacketReceiveEvent e, MovementComponent movementComponent, Player player) {

    }


}
