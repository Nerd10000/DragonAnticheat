package ac.dragon.ecs.systems;

import org.bukkit.entity.Player;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;

import ac.dragon.ecs.EcsManager;
import ac.dragon.ecs.component.MovementComponent;
import ac.dragon.utils.data.Movement;

public class MovementSystem implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (!WrapperPlayClientPlayerFlying.isFlying(event.getPacketType()))
            return;

        WrapperPlayClientPlayerFlying wrapper = new WrapperPlayClientPlayerFlying(event);

        MovementComponent component = EcsManager.movements.computeIfAbsent(
                event.getUser().getEntityId(),
                id -> new MovementComponent());

        Movement movement = new Movement();


        movement.x = wrapper.getLocation().getX();
        movement.y = wrapper.getLocation().getY();
        movement.z = wrapper.getLocation().getZ();

        movement.onGround = wrapper.isOnGround();

        if (component.current != null) {
            movement.lastX = component.current.x;
            movement.lastY = component.current.y;
            movement.lastZ = component.current.z;

            movement.deltaX = movement.x - movement.lastX;
            movement.deltaY = movement.y - movement.lastY;
            movement.deltaZ = movement.z - movement.lastZ;

            movement.previousDeltaX = component.current.deltaX;
            movement.previousDeltaY = component.current.deltaY;
            movement.previousDeltaZ = component.current.deltaZ;
        }


        component.previous = component.current;
        component.current = movement;

        component.movementHistory.add(movement);

        if (component.movementHistory.size() > 20) {
            component.movementHistory.remove(0);
        }

        
    }

}
