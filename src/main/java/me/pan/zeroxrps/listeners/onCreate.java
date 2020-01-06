package me.pan.zeroxrps.listeners;

import me.pan.zeroxrps.modules.game.RewardsHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onCreate implements Listener {

    @EventHandler
    public void onMSG(AsyncPlayerChatEvent e) {
        if (!RewardsHandler.getRewardlist().containsKey(e.getPlayer())) {
            return;
        }
        RewardsHandler.getRewardlist().replace(e.getPlayer(), e.getMessage());
    }
}
