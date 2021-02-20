package eu.hobbydevs.spigot.punishmenthelp.commands;

import eu.hobbydevs.spigot.punishmenthelp.access.ActionsAccess;
import eu.hobbydevs.spigot.punishmenthelp.access.PunishmentAccess;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PunisherCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length != 2) return false;
        String name = args[0];
        String playername = args[1];

        Player p = Bukkit.getPlayer(playername);

        if(p == null) sender.sendMessage("§4This player does not exist.");

        if(ActionsAccess.getStages(name) != 0) {
            PunishmentAccess.addOffence(p.getUniqueId(), name);
            int currentStage = PunishmentAccess.getOffenceStage(p.getUniqueId(), name);
            String[] punishment = ActionsAccess.getPunishment(name, currentStage);

            for(String s : punishment) {
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), s);
            }
            sender.sendMessage("Punished " + playername + " for " + name + ". Their current offence stage on this action is " + Integer.toString(currentStage) + ".");
        } else {
            return false;
        }

        return true;
    }
}
