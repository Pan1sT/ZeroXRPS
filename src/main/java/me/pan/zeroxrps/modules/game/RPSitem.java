package me.pan.zeroxrps.modules.game;

import lombok.Getter;
import me.pan.zeroxrps.utils.ItemBuilder;
import me.pan.zeroxrps.utils.Settings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
public class RPSitem {

    @Getter public static RPSitem instance;
    @Getter public ItemStack rock;
    @Getter public ItemStack paper;
    @Getter public ItemStack scissor;
    @Getter public ItemStack udItem;

    public RPSitem() {
        instance = this;
        rock = new ItemBuilder(Material.getMaterial(Settings.getROCKMATERIAL())).withName(Settings.getROCKNAME()).withLore(Settings.getROCKLORE()).buildStack();
        paper = new ItemBuilder(Material.getMaterial(Settings.getPAPERMATERIAL())).withName(Settings.getPAPERNAME()).withLore(Settings.getPAPERLORE()).buildStack();
        scissor = new ItemBuilder(Material.getMaterial(Settings.getSCISSORMATERIAL())).withName(Settings.getSCISSORNAME()).withLore(Settings.getSCISSORLORE()).buildStack();
        udItem = new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getNOTHINGCOLOR()).withName(Settings.getNOTHINGNAME()).buildStack();
    }

    public ItemStack getHead(Player p){
        return new ItemBuilder().toSkullBuilder().withOwner(p.getName()).buildSkull();
    }
}
