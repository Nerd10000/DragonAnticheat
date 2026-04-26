package ac.dragon.commands;

import ac.dragon.DragonAnticheat;
import ac.dragon.commands.subcommands.*;
import ac.dragon.config.ConfigOptions;
import ac.dragon.utils.TextUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jspecify.annotations.NonNull;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        if (args.length == 0) {
            new HelpCommand().onRun(sender, command, label, args);
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "ping":
                new PingCommand().onRun(sender, command, label, args);
                break;
            case "toggle-debug":
                new DebugCommand().onRun(sender, command, label, args);
                break;
            case "help":
                new HelpCommand().onRun(sender, command, label, args);
                break;
            case "config":
                new ConfigCommand().onRun(sender, command, label, args);
                break;
            case "reload":
                new ReloadCommand().onRun(sender, command, label, args);
                break;
            default:
                DragonAnticheat.adventure().sender(sender).sendMessage(TextUtils.color(DragonAnticheat.getConfigManager().get(ConfigOptions.INCORRECT_USAGE, String.class)
                        .replace("%prefix%", DragonAnticheat.getConfigManager().get(ConfigOptions.PREFIX, String.class))));
                break;
        }

        return true;
    }
}
