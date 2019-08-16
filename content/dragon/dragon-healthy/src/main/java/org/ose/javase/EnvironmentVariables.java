package org.ose.javase;

public class EnvironmentVariables {
    public static void main(String[] args) {
        System.getenv().entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        });
    }
}
