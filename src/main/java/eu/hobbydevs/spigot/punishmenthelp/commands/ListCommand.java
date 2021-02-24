package eu.hobbydevs.spigot.punishmenthelp.commands;

import eu.hobbydevs.spigot.punishmenthelp.Main;
import eu.hobbydevs.spigot.punishmenthelp.access.PunishmentAccess;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.SubCommand;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.list.SubCommandActions;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.list.SubCommandPlayer;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.list.SubCommandPunishments;
import eu.hobbydevs.spigot.punishmenthelp.lib.Punishments;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCommand implements CommandExecutor {

    private Map<String, SubCommand> subcommands = new HashMap<>();

    public ListCommand() {
        super();

        subcommands.put("actions", new SubCommandActions());
        subcommands.put("player", new SubCommandPlayer());
        subcommands.put("punishments", new SubCommandPunishments());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean ret;
        if(args.length >= 1) {
            SubCommand sc = subcommands.get(args[0]);
            if(sc == null) return false;

            ret = sc.execute(sender, args);
        } else return false;

        return ret;
    }
}
