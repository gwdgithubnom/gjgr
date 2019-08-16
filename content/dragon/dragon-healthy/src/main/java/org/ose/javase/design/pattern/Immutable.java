// 1. Object state can not be modified after construction, any modification should result in a new immutable object.
// 2. All fields of an immutable class should be final.
// 3. Class and methods should be final in order to restrict sub­class for altering the immutability of parent class.

package org.ose.javase.design.pattern;

import java.util.Date;

public final class Immutable {
    private final String name;
    private final Date   date;

    public Immutable(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return (Date) date.clone();
    }
}
