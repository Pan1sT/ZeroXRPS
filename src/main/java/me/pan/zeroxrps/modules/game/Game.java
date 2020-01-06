package me.pan.zeroxrps.modules.game;

import lombok.Getter;
import me.pan.zeroxrps.ZeroXRPS;
import me.pan.zeroxrps.modules.nms.NMSHandler;
import me.pan.zeroxrps.utils.Color;
import me.pan.zeroxrps.utils.ItemBuilder;
import me.pan.zeroxrps.utils.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Game implements Listener {

    private Player p;
    private RPSChoice p1Choice;
    private RPSChoice p2Choice;
    private String rewardsID;

    HashMap<UUID, List<String>> RPSRecord = new HashMap<>();
    @Getter
    public static List<Player> inGame = new ArrayList<>();

    public Game(Player p, RPSChoice rpsChoice, String rewardsID) {
        this.p = p;
        this.p1Choice = rpsChoice;
        this.rewardsID = rewardsID;
    }

    public void play() {
        inGame.add(p);
        this.p2Choice = RPSChoice.getRandomChoice();
        p.getOpenInventory().setItem(31, new ItemStack(Material.AIR));
        p.getOpenInventory().setItem(30, getRPS(p1Choice));
        p.getOpenInventory().setItem(32, getRPS(p2Choice));
        if (winorlose() == RPSState.WIN) {
            NMSHandler.getUpdateTitle().update(p, Settings.getGUIWIN());
            p.getOpenInventory().getTopInventory().setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getWINPANECOLOR()).withName(Settings.getWINPANENAME()).buildStack());
            p.getOpenInventory().getTopInventory().setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getWINPANECOLOR()).withName(Settings.getWINPANENAME()).buildStack());
            p.getOpenInventory().getTopInventory().setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getWINPANECOLOR()).withName(Settings.getWINPANENAME()).buildStack());
            p.getOpenInventory().getTopInventory().setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getLOSEPANCOLOR()).withName(Settings.getLOSEPANENAME()).buildStack());
            p.getOpenInventory().getTopInventory().setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getLOSEPANCOLOR()).withName(Settings.getLOSEPANENAME()).buildStack());
            p.getOpenInventory().getTopInventory().setItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getLOSEPANCOLOR()).withName(Settings.getLOSEPANENAME()).buildStack());
            p.updateInventory();
            p.sendMessage(Settings.getWINMSG());
            new RewardsHandler(p, rewardsID).give();
        } else if (winorlose() == RPSState.LOSE) {
            NMSHandler.getUpdateTitle().update(p, Settings.getGUILOSE().replaceAll("%player%", p.getName()));
            p.getOpenInventory().getTopInventory().setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getLOSEPANCOLOR()).withName(Settings.getLOSEPANENAME()).buildStack());
            p.getOpenInventory().getTopInventory().setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getLOSEPANCOLOR()).withName(Settings.getLOSEPANENAME()).buildStack());
            p.getOpenInventory().getTopInventory().setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getLOSEPANCOLOR()).withName(Settings.getLOSEPANENAME()).buildStack());
            p.getOpenInventory().getTopInventory().setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getWINPANECOLOR()).withName(Settings.getWINPANENAME()).buildStack());
            p.getOpenInventory().getTopInventory().setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getWINPANECOLOR()).withName(Settings.getWINPANENAME()).buildStack());
            p.getOpenInventory().getTopInventory().setItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getWINPANECOLOR()).withName(Settings.getWINPANENAME()).buildStack());
            p.updateInventory();
            p.sendMessage(Settings.getLOSEMSG());
        } else if (winorlose() == RPSState.DRAW) {
            NMSHandler.getUpdateTitle().update(p, Settings.getGUIDRAW().replaceAll("%player%", p.getName()));
            p.getOpenInventory().getTopInventory().setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getDRAWPANCOLOR()).withName(Settings.DRAWPANENAME).buildStack());
            p.getOpenInventory().getTopInventory().setItem(19, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getDRAWPANCOLOR()).withName(Settings.DRAWPANENAME).buildStack());
            p.getOpenInventory().getTopInventory().setItem(28, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getDRAWPANCOLOR()).withName(Settings.DRAWPANENAME).buildStack());
            p.getOpenInventory().getTopInventory().setItem(16, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getDRAWPANCOLOR()).withName(Settings.DRAWPANENAME).buildStack());
            p.getOpenInventory().getTopInventory().setItem(25, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getDRAWPANCOLOR()).withName(Settings.DRAWPANENAME).buildStack());
            p.getOpenInventory().getTopInventory().setItem(34, new ItemBuilder(Material.STAINED_GLASS_PANE).withData(Settings.getDRAWPANCOLOR()).withName(Settings.DRAWPANENAME).buildStack());
            p.updateInventory();
            p.sendMessage(Settings.getDRAWMSG().replaceAll("%player%", p.getName()));
        }
        Bukkit.getScheduler().runTaskLater(ZeroXRPS.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                inGame.remove(p);
                if (!(p.getOpenInventory().getTitle().equals(Settings.getGUITITLE())))
                    return;
                new GameUI(p.getOpenInventory().getTopInventory(), p, rewardsID).initAfterPlay();
            }
        }, 30);
    }

    public ItemStack getRPS(RPSChoice choice) {
        switch (choice) {
            case SCISSOR:
                return RPSitem.getInstance().getScissor();
            case PAPER:
                return RPSitem.getInstance().getPaper();
            case ROCK:
                return RPSitem.getInstance().getRock();
        }
        return null;
    }

    public String getSymbol(RPSChoice choice) {
        switch (choice) {
            case SCISSOR:
                return Settings.getSCISSORNAME();
            case PAPER:
                return Settings.getPAPERNAME();
            case ROCK:
                return Settings.getROCKNAME();
            default:
                return "ERROR";
        }
    }

    public void addToHashMap(Player p, String str) {
        if (RPSRecord.containsKey(p.getUniqueId())) {
            List<String> list = RPSRecord.get(p.getUniqueId());
            list.add(str);
            RPSRecord.replace(p.getUniqueId(), list);
        }
        List<String> list = RPSRecord.get(p.getUniqueId());
        list.add(str);
        RPSRecord.put(p.getUniqueId(), list);
    }

    public RPSState winorlose() {
        switch (p1Choice) {
            case ROCK:
                if (p2Choice == RPSChoice.SCISSOR) return RPSState.WIN;
                if (p2Choice == RPSChoice.ROCK) return RPSState.DRAW;
                break;
            case PAPER:
                if (p2Choice == RPSChoice.ROCK) return RPSState.WIN;
                if (p2Choice == RPSChoice.PAPER) return RPSState.DRAW;
                break;
            case SCISSOR:
                if (p2Choice == RPSChoice.PAPER) return RPSState.WIN;
                if (p2Choice == RPSChoice.SCISSOR) return RPSState.DRAW;
                break;
            default:
                return RPSState.LOSE;
        }
        return RPSState.LOSE;
    }
}

