package xyz.damt.section;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.damt.section.commands.GrantCommand;
import xyz.damt.section.listeners.GrantMenu;

public class Grant extends JavaPlugin {

    private static Grant instance;
    public Player p;

    public void onEnable() {
        instance = this;
        registerAll();
    }
    private void registerCommands() {
        getCommand("grant").setExecutor(new GrantCommand());
    }
    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new GrantMenu(), this);
    }
    private void loadConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
    }
    private void loadMessages() {
        Bukkit.getConsoleSender().sendMessage(Util.chat("&6&lFastGrant has been enabled!"));
        Bukkit.getConsoleSender().sendMessage(Util.chat("&6&ldeveloped by damt"));
    }
    private void registerAll() {
        loadMessages();
        loadConfig();
        registerCommands();
        registerListeners();
    }

    public static Grant getInstance() {
        return instance;
    }
}
