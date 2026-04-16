package ac.dragon.ecs.systems;

import ac.dragon.ecs.EcsManager;
import ac.dragon.utils.TransactionUtils;
import ac.dragon.utils.data.Transaction;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerPing;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class TransactionSystem implements PacketListener {
    private  static HashMap<Integer, Long> sendTimes = new HashMap<>();

    public static void sendPing(Player p){

        WrapperPlayServerPing serverPing = new WrapperPlayServerPing((int) TransactionUtils.generateNonce());
        sendTimes.put(p.getEntityId(), System.currentTimeMillis());
        PacketEvents.getAPI().getPlayerManager().sendPacket(p,serverPing);

    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {

        if (event.getPacketType() == PacketType.Play.Client.PONG){
            Transaction transaction = new Transaction();
            transaction.timestamp = System.currentTimeMillis();
            transaction.delta = Math.abs(System.currentTimeMillis() - sendTimes.get(event.getUser().getEntityId()));

            EcsManager.transactions.get(event.getUser().getEntityId()).transactionList.add(transaction);
            EcsManager.transactions.get(event.getUser().getEntityId()).delta = transaction.delta;

        }
    }
}