package eu.hobbydevs.spigot.punishmenthelp.commands.sub;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface SubCommand {

    boolean execute(CommandSender sender, String[] args);
}
