package org.ose.javase;

import java.util.Date;
import java.util.GregorianCalendar;

public class CloneEquals {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee e1 = new Employee("Leo", 2015, 8, 1);
        Employee e2 = e1.clone();
        System.out.println(e1.equals(e2));

        Manager m1 = new Manager("Leo", 2015, 8, 1, 10000);
        Manager m2 = m1.clone();
        System.out.println(m1.equals(m2));
    }
}

class Employee implements Cloneable {
    private String name;
    private Date   hireDay;

    public Employee(String name, int year, int month, int day) {
        this.name = name;
        this.hireDay = new GregorianCalendar(year, month, day).getTime();
    }

    @Override
    public Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone(); // call Object.clone()
        cloned.hireDay = (Date) this.hireDay.clone(); // clone mutable fields

        return cloned;
    }

    @Override
    public boolean equals(Object otherObject) {
        // a quick test to see if the objects are identical
        if (this == otherObject) {
            return true;
        }

        // must return false if the explicit parameter is null
        if (otherObject == null) {
            return false;
        }

        // if the (runtime) classes don't match, they can't be equal
        if (this.getClass() != otherObject.getClass()) {
            return false;
        }

        // now we know otherObject is a non-null Employee
        Employee other = (Employee) otherObject;

        // test whether the fields have identical values
        return this.name.equals(other.name) && this.hireDay.equals(other.hireDay);
    }

    @Override
    public int hashCode() {
        return 7 * this.name.hashCode() + 13 * this.hireDay.hashCode();
    }
}

class Manager extends Employee implements Cloneable {
    private double bonus;

    public Manager(String name, int year, int month, int day, double bonus) {
        super(name, year, month, day);
        this.bonus = bonus;
    }

    @Override
    public Manager clone() throws CloneNotSupportedException {
        Manager cloned = (Manager) super.clone(); // call Employee.clone()
        cloned.bonus = this.bonus;

        return cloned;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (!super.equals(otherObject)) {
            return false;
        }

        // super.equals has checked that this and otherObject belong to the same class
        Manager other = (Manager) otherObject;

        return this.bonus == other.bonus;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 17 * new Double(this.bonus).hashCode();
    }
}
