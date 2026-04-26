package ac.dragon.checks.speed;

import ac.dragon.checks.Check;
import ac.dragon.ecs.component.MovementComponent;
import ac.dragon.utils.MathUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.entity.Player;

import static ac.dragon.utils.MathUtil.getMovementMultiplier;
import static ac.dragon.utils.MathUtil.getPotionEffectMultiplier;

public class SpeedA extends Check {

    public SpeedA(String name) {
        super(name);
    }

    @Override
    public void handleFlying(PacketReceiveEvent e, MovementComponent movementComponent, Player player) {

        if (shouldBeExempt(movementComponent, player)) {
            return;
        }

        double actualXZ = Math.hypot(movementComponent.current.deltaX, movementComponent.current.deltaZ);
        double sliperinessLastTick = movementComponent.previous.block.getType().getSlipperiness();
        double sliperiness = movementComponent.current.block.getType().getSlipperiness();
        double effectMultiplier = getPotionEffectMultiplier(player);
        double movementEffectMultiplier = getMovementMultiplier(player, movementComponent);
        double lastDeltaXZ = Math.hypot(movementComponent.current.previousDeltaX,
                movementComponent.current.previousDeltaZ);

        double predicted = lastDeltaXZ * sliperinessLastTick * 0.91
                + 0.1 * movementEffectMultiplier * effectMultiplier * Math.pow((0.6 / sliperiness), 3);

        double delta = Math.abs(predicted - actualXZ);
        // player.sendMessage("§dP: §5" + predicted + " §d A: " + actualXZ + " §6Delta:
        // " + delta + " §eBlock " + movementComponent.current.block.getType() + "§7(" +
        // movementComponent.current.block.getType().getSlipperiness() + "§7)");


        if (delta > ((double) getSetting("limit"))) {
            increaseBuffer();
            movementComponent.movementHistory.getLast().flag = true;

            if (buffer > ((int) getSetting("max_buffer"))) {
                setDescription(String.format("p=%f a=%f d=%f l=%dms t=%d", predicted,actualXZ,delta,player.getPing(),20));
                alert(player);
                buffer = 0;
                e.setCancelled(true);
            }
        }
        decreaseBuffer();
    }

    @Override
    public boolean shouldBeExempt(MovementComponent component, Player p) {

        if (Math.hypot(component.current.deltaX, component.current.deltaZ) <= 0) {
            decreaseBuffer();
            return true;
        }

        if (!component.current.onGround && !component.previous.onGround) {
            decreaseBuffer();
            return true;
        }

        if (Math.abs(component.current.deltaYaw) > 1F) {
            decreaseBuffer();
            return true;
        }
        if (p.isSneaking()) {
            return true;
        }
        if (MathUtil.isCollidingWithBlocks(p)) {
            return true;
        }
        if (p.isGliding()) {
            return true;
        }
        return false;
    }


}
