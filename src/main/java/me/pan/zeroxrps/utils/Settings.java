package me.pan.zeroxrps.utils;

import lombok.Getter;
import me.pan.zeroxrps.files.configFile;

import java.util.List;

public class Settings {

    //GUI
    @Getter public static String GUITITLE = Color.translate(configFile.getInstance().getString("GUI.title"));
    @Getter public static String GUIWIN = Color.translate(configFile.getInstance().getString("GUI.win"));
    @Getter public static String GUILOSE = Color.translate(configFile.getInstance().getString("GUI.lose"));
    @Getter public static String GUIDRAW = Color.translate(configFile.getInstance().getString("GUI.draw"));
    //Choices' DisplayName
    @Getter public static String ROCKNAME = Color.translate(configFile.getInstance().getString("GUIItems.Rock.name"));
    @Getter public static String PAPERNAME = Color.translate(configFile.getInstance().getString("GUIItems.Paper.name"));
    @Getter public static String SCISSORNAME = Color.translate(configFile.getInstance().getString("GUIItems.Scissor.name"));
    //Choices' Material
    @Getter public static String ROCKMATERIAL = configFile.getInstance().getString("GUIItems.Rock.item");
    @Getter public static String PAPERMATERIAL = configFile.getInstance().getString("GUIItems.Paper.item");
    @Getter public static String SCISSORMATERIAL = configFile.getInstance().getString("GUIItems.Scissor.item");
    //Choices' Lore
    @Getter public static List<String> ROCKLORE = Color.translate(configFile.getInstance().getStringList("GUIItems.Rock.lore"));
    @Getter public static List<String> PAPERLORE = Color.translate(configFile.getInstance().getStringList("GUIItems.Paper.lore"));
    @Getter public static List<String> SCISSORLORE = Color.translate(configFile.getInstance().getStringList("GUIItems.Scissor.lore"));
    //Other GUIItems
    @Getter public static String WINPANENAME =  Color.translate(configFile.getInstance().getString("GUIItems.Win.name"));
    @Getter public static String LOSEPANENAME =  Color.translate(configFile.getInstance().getString("GUIItems.Lose.name"));
    @Getter public static String DRAWPANENAME =  Color.translate(configFile.getInstance().getString("GUIItems.Draw.name"));
    @Getter public static int WINPANECOLOR = configFile.getInstance().getInt("GUIItems.Win.colorID");
    @Getter public static int LOSEPANCOLOR = configFile.getInstance().getInt("GUIItems.Lose.colorID");
    @Getter public static int DRAWPANCOLOR = configFile.getInstance().getInt("GUIItems.Draw.colorID");
    @Getter public static String NPCOWNER =  configFile.getInstance().getString("GUIItems.NPC.owner");
    @Getter public static String NPCNAME =  Color.translate(configFile.getInstance().getString("GUIItems.NPC.name"));
    @Getter public static String EXITNAME =  Color.translate(configFile.getInstance().getString("GUIItems.EXIT.name"));
    @Getter public static String NOTHINGNAME =  Color.translate(configFile.getInstance().getString("GUIItems.NOTHING.name"));
    @Getter public static int NOTHINGCOLOR = configFile.getInstance().getInt("GUIItems.NOTHING.colorID");
    //States
    @Getter public static String WINMSG = configFile.getInstance().getString("State.Win.message");
    @Getter public static String LOSEMSG = configFile.getInstance().getString("State.Lose.message");
    @Getter public static String DRAWMSG = configFile.getInstance().getString("State.Draw.message");
    //
}
