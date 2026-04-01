package ac.dragon.checks.speed;

import org.bukkit.entity.Player;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;

import ac.dragon.checks.Check;
import ac.dragon.ecs.component.MovementComponent;

public class SpeedA  extends Check{

	public SpeedA(String name, int max) {
		super(name, max);
	}

    @Override
    public void handleFlying(PacketReceiveEvent e, MovementComponent movementComponent, Player player) {
        
    }
	
    
}
