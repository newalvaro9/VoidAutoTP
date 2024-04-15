package me.newalvaro9.voidautotp;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

import java.util.Objects;

public class VoidAutoTP extends JavaPlugin implements Listener {

    private Location teleportLocation;
    private String message;
    private Boolean isEnabled;
    private Integer voidLayer;

    @Override
    public void onEnable() {
        // Register the event listener
        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("\n" +
                " __      __   _     _                 _          _______ _____  \n" +
                " \\ \\    / /  (_)   | |     /\\        | |        |__   __|  __ \\ \n" +
                "  \\ \\  / /__  _  __| |    /  \\  _   _| |_ ___      | |  | |__) |\n" +
                "   \\ \\/ / _ \\| |/ _` |   / /\\ \\| | | | __/ _ \\     | |  |  ___/ \n" +
                "    \\  / (_) | | (_| |  / ____ \\ |_| | || (_) |    | |  | |     \n" +
                "     \\/ \\___/|_|\\__,_| /_/    \\_\\__,_|\\__\\___/     |_|  |_|     \n");

        // Load the config.yml
        saveDefaultConfig();
        loadConfiguration();

        getLogger().info("Void Auto TP has been successfully enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("VoidAutoTP is now disabled.");
    }

    private void loadConfiguration() {
        reloadConfig();

        isEnabled = getConfig().getBoolean("enabled");
        voidLayer = getConfig().getInt("void_layer");
        message = getConfig().getString("message");

        if (getConfig().contains("teleport")) {
            World world = getServer().getWorld(getConfig().getString("teleport.world_name"));
            double x = getConfig().getDouble("teleport.x");
            double y = getConfig().getDouble("teleport.y");
            double z = getConfig().getDouble("teleport.z");
            float yaw = (float) getConfig().getDouble("teleport.yaw");
            float pitch = (float) getConfig().getDouble("teleport.pitch");

            if (world != null) {
                teleportLocation = new Location(world, x, y, z, yaw, pitch);
            } else {
                throw new IllegalArgumentException("Invalid world specified in [VoidAutoTP/config.yml] for teleport location.");
            }
        } else {
            throw new IllegalArgumentException("No teleport location specified in [VoidAutoTP/config.yml].");
        }

        if (voidLayer >= 0) {
            throw new IllegalArgumentException("Invalid void_layer specified in [VoidAutoTP/config.yml].");
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getY() < voidLayer && isEnabled) {
            // Scheduling the teleportation and message sending on the next tick to avoid warning
            Bukkit.getScheduler().runTaskLater(this, () -> {
                player.teleport(teleportLocation);
                if(!Objects.equals(message, "")) {
                    player.sendMessage(message);
                }
            }, 1);
        }
    }
}