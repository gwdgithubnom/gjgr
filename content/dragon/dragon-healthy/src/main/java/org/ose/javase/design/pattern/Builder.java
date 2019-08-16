// Step-by-step process to build something,
// i.e., every produce will follow the same process even though each step is different.

package org.ose.javase.design.pattern;

public class Builder {
    public static void main(String[] args) {
        Waiter waiter = new Waiter();

        // tea
        waiter.setStarbucksBuilder(new TeaBuilder());
        waiter.constructStarbucks();
        Starbucks tea = waiter.getstarbucksDrink();
        System.out.println(tea);

        // coffee
        waiter.setStarbucksBuilder(new CoffeeBuilder());
        waiter.constructStarbucks();
        Starbucks coffee = waiter.getstarbucksDrink();
        System.out.println(coffee);
    }
}

// to be built
class Starbucks {
    private String size;
    private String drink;

    public void setSize(String size) {
        this.size = size;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    @Override
    public String toString() {
        return String.format("[%s %s]", size, drink);
    }
}

// abstract builder
abstract class StarbucksBuilder {
    protected Starbucks starbucks;

    public abstract void buildSize();

    public abstract void buildDrink();

    public void createStarbucks() {
        starbucks = new Starbucks();
        System.out.println("a drink is created");
    }

    public Starbucks getStarbucks() {
        return starbucks;
    }
}

// concrete builders

class TeaBuilder extends StarbucksBuilder {
    @Override
    public void buildSize() {
        starbucks.setSize("large");
        System.out.println("build large size");
    }

    @Override
    public void buildDrink() {
        starbucks.setDrink("tea");
        System.out.println("build tea");
    }
}

class CoffeeBuilder extends StarbucksBuilder {
    @Override
    public void buildSize() {
        starbucks.setSize("medium");
        System.out.println("build medium size");
    }

    @Override
    public void buildDrink() {
        starbucks.setDrink("coffee");
        System.out.println("build coffee");
    }
}

// encapsulate the builder

class Waiter {
    private StarbucksBuilder starbucksBuilder;

    public void setStarbucksBuilder(StarbucksBuilder starbucksBuilder) {
        this.starbucksBuilder = starbucksBuilder;
    }

    public void constructStarbucks() {
        starbucksBuilder.createStarbucks();
        starbucksBuilder.buildDrink();
        starbucksBuilder.buildSize();
    }

    public Starbucks getstarbucksDrink() {
        return starbucksBuilder.getStarbucks();
    }
}
