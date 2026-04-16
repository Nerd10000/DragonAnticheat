package ac.dragon.ecs.entity;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ac.dragon.ecs.EcsManager;
import ac.dragon.ecs.component.CompensationComponent;
import ac.dragon.utils.data.WorldSnapshot;

public class EntityManager {
    private int nextId;

    public void setNextId(int id) {
        nextId = id++;
    }

    public void createId() {
        nextId++;

    }

    
}
