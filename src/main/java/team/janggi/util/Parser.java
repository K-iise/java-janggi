package team.janggi.util;

public class Parser {

    public static int parseByInteger(String input, String errorMessage) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
