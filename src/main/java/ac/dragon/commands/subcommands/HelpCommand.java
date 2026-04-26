package ac.dragon.commands.subcommands;

import ac.dragon.DragonAnticheat;
import ac.dragon.config.ConfigOptions;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class HelpCommand implements SubCommand {
    @Override
    public void onRun(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {

        //TODO implement permission logic
        List<String> helpMsg = DragonAnticheat.getConfigManager().get(ConfigOptions.HELP_COMMAND, List.class);


        for (String s : helpMsg) {
            String built = s.replace("%version%", DragonAnticheat.getPlugin().getDescription().getVersion());

            DragonAnticheat.adventure().sender(sender).sendMessage(
                    LegacyComponentSerializer.legacyAmpersand().deserialize(built));
        }

    }
}
