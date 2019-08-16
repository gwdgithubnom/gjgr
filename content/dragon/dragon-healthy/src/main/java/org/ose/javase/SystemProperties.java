package org.ose.javase;

public class SystemProperties {
    public static void main(String[] args) {
        // platform's default encoding
        System.out.println("file.encoding: " + System.getProperty("file.encoding"));

        // Character that separates components of a file path. This is "/" on
        // UNIX and "\" on Windows.
        System.out.println("file.separator: " + System.getProperty("file.separator"));

        // Path used to find directories and JAR archives containing class files.
        // Elements of the class path are separated by a platform-specific
        // character specified in the path.separator property.
        System.out.println("java.class.path: " + System.getProperty("java.class.path"));

        // Installation directory for Java Runtime Environment (JRE)
        System.out.println("java.home: " + System.getProperty("java.home"));

        // JRE vendor name
        System.out.println("java.vendor: " + System.getProperty("java.vendor"));

        // JRE vendor URL
        System.out.println("java.vendor.url: " + System.getProperty("java.vendor.url"));

        // JRE version number
        System.out.println("java.version: " + System.getProperty("java.version"));

        // Sequence used by operating system to separate lines in text files
        // 0x0A on Mac/Unix
        // 0x0D 0x0A on Windows
        System.out.println("line.separator: " + System.getProperty("line.separator"));

        // Operating system architecture
        System.out.println("os.arch: " + System.getProperty("os.arch"));

        // Operating system name
        System.out.println("os.name: " + System.getProperty("os.name"));

        // Operating system version
        System.out.println("os.version: " + System.getProperty("os.version"));

        // Path separator character used in java.class.path
        System.out.println("path.separator: " + System.getProperty("path.separator"));

        // User working directory
        System.out.println("user.dir: " + System.getProperty("user.dir"));

        // User home directory
        System.out.println("user.home: " + System.getProperty("user.home"));

        // User account name
        System.out.println("user.name: " + System.getProperty("user.name"));
    }
}
