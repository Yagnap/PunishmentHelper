package eu.hobbydevs.spigot.punishmenthelp.lib;

import com.google.common.base.Predicates;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

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

    public static boolean isTime(String string) {
        char[] chars = string.toCharArray();

        boolean isString = false;
        String currentChain = "";
        String[] validTimeExpressions = new String[]{
                "second", "sec", "s",
                "minute", "min", "m",
                "hour", "h",
                "day", "d",
                "week", "w",
                "month", "year", "y"
        };

        for(int i = 0; i < chars.length; i++) {
            if(isString = !isInt(Character.toString(chars[i]))) {
                currentChain += chars[i];
            }
            if(!isString) {
                if(!Arrays.stream(validTimeExpressions).anyMatch(Predicates.containsPattern(currentChain))) return false;
                currentChain = "";
            }
        }

        if(!isString || !Arrays.stream(validTimeExpressions).anyMatch(Predicates.containsPattern(currentChain))) return false;

        return true;
    }

}
