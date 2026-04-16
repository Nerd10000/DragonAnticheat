package ac.dragon.checks.autoclicker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;

import com.github.retrooper.packetevents.event.PacketReceiveEvent;

import ac.dragon.checks.Check;
import ac.dragon.ecs.component.ClickComponent;

public class AutoClickerC extends Check {

    public AutoClickerC(String name) {
        super(name);

    }
    @Override
    public void handleAttack(PacketReceiveEvent e, ClickComponent clicks, Player player) {
        // TODO Auto-generated method stub
        Set<Long> seen = new HashSet<>();
        List<Long> duplicates = new ArrayList<>();

        clicks.deltaList.stream()
                .filter(n -> !seen.add(n))
                .forEach(duplicates::add);
        
        if (duplicates.size() > (clicks.deltaList.size() * 0.5)) {
            increaseBuffer();
            if (buffer > 10) {
                alert(player);
                buffer = 0;
            }
        } else {
            buffer -= 0.2;
        }
    }

}
