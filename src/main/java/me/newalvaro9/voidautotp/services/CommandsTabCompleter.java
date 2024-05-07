package me.newalvaro9.voidautotp.services;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CommandsTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            String arg = args[0].toLowerCase();
            if ("help".startsWith(arg)) {
                completions.add("help");
            }
            if ("reload".startsWith(arg)) {
                completions.add("reload");
            }
            if ("setTpLocation".startsWith(arg)) {
                completions.add("setTpLocation");
            }
        }

        return completions;
    }

}
