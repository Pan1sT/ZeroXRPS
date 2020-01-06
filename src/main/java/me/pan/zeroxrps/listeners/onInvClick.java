package me.pan.zeroxrps.listeners;

import lombok.Getter;
import me.pan.zeroxrps.ZeroXRPS;
import me.pan.zeroxrps.modules.game.Game;
import me.pan.zeroxrps.modules.game.GameUI;
import me.pan.zeroxrps.modules.game.RPSChoice;
import me.pan.zeroxrps.modules.game.RPSitem;
import me.pan.zeroxrps.modules.nms.NMSHandler;
import me.pan.zeroxrps.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class onInvClick implements Listener {
    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof GameUI)) {
            return;
        }
        if (e.getClick().equals(ClickType.NUMBER_KEY)) {
            e.setCancelled(true);
        }
        e.setCancelled(true);

        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();
        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;
        // Using slots click is a best option for your inventory click's
        if (Game.getInGame().contains(p)) {
            return;
        }
        if (e.getRawSlot() == 30) new Game(p, RPSChoice.ROCK, GameUI.getRewardsMAP().get(p)).play();
        if (e.getRawSlot() == 31) new Game(p, RPSChoice.PAPER, GameUI.getRewardsMAP().get(p)).play();
        if (e.getRawSlot() == 32) new Game(p, RPSChoice.SCISSOR, GameUI.getRewardsMAP().get(p)).play();
        if (e.getRawSlot() == 49) e.getWhoClicked().closeInventory();
    }
}
