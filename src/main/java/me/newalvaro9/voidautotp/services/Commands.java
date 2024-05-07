package me.newalvaro9.voidautotp.services;

;
import me.newalvaro9.voidautotp.VoidAutoTP;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private final VoidAutoTP plugin = VoidAutoTP.getPlugin();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("voidautotp")) {
            /* HELP COMMAND */
            if (args.length == 1 && args[0].equalsIgnoreCase("help")) {

                if (!sender.hasPermission("voidautotp.admin")) {
                    return false;
                }

                String message =
                        ChatColor.BOLD + "=== VoidAutoTP Commands ===\n" +
                        ChatColor.GOLD + "/voidautotp help" + ChatColor.WHITE + " - Displays this menu.\n" +
                        ChatColor.GOLD + "/voidautotp reload" + ChatColor.WHITE + " - Reload the plugin's configuration.\n" +
                        ChatColor.GOLD + "/voidautotp setTpLocation" + ChatColor.WHITE + " - Set the tp location.\n";

                sender.sendMessage(message);
                return true;
            }

            /* RELOAD COMMAND */
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {

                if (!sender.hasPermission("voidautotp.admin")) {
                    return false;
                }

                plugin.reloadConfig();

                sender.sendMessage(ChatColor.GREEN + "Successfully reloaded VoidAutoTP!");
                return true;
            }

            /* SET TP LOCATION COMMAND */
            if (args.length == 1 && args[0].equalsIgnoreCase("setTpLocation")) {
                if (sender instanceof Player) {
                    if (!sender.hasPermission("voidautotp.admin")) {
                        return false;
                    }

                    Location location = ((Player) sender).getLocation();
                    String worldName = location.getWorld().getName();
                    double x = location.getX();
                    double y = location.getY();
                    double z = location.getZ();
                    float yaw = location.getYaw();
                    float pitch = location.getPitch();

                    plugin.getConfig().set("teleport.world_name", worldName);
                    plugin.getConfig().set("teleport.x", x);
                    plugin.getConfig().set("teleport.y", y);
                    plugin.getConfig().set("teleport.z", z);
                    plugin.getConfig().set("teleport.yaw", yaw);
                    plugin.getConfig().set("teleport.pitch", pitch);
                    plugin.saveConfig();

                    sender.sendMessage(ChatColor.GREEN + "Teleport location set to your current location.");
                } else {
                    sender.sendMessage(ChatColor.RED + "Only players can use this command.");
                }
                return true;
            }
        }
        return false;
    }
}