package eu.hobbydevs.spigot.punishmenthelp.commands.sub.list;

import eu.hobbydevs.spigot.punishmenthelp.Main;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.SubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class SubCommandActions implements SubCommand {
    private static FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        String output = "";
        if(args.length == 2) {
            if(config.get("Punishmenthelper.Actions." + args[1] + ".exists").equals(true)) {
                for(int i = 0; i < config.getConfigurationSection("Punishmenthelper.Actions." + args[1]).getKeys(false).stream().count(); i++) {
                    if(!config.get("Punishmenthelper.Actions." + args[1] + "stage" + Integer.toString(i)).equals(true)) {
                        for(String s : (String[]) config.get("Punishmenthelper.Actions." + args[1] + "stage" + Integer.toString(i))) {
                            output += "- " + s;
                        }
                    }
                    sender.sendMessage(output);
                }
            } else {
                sender.sendMessage("§4That action is not punishable.");
                return false;
            }
        } else {
            for(String key : config.getConfigurationSection("Punishmenthelper.Actions").getKeys(false)) {
                output += key + "\n";
            }
        }
        return true;
    }
}
