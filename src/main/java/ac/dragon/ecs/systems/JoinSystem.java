package ac.dragon.ecs.systems;

import ac.dragon.DragonAnticheat;
import ac.dragon.ecs.EcsManager;
import ac.dragon.ecs.component.ClickComponent;
import ac.dragon.ecs.component.MovementComponent;
import ac.dragon.ecs.component.TransactionComponent;
import ac.dragon.ecs.component.ViolationComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinSystem implements org.bukkit.event.Listener{
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        DragonAnticheat.getIdMap().put(e.getPlayer().getUniqueId(), e.getPlayer().getEntityId());
        EcsManager.clicks.putIfAbsent(e.getPlayer().getEntityId(), new ClickComponent());
        EcsManager.violations.putIfAbsent(e.getPlayer().getEntityId(), new ViolationComponent());
        EcsManager.movements.putIfAbsent(e.getPlayer().getEntityId(), new MovementComponent());
        EcsManager.transactions.put(e.getPlayer().getEntityId(), new TransactionComponent());
    }
}
