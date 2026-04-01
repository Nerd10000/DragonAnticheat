package ac.dragon.utils.data;

public class Movement {
    public double x, y, z, lastX, lastY, lastZ;
    public double deltaX, deltaY, deltaZ, previousDeltaX, previousDeltaY, previousDeltaZ;
    
    public boolean onGround, isInAir, isInWater, isInLava;
    public boolean flag;  
}
