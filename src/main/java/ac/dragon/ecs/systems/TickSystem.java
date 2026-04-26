package ac.dragon.ecs.systems;

import ac.dragon.utils.TransactionUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ac.dragon.DragonAnticheat;
import ac.dragon.ecs.EcsManager;
import ac.dragon.ecs.component.TickComponent;

public class TickSystem {
    
    public static void timer(){
        Bukkit.getScheduler().runTaskTimer(DragonAnticheat.getPlugin(), ()-> {
            for (Player p : Bukkit.getOnlinePlayers()){
                TransactionUtils.sendPing(p);

                TickComponent component = EcsManager.ticks.computeIfAbsent(p.getEntityId(), id -> new TickComponent());
                component.count++;

                if (component.history.size() > 50) {
                    component.history.removeFirst();
                }
                
                component.history.add(System.currentTimeMillis());



            }
        }, 0, 1L);
    }

}
