package ac.dragon.commands.subcommands;

import ac.dragon.DragonAnticheat;
import ac.dragon.checks.Check;
import ac.dragon.config.ConfigOptions;
import ac.dragon.ecs.EcsManager;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class ConfigCommand implements SubCommand {
    @Override
    public void onRun(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        if (sender instanceof Player p) {


            StringBuilder builder = new StringBuilder(DragonAnticheat.getConfigManager().get(ConfigOptions.CONFIG_COMMAND_HEADER, String.class));

            for (Check check : EcsManager.violations.get(p.getEntityId()).checkClasses) {
                String format = DragonAnticheat.getConfigManager().get(ConfigOptions.CONFIG_CHECK_FORMAT, String.class);
                String name = check.getName();
                String max_vl = String.valueOf(check.getMax());
                String max_buffer = String.valueOf(check.getSetting("max_buffer"));
                String limit = String.valueOf(check.getSetting("limit"));
                String decay = String.valueOf(check.getSetting("decay"));
                String section = format.replace("%checkname%", name)
                        .replace("%max_buffer%", max_buffer)
                        .replace("%max_vl%", max_vl)
                        .replace("%limit%", limit)
                        .replace("%decay%", decay);

                builder.append("\n" + section);
            }

            DragonAnticheat.adventure().sender(sender).sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(builder.toString()));
        }


    }
}
