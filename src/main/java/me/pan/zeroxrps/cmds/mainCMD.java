package me.pan.zeroxrps.cmds;

import me.pan.zeroxrps.modules.game.Game;
import me.pan.zeroxrps.modules.game.GameUI;
import me.pan.zeroxrps.modules.game.RPSChoice;
import me.pan.zeroxrps.modules.game.RewardsHandler;
import me.pan.zeroxrps.utils.Color;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class mainCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length != 1 && args.length != 2) {
                p.sendMessage("Invalid Command!");
                return false;
            }
            if (args.length == 1) {
                new GameUI(null, p, args[0]).open();
            }
            if (args.length == 2) {
                if (args[0].equals("addrewards")) {
                    new RewardsHandler(p, args[1]).create();

                }
            }
        }
        return false;
    }
}
