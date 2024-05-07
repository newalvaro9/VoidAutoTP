package me.newalvaro9.voidautotp.listeners;

import me.newalvaro9.voidautotp.VoidAutoTP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class OnPlayerMove implements Listener {
    private final VoidAutoTP plugin = VoidAutoTP.getPlugin();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getLocation().getY() < plugin.getConfig().getInt("void_layer") && plugin.getConfig().getBoolean("enabled")) {
            // Scheduling the teleportation and message sending on the next tick to avoid warning
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.setFallDistance(0f);
                player.teleport(getTeleportLocation());

                String message = plugin.getConfig().getString("message");
                if (!Objects.equals(message, "")) {
                    player.sendMessage(message);
                }
            }, 1);
        }
    }

    private Location getTeleportLocation() {
        World world = plugin.getServer().getWorld(plugin.getConfig().getString("teleport.world_name"));
        double x = plugin.getConfig().getDouble("teleport.x");
        double y = plugin.getConfig().getDouble("teleport.y");
        double z = plugin.getConfig().getDouble("teleport.z");
        float yaw = (float) plugin.getConfig().getDouble("teleport.yaw");
        float pitch = (float) plugin.getConfig().getDouble("teleport.pitch");

        if (world != null) {
            return new Location(world, x, y, z, yaw, pitch);
        } else {
            throw new IllegalArgumentException("Invalid world specified in [VoidAutoTP/config.yml] for teleport location.");
        }
    }
}