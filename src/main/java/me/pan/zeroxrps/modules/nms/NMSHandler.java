package me.pan.zeroxrps.modules.nms;

import lombok.Getter;
import me.pan.zeroxrps.modules.nms.common.SignEdit;
import me.pan.zeroxrps.modules.nms.common.TitleUpdator;
import me.pan.zeroxrps.modules.nms.v1_12_R1.editSign;
import me.pan.zeroxrps.modules.nms.v1_12_R1.updateTitle;
import org.bukkit.Bukkit;

public class NMSHandler {

    @Getter public static TitleUpdator updateTitle;
    @Getter public static SignEdit editSign;

    public NMSHandler() {
        String ver = getNMSVersion();
        switch (ver){
            case "v1_12_R1":
                updateTitle = new updateTitle();
                editSign = new editSign();
                break;
            case "v1_13_R2":
                updateTitle = new me.pan.zeroxrps.modules.nms.v1_13_R2.updateTitle();
                editSign = new me.pan.zeroxrps.modules.nms.v1_13_R2.editSign();
                break;
            case "v1_14_R1":
                updateTitle = new me.pan.zeroxrps.modules.nms.v1_14_R1.updateTitle();
                editSign = new me.pan.zeroxrps.modules.nms.v1_14_R1.editSign();
                break;
        }
    }

    public static String getNMSVersion(){
        String v = Bukkit.getServer().getClass().getPackage().getName();
        return v.substring(v.lastIndexOf('.') + 1);
    }

}
