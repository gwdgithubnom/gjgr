package org.ose.javase.regex;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ose.javase.io.TextFile;

public class JGrep {
    public static void main(String[] args) throws IOException {
        grep("src/com.htyleo.regex/JGrep.java", "line"); // words start with "line"
    }

    public static void grep(String fileName, String regex) throws IOException {
        StringBuilder fixedRegex = new StringBuilder(".*").append(regex).append(".*");
        Pattern p = Pattern.compile(fixedRegex.toString());
        Matcher m = p.matcher("");

        String[] lines = TextFile.read(fileName).split("\n");
        for (int lineNum = 1; lineNum <= lines.length; lineNum++) {
            m.reset(lines[lineNum - 1]);
            if (m.matches()) {
                System.out.printf("Line %d %s\n", lineNum, lines[lineNum - 1]);
            }
        }
    }
}
