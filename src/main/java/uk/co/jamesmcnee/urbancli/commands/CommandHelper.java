package uk.co.jamesmcnee.urbancli.commands;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

import java.util.stream.IntStream;

import static java.lang.String.format;

public abstract class CommandHelper {

    public static AttributedString standard(String string) {
        return new AttributedString(string);
    }

    public static AttributedString bold(String string) {
        return new AttributedString(string, AttributedStyle.BOLD);
    }

    public static AttributedString colouredPrefix(String prefix, ConsoleColour colour, String suffix) {
        String indentedSuffix = suffix.replaceAll("\n", format("%s%s", "\n", generateSpaces(prefix.length())));

        return new AttributedString(format("%s%s", ConsoleColour.wrapString(prefix, colour), indentedSuffix));
    }

    public static AttributedString standardPrefixed(String prefix, String suffix) {
        return standardPrefixed(prefix, suffix, true);
    }

    public static AttributedString standardPrefixed(String prefix, String suffix, boolean colonSeparate) {
        if (colonSeparate) {
            return colouredPrefix(format("%s: ", prefix), ConsoleColour.BLUE_BOLD, suffix);
        } else {
            return colouredPrefix(prefix, ConsoleColour.BLUE_BOLD, suffix);
        }
    }

    public static AttributedString error(String string) {
        return colouredPrefix("Error: ", ConsoleColour.RED, string);
    }

    private static String generateSpaces(int numberOfSpaces) {
        return IntStream.range(0, numberOfSpaces).mapToObj(i -> " ").reduce("", String::concat);
    }
}
