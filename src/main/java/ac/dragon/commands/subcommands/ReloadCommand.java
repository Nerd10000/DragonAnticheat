package ac.dragon.commands.subcommands;

import ac.dragon.DragonAnticheat;
import ac.dragon.config.ConfigOptions;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

public class ReloadCommand implements SubCommand {
    @Override
    public void onRun(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        DragonAnticheat.getPlugin().reloadConfig();

        DragonAnticheat.adventure().sender(sender).sendMessage(
                LegacyComponentSerializer.legacyAmpersand().deserialize(DragonAnticheat.getConfigManager().get(ConfigOptions.RELOAD_COMMAND, String.class))
        );
    }
}
