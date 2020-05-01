package me.csed2.moneymanager.utils;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

/**
 * Allows you to change the alignment of a string.
 *
 * Taken from here: https://howtodoinjava.com/java/string/how-to-left-right-or-center-align-string-in-java/
 */
public class StringAlignUtils extends Format {

    private static final long serialVersionUID = 1L;

    public enum Alignment {
        LEFT, CENTRE, RIGHT,
    }

    /**
     * Current justification for formatting
     **/
    private Alignment currentAlignment;

    /**
     * Current max length in a line
     **/
    private int maxChars;

    public StringAlignUtils(int maxChars, Alignment align) {
        switch (align) {
            case LEFT:
            case CENTRE:
            case RIGHT:
                this.currentAlignment = align;
                break;
            default:
                throw new IllegalArgumentException("Error: Invalid Justification Arg!");
        }
        if (maxChars < 0) {
            throw new IllegalArgumentException("maxChars must be positive.");
        }
        this.maxChars = maxChars;
    }

    public StringBuffer format(Object input, StringBuffer where, FieldPosition ignore) {
        String s = input.toString();
        List<String> strings = splitInputString(s);

        for (String wanted : strings) {
            // Get the spaces in the right place.
            switch (currentAlignment) {
                case RIGHT:
                    pad(where, maxChars - wanted.length());
                    where.append(wanted);
                    break;
                case CENTRE:
                    int toAdd = maxChars - wanted.length();
                    pad(where, toAdd / 2);
                    where.append(wanted);
                    pad(where, toAdd - toAdd / 2);
                    break;
                case LEFT:
                    where.append(wanted);
                    pad(where, maxChars - wanted.length());
                    break;
            }
        }
        return where;
    }

    protected final void pad(StringBuffer to, int howMany) {
        to.append(" ".repeat(Math.max(0, howMany)));
    }

    String format(String s) {
        return format(s, new StringBuffer(), null).toString();
    }

    /** ParseObject is required, but not useful here. */
    public Object parseObject(String source, ParsePosition pos) {
        return source;
    }

    private List<String> splitInputString(String str) {
        List<String> list = new ArrayList<>();
        if (str == null)
            return list;
        for (int i = 0; i < str.length(); i = i + maxChars)
        {
            int endIndex = Math.min(i + maxChars, str.length());
            list.add(str.substring(i, endIndex));
        }
        return list;
    }
}
