package edu.pdx.cs410J.moes;

/**
 * Class for formatting messages on the server side.  This is mainly to enable
 * test methods that validate that the server returned expected strings.
 */
public class Messages
{
    /**
     * This is to format automaticly the differenty missing param info
     * @param parameterName is the parameter missing's name
     * @return string formatted what is missing
     */
    public static String missingRequiredParameter( String parameterName )
    {
        return String.format("The required parameter \"%s\" is missing", parameterName);
    }

    /**
    public static String definedWordAs(String word, String definition )
    {
        return String.format( "Defined %s as %s", word, definition );
    }
    */

    /**
     * this function tells us that we have deleted all the entries
     * @return string saying above
     */
    public static String allDictionaryEntriesDeleted() {
        return "All dictionary entries have been deleted";
    }

}
