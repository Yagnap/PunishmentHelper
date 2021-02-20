package eu.hobbydevs.spigot.punishmenthelp.lib;

import java.util.HashMap;

public final class Punishments {
    public static final HashMap<String, Punishment> PUNISHMENTS = new HashMap<String, Punishment>() {
        {
            put("clearinv", new Punishment("/ci", false));
            put("ban", new Punishment("/ban", false));
            put("mute", new Punishment("/mute", false));
            put("tempban", new Punishment("/tempban", true));
            put("tempmute", new Punishment("/mute", true));

        }
    };
}
