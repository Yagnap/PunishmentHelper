package eu.hobbydevs.spigot.punishmenthelp.commands.sub.editor;

import eu.hobbydevs.spigot.punishmenthelp.access.ActionsAccess;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.SubCommand;
import eu.hobbydevs.spigot.punishmenthelp.lib.AuxMethods;
import eu.hobbydevs.spigot.punishmenthelp.lib.Punishment;
import eu.hobbydevs.spigot.punishmenthelp.lib.Punishments;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;

public class SubCommandAdd implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if(args.length < 3) return false;
        String name = args[1];
        if(ActionsAccess.getStages(name) != 0) {
            sender.sendMessage("This punishment already exists. Use /punisheredit edit " + name + "to edit or /punisheredit delete "
                    + name + "to delete it.");
        }

        ArrayList<String> currentStage = new ArrayList<>();
        ArrayList<String[]> stages = new ArrayList<>();

        boolean firstInStage = true;
        boolean nextNewStage = false;
        for(int i = 2; i < args.length; i++) {

            if(nextNewStage) {
                stages.add((String[]) currentStage.toArray());
                currentStage = new ArrayList<>();
                nextNewStage = false;
                firstInStage = true;
            }

            String current = args[i];
            if(current.endsWith(",")) {
                if(!firstInStage) return false;
                current = current.substring(0, current.length()-2);
                nextNewStage = true;
            }

            Punishment p = Punishments.PUNISHMENTS.get(current);
            if(p == null) {
                sender.sendMessage("Invalid arguments. Try /punisherlist punishments for valid arguments.");
            }

            if(p.doesRequireTime()) {
                String next = "";
                i++;
                try {
                    next = args[i];
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
                if(next.endsWith(",")) next = next.substring(0, next.length()-2);
                if(AuxMethods.isTime(next)) {
                    currentStage.add(Punishments.PUNISHMENTS.get(current).getCommand() + " " + next);
                } else {
                    sender.sendMessage("Please use a valid time.");
                    return false;
                }
            } else {

            }

            if(firstInStage) firstInStage = false;
        }

        ActionsAccess.addAction(name, stages);

        return false;
    }
}
