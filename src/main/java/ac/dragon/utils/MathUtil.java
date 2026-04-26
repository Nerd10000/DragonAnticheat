package ac.dragon.utils;

import ac.dragon.ecs.component.MovementComponent;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;

public class MathUtil {

    public static double mean(long[] array) {

        double sum = 0.0;
        for (double i : array) {
            sum += i;
        }

        int length = array.length;
        double mean = sum / length;

        double standardDeviation = 0.0;
        for (double num : array) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / length);

    }

    public static boolean isCollidingWithBlocks(Player p) {

        int radius = 1;
        for (double x = -radius; x <= radius; x++) {
            for (double y = 0; y <= radius; y++) {
                for (double z = -radius; z <= radius; z++) {
                    Location loc = p.getLocation().clone().add(x, y, z);
                    BoundingBox blockBox = p.getWorld().getBlockAt(loc).getBoundingBox();

                    BoundingBox playerBox = p.getBoundingBox().expand(1e-7, 1e-7, 1e-7);
                    Block block = p.getWorld().getBlockAt(loc);
                    if (playerBox.overlaps(blockBox) && !block.getType().isAir() && block.getType().isSolid()) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static double getPotionEffectMultiplier(Player p) {

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

    public static double getMovementMultiplier(Player p, MovementComponent component) {

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
