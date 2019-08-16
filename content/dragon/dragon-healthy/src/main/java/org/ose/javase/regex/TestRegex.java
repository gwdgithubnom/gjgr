package org.ose.javase.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {
    public static void main(String[] args) {
        TestRegex.match("12.abc3456.de", "(\\d+)\\.(?:[a-z]+)"); // (?:X) indicates passive group
    }

    public static void match(String input, String regex) {
        System.out.println("Input: \"" + input + "\"");
        System.out.println("Regex: \"" + regex + "\"");

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(input);

        System.out.println("Full match: " + m.matches());
        System.out.println("Prefix match: " + m.lookingAt());
        while (m.find()) {
            int groupCount = m.groupCount(); // does not include group 0
            System.out.println("Group count = " + groupCount);
            System.out.println("Match com.htyleo.regex: \"" + m.group() + "\" at positions ["
                               + m.start() + "-" + m.end() + ")");
            for (int gi = 1; gi <= groupCount; gi++) {
                System.out.println("Match group " + gi + ": \"" + m.group(gi) + "\" at positions ["
                                   + m.start(gi) + "-" + m.end(gi) + ")");
            }
        }
    }
}
