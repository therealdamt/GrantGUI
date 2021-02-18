package xyz.damt.section;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.section.commands.GrantCommand;
import xyz.damt.section.listeners.GrantMenu;
import xyz.damt.section.util.Util;

public class Grant extends JavaPlugin {

    private static Grant instance;
    public Player p;

    @EventHandler
    public void onLoad() {
        instance = this;
    }

    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage(Util.chat("&6&lFastGrant has been enabled!"));
        Bukkit.getConsoleSender().sendMessage(Util.chat("&6&lDeveloped by damt"));

        this.saveDefaultConfig();

        this.getCommand("grant").setExecutor(new GrantCommand());
        Bukkit.getPluginManager().registerEvents(new GrantMenu(), this);
    }

    public static Grant getInstance() {
        return instance;
    }
}
