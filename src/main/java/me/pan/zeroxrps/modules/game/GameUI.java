package me.pan.zeroxrps.modules.game;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import me.pan.zeroxrps.files.rewardFile;
import me.pan.zeroxrps.modules.nms.NMSHandler;
import me.pan.zeroxrps.utils.ItemBuilder;
import me.pan.zeroxrps.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;

public class GameUI implements InventoryHolder, Listener {
    // Create a new inventory, with "this" owner for comparison with other inventories, a size of nine, called example
    private Inventory inv;
    private Player p;
    private String rewardsID;

    @Getter private static HashMap<Player, String> rewardsMAP = new HashMap<>();

    public GameUI(Inventory inv, Player p, String rewardsID) {
        this.p = p;
        this.rewardsID = rewardsID;
        if (notexist()){
            p.sendMessage("The rewardsID is invalid!");
            return;
        }
        if (getRewardsMAP().containsKey(p)){
            getRewardsMAP().replace(p, rewardsID);
        }else{
            getRewardsMAP().put(p, rewardsID);
        }
        if (inv == null) {
            this.inv = Bukkit.createInventory(this, 54, Settings.getGUITITLE());
        } else {
            this.inv = inv;
        }
        initializeItems();
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inv;
    }

    // You can call this whenever you want to put the items in
    public void initializeItems() {
        inv.setItem(1, RPSitem.getInstance().getHead(p));
        inv.setItem(7, new ItemBuilder().withName(Settings.getNPCNAME()).toSkullBuilder().withOwner(Settings.getNPCOWNER()).buildSkull());
        inv.setItem(10, RPSitem.getInstance().getUdItem());
        inv.setItem(19, RPSitem.getInstance().getUdItem());
        inv.setItem(28, RPSitem.getInstance().getUdItem());
        inv.setItem(16, RPSitem.getInstance().getUdItem());
        inv.setItem(25, RPSitem.getInstance().getUdItem());
        inv.setItem(34, RPSitem.getInstance().getUdItem());
        inv.setItem(30, RPSitem.getInstance().getRock());
        inv.setItem(31, RPSitem.getInstance().getPaper());
        inv.setItem(32, RPSitem.getInstance().getScissor());
        inv.setItem(49, new ItemBuilder(Material.BARRIER).withName(Settings.getEXITNAME()).buildStack());
    }

    public void initAfterPlay() {
        NMSHandler.getUpdateTitle().update(p, Settings.getGUITITLE());
        inv.setItem(10, RPSitem.getInstance().getUdItem());
        inv.setItem(19, RPSitem.getInstance().getUdItem());
        inv.setItem(28, RPSitem.getInstance().getUdItem());
        inv.setItem(16, RPSitem.getInstance().getUdItem());
        inv.setItem(25, RPSitem.getInstance().getUdItem());
        inv.setItem(34, RPSitem.getInstance().getUdItem());
        inv.setItem(30, RPSitem.getInstance().getRock());
        inv.setItem(31, RPSitem.getInstance().getPaper());
        inv.setItem(32, RPSitem.getInstance().getScissor());
        p.updateInventory();
    }

    // Nice little method to create a gui item with a custom name, and description
    private ItemStack createGuiItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> metalore = new ArrayList<String>();

        for (String lorecomments : lore) {

            metalore.add(lorecomments);

        }

        meta.setLore(metalore);
        item.setItemMeta(meta);
        return item;
    }

    // You can open the inventory with this
    public void open() {
        if (notexist()){
            return;
        }
        p.openInventory(inv);
    }

    public boolean notexist() {
        return rewardFile.getInstance().getString("" + rewardsID).equals("null");
    }
/*
    public void update(Player p, String title) {
        EntityPlayer ep = ((CraftPlayer) p).getHandle();
        PacketPlayOutOpenWindow packet = new PacketPlayOutOpenWindow(ep.activeContainer.windowId, "minecraft:chest", new ChatMessage(title), p.getOpenInventory().getTopInventory().getSize());
        ep.playerConnection.sendPacket(packet);
        ep.updateInventory(ep.activeContainer);
    }

 */
}
