package ac.dragon.checks.speed;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import ac.dragon.checks.Check;
import ac.dragon.ecs.component.MovementComponent;


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
            return true;
        }

        if (!component.current.onGround && !component.previous.onGround) {
            return true;
        }

        if (component.current.deltaYaw < 1F) {
            return true;
        }
        if (p.isGliding()) {
            return true;
        }
        return false;
    }

    private double getPotionEffectMultiplier(Player p) {

        double multiplier = 1.0;

        for (PotionEffect effect : p.getActivePotionEffects()) {

            if (effect.getType() == PotionEffectType.SPEED) {
                multiplier *= 1.0 + 0.2 * (effect.getAmplifier() + 1);
            }

            if (effect.getType() == PotionEffectType.SLOW) {
                multiplier *= 1.0 - 0.15 * (effect.getAmplifier() + 1);
            }
        }

        return multiplier;
    }

    private double getMovementMultiplier(Player p, MovementComponent component) {

        double dx = component.current.deltaX;
        double dz = component.current.deltaZ;

        double horizontal = Math.hypot(dx, dz);

        if (horizontal < 1E-5) {
            return 0.0;
        }

        double base;

        if (p.isSneaking()) {
            base = 0.3;
        } else if (p.isSprinting()) {
            base = 1.3;
        } else {
            base = 1.0;
        }

        double yaw = Math.toRadians(p.getLocation().getYaw());
        double sin = Math.sin(yaw);
        double cos = Math.cos(yaw);

        double forward = -dx * sin + dz * cos;
        double strafe = dx * cos + dz * sin;

        double forwardAbs = Math.abs(forward);
        double strafeAbs = Math.abs(strafe);

        double ratio = strafeAbs / (forwardAbs + 1E-5);

        double directional;

        if (ratio > 0.7 && ratio < 1.3) {

            if (p.isSneaking()) {
                directional = 0.98 * 1.414;
            } else {
                directional = 1.0;
            }

        } else {
            directional = 0.98;
        }

        directional = Math.min(directional, 1.0);

        return base * directional;
    }

}
