package xyz.damt.section.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.damt.section.Grant;
import xyz.damt.section.Util;

import java.util.ArrayList;
import java.util.List;

public class GrantMenu implements Listener {

    public static void applyInventory(Player player) {

        int slots = Grant.getInstance().getConfig().getInt("INVENTORY.SIZE");
        String name = Grant.getInstance().getConfig().getString("INVENTORY.TITLE");

        Inventory inv = Bukkit.createInventory(null, slots, Util.chat(name));

        for (String section : Grant.getInstance().getConfig().getConfigurationSection("RANKS").getKeys(false)) {
            String material = Grant.getInstance().getConfig().getString("RANKS." + section + ".MATERIAL");
            String displayname = Grant.getInstance().getConfig().getString("RANKS." + section + ".DISPLAY-NAME");
            int slot = Grant.getInstance().getConfig().getInt("RANKS." + section + ".SLOT");
            int data = Grant.getInstance().getConfig().getInt("RANKS." + section + ".DATA");
            List<String> lore = new ArrayList<String>();
            for (String list : Grant.getInstance().getConfig().getStringList("RANKS." + section + ".LORE")) {
                lore.add(Util.chat(list));
            }


            ItemStack stack = new ItemStack(Material.valueOf(material), 1, (byte) data);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(Util.chat(displayname));
            meta.setLore(lore);
            stack.setItemMeta(meta);

            inv.setItem(slot, stack);

        }
        player.openInventory(inv);
    }
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        String name = Grant.getInstance().getConfig().getString("INVENTORY.TITLE");


        if (e.getWhoClicked() instanceof Player) {
            if (e.getClickedInventory() == null) return;
            if (e.getClickedInventory().getTitle().equals(Util.chat(name))) {
                if (e.getCurrentItem() != null) {
                    e.setCancelled(true);
                }
                for (String section : Grant.getInstance().getConfig().getConfigurationSection("RANKS").getKeys(false)) {

                    String material = Grant.getInstance().getConfig().getString("RANKS." + section + ".MATERIAL");
                    String displayname = Grant.getInstance().getConfig().getString("RANKS." + section + ".DISPLAY-NAME");
                    ItemStack item = e.getCurrentItem();


                    if (item != null && item.getType().equals(Material.valueOf(material)) && item.getItemMeta().getDisplayName().equals(Util.chat(displayname))) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Grant.getInstance().getConfig().getString("RANKS." + section + ".COMMAND").replaceAll("%player%",
                                Grant.getInstance().p.getName()));
                    }

                }
                player.closeInventory();
                player.sendMessage(Util.chat(Grant.getInstance().getConfig().getString("MESSAGES.RANK-UPDATED")).replaceAll("%player%", Grant.getInstance().p.getName()));
            }
        }
    }

}
