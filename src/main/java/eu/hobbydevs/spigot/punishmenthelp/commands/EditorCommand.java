package eu.hobbydevs.spigot.punishmenthelp.commands;

import eu.hobbydevs.spigot.punishmenthelp.access.ActionsAccess;
import eu.hobbydevs.spigot.punishmenthelp.lib.AuxMethods;
import eu.hobbydevs.spigot.punishmenthelp.lib.Punishment;
import eu.hobbydevs.spigot.punishmenthelp.lib.Punishments;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Locale;

public class EditorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length <= 1) return false;

        String name = args[1];

        switch(args[0]) {
            case "add": {
                if(args.length < 3) return false;
                if(ActionsAccess.getStages(name) != 0) {
                    sender.sendMessage("§4This action already exists.");
                    return false;
                }

                ArrayList<String[]> punishments = new ArrayList<String[]>();
                ArrayList<String> currentStage = new ArrayList<String>();

                for(int i = 2; i < args.length; i++) {

                    String current = args[i].toLowerCase();

                    if(Punishments.PUNISHMENTS.containsKey(current)) {

                        boolean newStage = false;
                        boolean probablyTime = false;

                        if(current.endsWith(",")) {
                            newStage = true;
                            current = current.substring(0, current.length() - 2);
                        }

                        Punishment pun = Punishments.PUNISHMENTS.get(current); // get it?

                        if(pun == null) {
                            sender.sendMessage("\"" + current + "\" is not a valid keyword. Addition failed.");
                            return false;
                        }

                        if(pun.doesRequireTime()) {
                            i++;
                            String timeString = args[i];

                            if(timeString.endsWith("s") || timeString.endsWith("sec") || timeString.endsWith("second")||
                                    timeString.endsWith("m") || timeString.endsWith("min") || timeString.endsWith("minute") ||
                                    timeString.endsWith("h") || timeString.endsWith("hour") ||
                                    timeString.endsWith("d") || timeString.endsWith("day") ||
                                    timeString.endsWith("month") || timeString.endsWith("y") ||
                                    timeString.endsWith("year")
                            ) {
                                probablyTime = true;
                            }

                            if(probablyTime && AuxMethods.isInt(timeString.substring(0, timeString.length()-2))) {
                                currentStage.add(current + timeString);

                            } else {
                                sender.sendMessage("\"" + current + "\" requires a valid time. Addition failed.");
                                return false;
                            }

                        } else {
                            currentStage.add(current);
                        }
                        if(newStage) {
                            punishments.add((String[]) currentStage.toArray());
                            currentStage = new ArrayList<String>();
                        }
                    }
                }

                ActionsAccess.addAction(name, punishments);

                sender.sendMessage("Successfully added new punishable action.");

                break;
            }
            case "edit": {

                if(ActionsAccess.getStages(name) == 0) {
                    sender.sendMessage("§4This action does not exist.");
                    return false;
                }
                int stage = 0;
                String rawStage = args[2];
                if(AuxMethods.isInt(rawStage)) {
                    stage = Integer.parseInt(rawStage);
                    if(stage > ActionsAccess.getStages(name) +1) {
                        sender.sendMessage("§4This stage is invalid.");
                        return false;
                    }
                } else {
                    sender.sendMessage("§4This stage is invalid.");
                    return false;
                }

                ArrayList<String> currentStage = new ArrayList<String>();

                if(args.length < 4) return false;

                for(int i = 2; i < args.length; i++) {

                    String current = args[i].toLowerCase();

                    if(Punishments.PUNISHMENTS.containsKey(current)) {
                        boolean probablyTime = false;

                        Punishment pun = Punishments.PUNISHMENTS.get(current); // get it?

                        if(pun == null) {
                            sender.sendMessage("\"" + current + "\" is not a valid keyword. Addition failed.");
                            return false;
                        }

                        if(pun.doesRequireTime()) {
                            i++;
                            String timeString = args[i];

                            if(timeString.endsWith("s") || timeString.endsWith("sec") || timeString.endsWith("second")||
                                    timeString.endsWith("m") || timeString.endsWith("min") || timeString.endsWith("minute") ||
                                    timeString.endsWith("h") || timeString.endsWith("hour") ||
                                    timeString.endsWith("d") || timeString.endsWith("day") ||
                                    timeString.endsWith("month") || timeString.endsWith("y") ||
                                    timeString.endsWith("year")
                            ) {
                                probablyTime = true;
                            }

                            if(probablyTime && AuxMethods.isInt(timeString.substring(0, timeString.length()-2))) {
                                currentStage.add(current + timeString);

                            } else {
                                sender.sendMessage("\"" + current + "\" requires a valid time. Edit failed.");
                                return false;
                            }

                        } else {
                            currentStage.add(current);
                        }
                    }
                }

                ActionsAccess.editAction(name, stage, (String[]) currentStage.toArray());

                break;
            }
            case "delete": {
                if(args.length != 2) return false;

                if(ActionsAccess.getStages(name) == 0) return false;

                ActionsAccess.removeAction(name);
                sender.sendMessage("Removed action \"" + name + "\" from being punishable.");

            }

            default: return false;

        }

        return true;
    }
}
