package ac.dragon.ecs.component;

public class RotationComponent {
    
    public float yaw, pitch;
    public float deltaYaw, deltaPitch, previousDeltaYaw, previousDeltaPitch;
    public float sensitivityX, sensitivityY;

    public boolean flag;
}
