package ac.dragon.checks.packets;

import org.bukkit.entity.Player;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;

import ac.dragon.DragonAnticheat;
import ac.dragon.checks.Check;
import ac.dragon.ecs.EcsManager;
import ac.dragon.ecs.component.MovementComponent;
import ac.dragon.ecs.component.TickComponent;
import ac.dragon.ecs.entity.EntityManager;
import ac.dragon.utils.CompensationUtils;

public class PacketA extends Check {

    public PacketA(String name) {
        super(name);
    }

    @Override
    public void handleFlying(PacketReceiveEvent e, MovementComponent movementComponent, Player player) {
        // TODO Auto-generated method stub
        int estimated = CompensationUtils.estimateTicks(player);
        player.sendMessage(String.format("tick=%s ping=%s choosen=%s",EcsManager.ticks.get(player.getEntityId()).count, player.getPing(), CompensationUtils.recall(estimated, player.getEntityId())));
    }

    
    
    


}
