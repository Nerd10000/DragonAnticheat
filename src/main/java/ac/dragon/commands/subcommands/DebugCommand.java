package ac.dragon.commands.subcommands;

import ac.dragon.DragonAnticheat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class DebugCommand implements SubCommand {
    @Override
    public void onRun(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {

        if (sender instanceof Player p) {
            DragonAnticheat.isDebugBuild = !DragonAnticheat.isDebugBuild;
            p.sendMessage("§7You have turned §bdebug mode§7 to §b'" + DragonAnticheat.isDebugBuild + "'§7!");
        }

    }
}
