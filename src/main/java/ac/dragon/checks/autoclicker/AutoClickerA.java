package ac.dragon.checks.autoclicker;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity.InteractAction;

import ac.dragon.DragonAnticheat;
import ac.dragon.checks.Check;
import ac.dragon.config.ConfigOptions;
import ac.dragon.ecs.EcsManager;
import ac.dragon.ecs.component.ClickComponent;
import ac.dragon.ecs.systems.ClickSystem;
import ac.dragon.utils.TextUtils;
import net.kyori.adventure.audience.Audiences;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class AutoClickerA extends Check {
   
    public AutoClickerA(String name) {
		super(name);
		
	}
    

	@Override
	public void handleAttack(PacketReceiveEvent e, ClickComponent clicks, Player player) {
        if (clicks.cps > 16) {
           
            alert(player);
        }
	}
}
