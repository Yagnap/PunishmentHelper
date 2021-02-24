package eu.hobbydevs.spigot.punishmenthelp.commands.sub.editor;

import eu.hobbydevs.spigot.punishmenthelp.access.ActionsAccess;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.SubCommand;
import eu.hobbydevs.spigot.punishmenthelp.lib.AuxMethods;
import eu.hobbydevs.spigot.punishmenthelp.lib.Punishment;
import eu.hobbydevs.spigot.punishmenthelp.lib.Punishments;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class SubCommandEdit implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {

        String name = args[0];
        if(ActionsAccess.getStages(name) == 0) return false;

        if(args.length < 4) return false;
        int stage = 0;
        if(AuxMethods.isInt(args[2])) {
            stage = Integer.parseInt(args[2]);
        } else return false;

        ArrayList<String> puns = new ArrayList<>();

        for(int i = 3; i < args.length; i++){
            String current = args[i];
            Punishment pun;
            if((pun = Punishments.PUNISHMENTS.get(current)) != null) {
                if(pun.doesRequireTime()) {
                    String next = "";
                    i++;
                    try {
                        next = args[i];
                    } catch (IndexOutOfBoundsException e) {
                        return false;
                    }
                    if(AuxMethods.isTime(next)) {
                        puns.add(Punishments.PUNISHMENTS.get(current).getCommand() + " " + next);
                    } else {
                        sender.sendMessage("Please use a valid time.");
                        return false;
                    }
                } else {
                    puns.add(Punishments.PUNISHMENTS.get(current).getCommand());
                }
            } else return false;
        }

        ActionsAccess.editAction(name, stage, (String[]) puns.toArray());
        sender.sendMessage("Edited Action successfully.");

        return true;
    }
}
