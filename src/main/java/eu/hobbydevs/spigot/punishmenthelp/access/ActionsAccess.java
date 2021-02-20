package eu.hobbydevs.spigot.punishmenthelp.access;

import eu.hobbydevs.spigot.punishmenthelp.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

public class ActionsAccess {
    private static FileConfiguration config = Main.getInstance().getConfig();

    public static void addAction(String name, ArrayList<String[]> punishmentsPerStage) {
        int length = punishmentsPerStage.size();

        for(int i = 0; i < length; i++) {
            config.set("Punishmenthelper.Actions." + name + ".stage" + Integer.toString(i), punishmentsPerStage.get(i));
        }

        config.set("Punishmenthelper.Actions." + name + ".exists", true);
        config.set("Punishmenthelper.Actions." + name + ".stages", length);

        Main.getInstance().saveConfig();

    }

    public static void editAction(String name, int stage, String[] punishments) {
        config.set("Punishmenthelper.Actions." + name + ".stage" + Integer.toString(stage), punishments);
        config.set("Punishmenthelper.Actions." + name + ".stages", getStages(name) + 1);

        Main.getInstance().saveConfig();
    }

    /**
     *
     * @return Stages of the provided action, 0 if it does not exist.
     */
    public static Integer getStages(String name) {

        int stages = 0;

        // equals because it could be null
        if(config.get("Punishmenthelper.Actions." + name + ".exists").equals(true)) {
            stages =  Integer.parseInt((String) config.get("Punishmenthelper.Actions." + name + ".stages"));
        }

        return stages;
    }

    public static String[] getPunishment(String name, int stage) {
        return (String[]) config.get("Punishmenthelper.Actions." + name + ".stage" + Integer.toString(stage));
    }

    public static void removeAction(String name) {
        config.set("Punishmenthelper.Actions." + name + ".exists", false);
        config.set("Punishmenthelper.Actions." + name, null);
    }

}
