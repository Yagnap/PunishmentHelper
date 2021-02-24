package eu.hobbydevs.spigot.punishmenthelp.commands.sub.list;

import eu.hobbydevs.spigot.punishmenthelp.Main;
import eu.hobbydevs.spigot.punishmenthelp.access.PunishmentAccess;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SubCommandPlayer implements SubCommand {
    private static FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public boolean execute(CommandSender sender, String[] args) {
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
        return true;
    }
}
