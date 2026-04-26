package ac.dragon.commands.subcommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

public interface SubCommand {

    void onRun(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args);
}
