package xyz.damt.section.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.damt.section.Grant;
import xyz.damt.section.Util;
import xyz.damt.section.listeners.GrantMenu;

public class GrantCommand implements CommandExecutor {


    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("grant.gui")) {
                if (args.length == 1) {
                    Grant.getInstance().p = Bukkit.getPlayer(args[0]);

                    if (Grant.getInstance().p != null) {
                        GrantMenu.applyInventory(player);
                    } else {
                        player.sendMessage(Util.chat("&cThat player does not exist!"));
                    }
                } else {
                    player.sendMessage(Util.chat("&c/grant <player>"));
                }
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(Util.chat("&cThis is a player only command!"));
        }

        return false;
    }
}
