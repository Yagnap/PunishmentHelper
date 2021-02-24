package eu.hobbydevs.spigot.punishmenthelp.commands.sub.editor;

import eu.hobbydevs.spigot.punishmenthelp.access.ActionsAccess;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.SubCommand;
import org.bukkit.command.CommandSender;

public class SubCommandDelete implements SubCommand {
    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if(args.length != 2) return false;
        String name = args[1];
        if(ActionsAccess.getStages(name) == 0) return false;

        ActionsAccess.removeAction(name);
        sender.sendMessage("Removed action \"" + name + "\" from being punishable.");

        return true;
    }
}
