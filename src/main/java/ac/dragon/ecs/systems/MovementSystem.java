package ac.dragon.ecs.systems;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

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
        Player p = event.getPlayer();

        movement.block = p.getLocation().subtract(new Vector(0, 0.5, 0)).getBlock();

        movement.x = wrapper.getLocation().getX();
        movement.y = wrapper.getLocation().getY();
        movement.z = wrapper.getLocation().getZ();
        movement.yaw = wrapper.getLocation().getYaw();
        movement.pitch = wrapper.getLocation().getPitch();

        movement.onGround = wrapper.isOnGround();

        if (component.current != null) {
            movement.lastX = component.current.x;
            movement.lastY = component.current.y;
            movement.lastZ = component.current.z;
            movement.lastYaw = component.current.yaw;
            movement.lastPitch = component.current.pitch;
            
            movement.deltaX = movement.x - movement.lastX;
            movement.deltaY = movement.y - movement.lastY;
            movement.deltaZ = movement.z - movement.lastZ;
            movement.deltaYaw = movement.yaw - movement.lastYaw;
            movement.deltaPitch = movement.pitch - movement.lastPitch;

            movement.previousDeltaX = component.current.deltaX;
            movement.previousDeltaY = component.current.deltaY;
            movement.previousDeltaZ = component.current.deltaZ;
            movement.previousDeltaYaw = component.current.deltaYaw;
            movement.previousDeltaPitch = component.current.deltaPitch;
        }

        component.previous = component.current;
        component.current = movement;

        component.movementHistory.add(movement);

        if (component.movementHistory.size() > 20) {
            component.movementHistory.remove(0);
        }

    }

}
