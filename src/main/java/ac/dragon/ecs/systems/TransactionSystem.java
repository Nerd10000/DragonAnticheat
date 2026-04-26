package ac.dragon.ecs.systems;

import ac.dragon.ecs.EcsManager;
import ac.dragon.utils.data.Transaction;
import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

import static ac.dragon.utils.TransactionUtils.sendTimes;

public class TransactionSystem implements PacketListener {

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