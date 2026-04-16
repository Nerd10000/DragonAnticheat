package ac.dragon.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;

import org.bukkit.entity.Player;

import ac.dragon.ecs.component.MovementComponent;
import ac.dragon.utils.data.Movement;

public class TeleportUtil {

    public static Movement getLastFlagged(Player p, MovementComponent component) {
        for (int i = component.movementHistory.size() - 1; i >= 0; i--) {
            Movement m = component.movementHistory.get(i);

            if (!m.flag) {
                return m;
            }
        }

        return component.previous; // fallback
    }
}
