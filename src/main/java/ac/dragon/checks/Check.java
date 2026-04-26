package ac.dragon.checks;

import ac.dragon.DragonAnticheat;
import ac.dragon.config.ConfigOptions;
import ac.dragon.ecs.component.ClickComponent;
import ac.dragon.ecs.component.MovementComponent;
import ac.dragon.utils.TextUtils;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import org.bukkit.entity.Player;

public abstract class Check {
    private String name;
    private int max;
    private int vl = 0;
    public double buffer = 0;
    private String description = "";

    public Check(String name) {
        this.name = name;
        this.max = DragonAnticheat.getPlugin().getConfig().getInt(String.format("checks.%s.max_vl", name));
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
                .replace("%max%", String.valueOf(max))
                .replace("%description%", description)));

        if (vl > max) {
            vl = 0;

        }
        System.out.println();
    }


    public void setDescription(String x) {
        this.description = x;
    }

    public void handleFlying(PacketReceiveEvent e, MovementComponent movementComponent, Player player) {

    }

    public void handleAttack(PacketReceiveEvent e, ClickComponent click, Player player) {

    }

    public void increaseBuffer() {
        buffer++;
    }

    public void decreaseBuffer(double x) {
        buffer -= x;
    }

    public void decreaseBuffer(){
        buffer = Math.max(buffer - DragonAnticheat.getPlugin().getConfig().getDouble(String.format("checks.%s.decay", name)), 0);
    }

    public boolean shouldBeExempt(Player p) {
        return false;
    }

    public boolean shouldBeExempt(MovementComponent component, Player p) {
        return false;
    }

    public boolean shouldBeExempt(ClickComponent component, Player p) {
        return false;
    }

    public Object getSetting(String path){
        return DragonAnticheat.getPlugin().getConfig().get(String.format("checks.%s.%s", name,path));
    }

    public String getName() {
        return name;
    }

    public int getMax() {
        return max;
    }

    public int getVl() {
        return vl;
    }

    public double getBuffer() {
        return buffer;
    }

    public String getDescription() {
        return description;
    }
}
