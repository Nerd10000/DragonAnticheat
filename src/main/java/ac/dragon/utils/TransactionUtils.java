package ac.dragon.utils;

import ac.dragon.ecs.EcsManager;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerPing;
import org.bukkit.entity.Player;

import java.security.SecureRandom;
import java.util.HashMap;

public class TransactionUtils {
    private static final SecureRandom secureRandom = new SecureRandom();
    public static HashMap<Integer, Long> sendTimes = new HashMap<>();

    public static int generateNonce() {
        return secureRandom.nextInt();
    }

    public static int getPing(Player p) {
        long delta = EcsManager.transactions.get(p.getEntityId()).delta;

        return Math.toIntExact(delta);
    }

    public static void sendPing(Player p) {

        WrapperPlayServerPing serverPing = new WrapperPlayServerPing(TransactionUtils.generateNonce());
        sendTimes.put(p.getEntityId(), System.currentTimeMillis());
        PacketEvents.getAPI().getPlayerManager().sendPacket(p, serverPing);

    }
}
