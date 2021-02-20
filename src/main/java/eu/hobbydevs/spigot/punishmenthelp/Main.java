package eu.hobbydevs.spigot.punishmenthelp;

import eu.hobbydevs.spigot.punishmenthelp.commands.EditorCommand;
import eu.hobbydevs.spigot.punishmenthelp.commands.ListCommand;
import eu.hobbydevs.spigot.punishmenthelp.commands.PunisherCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;

        getCommand("punisher").setExecutor(new PunisherCommand());
        getCommand("punisheredit").setExecutor(new EditorCommand());
        getCommand("punisherlist").setExecutor(new ListCommand());
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}
