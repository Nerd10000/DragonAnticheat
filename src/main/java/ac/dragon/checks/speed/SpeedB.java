package ac.dragon.checks.speed;

import ac.dragon.checks.Check;
import ac.dragon.ecs.component.MovementComponent;
import ac.dragon.utils.MathUtil;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.entity.Player;

public class SpeedB extends Check {
    public SpeedB(String name) {
        super(name);
    }

    @Override
    public void handleFlying(PacketReceiveEvent e, MovementComponent movementComponent, Player player) {
        if (shouldBeExempt(movementComponent, player)) return;

        double previousDeltaX = movementComponent.current.previousDeltaX;
        double previousDeltaZ = movementComponent.current.previousDeltaZ;

        double previousSliperiness = movementComponent.previous.block.getType().getSlipperiness();
        double movementMul = MathUtil.getMovementMultiplier(player, movementComponent);
        double potionEffect = MathUtil.getPotionEffectMultiplier(player);

        double sliperiness = movementComponent.current.block.getType().getSlipperiness();

        double dirX = -Math.sin(Math.toRadians(movementComponent.current.lastYaw % 360));
        double dirZ = Math.cos(Math.toRadians(movementComponent.current.lastYaw % 360));

        double predictionX = previousDeltaX *
                previousSliperiness * 0.91 + 0.1 * movementMul * potionEffect *
                Math.pow(0.6 / sliperiness, 3) * dirX;

        double predictionZ = previousDeltaZ *
                previousSliperiness * 0.91 + 0.1 * movementMul * potionEffect *
                Math.pow(0.6 / sliperiness, 3) * dirZ;


        double deltaXZ = Math.hypot(movementComponent.current.deltaX, movementComponent.current.deltaZ);
        double predictedXZ = Math.hypot(predictionX, predictionZ);

        double error = Math.hypot(
                movementComponent.current.deltaX - predictionX,
                movementComponent.current.deltaZ - predictionZ
        );
        double rounded = Math.round(error * 10000.0) / 10000.0;
        
        if (movementComponent.current.deltaYaw == 0) {

            if (rounded > (double) getSetting("limit1")) {

                increaseBuffer();

                if (buffer > (int) getSetting("max_buffer")) {
                    setDescription(String.format("p=%.4f a=%.4f e=%.3f y=%.1f", predictedXZ, deltaXZ, error, movementComponent.current.yaw));
                    alert(player);

                    buffer = 0;
                }
            } else {
                decreaseBuffer();
            }

        } else {
            //Rotated yaw

            if (rounded > (double) getSetting("limit2")) {
                increaseBuffer();

                if (buffer > (int) getSetting("max_buffer")) {
                    alert(player);

                    buffer = 0;
                }
            } else {
                decreaseBuffer();
            }
        }

        // print("§bP: " + predictedXZ + " §3A: " + deltaXZ + " §cE: " + rounded + " §aYaw" + (movementComponent.current.yaw % 360));


    }

    @Override
    public boolean shouldBeExempt(MovementComponent component, Player p) {
        if (component.current.deltaY != 0) {
            decreaseBuffer();
            return true;
        }
        if (p.isSneaking()) {
            decreaseBuffer();
            return true;
        }
        if (MathUtil.isCollidingWithBlocks(p)) {
            decreaseBuffer();
            return true;
        }

        return false;
    }
}
