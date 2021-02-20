package eu.hobbydevs.spigot.punishmenthelp.access;

import eu.hobbydevs.spigot.punishmenthelp.Main;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class PunishmentAccess {
    private static FileConfiguration config = Main.getInstance().getConfig();

    public static void addOffence(UUID player, String category) {
        Integer value = Integer.parseInt((String) config.get("Punishmenthelper.Offences." + player.toString() + category));
        if(value != null) {
            config.set("Punishmenthelper.Offences." + player.toString() + category, value+1);
        } else {
            config.set("Punishmenthelper.Offences." + player.toString() + category, null);
        }

        Main.getInstance().saveConfig();
    }
    public static void removeOffence(UUID player, String category) {
        Integer value = Integer.parseInt((String) config.get("Punishmenthelper.Offences." + player.toString() + category));
        if(value != null && value >=1) {
            config.set("Punishmenthelper.Offences." + player.toString() + category, value-1);
        }

        Main.getInstance().saveConfig();

    }
    public static void clearOffences(UUID player) {
        config.set("Punishmenthelper.Offences." + player.toString(), null);

        Main.getInstance().saveConfig();
    }

    public static int getOffenceStage(UUID player, String category) {
        Integer value = Integer.parseInt((String) config.get("Punishmenthelper.Offences." + player.toString() + category));
        if(value == null) value = 0;

        return value;
    }

}
