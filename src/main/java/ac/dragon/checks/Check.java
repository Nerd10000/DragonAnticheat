 package ac.dragon.checks;

import org.bukkit.entity.Player;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.player.User;

import ac.dragon.DragonAnticheat;
import ac.dragon.config.ConfigOptions;
import ac.dragon.ecs.component.ClickComponent;
import ac.dragon.ecs.component.MovementComponent;
import ac.dragon.utils.TextUtils;

public abstract class Check {
    private String name;
    private int max;
    private int vl = 0;
    public double buffer = 0;
    public Check(String name, int max) {
        this.name = name;
        this.max = max;
    }

    public void alert(Player player) {
        vl++;
        DragonAnticheat.adventure().all().sendMessage(TextUtils.color(DragonAnticheat.getConfigManager()
                .get(ConfigOptions.FORMAT, String.class)
                .replace("%prefix%",
                        DragonAnticheat.getConfigManager().get(ConfigOptions.PREFIX, String.class))
                .replace("%p%", player.getName())
                .replace("%check%", name)
                .replace("%vl%", String.valueOf(vl))
                .replace("%max%", String.valueOf(max))));

        if (vl > max) {
            vl = 0;
        }
    }
    public void handleFlying(PacketReceiveEvent e,MovementComponent movementComponent, Player player){
        
    }
    public void handleAttack(PacketReceiveEvent e, ClickComponent click, Player player) {

    }
    public void increaseBuffer(){
        buffer++;
    }

    public void decreaseBuffer(double x){
        buffer -= x;
    }



}
