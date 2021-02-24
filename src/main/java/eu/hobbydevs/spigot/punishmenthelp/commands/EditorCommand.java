package eu.hobbydevs.spigot.punishmenthelp.commands;

import eu.hobbydevs.spigot.punishmenthelp.commands.sub.SubCommand;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.editor.SubCommandAdd;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.editor.SubCommandDelete;
import eu.hobbydevs.spigot.punishmenthelp.commands.sub.editor.SubCommandEdit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class EditorCommand implements CommandExecutor {

    private Map<String, SubCommand> subcommands = new HashMap<>();

    public EditorCommand() {
        super();

        subcommands.put("add", new SubCommandAdd());
        subcommands.put("delete", new SubCommandDelete());
        subcommands.put("edit", new SubCommandEdit());
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
