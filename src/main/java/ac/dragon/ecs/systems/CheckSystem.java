package ac.dragon.ecs.systems;

import java.sql.Wrapper;
import java.util.List;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity.InteractAction;

import ac.dragon.checks.Check;
import ac.dragon.ecs.EcsManager;
import ac.dragon.ecs.component.ViolationComponent;

public class CheckSystem implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent e) {
        if (e.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(e);

            if (wrapper.getAction() == InteractAction.ATTACK) {
                int id = e.getUser().getEntityId();

                ViolationComponent checks = EcsManager.violations.get(id);

                for (Check c : checks.checkClasses) {
                    c.handleAttack(e, EcsManager.clicks.get(id), e.getPlayer());
                }

            }

        }else if (WrapperPlayClientPlayerFlying.isFlying(e.getPacketType())) {
            
            int id = e.getUser().getEntityId();
            ViolationComponent checks = EcsManager.violations.get(id);

            for (Check c : checks.checkClasses){
                c.handleFlying(e, EcsManager.movements.get(id), e.getPlayer());
            }
        }
    }

}
