package me.newalvaro9.voidautotp;

import me.newalvaro9.voidautotp.listeners.OnPlayerMove;
import me.newalvaro9.voidautotp.services.Commands;
import me.newalvaro9.voidautotp.services.CommandsTabCompleter;
import me.newalvaro9.voidautotp.services.UpdateChecker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class VoidAutoTP extends JavaPlugin {
    private static VoidAutoTP plugin;

    public static VoidAutoTP getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        new UpdateChecker(this, 116211).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("Resource up-to-date.");
            } else {
                getLogger().info("There is a new update available. --> https://www.spigotmc.org/resources/voidautotp.116211/");
            }
        });

        Bukkit.getPluginManager().registerEvents(new OnPlayerMove(), this);
        getCommand("voidautotp").setExecutor(new Commands());
        getCommand("voidautotp").setTabCompleter(new CommandsTabCompleter());

        getLogger().info("\n" +
                " __      __   _     _                 _          _______ _____  \n" +
                " \\ \\    / /  (_)   | |     /\\        | |        |__   __|  __ \\ \n" +
                "  \\ \\  / /__  _  __| |    /  \\  _   _| |_ ___      | |  | |__) |\n" +
                "   \\ \\/ / _ \\| |/ _` |   / /\\ \\| | | | __/ _ \\     | |  |  ___/ \n" +
                "    \\  / (_) | | (_| |  / ____ \\ |_| | || (_) |    | |  | |     \n" +
                "     \\/ \\___/|_|\\__,_| /_/    \\_\\__,_|\\__\\___/     |_|  |_|     \n");

        saveDefaultConfig();

        getLogger().info("VoidAutoTP has been successfully enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("VoidAutoTP is now disabled.");
    }
}