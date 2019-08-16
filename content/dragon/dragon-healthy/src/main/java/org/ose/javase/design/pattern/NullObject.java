package org.ose.javase.design.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NullObject {
    public static void main(String[] args) {
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("Gary", "Chan"));
        persons.add(NullPerson.NULL);
        for (Person person : persons) {
            // singleton, can use either "==" or (person instanceof Null) to test nullness
            if (person == NullPerson.NULL) {
                System.out.print("Null: ");
            }
            System.out.println(person);
        }
        System.out.println();

        List<Robot> robots = new ArrayList<Robot>();
        robots.add(new SnowRemovalRobot("robot1"));
        robots.add(new NullRobot());
        for (Robot robot : robots) {
            // not singleton, must use "instanceof" to test
            if (robot instanceof Null) {
                System.out.print("Null: ");
            }
            System.out.println(robot);
        }
    }
}

interface Null {
}

class Person {
    private final String first;
    private final String last;

    public Person(String first, String last) {
        this.first = first;
        this.last = last;
    }

    @Override
    public String toString() {
        return first + " " + last;
    }
}

class NullPerson extends Person implements Null {
    // singleton
    public static final Person NULL = new NullPerson();

    private NullPerson() {
        super("N/A", "N/A");
    }

    @Override
    public String toString() {
        return "NullPerson";
    }
}

interface Robot {
    public String name();

    public List<String> operations();
}

class NullRobot implements Robot, Null {
    @Override
    public String name() {
        return "N/A";
    }

    @Override
    public List<String> operations() {
        return Collections.<String> emptyList();
    }
}

class SnowRemovalRobot implements Robot {
    private String name;

    public SnowRemovalRobot(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<String> operations() {
        return new ArrayList<String>(Arrays.asList("speak", "move"));
    }
}
