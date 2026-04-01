package ac.dragon.ecs.systems;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity.InteractAction;

import ac.dragon.DragonAnticheat;
import ac.dragon.ecs.EcsManager;
import ac.dragon.ecs.component.ClickComponent;

public class ClickSystem implements PacketListener {
    @Override
    public void onPacketReceive(PacketReceiveEvent e) {
        if (e.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(e);
            if (wrapper.getAction() == InteractAction.ATTACK) {
                ClickComponent clickComponent = EcsManager.clicks.computeIfAbsent(
                        e.getUser().getEntityId(),
                        id -> new ClickComponent());

                long now = System.currentTimeMillis();
                clickComponent.historyList.add(now);

                clickComponent.deltaList.clear();
                for (int i = 1; i < clickComponent.historyList.size(); i++) {
                    clickComponent.deltaList
                            .add(clickComponent.historyList.get(i) - clickComponent.historyList.get(i - 1));
                }

                updateCPS(clickComponent, now);
            }
        }
    }

    private void updateCPS(ClickComponent component, long now) {
        // Remove clicks older than 1 second
        component.historyList.removeIf(time -> now - time > 1000);

        // Now size = real CPS
        component.cps = component.historyList.size();
    }

    private void cleanup(ClickComponent component, long now) {
        component.historyList.removeIf(time -> now - time > 1000);
    }

}