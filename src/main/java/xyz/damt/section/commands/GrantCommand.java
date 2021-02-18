package xyz.damt.section.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.damt.section.Grant;
import xyz.damt.section.util.Util;
import xyz.damt.section.listeners.GrantMenu;

public class GrantCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(Util.chat("&cThis is a player only command!"));
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("grant.gui")) {
            player.sendMessage(Util.chat("&cNo Permission!"));
            return false;
        }

        if (args.length != 1) {
            player.sendMessage(Util.chat("&c/grant <player>"));
            return false;
        }

        Grant.getInstance().p = Bukkit.getPlayer(args[0]);

        if (Grant.getInstance().p == null) {
            player.sendMessage(Util.chat("&cThat player does not exist!"));
            return false;
        }

        GrantMenu.applyInventory(player);
        return false;
    }
}
