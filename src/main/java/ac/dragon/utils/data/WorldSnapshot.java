package ac.dragon.utils.data;

import org.bukkit.World;

public class WorldSnapshot {
    
    public int tick;
    public long timestamp;
    public World clonedWorld;
    
    public WorldSnapshot(int tick, long timestamp, World clonedWorld) {
        this.tick = tick;
        this.timestamp = timestamp;
        this.clonedWorld = clonedWorld;
        
    }

    @Override
    public String toString() {
        return "WorldSnapshot [tick=" + tick + ", timestamp=" + timestamp + ", world=" + clonedWorld + "]";
    }

    
    
    
}
