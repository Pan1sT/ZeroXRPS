package me.pan.zeroxrps.modules.game;

import lombok.Getter;
import me.pan.zeroxrps.ZeroXRPS;
import me.pan.zeroxrps.files.rewardFile;
import me.pan.zeroxrps.modules.nms.NMSHandler;
import me.pan.zeroxrps.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RewardsHandler {

    private Player p;
    private String rewardID;
    private String rewards;

    @Getter
    private static HashMap<Player, String> rewardlist = new HashMap<>();

    public RewardsHandler(Player p, String rewardID) {
        this.p = p;
        this.rewardID = rewardID;

    }

    public void give() {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), rewardFile.getInstance().getString("" + rewardID + ".cmd").replaceAll("%player%", p.getName()));
        p.sendMessage(Color.translate(rewardFile.getInstance().getString("" + rewardID + ".message").replaceAll("%player%", p.getName())));
    }

    public void create() {
        if (!notexist()) {
            p.sendMessage("ALREADT EXIST!");
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                rewards = rewardlist.get(p);
                if (rewards == null || rewards.equals("") || rewards.equals(" ")) {
                    getRewardlist().put(p, "");
                    p.sendMessage("Please type the reward command without any slash in the text box !");
                    create();
                    cancel();
                    return;
                }
                rewardFile.getInstance().getConfiguration().set("" + rewardID + ".cmd", rewards);
                rewardFile.getInstance().getConfiguration().set("" + rewardID + ".message", "Rewards has sent to %player%");
                rewardFile.getInstance().save();
                p.sendMessage("rewards = " + rewards);
                rewardlist.remove(p);
                cancel();
            }
        }.runTaskTimer(ZeroXRPS.getInstance(), 20, 0);

    }

    public void validReward(String rewardID) {
    }

    public boolean notexist() {
        boolean yesno = rewardFile.getInstance().getString("" + rewardID).equals("null");
        Bukkit.broadcastMessage("boolean + " + yesno);
        return yesno;

    }
}
