package org.ose.javase.regex;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
/*
public class DirectoryList {
    public static void main(String[] args) throws IOException {
        String path = ".";
        String regex = ".*\\.java";

        DirInfo result = walk(new File(path).getCanonicalFile(), regex);
        result.dirs.forEach(item -> {
            System.out.println("DIR: " + item);
        });
        System.out.println();
        result.files.forEach(item -> {
            System.out.println("FILE: " + item);
        });
    }

    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return Pattern.matches(regex, name);
            }
        });
    }

    public static DirInfo walk(File startDir, String regex) {
        DirInfo result = new DirInfo();
        File[] files = startDir.listFiles();
        if (files != null) {
            for (File item : files) {
                if (item.isDirectory()) {
                    result.dirs.add(item);
                    result.addAll(walk(item, regex));
                } else {
                    if (Pattern.matches(regex, item.getName())) { // com.htyleo.regex only applies to files
                        result.files.add(item);
                    }
                }
            }
        }

        return result;
    }
}

class DirInfo {
    public List<File> dirs  = new ArrayList<File>();
    public List<File> files = new ArrayList<File>();

    public void addAll(DirInfo other) {
        this.dirs.addAll(other.dirs);
        this.files.addAll(other.files);
    }
}
*/