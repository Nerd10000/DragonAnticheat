package ac.dragon.utils;

import ac.dragon.DragonAnticheat;
import org.bukkit.Bukkit;

public class DebugUtils {

    public static void print(String message) {
        if (DragonAnticheat.isDebugBuild) {
            Bukkit.broadcastMessage(message);
        }
    }
}
