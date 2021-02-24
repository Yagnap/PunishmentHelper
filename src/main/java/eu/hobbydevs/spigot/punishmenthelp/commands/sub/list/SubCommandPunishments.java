package eu.hobbydevs.spigot.punishmenthelp.commands.sub.list;

import eu.hobbydevs.spigot.punishmenthelp.Main;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.SubCommand;
import eu.hobbydevs.spigot.punishmenthelp.lib.Punishments;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class SubCommandPunishments implements SubCommand {
    private static FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        String output = "";
        for(String s : Punishments.PUNISHMENTS.keySet()) {
            output += "Keyword:" + s + "\n" + Punishments.PUNISHMENTS.get(s).toString();
        }

        sender.sendMessage(output);

        return true;
    }
}
