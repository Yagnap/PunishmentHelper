package eu.hobbydevs.spigot.punishmenthelp.lib;

public class Punishment {
    private boolean requiresTime;
    private String commandName;

    public Punishment(String commandName, boolean requiresTime) {
        this.requiresTime = requiresTime;
        this.commandName = commandName;
    }

    public boolean doesRequireTime() {
        return requiresTime;
    }

    public String getCommand() {
        return commandName;
    }

    @Override
    public String toString() {
        return "Command issued:\n" + commandName + "\nTime required? " + (requiresTime ? "yes" : "no");
    }
}
