package me.pan.zeroxrps;

import lombok.Getter;
import lombok.Setter;
import me.pan.zeroxrps.cmds.mainCMD;
import me.pan.zeroxrps.files.configFile;
import me.pan.zeroxrps.files.rewardFile;
import me.pan.zeroxrps.listeners.onCreate;
import me.pan.zeroxrps.listeners.onInvClick;
import me.pan.zeroxrps.modules.game.RPSitem;
import me.pan.zeroxrps.modules.game.RewardsHandler;
import me.pan.zeroxrps.modules.nms.NMSHandler;
import me.pan.zeroxrps.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

@Setter
@Getter
public final class ZeroXRPS extends JavaPlugin {

    @Getter
    public static ZeroXRPS instance;
    String prefix = Color.translate("&c[&fZeroXRPS&c] &f>> ");

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(Color.translate("&c[&fZeroXRPS&c]&2==================================================="));
        register();
        Bukkit.getConsoleSender().sendMessage(Color.translate(prefix + "&5Status: &aEnabled"));
        Bukkit.getConsoleSender().sendMessage(Color.translate(prefix + "&5Version: &6" + this.getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Color.translate(prefix + "&5Author: &6Pan >.0"));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&c[&fZeroXRPS&c]&2==================================================="));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Color.translate("&c[&fZeroXRPS&c]&2==================================================="));
        Bukkit.getConsoleSender().sendMessage(Color.translate(prefix + "&5Status: &cDisabled"));
        Bukkit.getConsoleSender().sendMessage(Color.translate(prefix + "&5Version: &6" + this.getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Color.translate(prefix + "&5Author: &6Pan >.0"));
        Bukkit.getConsoleSender().sendMessage(Color.translate("&c[&fZeroXRPS&c]&2========================" +
                "=========================="));
    }

    private void register() {
        instance = this;
        loadFiles();
        new RPSitem();
        new NMSHandler();
        getCommand("rps").setExecutor(new mainCMD());
        getServer().getPluginManager().registerEvents(new onInvClick(), this);
        getServer().getPluginManager().registerEvents(new onCreate(), this);
    }

    private void loadFiles() {
        long startT = System.currentTimeMillis();
        new configFile();
        new rewardFile();
        long finishT = System.currentTimeMillis();
        Bukkit.getConsoleSender().sendMessage(Color.translate(prefix + "&9[Files] &eSuccessfully Loaded! &7(TimeUsed: " + (finishT - startT) + " ms)"));
    }

}
