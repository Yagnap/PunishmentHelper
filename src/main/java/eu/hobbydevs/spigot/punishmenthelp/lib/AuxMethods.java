package eu.hobbydevs.spigot.punishmenthelp.lib;

public class AuxMethods {

    /**
     * Checks if a String can be converted to an Integer.
     * @param string The string which should be checked.
     * @return Returns false if the provided String is not an Integer, true if it is.
     */
    public static boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch(NumberFormatException e)  {
            return false;
        }
    }

}
