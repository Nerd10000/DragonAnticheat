package ac.dragon.checks.packets;

import ac.dragon.checks.Check;
import ac.dragon.ecs.component.MovementComponent;
import ac.dragon.utils.CompensationUtils;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.entity.Player;

public class PacketA extends Check {

    public PacketA(String name) {
        super(name);
    }

    @Override
    public void handleFlying(PacketReceiveEvent e, MovementComponent movementComponent, Player player) {
        // TODO Auto-generated method stub
        int estimated = CompensationUtils.estimateTicks(player);
        //player.sendMessage(String.format("tick=%s ping=%s choosen=%s",EcsManager.ticks.get(player.getEntityId()).count, player.getPing(), CompensationUtils.recall(estimated, player.getEntityId())));
    }

    
    
    


}
