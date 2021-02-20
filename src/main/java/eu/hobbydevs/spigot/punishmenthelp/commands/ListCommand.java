package eu.hobbydevs.spigot.punishmenthelp.commands;

import eu.hobbydevs.spigot.punishmenthelp.Main;
import eu.hobbydevs.spigot.punishmenthelp.access.PunishmentAccess;
import eu.hobbydevs.spigot.punishmenthelp.lib.Punishments;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ListCommand implements CommandExecutor {
    private static FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 1) return false;
        String message = "";
        switch(args[0]) {
            case "player": {

                if(args.length != 2) return false;

                Player target = Bukkit.getPlayer(args[1]);
                if(target.equals(null)) {
                    sender.sendMessage("§4This player does not exist.");
                    return false;
                }
                String output = "";
                for(String key : config.getConfigurationSection("Punishmenthelper.Actions").getKeys(false)) {
                    if(!key.startsWith("stage") && !key.equals("exists")) {
                        output += key +": " + PunishmentAccess.getOffenceStage(target.getUniqueId(), key);
                    }
                }

                break;
            }
            case "actions": {
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


                break;
            }
            case "punishments": {
                    for(String s : Punishments.PUNISHMENTS.keySet()) {
                        message += "Keyword:" + s + "\n" + Punishments.PUNISHMENTS.get(s).toString();
                }

                break;
            }
            default: return false;
        }

        return true;
    }
}
