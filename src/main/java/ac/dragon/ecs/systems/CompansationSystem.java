package ac.dragon.ecs.systems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import ac.dragon.DragonAnticheat;
import ac.dragon.ecs.EcsManager;
import ac.dragon.ecs.component.CompensationComponent;
import ac.dragon.ecs.component.TickComponent;
import ac.dragon.ecs.entity.EntityManager;
import ac.dragon.utils.data.WorldSnapshot;

public class CompansationSystem {

    public static void timer() {
        Bukkit.getScheduler().runTaskTimer(DragonAnticheat.getPlugin(), task -> {

            for (Player p : Bukkit.getOnlinePlayers()) {

                addNewCompensatedWorld(p.getEntityId(),
                        new WorldSnapshot(
                                EcsManager.ticks.computeIfAbsent(p.getEntityId(), id -> new TickComponent()).count,
                                System.currentTimeMillis(), p.getWorld()));


            }

        }, 0L, 20L);
    }

    private static void addNewCompensatedWorld(int eid, WorldSnapshot worldSnapshot) {
        CompensationComponent component = EcsManager.compensations.computeIfAbsent(eid,
                id -> new CompensationComponent());

        component.current = worldSnapshot;

        if (component.current != null) {
            component.previous = component.current;
        }
        List<WorldSnapshot> history = component.history.getOrDefault(worldSnapshot.clonedWorld.getUID(), new ArrayList<>());
        history.add(worldSnapshot);
        component.history.put(worldSnapshot.clonedWorld.getUID(), history);
    
    }

}
