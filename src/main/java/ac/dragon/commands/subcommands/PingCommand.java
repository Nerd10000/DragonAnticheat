package ac.dragon.commands.subcommands;

import ac.dragon.utils.TransactionUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class PingCommand implements SubCommand {


    @Override
    public void onRun(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        if (!sender.hasPermission("dragon.debug.ping")) return;

        if (sender instanceof Player p) {
            p.sendMessage("§aYour ping§2 " + TransactionUtils.getPing(p) + "§ams based on transactions.");
        }
    }
}
