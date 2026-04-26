package ac.dragon.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class MainCommandAutoCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {
        if (args.length >= 1) {
            return List.of("ping", "toggle-debug", "help", "config", "reload");
        }

        return List.of();
    }
}
