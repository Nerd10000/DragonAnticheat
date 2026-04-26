package ac.dragon.utils.data;

import org.bukkit.block.Block;

public class Movement {
    public double x, y, z, lastX, lastY, lastZ;
    public double deltaX, deltaY, deltaZ, previousDeltaX, previousDeltaY, previousDeltaZ;
    public float yaw, lastYaw, pitch, lastPitch;
    public float deltaYaw, deltaPitch, previousDeltaYaw, previousDeltaPitch;
    public boolean onGround, isInAir, isInWater, isInLava;
    public boolean flag;
    public int tickSinceJump;
    public Block block;
}
