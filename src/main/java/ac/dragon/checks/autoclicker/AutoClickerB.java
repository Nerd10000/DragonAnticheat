package ac.dragon.checks.autoclicker;

import org.bukkit.entity.Player;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;

import ac.dragon.checks.Check;
import ac.dragon.ecs.component.ClickComponent;
import ac.dragon.utils.MathUtil;

public class AutoClickerB extends Check {

    public AutoClickerB(String name, int max) {
        super(name, max);

    }

    @Override
    public void handleAttack(PacketReceiveEvent e, ClickComponent clicks, Player player) {
        double std = MathUtil.mean(clicks.deltaList.stream()
                .mapToLong(i -> i).toArray());

        if (std < 20) {
            increaseBuffer();

            if (buffer > 15) {
                alert(player);
                buffer = 0;
            }
        }else {
            decreaseBuffer(0.44);
        }
    }

}
