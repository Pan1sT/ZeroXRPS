package me.pan.zeroxrps.modules.nms.v1_13_R2;

import me.pan.zeroxrps.modules.nms.common.SignEdit;
import net.minecraft.server.v1_13_R2.BlockPosition;
import net.minecraft.server.v1_13_R2.EntityPlayer;
import net.minecraft.server.v1_13_R2.PacketPlayOutOpenSignEditor;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class editSign implements SignEdit {
    @Override
    public void openSign(Player p) {
        EntityPlayer ep = ((CraftPlayer) p).getHandle();
        BlockPosition bp = new BlockPosition(ep);
        PacketPlayOutOpenSignEditor packet = new PacketPlayOutOpenSignEditor(bp);
        ep.playerConnection.sendPacket(packet);
    }
}
