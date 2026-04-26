package ac.dragon.utils;

import ac.dragon.ecs.EcsManager;
import ac.dragon.utils.data.WorldSnapshot;
import org.bukkit.entity.Player;

import java.util.List;

public class CompensationUtils {
    // Compensation logic
    public static  int estimateTicks(Player player) {

        double ping = TransactionUtils.getPing(player);

        double latency = ping * 0.5;

      
        int ticks = (int) Math.ceil(latency / 50.0);

       
        return Math.max(1, Math.min(ticks, 10));
    }

    public static WorldSnapshot recall(int delta, int entityId) {

        var comp = EcsManager.compensations.get(entityId);
        if (comp == null)
            return null;

        int currentTick = EcsManager.ticks.get(entityId).count;
        int targetTick = currentTick - delta;

        WorldSnapshot snapshot = null;

        for (List<WorldSnapshot> snapshots : comp.history.values()){
            for (WorldSnapshot snapshot2 : snapshots){
                if (targetTick == snapshot2.tick) {
                    snapshot = snapshot2;
                }
            }
        }
        if (snapshot != null) {
            return snapshot;
        }

        return comp.current;
    }
}
